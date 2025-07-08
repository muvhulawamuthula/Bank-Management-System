package com.bankingsystem.bankingapp.repository;

import com.bankingsystem.bankingapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
Optional<Customer> findByEmail(String email);
}