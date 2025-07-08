package com.bankingsystem.bankingapp.controller;

import com.bankingsystem.bankingapp.model.Customer;
import com.bankingsystem.bankingapp.service.BankAccountService;
import com.bankingsystem.bankingapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {

private final CustomerService customerService;
private final BankAccountService bankAccountService;

@GetMapping("/register")
public String registerPage() {
return "register";
}

@GetMapping("/dashboard")
public String dashboardPage(Model model, Authentication auth) {
Customer customer = customerService.getCustomerByEmail(auth.getName())
.orElseThrow(() -> new RuntimeException("User not found"));
model.addAttribute("accounts", bankAccountService.getAccountsForCustomer(customer));
return "dashboard";
}

@GetMapping("/deposit")
public String depositPage() {
return "deposit";
}

@GetMapping("/withdraw")
public String withdrawPage() {
return "withdraw";
}
}