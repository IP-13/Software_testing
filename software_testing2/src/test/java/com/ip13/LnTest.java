package com.ip13;

import com.ip13.basicFunctions.Ln;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTest {
    private final Ln ln = new Ln();
    private final double error = 0.01;

    @ParameterizedTest
    @ValueSource(doubles = {1, Math.E, 10e-5, Math.E * Math.E, 123456789, Double.MAX_VALUE, 1.1111111, 123.123})
    void lnTest(double d) {
        assertEquals(Math.log(d), ln.ln(d, error), error);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-100, -10, -5, -2, -1, 0})
    void illegalArgTest(double d) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> ln.ln(d, error));
        assertEquals("ln is not defined if arg <= 0", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 35, 50, 100})
    void sequenceTest(double d) {
        assertEquals(Math.log(d), ln.ln(d, error), error);
    }
}
