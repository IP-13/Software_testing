package com.ip13.basicFucntions;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sin {
    public double sin(double x, double error) {
        BigDecimal actual = new BigDecimal(x).remainder(new BigDecimal(2 * Math.PI));

        BigDecimal init_val = actual;

        BigDecimal last_term = actual;

        BigDecimal epsilon = BigDecimal.valueOf(error / 1000).abs();

        int n = 1;

        while (last_term.abs().compareTo(epsilon) > 0) {
            BigDecimal sign = BigDecimal.valueOf(-1).pow(n);
            BigDecimal numerator = init_val.pow(2 * n + 1);
            BigDecimal denominator = new BigDecimal(Util.fact(2 * n + 1));
            last_term = sign.multiply(numerator).divide(denominator, MathContext.DECIMAL128);
            actual = actual.add(last_term);
            n++;
        }

        return actual.doubleValue();
    }
}