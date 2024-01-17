package com.masai.service;

import java.util.List;

import com.masai.model.Delivery_Partners;

public interface Delivery_PartnerService {

	public Delivery_Partners addDeliveryPartner(Delivery_Partners deliveryPartner);
	
	public List<Delivery_Partners> getAllDeliveryPartners();
	
	 public Delivery_Partners getDeliveryPartnerById(int deliveryPartnerId);
}
