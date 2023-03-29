package com.ip13;

import com.ip13.basicFucntions.Ln;
import com.ip13.functions.LogarithmicFunctions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogarithmicFunctionsTest {
    @ParameterizedTest
    @CsvSource(value = {"10;10", "16;3", "123;5", "0.1;5", "10;0.1"}, delimiter = ';')
    public void logTest(String par1, String par2) {
        LogarithmicFunctions logarithmicFunctions = new LogarithmicFunctions(new Ln());
        double error = 0.001;

        double x = Double.parseDouble(par1);
        double base = Double.parseDouble(par2);

        double expected = Math.log(x) / Math.log(base);
        double actual = logarithmicFunctions.log(x, base, error);

        assertTrue((expected - actual) / expected < 0.001);
    }
}
