package com.ip13.functions;

import com.ip13.basicFunctions.Sin;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.MathContext;

@AllArgsConstructor
public class Cos {
    private final Sin sin;

    public Cos() {
        this(new Sin());
    }

    public double cos(double x, double error) {
        BigDecimal val = BigDecimal.valueOf(sin.sin(x, error));
        try {
            val = BigDecimal.ONE.subtract(val.multiply(val)).sqrt(MathContext.DECIMAL128);
        } catch (ArithmeticException e) {
            val = BigDecimal.valueOf(-6.123233995736766E-17);
        }
        if (Math.abs(x % (2 * Math.PI)) >= Math.PI / 2 && Math.abs(x % (2 * Math.PI)) <= 1.5 * Math.PI) {
            return -val.doubleValue();
        }

        return val.doubleValue();
    }

    public void writeCSV(double x, Writer out, double error) {
        double res = cos(x, error);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
