package com.mycrm.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycrm.model.Customer;
import com.mycrm.service.CustomerService;

@Repository
public class CustomerImplementation implements CustomerService {
	
	private SessionFactory sessionFactory;
	private Session session;
	
	@Autowired
	public CustomerImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}
	
	@Transactional
	public List<Customer> findAll() {
		Transaction transaction = session.beginTransaction();
		List<Customer> theCustomers = session.createQuery("from Customer").list();
		transaction.commit();
		return theCustomers;
	}
	
	@Transactional
	public Customer findById(int theId) {
		Customer theCustomer = new Customer();
		Transaction transaction = session.beginTransaction();
		theCustomer = session.get(Customer.class, theId);
		transaction.commit();
		return theCustomer;
	}
	
	@Transactional
	public void save(Customer thCustomer) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(thCustomer);
		transaction.commit();
	}
	
	@Transactional
	public void deleteById(int theId) {
		Customer theCustomer = new Customer();
		Transaction transaction = session.beginTransaction();
		theCustomer = session.get(Customer.class, theId);
		session.delete(theCustomer);
		transaction.commit();
	}

}
