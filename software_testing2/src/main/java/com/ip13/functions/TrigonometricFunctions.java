package com.ip13.functions;

import com.ip13.basicFunctions.Sin;
import com.ip13.basicFunctions.Util;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Double.NaN;

@AllArgsConstructor
public class TrigonometricFunctions {
    private final Sin sin;
    private final Cos cos;

    public TrigonometricFunctions() {
        this(new Sin(), new Cos());
    }

    public double tan(double x, double error) {
        if (Util.is_equal(Math.abs(x % (Math.PI)), 0.5 * Math.PI, 0.0000000001)) {
            throw new IllegalArgumentException("tan is not defined");
        }

        return sin.sin(x, error) / cos.cos(x, error);
    }

    public double sec(double x, double error) {
        if (Util.is_equal(Math.abs(x % (Math.PI)), 0.5 * Math.PI, 0.0000000001)) {
            throw new IllegalArgumentException("sec is not defined");
        }

        return 1 / cos.cos(x, error);
    }

    public double cot(double x, double error) {
        if (Util.is_equal(Math.abs(x % (Math.PI)), 0, 0.0000000001)) {
            throw new IllegalArgumentException("cot is not defined");
        }

        BigDecimal numerator = BigDecimal.valueOf(cos.cos(x, error));
        BigDecimal denominator = BigDecimal.valueOf(sin.sin(x, error));

        return numerator.divide(denominator, MathContext.DECIMAL128).doubleValue();
    }

    public void writeCSV(double x, PrintWriter out, double error) {
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            try {
                printer.printRecord(x, tan(x, error), sec(x, error), cot(x, error));
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("cot is not defined")) {
                    printer.printRecord(x, tan(x, error), sec(x, error), NaN);
                } else {
                    printer.printRecord(x, NaN, NaN, cot(x, error));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
