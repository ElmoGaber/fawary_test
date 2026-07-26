package com.example.ecommerce.service;

import com.example.ecommerce.contract.Shippable;
import com.example.ecommerce.model.*;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

public final class CheckoutService {
    private static final double SHIPPING_FEE = 30.0;
    private final ShippingService shippingService;
    private final Clock clock;

    public CheckoutService(ShippingService shippingService) { this(shippingService, Clock.systemUTC()); }
    public CheckoutService(ShippingService shippingService, Clock clock) {
        this.shippingService = shippingService; this.clock = clock;
    }
    public void checkout(Customer customer, Cart cart) {
        if (customer == null || cart == null) throw new IllegalArgumentException("Customer and cart are required");
        if (cart.isEmpty()) throw new IllegalStateException("Cannot checkout an empty cart");
        List<CartItem> items = cart.getItems();
        LocalDate today = LocalDate.now(clock);
        for (CartItem item : items) {
            Product p = item.product();
            if (p.isExpired(today)) throw new IllegalStateException(p.getName() + " is expired");
            if (item.quantity() > p.getQuantity()) throw new IllegalStateException(p.getName() + " is out of stock");
        }
        double subtotal = items.stream().mapToDouble(CartItem::subtotal).sum();
        List<CartItem> shippable = items.stream().filter(i -> i.product() instanceof Shippable).toList();
        double shipping = shippable.isEmpty() ? 0 : SHIPPING_FEE;
        double total = subtotal + shipping;
        if (customer.getBalance() < total) throw new IllegalStateException("Insufficient balance");
        shippingService.ship(shippable);
        items.forEach(i -> i.product().decreaseQuantity(i.quantity()));
        customer.debit(total);
        System.out.println("** Checkout receipt **\n");
        items.forEach(i -> System.out.printf("%dx %s %.0f%n", i.quantity(), i.product().getName(), i.subtotal()));
        System.out.printf("\n----------------------%n%nSubtotal %.0f%nShipping %.0f%nAmount %.0f%nCustomer Balance %.0f%n", subtotal, shipping, total, customer.getBalance());
        cart.clear();
    }
}
