package com.example.ecommerce.model;

public record CartItem(Product product, int quantity) {
    public CartItem {
        if (product == null) throw new IllegalArgumentException("Product is required");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
    }
    public double subtotal() { return product.getPrice() * quantity; }
}
