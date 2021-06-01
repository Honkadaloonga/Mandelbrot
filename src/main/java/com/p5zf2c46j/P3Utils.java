package com.p5zf2c46j;

import static java.lang.Math.*;

public class P3Utils {

    public static int constrain(int i, int n, int m) {
        return min(max(i, n), m);
    }

    public static long constrain(long i, long n, long m) {
        return min(max(i, n), m);
    }

    public static float constrain(float i, float n, float m) {
        return min(max(i, n), m);
    }

    public static double constrain(double i, double n, double m) {
        return min(max(i, n), m);
    }

    public static float map(float v, float imin, float imax, float omin, float omax) {
        return omin + (omax - omin) * ((v - imin) / (imax - imin));
    }

    public static double map(double v, double imin, double imax, double omin, double omax) {
        return omin + (omax - omin) * ((v - imin) / (imax - imin));
    }

    public static float lerp(float v1, float v2, float fac) {
        return v1 + (v2 - v1) * fac;
    }

    public static double lerp(double v1, double v2, double fac) {
        return v1 + (v2 - v1) * fac;
    }

    public static float normalize(float v, float imin, float imax) {
        return (v - imin) / (imax - imin);
    }

    public static double normalize(double v, double imin, double imax) {
        return (v - imin) / (imax - imin);
    }

    public static String nf(int i, int a) {
        return String.format("%0"+a+"d", i);
    }

    public static String nf(float i, int a) {
        return String.format("%."+a+"f", i).replace(',', '.');
    }

    public static String nf(double i, int a) {
        return String.format("%."+a+"f", i).replace(',', '.');
    }

    public static String showBits(int i) {
        StringBuilder out = new StringBuilder();
        for (int j = 31; j >= 0; --j) {
            out.append((i >>> j) & 1);
        }
        return out.toString();
    }

    public static String showBits(long i) {
        StringBuilder out = new StringBuilder();
        for (int j = 63; j >= 0; --j) {
            out.append((i >>> j) & 1);
        }
        return out.toString();
    }

    public static String showBits(float i) {
        return showBits(Float.floatToIntBits(i));
    }

    public static String showBits(double i) {
        return showBits(Double.doubleToLongBits(i));
    }

    public static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n < 2 || n%2 == 0 || n%3 == 0)
            return false;
    
        int s = (int) sqrt(n);
        for (int i = 5; i <= s; i += 6)
            if (n%i == 0 || n%(i+2) == 0)
                return false;
    
        return true;
    }

    public static boolean isPrime(long n) {
        if (n == 2 || n == 3)
            return true;
        if (n < 2 || n%2 == 0 || n%3 == 0)
            return false;
    
        long s = (long) sqrt(n);
        for (long i = 5; i <= s; i += 6)
            if (n%i == 0 || n%(i+2) == 0)
                return false;
    
        return true;
    }
}