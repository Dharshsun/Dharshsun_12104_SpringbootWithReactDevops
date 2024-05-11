package com.elbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elbs.model.Tariff;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TariffRepo {

	@Autowired
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Tariff> getAllTariff() {
		String getallQuery = "select a from Tariff a";
		Query query = manager.createQuery(getallQuery);
		return query.getResultList();

	}

	public void persist(Tariff tariff) {
		manager.persist(tariff);

	}
	
	

	public String deleteById(int tariffId) {
		
		Tariff tariff = manager.find(Tariff.class, tariffId);
		if (tariff!= null) {
	        manager.remove(tariff);
	        return "Deleted Tariff with id: " + tariffId;
	    } else {
	        return "Tariff with id: " + tariffId + " not found";
	    }
		
	}

	public void merge(Tariff tariff) {
	    Tariff existingTariff = manager.find(Tariff.class, tariff.getTariffId());
	    if (existingTariff!= null) {
	        existingTariff.setTariffCategory(tariff.getTariffCategory());
	        existingTariff.setTariffAmount(tariff.getTariffAmount());
	        existingTariff.setTariffStatus(tariff.getTariffStatus());
	        manager.merge(existingTariff);
	    }
	}
	
}