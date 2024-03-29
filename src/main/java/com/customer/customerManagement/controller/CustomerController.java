package com.customer.customerManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.customer.customerManagement.exception.CustomerNotFoundException;
import com.customer.customerManagement.model.Customer;
import com.customer.customerManagement.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {

	

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		Customer customerCreated = customerService.addCustomer(customer);
		Long customerId = customerCreated.getCustomer_ID();
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Customer created successfully. Customer ID=" + customerId);

	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		if (customers != null && !customers.isEmpty()) {
			return ResponseEntity.ok(customers);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{customer_ID}")
	public Customer getCustomerById(@PathVariable long customer_ID) {
		Customer customer = customerService.getCustomerById(customer_ID);
		if (customer != null) {
			return customer;
		} else {

			return null;
		}
	}

	@PutMapping("/{customer_ID}")
	public ResponseEntity<String> updateCustomerDetails(@PathVariable long customer_ID,
			@RequestBody Customer updatedCustomer) {
		try {
			customerService.updateCustomerDetails(customer_ID, updatedCustomer);
			return ResponseEntity.status(HttpStatus.CREATED).body("Customer updated successfully");
		} catch (CustomerNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer doesnot exists");

		}
	}

	@DeleteMapping("/delete/{customer_ID}")
public ResponseEntity<String> deleteCustomer(@PathVariable long customer_ID) {
		Customer optionalCustomer = customerService.getCustomerById(customer_ID);
		if (optionalCustomer == null) {
			throw new CustomerNotFoundException("Customer not found");
		}
		customerService.deleteCustomer(customer_ID);
		return new ResponseEntity<>("Delete account and customer successful", HttpStatus.OK);

	}



}
