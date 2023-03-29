package com.ip13.functions;

import com.ip13.basicFucntions.Ln;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogarithmicFunctions {
    private final Ln ln;

    public double log(double x, double base, double error) {
        error /= 1000;

        if (base <= 0 || base == 1 || x <= 0) {
            throw new IllegalArgumentException("log is not defined");
        }

        return ln.ln(x, error) / ln.ln(base, error);
    }
}
