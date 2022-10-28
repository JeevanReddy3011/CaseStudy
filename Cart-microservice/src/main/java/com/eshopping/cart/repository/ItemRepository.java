package com.eshopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.cart.model.Items;



@Repository
public interface ItemRepository extends JpaRepository<Items, Integer> {
	
	Items findByItemId(int itemId);

}
