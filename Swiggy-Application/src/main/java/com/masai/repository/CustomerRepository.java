package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer>  {

}
