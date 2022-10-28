package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderAddresses;
import com.example.demo.model.Orders;
import com.example.demo.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/getallorders")
	public List<Orders> getAllOrders(){
		return orderService.getAllOrders();
		
	}
	
	@GetMapping("/shop")
	public String shop() {
		return "<h1>Shop Here</h1>";
	}
	
	@PostMapping("/placeorder")
	public OrderAddresses placeOrder(@RequestBody Orders order){
		
		return orderService.placeOrder(order);
		
	}
	@PutMapping("/updateorder")
	public Orders updateOrder(@RequestBody Orders order){
		return orderService.cancelOrder(order);
		
	}
	@DeleteMapping("/deleteorder")
	public String deleteOrder(){
		return orderService.deleteOrder();
		
	}
	
}
