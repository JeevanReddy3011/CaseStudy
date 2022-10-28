package com.eshopping.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.profile.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	Address findByAddressId(int addressId);
	List<Address> findByUserId(int userId);
	
}
