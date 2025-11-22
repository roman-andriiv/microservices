package com.randriiv.clients.notification;

public record NotificationRequest(Integer customerId, String customerEmail, String message) {}
