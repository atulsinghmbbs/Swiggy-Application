package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.exception.Delivery_PartnerException;
import com.masai.exception.OrderException;
import com.masai.model.Orders;
import com.masai.status.OrderStatus;

public interface OrderService {
	
	public Orders placeOrder(Integer customerId, Integer restaurantId, List<String> items) throws OrderException , CustomerException;
	
	public void assignDeliveryPartner(int orderId, int deliveryPartnerId) throws OrderException , Delivery_PartnerException;
	
	public void updateOrderStatus(int orderId, OrderStatus newStatus) throws OrderException;
	
	public List<Orders> getCustomerOrderHistory(int customerId) throws OrderException , CustomerException;
	
	public Orders getOrderById(int orderId) throws OrderException;

}
