package com.customer.customerManagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.customerManagement.model.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
