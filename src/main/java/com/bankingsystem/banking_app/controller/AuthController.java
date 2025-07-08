package com.bankingsystem.bankingapp.controller;

import com.bankingsystem.bankingapp.dto.LoginRequest;
import com.bankingsystem.bankingapp.dto.RegisterRequest;
import com.bankingsystem.bankingapp.model.Customer;
import com.bankingsystem.bankingapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

private final CustomerService customerService;
private final AuthenticationManager authManager;

@PostMapping("/register")
public String register(@RequestBody RegisterRequest request) {
Customer customer = Customer.builder()
.fullName(request.getFullName())
.email(request.getEmail())
.password(request.getPassword())
.build();

customerService.registerCustomer(customer);
return "Registration successful";
}

@PostMapping("/login")
public String login(@RequestBody LoginRequest request) {
Authentication auth = authManager.authenticate(
new UsernamePasswordAuthenticationToken(
request.getEmail(),
request.getPassword()
)
);

SecurityContextHolder.getContext().setAuthentication(auth);
return "Login successful";
}
}
