package com.ip13;

import com.ip13.basicFunctions.Ln;
import com.ip13.basicFunctions.Sin;
import com.ip13.functions.Cos;
import com.ip13.functions.FunctionSystem;
import com.ip13.functions.LogarithmicFunctions;
import com.ip13.functions.TrigonometricFunctions;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

import static java.lang.Double.NaN;

public class CsvWriter {

    private static final class State<T> {
        T obj;
        PrintWriter writer;
        double x;

        public State(T obj, PrintWriter writer, double x) {
            this.obj = obj;
            this.writer = writer;
            this.x = x;
        }
    }

    private static final String dirPathIn = "/home/vsevolod/IdeaProjects/Software_testing/software_testing2/src/main/resources/com/ip13/CSVFiles/input/";
    private static final String dirPathOut = "/home/vsevolod/IdeaProjects/Software_testing/software_testing2/src/main/resources/com/ip13/CSVFiles/output/";
    private static final Map<Class<?>, Consumer<State<?>>> procedures = new HashMap<>();

    static {
        procedures.put(FunctionSystem.class, (state) -> writeCSVFunc(state.x, state.writer));
        procedures.put(Ln.class, (state) -> writeCSVLn(state.x, state.writer));
        procedures.put(LogarithmicFunctions.class, (state) -> writeCSVLog(state.x, state.writer));
        procedures.put(Sin.class, (state) -> writeCSVSin(state.x, state.writer));
        procedures.put(Cos.class, (state) -> writeCSVCos(state.x, state.writer));
        procedures.put(TrigonometricFunctions.class, (state) -> writeCSVTrig(state.x, state.writer));
    }

    private static final Map<Class<?>, Object> objects = new HashMap<>();

    static {
        objects.put(FunctionSystem.class, new FunctionSystem());
        objects.put(Ln.class, new Ln());
        objects.put(LogarithmicFunctions.class, new LogarithmicFunctions());
        objects.put(Sin.class, new Sin());
        objects.put(Cos.class, new Cos());
        objects.put(TrigonometricFunctions.class, new TrigonometricFunctions());
    }

    public static void writeCSVLn(double x, Writer out) {
        double res;
        try {
            res = Math.log(x);
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

    public static void writeCSVSin(double x, Writer out) {
        double res = Math.sin(x);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCSVCos(double x, Writer out) {
        double res = Math.cos(x);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double log(double x, double base) {
        return Math.log(x) / Math.log(base);
    }

    private static double myFunc(double x) {
        return (x <= 0) ? (((Math.sin(x) + Math.tan(x) - Math.cos(x)) / Math.tan(x) + Math.sin(x)) * (((1 / Math.cos(x)
                - Math.cos(x)) * Math.sin(x)) - 1 / Math.tan(x)))
                : (Math.pow(Math.log(x) / log(x, 2), 3) - log(x, 3)) - log(x, 5) + (Math.log10(x)
                + (log(x, 2) / Math.log(x))) / log(x, 2);
    }

    public static void writeCSVFunc(double x, PrintWriter out) {
        double res;
        try {
            res = myFunc(x);
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

    public static void writeCSVLog(double x, PrintWriter out) {
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            try {
                printer.printRecord(x, Math.log(x), log(x, 2), log(x, 3), log(x, 5), log(x, 10));
            } catch (IllegalArgumentException e) {
                printer.printRecord(x, NaN, NaN, NaN, NaN, NaN);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCSVTrig(double x, PrintWriter out) {
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            try {
                printer.printRecord(x, Math.tan(x), 1/Math.cos(x), 1/Math.tan(x));
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("cot is not defined")) {
                    printer.printRecord(x, Math.tan(x), 1/Math.cos(x), NaN);
                } else {
                    printer.printRecord(x, NaN, NaN, 1/Math.tan(x));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generateTableValues() {
        for (Class<?> curClass : procedures.keySet()) {
            String inPath = dirPathIn + curClass.getName() + "In.csv";
            String outPath = dirPathOut + curClass.getName() + "Out.csv";
            File f = new File(inPath);

            try (Scanner scanner = new Scanner(f); PrintWriter writer = new PrintWriter(outPath, StandardCharsets.UTF_8)) {
                scanner.useDelimiter(",");
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                int step = scanner.nextInt();
                for (int x = start; x <= end; x++) {
                    procedures.get(curClass).accept(new State<>(objects.get(curClass), writer, ((double) x) / step));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}