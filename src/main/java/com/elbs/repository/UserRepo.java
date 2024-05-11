package com.elbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elbs.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepo{
	@Autowired
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		String getallQuery = "select a from User a";
		Query query = manager.createQuery(getallQuery);
		return query.getResultList();

	}

	public void persist(User user) {
		manager.persist(user);		
	}

	public String deleteById(int userId) {
		User user = manager.find(User.class, userId);
		if (user!= null) {
	        manager.remove(user);
	        return "Deleted Bill with id: " + userId;
	    } else {
	        return "Bill with id: " + userId + " not found";
	    }
		
	}

}
