package com.ip13;

import com.ip13.basicFucntions.Ln;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LnTest {
    private final double arg1 = Math.E;
    private final double arg2 = 0.00001;
    private final double arg3 = Math.E * Math.E * Math.E;
    private final double arg4 = 123456789;
    private final double arg5 = Double.MAX_VALUE;
    private final double arg6 = 1.1111111;
    private final double arg7 = 123.123;

    @ParameterizedTest
    @ValueSource(doubles = {arg1, arg2, arg3, arg4, arg5, arg6, arg7})
    void lnTest(double d) {
        Ln ln = new Ln();
        double error = 0.1;
        assertEquals(Math.log(d), ln.ln(d, error), error);
    }
}
