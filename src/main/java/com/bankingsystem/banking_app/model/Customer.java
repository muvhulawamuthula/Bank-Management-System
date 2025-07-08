package com.bankingsystem.banking_app.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String fullName;

@Column(unique = true, nullable = false)
private String email;

private String password; // This will be encrypted in future steps

@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
private List<BankAccount> accounts;
}