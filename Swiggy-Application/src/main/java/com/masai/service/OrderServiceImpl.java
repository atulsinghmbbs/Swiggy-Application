package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.Delivery_PartnerException;
import com.masai.exception.OrderException;
import com.masai.model.Customers;
import com.masai.model.Delivery_Partners;
import com.masai.model.Orders;
import com.masai.model.Restaurants;
import com.masai.repository.Delivery_PartnerRepository;
import com.masai.repository.OrderRepository;
import com.masai.status.OrderStatus;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private Delivery_PartnerRepository deliveryPartnerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private Delivery_PartnerService deliverypartnerservice;
    
  
	@Override
	public Orders placeOrder(Integer customerId, Integer restaurantId, List<String> items)
			throws OrderException, CustomerException {
		Customers customer = customerService.getCustomerById(customerId);
        Restaurants restaurant = restaurantService.getRestaurantById(restaurantId);

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setItems(items);
        order.setDeliveryPartner(null);
        order.setOrderStatus(OrderStatus.COOKING);
        
        Delivery_Partners newDeliveryPartner = new Delivery_Partners();
        newDeliveryPartner.setName("John Doe");
        newDeliveryPartner.setPhoneNumber("1234567890");

        
        Delivery_Partners deliveryPartner = deliverypartnerservice.addDeliveryPartner(newDeliveryPartner);

        // Set the delivery partner in the order
        order.setDeliveryPartner(deliveryPartner);

        return orderRepository.save(order);
		
	}

	@Override
	public void assignDeliveryPartner(int orderId, int deliveryPartnerId) 
			throws OrderException, Delivery_PartnerException {
		 Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderException ("OrderId not found"+orderId));

	       
	        Delivery_Partners deliveryPartner = deliveryPartnerRepository.findById(deliveryPartnerId)
	                .orElseThrow(() -> new Delivery_PartnerException("deliveryId not found"+deliveryPartnerId));

	       
	        if (deliveryPartner == null || !deliveryPartner.isActive()) {
	            throw new Delivery_PartnerException("Invalid delivery partner");
	        }

	        // Assign the delivery partner to the order
	        order.setDeliveryPartner(deliveryPartner);

	        // Update the order
	        orderRepository.save(order);
		
	}
	
	

	@Override
	public void updateOrderStatus(int orderId, OrderStatus newStatus) throws OrderException {
		Orders order = getOrderById(orderId);

        order.setOrderStatus(newStatus);
        orderRepository.save(order);
		
	}

	@Override
	public List<Orders> getCustomerOrderHistory(int customerId) throws OrderException, CustomerException {
		Customers customer = customerService.getCustomerById(customerId);
        return orderRepository.findByCustomer(customer);
	}

	@Override
	public Orders getOrderById(int orderId) throws OrderException {
		 return orderRepository.findById(orderId)
	                .orElseThrow(() -> new OrderException("Order not found with ID: " + orderId));
	}
}
