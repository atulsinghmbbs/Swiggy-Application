package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.Delivery_PartnerException;
import com.masai.model.Delivery_Partners;
import com.masai.repository.Delivery_PartnerRepository;


@Service
public class DeliveryPartnerServiceImpl implements Delivery_PartnerService {
	
	
	@Autowired
    private Delivery_PartnerRepository deliveryPartnerRepository;

    public Delivery_Partners addDeliveryPartner(Delivery_Partners deliveryPartner) {
    	
    	    deliveryPartner.setActive(true);
    	    
    	    return deliveryPartnerRepository.save(deliveryPartner);
    }

    public List<Delivery_Partners> getAllDeliveryPartners() {
        return deliveryPartnerRepository.findAll();
    }

    public Delivery_Partners getDeliveryPartnerById(int deliveryPartnerId) {
        return deliveryPartnerRepository.findById(deliveryPartnerId)
                .orElseThrow(() -> new  Delivery_PartnerException ("Delivery Partner not found with ID: " + deliveryPartnerId));
    }

}
