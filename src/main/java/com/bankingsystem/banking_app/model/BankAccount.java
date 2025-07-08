package com.bankingsystem.banking_app.model;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String accountNumber;

@Enumerated(EnumType.STRING)
private AccountType accountType;

private BigDecimal balance;

@ManyToOne
@JoinColumn(name = "customer_id")
private Customer customer;
}