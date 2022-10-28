package com.eshopping.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.wallet.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

	Wallet findByWalletId(int walletId);
	Wallet findByUserId(int userId);
	
}