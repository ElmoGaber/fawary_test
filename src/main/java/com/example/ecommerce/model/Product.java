package com.example.ecommerce.model;

import com.example.ecommerce.contract.Expirable;
import java.time.LocalDate;
import java.util.Objects;

public class Product implements Expirable {
    private final String name;
    private final double price;
    private int quantity;
    private final LocalDate expirationDate;

    public Product(String name, double price, int quantity) {
        this(name, price, quantity, null);
    }

    public Product(String name, double price, int quantity, LocalDate expirationDate) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Product name is required");
        if (!Double.isFinite(price) || price < 0) throw new IllegalArgumentException("Price must be non-negative");
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be non-negative");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    @Override public LocalDate expirationDate() { return expirationDate; }
    public void decreaseQuantity(int amount) {
        if (amount <= 0 || amount > quantity) throw new IllegalArgumentException("Invalid stock reduction");
        quantity -= amount;
    }
    @Override public boolean equals(Object o) { return o instanceof Product p && name.equals(p.name); }
    @Override public int hashCode() { return Objects.hash(name); }
}
