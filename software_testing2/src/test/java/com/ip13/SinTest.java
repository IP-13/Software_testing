package com.ip13;

import com.ip13.basicFucntions.Sin;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


@Tag("testSin")
public class SinTest {
    @ParameterizedTest
    @ValueSource(doubles = {Math.PI, 2 * Math.PI, 0.5 * Math.PI, 0.75 * Math.PI, 0.18 * Math.PI, 0, 1, 17})
    void sinTest(double d) {
        Sin sin = new Sin();
        double error = 0.001;
        assertEquals(Math.sin(d), sin.sin(d, error), error);
    }
}
