package com.randriiv;

public record CustomerRegistrationRequest(
    String firstName,
    String lastName,
    String email) {
}
