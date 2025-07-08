package com.bankingsystem.bankingapp.service;

import com.bankingsystem.bankingapp.model.Customer;
import com.bankingsystem.bankingapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

private final CustomerRepository customerRepository;
private final PasswordEncoder passwordEncoder;

public Customer registerCustomer(Customer customer) {
// Check if email exists
Optional<Customer> existing = customerRepository.findByEmail(customer.getEmail());
if (existing.isPresent()) {
throw new RuntimeException("Email already registered");
}

// Encode password
customer.setPassword(passwordEncoder.encode(customer.getPassword()));
return customerRepository.save(customer);
}

public Optional<Customer> getCustomerByEmail(String email) {
return customerRepository.findByEmail(email);
}

public Optional<Customer> getCustomerById(Long id) {
return customerRepository.findById(id);
}
}