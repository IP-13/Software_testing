package com.ip13;

import com.ip13.functions.TrigonometricFunctions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Tag("trigonometricTest")
public class TrigonometricFunctionsTest {
    double error = 0.001;
    private final TrigonometricFunctions trigonometricFunctions = new TrigonometricFunctions();

    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, -1, 0.00000001, -0.000000001, 123.1231312421, -100000000})
    public void tanTest(double d) {
        double relativeError = Math.abs((Math.tan(d) - trigonometricFunctions.tan(d, error)) / Math.tan(d));

        assertTrue(relativeError <= error);
    }

    @Test
    public void tanTestZero() {
        assertEquals(trigonometricFunctions.tan(0, error), Math.tan(0), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI, -0.5 * Math.PI, 1.5 * Math.PI})
    public void tanThrowTest(double d) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> trigonometricFunctions.tan(d, error));
        assertEquals("tan is not defined", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, -1, 0.00000001, -0.00000001, 123.1231312421, -100000000})
    public void cotTest(double d) {
        double relativeError = Math.abs(((1 / Math.tan(d)) - trigonometricFunctions.cot(d, error)) / (1 / Math.tan(d)));
        assertTrue(relativeError <= error);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI, -Math.PI, 2 * Math.PI, 0})
    public void cotThrowTest(double d) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> trigonometricFunctions.cot(d, error));
        assertEquals("cot is not defined", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, -1, 0.00000001, -0.00000001, 123.1231312421, -100000000})
    public void secTest(double d) {
        double relativeError = Math.abs((1 / Math.cos(d)) - trigonometricFunctions.sec(d, error)) / (1 / Math.cos(d));
        assertTrue(relativeError <= error);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI, -0.5 * Math.PI, 1.5 * Math.PI})
    public void secThrowTest(double d) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> trigonometricFunctions.sec(d, error));
        assertEquals("sec is not defined", thrown.getMessage());
    }
}
