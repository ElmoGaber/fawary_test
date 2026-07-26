# E-commerce Checkout (Java 17)

Console Maven application demonstrating an object-oriented checkout flow.

## Run

```bash
mvn clean compile exec:java
```
<img width="999" height="574" alt="image" src="https://github.com/user-attachments/assets/943a3f4d-b0e8-4fa8-a9ed-f44eacd46585" />

## Structure

- `Product` owns product data and stock.
- `ShippableProduct` adds the shipping capability through `Shippable`.
- `Expirable` models optional expiration.
- `Cart` contains `CartItem` values.
- `CheckoutService` validates and coordinates an atomic checkout.
- `ShippingService` prints shipment information.
- `Main` demonstrates successful checkout and validation scenarios.

## Assumptions

Shipping is a fixed 30-unit fee whenever at least one item requires shipping. Weights are stored in grams; money uses `double` to match the challenge statement. Products without an expiration date never expire. Checkout validates all items and balance before changing stock or balance.

## Design notes

Composition through focused interfaces is used instead of a product-subclass matrix. Only `ShippableProduct` implements `Shippable`, so the shipping service receives shipping-capable products and never handles digital products.
