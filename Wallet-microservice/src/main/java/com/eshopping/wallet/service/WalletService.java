package com.eshopping.wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.wallet.exception.WalletCreationException;
import com.eshopping.wallet.model.Wallet;
import com.eshopping.wallet.repository.WalletRepository;

@Service
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;
	
	public Wallet createWallet(Wallet wallet) {	

		Wallet userWallet = walletRepository.findByUserId(wallet.getUserId());
			
		if(userWallet!=null) {
			throw new WalletCreationException("Wallet already exists");
		}
		
		wallet.setCurrentBalance(0.0);
		return walletRepository.save(wallet);
	}
	
	public Wallet getWalletByWalletId(int walletId) {
		return walletRepository.findByWalletId(walletId);
	}
	
	public Wallet getWalletByUserId(int userId) {
		return walletRepository.findByUserId(userId);
	}
	
	public List<Wallet> getAllWallets(){
		return walletRepository.findAll();
	}
	
	public Wallet addMoneyToWallet(Wallet wallet) {
		return walletRepository.save(wallet);
	}
	
	public String deleteWalletByWalletId(int walletId) {
		walletRepository.deleteById(walletId);
		return "Wallet with ID "+walletId+" is deleted successfully.";
	}
	
	public String deleteAllWallets() {
		walletRepository.deleteAll();
		return "All the wallets are deleted successfully.";
	}
}