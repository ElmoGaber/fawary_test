package com.example.ecommerce.model;

import com.example.ecommerce.contract.Shippable;
import java.time.LocalDate;

public final class ShippableProduct extends Product implements Shippable {
    private final double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        this(name, price, quantity, weight, null);
    }

    public ShippableProduct(String name, double price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity, expirationDate);
        if (!Double.isFinite(weight) || weight <= 0) throw new IllegalArgumentException("Weight must be positive");
        this.weight = weight;
    }

    @Override public double getWeight() { return weight; }
}
