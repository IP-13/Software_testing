package com.ip13;

import com.ip13.basicFucntions.Sin;
import com.ip13.functions.Cos;
import com.ip13.functions.TrigonometricFunctions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Tag("trigonometricTest")
public class TrigonometricFunctionsTest {
    private final TrigonometricFunctions trigonometricFunctions = new TrigonometricFunctions(new Sin(), new Cos(new Sin()));

    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, -1, 0.00000001, -0.000000001, 123.1231312421, -100000000})
    public void tanTest(double d) {
        double error = 0.001;

        double relative_error = Math.abs((Math.tan(d) - trigonometricFunctions.tan(d)) / Math.tan(d));

        assertTrue(relative_error <= 0.001);
    }

    @Test
    public void tanTestZero() {
        assertEquals(trigonometricFunctions.tan(0), Math.tan(0), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI, -0.5 * Math.PI, 1.5 * Math.PI})
    public void tanThrowTest(double d) {
        assertThrows(IllegalArgumentException.class, () -> trigonometricFunctions.tan(d));
    }

    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, -1, 0.00000001, -0.00000001, 123.1231312421, -100000000})
    public void cotTest(double d) {
        double error = 0.001;

        double relative_error = Math.abs(((1 / Math.tan(d)) - trigonometricFunctions.cot(d)) / (1 / Math.tan(d)));

        assertTrue(relative_error <= 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI, -Math.PI, 2 * Math.PI, 0})
    public void cotThrowTest(double d) {
        assertThrows(IllegalArgumentException.class, () -> trigonometricFunctions.cot(d));
    }

    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, -1, 0.00000001, -0.00000001, 123.1231312421, -100000000})
    public void secTest(double d) {
        double error = 0.001;

        double relative_error = Math.abs((1 / Math.cos(d)) - trigonometricFunctions.sec(d)) / (1 / Math.cos(d));

        assertTrue(relative_error <= 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI, -0.5 * Math.PI, 1.5 * Math.PI})
    public void secThrowTest(double d) {
        assertThrows(IllegalArgumentException.class, () -> trigonometricFunctions.sec(d));
    }

}
