package com.ip13.basicFunctions;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.MathContext;

public class Sin {

    public double sin(double x, double error) {
        BigDecimal actual = new BigDecimal(x).remainder(new BigDecimal(2 * Math.PI));

        BigDecimal init_val = actual;

        BigDecimal last_term = actual;

        int n = 1;

        while (last_term.abs().compareTo(BigDecimal.valueOf(error).abs()) > 0) {
            BigDecimal sign = BigDecimal.valueOf(-1).pow(n);
            BigDecimal numerator = init_val.pow(2 * n + 1);
            BigDecimal denominator = new BigDecimal(Util.fact(2 * n + 1));
            last_term = sign.multiply(numerator).divide(denominator, MathContext.DECIMAL128);
            actual = actual.add(last_term);
            n++;
        }

        return actual.doubleValue();
    }

    public void writeCSV(double x, Writer out, double error) {
        double res = sin(x, error);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}