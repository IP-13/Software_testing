package com.ip13;

import com.ip13.basicFunctions.Ln;
import com.ip13.basicFunctions.Sin;
import com.ip13.functions.Cos;
import com.ip13.functions.FunctionSystem;
import com.ip13.functions.LogarithmicFunctions;
import com.ip13.functions.TrigonometricFunctions;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

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
    private static double error = 0.001;

    static {
        procedures.put(FunctionSystem.class, (state) -> ((FunctionSystem) state.obj).writeCSV(state.x, state.writer, error));
        procedures.put(Ln.class, (state) -> ((Ln) state.obj).writeCSV(state.x, state.writer, error));
        procedures.put(LogarithmicFunctions.class, (state) -> ((LogarithmicFunctions) state.obj).writeCSV(state.x, state.writer, error));
        procedures.put(Sin.class, (state) -> ((Sin) state.obj).writeCSV(state.x, state.writer, error));
        procedures.put(Cos.class, (state) -> ((Cos) state.obj).writeCSV(state.x, state.writer, error));
        procedures.put(TrigonometricFunctions.class, (state) -> ((TrigonometricFunctions) state.obj).writeCSV(state.x, state.writer, error));
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

    public static void main(String[] args) {
        for (Class<?> curClass : procedures.keySet()) {
            String inPath = dirPathIn + curClass.getName() + "In.csv";
            String outPath = dirPathOut + curClass.getName() + "Out.csv";
            File f = new File(inPath);

            try (Scanner scanner = new Scanner(f); PrintWriter writer = new PrintWriter(outPath, StandardCharsets.UTF_8)) {
                scanner.useDelimiter(",");
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                int step = scanner.nextInt();
                error = Double.parseDouble(scanner.next());
                for (int x = start; x <= end; x++) {
                    procedures.get(curClass).accept(new State<>(objects.get(curClass), writer, ((double) x) / step));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}