package com.ip13;

import com.ip13.basicFunctions.Ln;
import com.ip13.functions.Cos;
import com.ip13.functions.FunctionSystem;
import com.ip13.functions.LogarithmicFunctions;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import com.ip13.basicFunctions.Sin;
import com.ip13.functions.TrigonometricFunctions;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class IntegrationTest {

    private static final double error = 0.000001;
    private static final double relError = 0.5;

    private static class Pair {
        public Pair(Object obj, CSVRecord record) {
            this.obj = obj;
            this.record = record;
        }

        public Object obj;
        public CSVRecord record;
    }

    private final static String dirPath = "/resources/com/ip13/CSVFiles/output/";
    private static final Map<Class<?>, Object> objects = new HashMap<>();

    static {
        objects.put(FunctionSystem.class, Mockito.mock(FunctionSystem.class));
        objects.put(Ln.class, Mockito.mock(Ln.class));
        objects.put(LogarithmicFunctions.class, Mockito.mock(LogarithmicFunctions.class));
        objects.put(Sin.class, Mockito.mock(Sin.class));
        objects.put(Cos.class, Mockito.mock(Cos.class));
        objects.put(TrigonometricFunctions.class, Mockito.mock(TrigonometricFunctions.class));
    }

    private static final Map<Class<?>, Consumer<Pair>> procedures = new HashMap<>();

    static {
        procedures.put(FunctionSystem.class, (pair) -> Mockito.when(((FunctionSystem) pair.obj).solveSystem(Double.parseDouble(pair.record.get(0)), error)).thenReturn(Double.parseDouble(pair.record.get(1))));
        procedures.put(Ln.class, (pair) -> Mockito.when(((Ln) pair.obj).ln(Double.parseDouble(pair.record.get(0)), error)).thenReturn(Double.parseDouble(pair.record.get(1))));
        procedures.put(LogarithmicFunctions.class, (pair) -> {
            Mockito.when(((LogarithmicFunctions) pair.obj).log(Double.parseDouble(pair.record.get(0)), Math.E, error)).thenReturn(Double.parseDouble(pair.record.get(1)));
            Mockito.when(((LogarithmicFunctions) pair.obj).log(Double.parseDouble(pair.record.get(0)), 2, error)).thenReturn(Double.parseDouble(pair.record.get(2)));
            Mockito.when(((LogarithmicFunctions) pair.obj).log(Double.parseDouble(pair.record.get(0)), 3, error)).thenReturn(Double.parseDouble(pair.record.get(3)));
            Mockito.when(((LogarithmicFunctions) pair.obj).log(Double.parseDouble(pair.record.get(0)), 5, error)).thenReturn(Double.parseDouble(pair.record.get(4)));
            Mockito.when(((LogarithmicFunctions) pair.obj).log(Double.parseDouble(pair.record.get(0)), 10, error)).thenReturn(Double.parseDouble(pair.record.get(5)));
        });
        procedures.put(Sin.class, (pair) -> {
            double x = Double.parseDouble(pair.record.get(0));
            Mockito.when(((Sin) pair.obj).sin(x, error)).thenReturn(Double.parseDouble(pair.record.get(1)));
        });
        procedures.put(Cos.class, (pair) -> Mockito.when(((Cos) pair.obj).cos(Double.parseDouble(pair.record.get(0)), error)).thenReturn(Double.parseDouble(pair.record.get(1))));
        procedures.put(TrigonometricFunctions.class, (pair) -> {
            Mockito.when(((TrigonometricFunctions) pair.obj).tan(Double.parseDouble(pair.record.get(0)), error)).thenReturn(Double.parseDouble(pair.record.get(1)));
            Mockito.when(((TrigonometricFunctions) pair.obj).sec(Double.parseDouble(pair.record.get(0)), error)).thenReturn(Double.parseDouble(pair.record.get(2)));
            Mockito.when(((TrigonometricFunctions) pair.obj).cot(Double.parseDouble(pair.record.get(0)), error)).thenReturn(Double.parseDouble(pair.record.get(3)));
        });
    }

    @BeforeAll
    public static void init() {
        CsvWriter writer = new CsvWriter();
        writer.generateTableValues();
        for (Class<?> mockClass : procedures.keySet()) {
            try (InputStream inputStream = IntegrationTest.class.getResourceAsStream(dirPath + mockClass.getName() + "Out.csv");
                 Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream))) {
                for (CSVRecord record : CSVFormat.DEFAULT.parse(reader)) {
                    procedures.get(mockClass).accept(new Pair(objects.get(mockClass), record));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/resources/com/ip13/CSVFiles/output/com.ip13.functions.FunctionSystemOut.csv")
    public void testFunctionWithMocks(double x, double f) {
        FunctionSystem function = new FunctionSystem((Sin) objects.get(Sin.class), (Cos) objects.get(Cos.class), (TrigonometricFunctions) objects.get(TrigonometricFunctions.class), (Ln) objects.get(Ln.class), (LogarithmicFunctions) objects.get(LogarithmicFunctions.class));
        if (Double.isNaN(f)) {
            Assertions.assertTrue(Double.isNaN(function.solveSystem(x, error)));
        } else if (Double.isInfinite(f)) {
            Assertions.assertTrue(Double.isInfinite(function.solveSystem(x, error)));
        } else {
            Assertions.assertTrue(Math.abs((f - function.solveSystem(x, error)) / f) < relError);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/resources/com/ip13/CSVFiles/output/com.ip13.functions.FunctionSystemOut.csv")
    public void testFunctionWithTrigon(double x, double f) {
        FunctionSystem function = new FunctionSystem((Sin) objects.get(Sin.class), (Cos) objects.get(Cos.class), new TrigonometricFunctions((Sin) objects.get(Sin.class), (Cos) objects.get(Cos.class)), (Ln) objects.get(Ln.class), (LogarithmicFunctions) objects.get(LogarithmicFunctions.class));
        if (Double.isNaN(f)) {
            Assertions.assertTrue(Double.isNaN(function.solveSystem(x, error)));
        } else if (Double.isInfinite(f)) {
            Assertions.assertTrue(Double.isInfinite(function.solveSystem(x, error)));
        } else {
            Assertions.assertTrue(Math.abs((f - function.solveSystem(x, error)) / f) < relError);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/resources/com/ip13/CSVFiles/output/com.ip13.functions.FunctionSystemOut.csv")
    public void testFunctionWithTrigonLog(double x, double f) {
        FunctionSystem function = new FunctionSystem((Sin) objects.get(Sin.class), (Cos) objects.get(Cos.class), new TrigonometricFunctions((Sin) objects.get(Sin.class), (Cos) objects.get(Cos.class)), (Ln) objects.get(Ln.class), new LogarithmicFunctions((Ln) objects.get(Ln.class)));
        if (Double.isNaN(f)) {
            Assertions.assertTrue(Double.isNaN(function.solveSystem(x, error)));
        } else if (Double.isInfinite(f)) {
            Assertions.assertTrue(Double.isInfinite(function.solveSystem(x, error)));
        } else {
            Assertions.assertTrue(Math.abs((f - function.solveSystem(x, error)) / f) < relError);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/resources/com/ip13/CSVFiles/output/com.ip13.functions.FunctionSystemOut.csv")
    public void testFunctionWithTrigonLogCos(double x, double f) {
        FunctionSystem function = new FunctionSystem((Sin) objects.get(Sin.class), new Cos((Sin) objects.get(Sin.class)), new TrigonometricFunctions((Sin) objects.get(Sin.class), new Cos((Sin) objects.get(Sin.class))), (Ln) objects.get(Ln.class), new LogarithmicFunctions((Ln) objects.get(Ln.class)));
        if (Double.isNaN(f)) {
            Assertions.assertTrue(Double.isNaN(function.solveSystem(x, error)));
        } else if (Double.isInfinite(f)) {
            Assertions.assertTrue(Double.isInfinite(function.solveSystem(x, error)));
        } else {
            Assertions.assertTrue(Math.abs((f - function.solveSystem(x, error)) / f) < relError);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/resources/com/ip13/CSVFiles/output/com.ip13.functions.FunctionSystemOut.csv")
    public void testFunctionWithTrigonLogCosSin(double x, double f) {
        FunctionSystem function = new FunctionSystem(new Sin(), new Cos(new Sin()), new TrigonometricFunctions(new Sin(), new Cos(new Sin())), (Ln) objects.get(Ln.class), new LogarithmicFunctions((Ln) objects.get(Ln.class)));
        if (Double.isNaN(f)) {
            Assertions.assertTrue(Double.isNaN(function.solveSystem(x, error)));
        } else if (Double.isInfinite(f)) {
            Assertions.assertTrue(Double.isInfinite(function.solveSystem(x, error)));
        } else {
            Assertions.assertTrue(Math.abs((f - function.solveSystem(x, error)) / f) < relError);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/resources/com/ip13/CSVFiles/output/com.ip13.functions.FunctionSystemOut.csv")
    public void testFunctionWithoutMocks(double x, double f) {
        FunctionSystem function = new FunctionSystem(new Sin(), new Cos(new Sin()), new TrigonometricFunctions(new Sin(), new Cos(new Sin())), new Ln(), new LogarithmicFunctions(new Ln()));
        if (Double.isNaN(f)) {
            Assertions.assertTrue(Double.isNaN(function.solveSystem(x, error)));
        } else if (Double.isInfinite(f)) {
            Assertions.assertTrue(Double.isInfinite(function.solveSystem(x, error)));
        } else {
            Assertions.assertTrue(Math.abs((f - function.solveSystem(x, error)) / f) < relError);
        }
    }
}