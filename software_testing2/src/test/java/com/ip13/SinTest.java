package com.ip13;

import com.ip13.basicFunctions.Sin;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("testSin")
public class SinTest {
    private final Sin sin = new Sin();
    private final double error = 0.001;

    @ParameterizedTest
    @ValueSource(doubles = {100000, 1, 0, -1, 0.00000001, -0.000000001, 123.1231312421, -100000000})
    void sinTest1(double d) {
        assertEquals(Math.sin(d), sin.sin(d, error), error);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, Math.PI, 2 * Math.PI, 0.5 * Math.PI, 0.75 * Math.PI, 0.18 * Math.PI, 0, 1, 17})
    void sinTest2(double d) {
        assertEquals(Math.sin(d), sin.sin(d, error), error);
    }
}
