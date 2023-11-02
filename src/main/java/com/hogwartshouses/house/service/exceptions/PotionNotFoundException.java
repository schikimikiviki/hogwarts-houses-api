package com.hogwartshouses.house.service.exceptions;

public class PotionNotFoundException extends RuntimeException {
    public PotionNotFoundException() {
        super("Potion.java not found");
    }

    public PotionNotFoundException(String message) {
        super(message);
    }
}
