package com.hogwartshouses.house.service.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super("Room.java not found");
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
