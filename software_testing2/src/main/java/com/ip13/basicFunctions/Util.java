package com.ip13.basicFunctions;

import java.math.BigInteger;

public class Util {
    public static BigInteger fact(int n) {
        BigInteger result = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

    public static boolean is_equal(double x, double y, double error) {
        return Math.abs(x - y) < error;
    }
}