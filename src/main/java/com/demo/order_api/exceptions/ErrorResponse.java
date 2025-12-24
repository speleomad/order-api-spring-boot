package com.demo.order_api.exceptions;
public record ErrorResponse(
    int status,
    String message,
    long timestamp
) {}