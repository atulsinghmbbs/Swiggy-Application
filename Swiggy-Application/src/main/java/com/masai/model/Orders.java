package com.masai.model;

import java.util.List;

import com.masai.status.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customerId")
    @NotNull(message = "Customer cannot be null")
    private Customers customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "restaurantId")
    @NotNull(message = "Restaurant cannot be null")
    private Restaurants restaurant;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "deliveryPartnerId")
    @NotNull(message = "Delivery partner cannot be null")
    private Delivery_Partners deliveryPartner;

    @ElementCollection
    private List<String> items;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order status cannot be null")
    private OrderStatus orderStatus;
}
