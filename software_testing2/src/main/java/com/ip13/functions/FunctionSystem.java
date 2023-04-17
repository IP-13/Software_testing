package com.ip13.functions;

import com.ip13.basicFunctions.Ln;
import com.ip13.basicFunctions.Sin;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.PrintWriter;
import java.io.IOException;

import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;

@AllArgsConstructor
public class FunctionSystem {
    private final Sin sin;
    private final Cos cos;
    private final TrigonometricFunctions trigFunc;
    private final Ln ln;
    private final LogarithmicFunctions logFunc;

    public FunctionSystem() {
        this(new Sin(), new Cos(), new TrigonometricFunctions(), new Ln(), new LogarithmicFunctions());
    }

    public double solveSystem(double x, double error) {
        try {
            if (x <= 0) {
                return negativeArg(x, error);
            }
            return positiveArg(x, error);
        } catch (IllegalArgumentException e) {
            return POSITIVE_INFINITY;
        }
    }

    private double negativeArg(double x, double error) {
        return ((sin.sin(x, error) + trigFunc.tan(x, error) - cos.cos(x, error)) / trigFunc.tan(x, error) + sin.sin(x, error))
                * (((trigFunc.sec(x, error) - cos.cos(x, error)) * sin.sin(x, error)) - trigFunc.cot(x, error));
    }

    private double positiveArg(double x, double error) {
        return (Math.pow(ln.ln(x, error) / logFunc.log(x, 2, error), 3) - logFunc.log(x, 3, error)) -
                logFunc.log(x, 5, error) + (logFunc.log(x, 10, error) +
                (logFunc.log(x, 2, error) / ln.ln(x, error))) / logFunc.log(x, 2, error);
    }

    public void writeCSV(double x, PrintWriter out, double error) {
        double res;
        try {
            res = solveSystem(x, error);
        } catch (IllegalArgumentException e) {
            res = NaN;
        }
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
