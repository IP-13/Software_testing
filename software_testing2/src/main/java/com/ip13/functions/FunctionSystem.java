package com.ip13.functions;

import com.ip13.basicFucntions.Ln;
import com.ip13.basicFucntions.Sin;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FunctionSystem {
    private final Sin sin;
    private final Cos cos;
    private final TrigonometricFunctions trigFunc;
    private final Ln ln;
    private final LogarithmicFunctions logFunc;
    private final double error = 0.001;

    public double solveSystem(double x) {
        if (x <= 0) {
            return negativeArg(x);
        }

        return positiveArg(x);
    }

    private double negativeArg(double x) {
        return ((sin.sin(x, error) + trigFunc.tan(x) - cos.cos(x, error)) / trigFunc.tan(x) + sin.sin(x, error))
                * (((trigFunc.sec(x) - cos.cos(x, error)) * sin.sin(x, error)) - trigFunc.cot(x));
    }

    private double positiveArg(double x) {
        return (Math.pow(ln.ln(x, error) / logFunc.log(x, 2, error), 3) - logFunc.log(x, 3, error)) -
                logFunc.log(x, 5, error) + (logFunc.log(x, 10, error) +
                (logFunc.log(x, 2, error) / ln.ln(x, error))) / logFunc.log(x, 2, error);
    }
}
