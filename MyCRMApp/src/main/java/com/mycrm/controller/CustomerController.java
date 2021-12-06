package com.mycrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.wiring.ClassNameBeanWiringInfoResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycrm.model.Customer;
import com.mycrm.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	public CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		List<Customer> theCustomers = customerService.findAll();
		theModel.addAttribute("Customers", theCustomers);
		return "List-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Customer theCustomer = new Customer();
		theModel.addAttribute("Customer", theCustomer);
		return "Customer-Form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {
		Customer theCustomer = customerService.findById(theId);
		theModel.addAttribute("Customer", theCustomer);
		return "Customer-Form";
	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int theId, @RequestParam("firstName") String theFirstName,
			@RequestParam("lastName") String theLastName, @RequestParam("email") String theEmail, Model theModel) {
		Customer theCustomer;

		if (theId != 0) {
			theCustomer = customerService.findById(theId);
			theCustomer.setFirstName(theFirstName);
			theCustomer.setLastName(theLastName);
			theCustomer.setEmail(theEmail);
		} else {
			theCustomer = new Customer(theFirstName, theLastName, theEmail);
		}
		customerService.save(theCustomer);
		return "redirect:/customers/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {
		customerService.deleteById(theId);
		return "redirect:/customers/list";
	}

}
