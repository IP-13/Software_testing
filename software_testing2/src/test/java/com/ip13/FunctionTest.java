package com.ip13;

import com.ip13.functions.FunctionSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Double.POSITIVE_INFINITY;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {
    private final FunctionSystem func = new FunctionSystem();
    private final double error = 0.01;

    private double log(double x, double base) {
        return Math.log(x) / Math.log(base);
    }

    private double myFunc(double x) {
        return (x <= 0) ? (((Math.sin(x) + Math.tan(x) - Math.cos(x)) / Math.tan(x) + Math.sin(x)) * (((1 / Math.cos(x)
                - Math.cos(x)) * Math.sin(x)) - 1 / Math.tan(x)))
                : (Math.pow(Math.log(x) / log(x, 2), 3) - log(x, 3)) - log(x, 5) + (Math.log10(x)
                + (log(x, 2) / Math.log(x))) / log(x, 2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.5, -3.545, -2.543, -2.392, -5.534, -5.684, -9.828, 0.695, 2.0})
    public void generalTest(double x) {
        double expected = myFunc(x);
        double actual = func.solveSystem(x, error);

        assertTrue(Math.abs(((expected - actual) / expected)) < error);
        assertEquals(myFunc(x), func.solveSystem(x, error), error);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-4 * Math.PI, -7 * Math.PI / 2, -3 * Math.PI, -2 * Math.PI, -3 * Math.PI / 2, -Math.PI})
    public void illegalArgTest(double x) {
        assertEquals(func.solveSystem(x, error), POSITIVE_INFINITY, error);
    }

    @Test
    public void periodNegFunc() {
        for (int x = -200; x < -100; x++) {
            assertEquals(func.solveSystem(x, error), func.solveSystem(x + 2 * Math.PI, error), error);
        }
    }
}
