package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Customers;
import com.masai.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	List<Orders> findByCustomer(Customers customer);
}
