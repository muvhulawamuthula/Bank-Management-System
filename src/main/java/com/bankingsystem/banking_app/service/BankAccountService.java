package com.bankingsystem.bankingapp.service;

import com.bankingsystem.bankingapp.model.BankAccount;
import com.bankingsystem.bankingapp.model.Customer;
import com.bankingsystem.bankingapp.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankAccountService {

private final BankAccountRepository bankAccountRepository;

public BankAccount createAccount(Customer customer) {
BankAccount account = BankAccount.builder()
.accountNumber(UUID.randomUUID().toString().substring(0, 10)) // simple account number
.balance(BigDecimal.ZERO)
.accountType(null) // You can pass this dynamically
.customer(customer)
.build();

return bankAccountRepository.save(account);
}

public List<BankAccount> getAccountsForCustomer(Customer customer) {
return bankAccountRepository.findByCustomer(customer);
}

public BankAccount deposit(String accountNumber, BigDecimal amount) {
BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber)
.orElseThrow(() -> new RuntimeException("Account not found"));

account.setBalance(account.getBalance().add(amount));
return bankAccountRepository.save(account);
}

public BankAccount withdraw(String accountNumber, BigDecimal amount) {
BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber)
.orElseThrow(() -> new RuntimeException("Account not found"));

if (account.getBalance().compareTo(amount) < 0) {
throw new RuntimeException("Insufficient balance");
}

account.setBalance(account.getBalance().subtract(amount));
return bankAccountRepository.save(account);
}
}