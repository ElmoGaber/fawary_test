package com.example.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (product == null || quantity <= 0) throw new IllegalArgumentException("Product and positive quantity are required");
        int existing = items.stream().filter(i -> i.product().equals(product)).mapToInt(CartItem::quantity).sum();
        if (existing + quantity > product.getQuantity()) throw new IllegalArgumentException("Requested quantity exceeds stock");
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).product().equals(product)) { items.set(i, new CartItem(product, existing + quantity)); return; }
        }
        items.add(new CartItem(product, quantity));
    }
    public boolean isEmpty() { return items.isEmpty(); }
    public List<CartItem> getItems() { return Collections.unmodifiableList(items); }
    public void clear() { items.clear(); }
}
