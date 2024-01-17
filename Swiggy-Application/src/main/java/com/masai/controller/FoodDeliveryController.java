package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customers;
import com.masai.model.Delivery_Partners;
import com.masai.model.Orders;
import com.masai.model.Restaurants;
import com.masai.service.CustomerService;
import com.masai.service.Delivery_PartnerService;
import com.masai.service.OrderService;
import com.masai.service.RestaurantService;
import com.masai.status.OrderStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class FoodDeliveryController {

	
	    @Autowired
	    private RestaurantService restaurantService;

	    @Autowired
	    private CustomerService customerService;

	    @Autowired
	    private OrderService orderService;

	    @Autowired
	    private Delivery_PartnerService deliveryPartnerService;

	    // Restaurant Endpoints

	    @PostMapping("/restaurants")
	    public ResponseEntity<Restaurants> addRestaurant(@Valid @RequestBody Restaurants restaurant) {
	        Restaurants addedRestaurant = restaurantService.addRestaurant(restaurant);
	        return new ResponseEntity<>(addedRestaurant, HttpStatus.CREATED);
	    }

	    @GetMapping("/restaurants")
	    public ResponseEntity<List<Restaurants>> getAllRestaurants() {
	        List<Restaurants> restaurants = restaurantService.getAllRestaurants();
	        return new ResponseEntity<>(restaurants, HttpStatus.OK);
	    }

	    // Customer Endpoints

	    @PostMapping("/customers")
	    public ResponseEntity<Customers> addCustomer(@Valid @RequestBody Customers customer) {
	        Customers addedCustomer = customerService.addCustomer(customer);
	        return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
	    }

	    @GetMapping("/customers")
	    public ResponseEntity<List<Customers>> getAllCustomers() {
	        List<Customers> customers = customerService.getAllCustomers();
	        return new ResponseEntity<>(customers, HttpStatus.OK);
	    }

	    // Order Endpoints

	    @PostMapping("/orders")
	    public ResponseEntity<Orders> placeOrder(@Valid @RequestParam Integer customerId, @RequestParam Integer restaurantId, @RequestBody List<String> items) {
	        Orders placedOrder = orderService.placeOrder(customerId, restaurantId, items);
	        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
	    }

	    @PostMapping("/orders/{orderId}/assign-delivery")
	    public ResponseEntity<Void> assignDeliveryPartner(@PathVariable int orderId, @RequestParam int deliveryPartnerId) {
	        orderService.assignDeliveryPartner(orderId, deliveryPartnerId);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	    @PostMapping("/orders/{orderId}/update-status")
	    public ResponseEntity<Void> updateOrderStatus(@PathVariable int orderId, @RequestParam OrderStatus newStatus) {
	        orderService.updateOrderStatus(orderId, newStatus);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	    @GetMapping("/customers/{customerId}/order-history")
	    public ResponseEntity<List<Orders>> getCustomerOrderHistory(@PathVariable int customerId) {
	        List<Orders> orderHistory = orderService.getCustomerOrderHistory(customerId);
	        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
	    }

	    // Delivery Partner Endpoints

	    @PostMapping("/delivery-partners")
	    public ResponseEntity<Delivery_Partners> addDeliveryPartner(@Valid @RequestBody Delivery_Partners deliveryPartner) {
	    	Delivery_Partners addedDeliveryPartner = deliveryPartnerService.addDeliveryPartner(deliveryPartner);
	        return new ResponseEntity<>(addedDeliveryPartner, HttpStatus.CREATED);
	    }

	    @GetMapping("/delivery-partners")
	    public ResponseEntity<List<Delivery_Partners>> getAllDeliveryPartners() {
	        List<Delivery_Partners> deliveryPartners = deliveryPartnerService.getAllDeliveryPartners();
	        return new ResponseEntity<>(deliveryPartners, HttpStatus.OK);
	    }
}
