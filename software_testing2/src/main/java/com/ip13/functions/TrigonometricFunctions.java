package com.ip13.functions;

import com.ip13.basicFucntions.Sin;
import com.ip13.basicFucntions.Util;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

@AllArgsConstructor
public class TrigonometricFunctions {
    private final Sin sin;
    private final Cos cos;
    private final double error = 0.00001;


    public double tan(double x) {
        if (Util.is_equal(Math.abs(x % (Math.PI)), 0.5 * Math.PI, 0.0000000001)) {
            throw new IllegalArgumentException("tan is not defined");
        }

        return sin.sin(x, error) / cos.cos(x, error);
    }

    public double sec(double x) {
        if (Util.is_equal(Math.abs(x % (Math.PI)), 0.5 * Math.PI, 0.0000000001)) {
            throw new IllegalArgumentException("tan is not defined");
        }

        return 1 / cos.cos(x, error);
    }

    public double cot(double x) {
        if (Util.is_equal(Math.abs(x % (Math.PI)), 0, 0.0000000001)) {
            throw new IllegalArgumentException("cos is not defined");
        }

        BigDecimal numerator = BigDecimal.valueOf(cos.cos(x, error));
        BigDecimal denominator = BigDecimal.valueOf(sin.sin(x, error));

        return numerator.divide(denominator, MathContext.DECIMAL128).doubleValue();
    }
}
