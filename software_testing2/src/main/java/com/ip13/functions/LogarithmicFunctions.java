package com.ip13.functions;

import com.ip13.basicFunctions.Ln;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Double.NaN;

@AllArgsConstructor
public class LogarithmicFunctions {
    private final Ln ln;

    public LogarithmicFunctions() {
        this(new Ln());
    }

    public double log(double x, double base, double error) {

        if (base <= 0 || base == 1 || x <= 0) {
            throw new IllegalArgumentException("log is not defined if base <= 0 or base == 1 or x <= 0");
        }

        return ln.ln(x, error) / ln.ln(base, error);
    }


    public void writeCSV(double x, PrintWriter out, double error) {
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            try {
                printer.printRecord(x, ln.ln(x, error), log(x, 2, error), log(x, 3, error), log(x, 5, error), log(x, 10, error));
            } catch (IllegalArgumentException e) {
                printer.printRecord(x, NaN, NaN, NaN, NaN, NaN);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
