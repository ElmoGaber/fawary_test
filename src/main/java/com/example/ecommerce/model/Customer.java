package com.example.ecommerce.model;

public final class Customer {
    private final String name;
    private double balance;

    public Customer(String name, double balance) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Customer name is required");
        if (!Double.isFinite(balance) || balance < 0) throw new IllegalArgumentException("Balance must be non-negative");
        this.name = name; this.balance = balance;
    }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void debit(double amount) {
        if (amount < 0 || amount > balance) throw new IllegalArgumentException("Insufficient balance");
        balance -= amount;
    }
}
