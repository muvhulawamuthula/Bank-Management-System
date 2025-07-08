package com.bankingsystem.bankingapp.repository;

import com.bankingsystem.bankingapp.model.BankAccount;
import com.bankingsystem.bankingapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
List<BankAccount> findByCustomer(Customer customer);
Optional<BankAccount> findByAccountNumber(String accountNumber);
}
