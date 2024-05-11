package com.elbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elbs.model.Bill;
import com.elbs.model.Tariff;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class BillRepo{
	@Autowired
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Bill> getAllBill() {
		String getallQuery = "select a from Bill a";
		Query query = manager.createQuery(getallQuery);
		return query.getResultList();

	}

	public void persist(Bill bill) {
		manager.persist(bill);
		
	}
	
public String deleteById(int billId) {
		
		Tariff tariff = manager.find(Tariff.class, billId);
		if (tariff!= null) {
	        manager.remove(tariff);
	        return "Deleted Bill with id: " + billId;
	    } else {
	        return "Bill with id: " + billId + " not found";
	    }
		
	}

}
