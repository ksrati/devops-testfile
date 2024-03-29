package com.customer.customerManagement.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.customer.customerManagement.exception.CustomerNotFoundException;

import com.customer.customerManagement.model.Customer;
import com.customer.customerManagement.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;


	

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	
	}

	public Customer addCustomer(Customer customer) {
		
		return customerRepository.save(customer);

	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(long customer_ID) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customer_ID);
		return optionalCustomer.orElse(null); // Return null if not found, handle accordingly in your application
	}

	public boolean updateCustomerDetails(long customer_ID, Customer updatedCustomer) {
		// Check if the customer exists
		Optional<Customer> optionalCustomer = customerRepository.findById(customer_ID);
		if (optionalCustomer.isPresent()) {
			// Update customer details
			Customer existingCustomer = optionalCustomer.get();
			existingCustomer.setFirst_name(updatedCustomer.getFirst_name());
			existingCustomer.setLast_name(updatedCustomer.getLast_name());
			existingCustomer.setDob(updatedCustomer.getDob());
			existingCustomer.setAddress(updatedCustomer.getAddress());
			existingCustomer.setPhone(updatedCustomer.getPhone());
		
			
			// Save the updated customer
			customerRepository.save(existingCustomer);
			return true;
		} else {
			// Handle customer not found
			throw new CustomerNotFoundException("customer does not exist");
		}
	}

	public void deleteCustomer(long customer_ID) {

	
		// Delete customer by ID
		customerRepository.deleteById(customer_ID);
	}

}
