package com.p5zf2c46j;

public class Complex {

    double x;
    double y;

    public Complex() {
        this.x = 0;
        this.y = 0;
    }

    public Complex(double a) {
        this.x = a;
        this.y = 0;
    }

    public Complex(double a, double b) {
        this.x = a;
        this.y = b;
    }

    public Complex set(double a, double b) {
        x = a;
        y = b;
        return this;
    }

    public Complex set(Complex c) {
        x = c.x;
        y = c.y;
        return this;
    }

    public Complex copy() {
        return new Complex(x, y);
    }

    public double mag() {
        return mag(this);
    }

    public static double mag(Complex c) {
        return Math.sqrt(c.x * c.x + c.y * c.y);
    }

    public double magSqr() {
        return magSqr(this);
    }

    public static double magSqr(Complex c) {
        return c.x * c.x + c.y * c.y;
    }

    public double arg() {
        return arg(this);
    }

    public static double arg(Complex c) {
        return Math.atan2(c.y, c.x);
    }

    public Complex conjugate() {
        return conjugate(this);
    }

    public static Complex conjugate(Complex c) {
        return new Complex(c.x, -c.y);
    }

    public Complex add(double a) {
        x += a;
        return this;
    }

    public Complex add(double a, double b) {
        x += a;
        y += b;
        return this;
    }

    public Complex add(Complex c) {
        x += c.x;
        y += c.y;
        return this;
    }

    public static Complex add(Complex c, double a) {
        c.x += a;
        return c;
    }

    public static Complex add(Complex c, double a, double b) {
        c.x += a;
        c.y += b;
        return c;
    }

    public static Complex add(Complex a, Complex b) {
        return new Complex(a.x + b.x, a.y + b.y);
    }

    public Complex sub(double a) {
        x -= a;
        return this;
    }

    public Complex sub(double a, double b) {
        x -= a;
        y -= b;
        return this;
    }

    public Complex sub(Complex c) {
        x -= c.x;
        y -= c.y;
        return this;
    }

    public static Complex sub(Complex c, double a) {
        c.x -= a;
        return c;
    }

    public static Complex sub(Complex c, double a, double b) {
        c.x -= a;
        c.y -= b;
        return c;
    }

    public static Complex sub(Complex a, Complex b) {
        return new Complex(a.x - b.x, a.y - b.y);
    }

    public Complex mult(double a) {
        x *= a;
        y *= a;
        return this;
    }

    public Complex mult(double a, double b) {
        set(x * a - y * b, x * b + y * a);
        return this;
    }

    public Complex mult(Complex c) {
        set(x * c.x - y * c.y, x * c.y + y * c.x);
        return this;
    }

    public static Complex mult(Complex c, double a) {
        c.x *= a;
        c.y *= a;
        return c;
    }

    public static Complex mult(Complex c, double a, double b) {
        return new Complex(c.x * a - c.y * b, c.x * b + c.y * a);
    }

    public static Complex mult(Complex a, Complex b) {
        return new Complex(a.x * b.x - a.y * b.y, a.x * b.y + a.y * b.x);
    }

    public Complex div(double a) {
        x /= a;
        y /= a;
        return this;
    }

    public Complex div(double a, double b) {
        double re = (x * a + y * b) / (a * a + b * b);
        double im = (y * a - x * b) / (a * a + b * b);
        set(re, im);
        return this;
    }

    public Complex div(Complex c) {
        double re = (x * c.x + y * c.y) / (c.x * c.x + c.y * c.y);
        double im = (y * c.x - x * c.y) / (c.x * c.x + c.y * c.y);
        set(re, im);
        return this;
    }

    public static Complex div(Complex c, double a) {
        c.x /= a;
        c.y /= a;
        return c;
    }

    public static Complex div(Complex c, double a, double b) {
        double re = (c.x * a + c.y * b) / (a * a + b * b);
        double im = (c.y * a - c.x * b) / (a * a + b * b);
        return new Complex(re, im);
    }

    public static Complex div(Complex a, Complex b) {
        double re = (a.x * b.x + a.y * b.y) / (b.x * b.x + b.y * b.y);
        double im = (a.y * b.x - a.x * b.y) / (b.x * b.x + b.y * b.y);
        return new Complex(re, im);
    }

    public Complex pow(int power) {
        return set(pow(this, power));
    }

    public static Complex pow(Complex c, int power) {
        if (power == 0) {
            if (c.x == 0 && c.y == 0) {
                return new Complex(Double.NaN, Double.NaN);
            }
            return new Complex(1, 0);
        }

        Complex out = c.copy();
        for (int i = 1; i < Math.abs(power); i++) {
            double re = out.x * c.x - out.y * c.y;
            double im = out.x * c.y + out.y * c.x;
            out.set(re, im);
        }
        return power < 0 ? new Complex(1, 0).div(out) : out;
    }

    public Complex pow(double a) {
        return set(pow(this, a));
    }

    public static Complex pow(Complex c, double a) {
        double r = Math.pow(c.x * c.x + c.y * c.y, a * 0.5d);
        double theta = a * arg(c);
        double re = r * Math.cos(theta);
        double im = r * Math.sin(theta);
        c.set(re, im);
        return c;
    }

    public Complex pow(double a, double b) {
        return set(pow(this, a, b));
    }

    public Complex pow(Complex c) {
        return set(pow(this, c.x, c.y));
    }

    public static Complex pow(Complex c, double a, double b) {
        double r = mag(c);
        double theta = arg(c);
        double texp = a * theta + b * Math.log(r);
        double r2 = Math.exp(-b * theta);
        double re = r * r2 * Math.cos(texp);
        double im = r * r2 * Math.sin(texp);
        c.set(re, im);
        return c;
    }

    public static Complex pow(Complex a, Complex b) {
        return pow(a, b.x, b.y);
    }

    public static Complex sin(Complex c) {
        double q = Math.exp(c.y);
        double qi = 1 / q;
        double re = Math.sin(c.x) * (q + qi) / 2;
        double im = Math.cos(c.x) * (q - qi) / 2;
        return new Complex(re, im);
    }

    public static Complex sinh(Complex c) {
        return new Complex(Math.sinh(c.x) * Math.cos(c.y), Math.cosh(c.x) * Math.sin(c.y));
    }

    public Complex sqr() {
        set(x * x - y * y, 2 * x * y);
        return this;
    }

    public static Complex sqr(Complex c) {
        return new Complex(c.x * c.x - c.y * c.y, 2 * c.x * c.y);
    }

    public Complex sqrt() {
        return set(sqrt(this));
    }

    public static Complex sqrt(Complex c) {
        if (c.x == 0 && c.y == 0) {
            return new Complex();
        }
        if (c.x < 0 && c.y == 0) {
            return new Complex(0, Math.sqrt(Math.abs(c.x)));
        }
        double r = c.mag();
        Complex d = c.copy().add(r);
        d = div(d, mag(d)).mult(Math.sqrt(r));
        return d;
    }

    public static Complex exp(Complex c) {
        double r = Math.exp(c.x);
        return new Complex(r * Math.cos(c.y), r * Math.sin(c.y));
    }

    public static Complex cos(Complex c) {
        double q = Math.exp(c.y);
        double qi = 1 / q;
        double r = Math.cos(c.x) * (q + qi) / 2;
        double i = -Math.sin(c.x) * (q - qi) / 2;
        return new Complex(r, i);
    }

    public static Complex tan(Complex c) {
        return div(sin(c), cos(c));
    }

    public static Complex cot(Complex c) {
        return recip(tan(c));
    }

    public static Complex sec(Complex c) {
        return recip(cos(c));
    }

    public static Complex cosec(Complex c) {
        return recip(sin(c));
    }

    public Complex recip() {
        return set(recip(this));
    }

    public static Complex recip(Complex c) {
        return div(new Complex(1, 0), c);
    }
}
