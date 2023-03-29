package com.ip13.functions;

import com.ip13.basicFucntions.Sin;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

@AllArgsConstructor
public class Cos {
    private final Sin sin;

    public double cos(double x, double error) {
        error /= 1000;

        BigDecimal val = BigDecimal.valueOf(sin.sin(x, error));
        val = BigDecimal.ONE.subtract(val.multiply(val)).sqrt(MathContext.DECIMAL128);

        if (Math.abs(x % (2 * Math.PI)) >= Math.PI / 2 && Math.abs(x % (2 * Math.PI)) <= 1.5 * Math.PI) {
            return -val.doubleValue();
        }

        return val.doubleValue();
    }
}
