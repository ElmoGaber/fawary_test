package com.example.ecommerce.service;

import com.example.ecommerce.contract.Shippable;
import com.example.ecommerce.model.CartItem;
import java.util.List;

public class ShippingService {
    public void ship(List<CartItem> items) {
        if (items.isEmpty()) return;
        double totalGrams = items.stream().mapToDouble(i -> i.product() instanceof Shippable s ? s.getWeight() * i.quantity() : 0).sum();
        System.out.println("** Shipment notice **\n");
        items.forEach(i -> { Shippable s = (Shippable) i.product(); System.out.printf("%dx %s %.0fg%n", i.quantity(), s.getName(), s.getWeight() * i.quantity()); });
        System.out.printf("\nTotal package weight %.1fkg%n%n", totalGrams / 1000);
    }
}
