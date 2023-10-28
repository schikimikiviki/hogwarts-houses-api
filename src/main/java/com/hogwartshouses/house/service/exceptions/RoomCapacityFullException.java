package com.hogwartshouses.house.service.exceptions;

public class RoomCapacityFullException extends RuntimeException {

    public RoomCapacityFullException() {
        super("Room is at full capacity. Cannot save.");
    }

    public RoomCapacityFullException(String message) {
        super(message);
    }
}
