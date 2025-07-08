package com.bankingsystem.bankingapp.security;

import com.bankingsystem.bankingapp.model.Customer;
import com.bankingsystem.bankingapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

private final CustomerRepository customerRepository;

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
Customer customer = customerRepository.findByEmail(email)
.orElseThrow(() -> new UsernameNotFoundException("User not found"));

return User.builder()
.username(customer.getEmail())
.password(customer.getPassword())
.roles("USER")
.build();
}
}