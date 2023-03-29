package org.example.task1;

import com.google.common.math.BigIntegerMath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Acos {
    public double countTaylorAcos(double x) {
        if (x < -1 || x > 1) {
            throw new IllegalArgumentException("Acos is defined on [-1, 1].");
        }

        int n = 1;
        BigDecimal actual = new BigDecimal(x);

        while (n < 200) {
            BigDecimal numerator = new BigDecimal(fact(2 * n)).
                    multiply(BigDecimal.valueOf(Math.pow(x, 2 * n + 1)));

            BigDecimal denominator = BigDecimal.valueOf(Math.pow(4, n)).
                    multiply(BigDecimal.valueOf(2L * n + 1)).
                    multiply(new BigDecimal(fact(n)).pow(2));

            BigDecimal delta = numerator.divide(denominator, MathContext.DECIMAL128);

            actual = actual.add(delta);

            n++;
        }

        return BigDecimal.valueOf(Math.PI / 2).subtract(actual).doubleValue();
    }

    public BigInteger fact(int n) {
        return BigIntegerMath.factorial(n);
    }
}

