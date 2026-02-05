package com.enigmacamp.tokonyadia.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class CalculatorServiceTest {
    CalculatorService calculatorService = new CalculatorService();

    @Test
    void addNumberMustBeReturn5() {
        // Given / Provide
        int a = 2;
        int b = 3;

        // When / Actual
        int result = calculatorService.addNumber(a, b);

        // Then / Expect
        assertEquals(5, result);
    }

    @Test
    void addNumberMustBeReturn15() {
        int a = 10;
        int b = 5;
        int result = calculatorService.addNumber(a, b);
        assertEquals(15, result);
    }

    @Test
    void minNumber() {
        // Implementasi tes minNumber bisa dilanjutkan di sini
    }
}