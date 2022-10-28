package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.MQConfig;
import com.example.demo.exception.InsufficientWalletFundsException;
import com.example.demo.exception.OrderPlacementException;
import com.example.demo.model.Address;
import com.example.demo.model.CartItems;
import com.example.demo.model.EmailBody;
import com.example.demo.model.OrderAddresses;
import com.example.demo.model.Orders;
import com.example.demo.model.SmsRequest;
import com.example.demo.model.User;
import com.example.demo.model.Wallet;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String CART_URL = "http://CART-MICROSERVICE/cart/view-all-items";
	private static final String PROFILE_URL = "http://PROFILE-MICROSERVICE/user/";
	private static final String WALLET_URL = "http://WALLET-MICROSERVICE/wallet/";
	
	private void sendOrderEmail(
			String username,
			String paymentMode, 
			String orderStatus,
			double amountPaid,
			User user) {
		
		String status = "";
		
		status = "placed successfully";
		
		String emailBody = "Dear "+username+",\n\n"+
				  "Your order has been "+status+" with payment mode "+paymentMode+
				  " for the amount "+amountPaid+".\n" 
				  + "Your order will be delivered to you in 3-4 buisness days.\n"+
				  "Continue shopping at http://localhost:8084/orders/shop.\n\n"+
				  "Thank you,\n"+
				  "EShopping Zone";
		
		String emailSubject = "Order Confirmation";
		
		if(orderStatus.equals("Rejected")) {
		status = "rejected" ;
	    emailBody = "Dear "+username+",\n\n"+
	    		  "Your order has been "+status+" with payment mode "+paymentMode+
				  " for the amount "+amountPaid+" for having insufficient funds in your e-wallet.\n" 
				  + "Kindly add more money to your wallet and try again.\n"+
				  "Continue shopping at http://localhost:8084/orders/shop.\n\n"+
				  "Thank you,\n"+
				  "EShopping Zone";
	    
	    emailSubject = "Order Rejected - Insufficient funds in wallet";
		
		}
		
		EmailBody mail = new EmailBody(
				user.getUsername(),
				user.getEmailId(),
				emailSubject, 
				emailBody
				);
		
		template.convertAndSend(MQConfig.EXCHANGE,
              MQConfig.EMAIL_ROUTING_KEY, mail);
	}

	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}
	
	public OrderAddresses placeOrder(Orders order) {
		
		CartItems cartItems = restTemplate.getForObject(CART_URL, CartItems.class);
		
		order.setAmountPaid(cartItems.getTotalAmount());
		
		User user = restTemplate.getForObject(PROFILE_URL+"get-user-by-id/"+order.getCustomerId(), User.class);
		
		if(user==null) {
			throw new OrderPlacementException("Please sign up before placing the order.");
		}
		
		List<Address> addressList = restTemplate.getForObject(PROFILE_URL+"get-address-by-userId/"+order.getCustomerId(), List.class);
		
		String number = "+91"+Long.toString(user.getMobileNumber());
		
		String username = user.getUsername();
		
		order.setDateTime(LocalDateTime.now());
		
		if(order.getModeOfPayment().equals("e-wallet")) {
			
			Wallet wallet = restTemplate.getForObject(WALLET_URL+"get-wallet-by-userId/"+order.getCustomerId(), Wallet.class);
			
			if(order.getAmountPaid() > wallet.getCurrentBalance()) {
				
				order.setOrderStatus("Rejected");
		
				orderRepository.save(order);
				
				sendOrderEmail(username, order.getModeOfPayment(), "Rejected", order.getAmountPaid(), user);
						
				template.convertAndSend(MQConfig.EXCHANGE,
			              MQConfig.SMS_ROUTING_KEY, 
			              new SmsRequest(number,"Order cancelled due to insufficient funds in wallet."));
				
				throw new InsufficientWalletFundsException("Please add more money to place the order.");
			}
			
			wallet.setCurrentBalance(wallet.getCurrentBalance()-order.getAmountPaid());
			restTemplate.put(WALLET_URL+"add-money-to-wallet", wallet);
		}
		
		order.setOrderStatus("Paid");
		
		orderRepository.save(order);
		
		sendOrderEmail(username, order.getModeOfPayment(), order.getOrderStatus(), order.getAmountPaid(), user);
		
		template.convertAndSend(MQConfig.EXCHANGE,
	              MQConfig.SMS_ROUTING_KEY, 
	              new SmsRequest(number,"Order placed successfully!."));
		
		return new OrderAddresses(order,addressList);
	
	}
	
	public Orders cancelOrder(Orders order) {
		return orderRepository.save(order);
	}
	
	public String deleteOrder() {
		 orderRepository.deleteAll();
		 return "orders-deleted";
	}

}
