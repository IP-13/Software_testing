package com.ip13;

import com.ip13.basicFucntions.Sin;
import com.ip13.functions.Cos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CosTest {
    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, 0, -1, 0.00000001, -0.000000001, 123.1231312421, -100000000})
    public void cosTest(double d) {
        Cos cos = new Cos(new Sin());
        double error = 0.001;

        double expected = Math.cos(d);
        double actual = cos.cos(d, error);

        assertTrue(Math.abs(((expected - actual) / expected)) < 0.001);
    }
}
