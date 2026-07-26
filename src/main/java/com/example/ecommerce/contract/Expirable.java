package com.example.ecommerce.contract;

import java.time.LocalDate;

public interface Expirable {
    LocalDate expirationDate();
    default boolean isExpired(LocalDate date) {
        return expirationDate() != null && expirationDate().isBefore(date);
    }
}
