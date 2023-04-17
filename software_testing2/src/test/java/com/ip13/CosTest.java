package com.ip13;

import com.ip13.functions.Cos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CosTest {
    private final Cos cos = new Cos();
    private final double error = 0.001;
    private final double relError = 0.5;

    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, 0, -1, 0.00000001, -0.000000001, 123.1231312421, -100000000})
    void cosTest1(double d) {
        double expected = Math.cos(d);
        double actual = cos.cos(d, error);

        assertTrue(Math.abs(((expected - actual) / expected)) < relError);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, Math.PI, 2 * Math.PI, 0.5 * Math.PI, 0.75 * Math.PI, 0.18 * Math.PI, 0, 1, 17})
    void cosTest2(double d) {
        double expected = Math.cos(d);
        double actual = cos.cos(d, error);

        assertTrue(Math.abs(((expected - actual) / expected)) < relError);
    }
}