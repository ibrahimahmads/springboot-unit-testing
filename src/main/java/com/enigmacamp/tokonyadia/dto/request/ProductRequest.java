package com.enigmacamp.tokonyadia.dto.request;

public record ProductRequest(
        String name,
        Double price,
        Integer stock
) {}
