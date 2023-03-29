package com.ip13.basicFucntions;

import java.math.BigDecimal;
import java.math.MathContext;

public class Ln {
    public double ln(double x, double error) {
        if (x <= 0) {
            throw new IllegalArgumentException("Ln is not defined if argument <= 0");
        }

        BigDecimal ln2 = new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094");

        BigDecimal actual = BigDecimal.ZERO;

        BigDecimal epsilon = BigDecimal.valueOf(error / 1000);

        if (x > 2) {
            while (x > 2) {
                actual = actual.add(ln2);
                x /= 2;
            }
        } else if (x < 1) {
            while (x < 1) {
                actual = actual.subtract(ln2);
                x *= 2;
            }
        }

        BigDecimal actual_reminder = BigDecimal.ZERO;

        int n = 1;

        double val = x - 1;

        BigDecimal last_term = BigDecimal.valueOf(val);

        while (last_term.abs().compareTo(epsilon) > 0 && n <= 200) {
            BigDecimal sign = BigDecimal.valueOf(-1).pow(n + 1);
            BigDecimal numerator = BigDecimal.valueOf(val).pow(n);
            BigDecimal denominator = new BigDecimal(n);
            last_term = sign.multiply(numerator).divide(denominator, MathContext.DECIMAL128);
            actual_reminder = actual_reminder.add(last_term);
            n++;
        }

        return actual.doubleValue() + actual_reminder.doubleValue();
    }
}
