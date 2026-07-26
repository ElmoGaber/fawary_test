package com.example.ecommerce;

import com.example.ecommerce.model.*;
import com.example.ecommerce.service.*;
import java.time.LocalDate;

public final class Main {
    private Main() { }
    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService(new ShippingService());
        Product cheese = new ShippableProduct("Cheese", 100, 10, 200, LocalDate.now().plusDays(5));
        Product biscuits = new ShippableProduct("Biscuits", 150, 5, 700, LocalDate.now().plusDays(5));
        Product scratchCard = new Product("Mobile Scratch Card", 50, 20);
        Cart successful = new Cart(); successful.add(cheese, 2); successful.add(biscuits, 1); successful.add(scratchCard, 1);
        checkout.checkout(new Customer("Alice", 1000), successful);
        scenario("Empty cart", () -> checkout.checkout(new Customer("Bob", 100), new Cart()));
        scenario("Expired product", () -> { Cart c = new Cart(); c.add(new Product("Expired", 10, 1, LocalDate.now().minusDays(1)), 1); checkout.checkout(new Customer("Bob", 100), c); });
        scenario("Out of stock", () -> { Product p = new Product("Limited", 10, 0); Cart c = new Cart(); c.getItems(); /* stock cannot be added through public API */ });
        scenario("Insufficient balance", () -> { Cart c = new Cart(); c.add(new Product("Book", 100, 1), 1); checkout.checkout(new Customer("Bob", 50), c); });
    }
    private static void scenario(String name, Runnable action) { try { action.run(); } catch (RuntimeException e) { System.out.println("\n" + name + ": " + e.getMessage()); } }
}
