package com.eshopping.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.wallet.model.Wallet;
import com.eshopping.wallet.service.WalletService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	@ApiOperation(
			value = "Creates a wallet for the user",
			response = Wallet.class
			)
	
	@PostMapping("/create-wallet")
	public Wallet addWallet(@RequestBody Wallet wallet) {
		return walletService.createWallet(wallet);
	}
	
	@ApiOperation(
			value = "Gets the wallet by user Id",
			notes = "Provide a wallet Id to get the wallet of a specific user",
			response = Wallet.class
			)
	
	@GetMapping("/get-wallet-by-walletId/{walletId}")
	public Wallet getWalletById(@PathVariable("walletId") int walletId) {
		return walletService.getWalletByWalletId(walletId);
	}
	
	@ApiOperation(
			value = "Gets the wallet by user Id",
			notes = "Provide an user Id to get the wallet of a specific user",
			response = Wallet.class
			)
	
	@GetMapping("/get-wallet-by-userId/{userId}")
	public Wallet getWalletByUserId(@PathVariable("userId") int userId) {
		return walletService.getWalletByUserId(userId);
	}
	
	@ApiOperation(
			value = "Gets all wallets of the users.",
			response = Wallet.class
			)
	
	@GetMapping("/get-wallets")
	public List<Wallet> getAllWallets(){
		return walletService.getAllWallets();
	}
	
	@ApiOperation(
			value = "Adds money to the wallet",
			response = Wallet.class
			)
	
	@PutMapping("/add-money-to-wallet")
	public Wallet updateWallet(@RequestBody Wallet wallet) {
		return walletService.addMoneyToWallet(wallet);
	}
	
	@ApiOperation(
			value = "Deletes the wallet by wallet Id",
			notes = "Provide a wallet Id to delete the wallet of a specific user",
			response = String.class
			)
	
	@DeleteMapping("/delete-by-walletId/{walletId}")
	public String deleteWalletById(@PathVariable("walletId") int walletId) {
		return walletService.deleteWalletByWalletId(walletId);
	}
	
	@ApiOperation(
			value = "Deletes the wallets of all the users",
			response = String.class
			)
	
	@DeleteMapping("/delete-wallets")
	public String deleteAllWallets() {
		return walletService.deleteAllWallets();
	}
	
}
