package com.ip13;

import com.ip13.functions.LogarithmicFunctions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class LogarithmicFunctionsTest {
    private final LogarithmicFunctions logFunc = new LogarithmicFunctions();
    private final double error = 0.001;

    @ParameterizedTest
    @CsvSource(value = {"10;10", "16;3", "123;5", "0.1;5", "10;0.1"}, delimiter = ';')
    public void logTest(String par1, String par2) {
        double x = Double.parseDouble(par1);
        double base = Double.parseDouble(par2);

        double expected = Math.log(x) / Math.log(base);
        double actual = logFunc.log(x, base, error);

        assertTrue((expected - actual) / expected < error);
    }

    @ParameterizedTest
    @CsvSource(value = {"-100;10", "-10;10", "-5;10", "-2;10", "-1;10", "0;10"}, delimiter = ';')
    void illegalArgTest(String par1, String par2) {
        double x = Double.parseDouble(par1);
        double base = Double.parseDouble(par2);
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> logFunc.log(x, base, error));
        assertEquals("log is not defined if base <= 0 or base == 1 or x <= 0", thrown.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"10;-100", "10;-10", "10;-5", "10;-2", "10;-1", "10;0", "10;1"}, delimiter = ';')
    void illegalBaseTest(String par1, String par2) {
        double x = Double.parseDouble(par1);
        double base = Double.parseDouble(par2);
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> logFunc.log(x, base, error));
        assertEquals("log is not defined if base <= 0 or base == 1 or x <= 0", thrown.getMessage());
    }
}
