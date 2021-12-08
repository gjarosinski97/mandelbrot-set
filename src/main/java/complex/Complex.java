package complex;

import java.util.regex.Pattern;

public class Complex implements Field<Complex> {
    private double r;
    private double i;

    public Complex() {
        this.r = 0.0;
        this.i = 0.0;
    }

    public Complex(double r) {
        this.r = r;
        this.i = 0.0;
    }

    public Complex(double r, double i) {
        this.r = r;
        this.i = i;
    }

    public Complex(Complex complex) {
        this.r = complex.r;
        this.i = complex.i;
    }

    public Complex(String complex) {
        var matcher = Pattern.compile("(-?.*?)([+-].*?)i").matcher(complex);
        if (matcher.matches()) {
            r = Double.parseDouble(matcher.group(1));
            i = Double.parseDouble(matcher.group(2));
        }
    }

    public Complex add(Complex complex) {
        this.r = this.r + complex.r;
        this.i = this.i + complex.i;
        return this;
    }

    public Complex sub(Complex complex) {
        double re = this.r - complex.r;
        double im = this.i - complex.i;
        this.r = re;
        this.i = im;
        return this;
    }

    public Complex mul(Complex complex) {
        double re = this.r * complex.r - this.i * complex.i;
        double im = this.r * complex.i + this.i * complex.r;
        this.r = re;
        this.i = im;
        return this;
    }

    public Complex div(Complex complex) {
        if ((complex.r * complex.r + complex.i * complex.i) == 0) {
            throw new ArithmeticException("Nie można dzielić przez 0");
        } else {
            double re = (this.r * complex.r + this.i * complex.i) / (complex.r * complex.r + complex.i * complex.i);
            double im = (this.i * complex.r - this.r * complex.i) / (complex.r * complex.r + complex.i * complex.i);
            this.r = re;
            this.i = im;
            return this;
        }
    }

    public double abs() {
        return Math.sqrt(this.r * this.r + this.i * this.i);
    }

    public double sqrAbs() {
        return this.r * this.r + this.i * this.i;
    }

    double phase() {
        return Math.atan2(this.i, this.r);
    }

    public double re() {
        return this.r;
    }

    public double im() {
        return this.i;
    }

    public static Complex add(Complex complex1, Complex complex2) {
        double re = complex1.r + complex2.r;
        double im = complex1.i + complex2.i;
        return new Complex(re, im);
    }

    static Complex sub(Complex complex1, Complex complex2) {
        double re = complex1.r - complex2.r;
        double im = complex1.i - complex2.i;
        return new Complex(re, im);
    }

    static Complex mul(Complex complex1, Complex complex2) {
        double re = complex1.r * complex2.r - complex1.i * complex2.i;
        double im = complex1.r * complex2.i + complex1.i * complex2.r;
        return new Complex(re, im);
    }

    static Complex div(Complex complex1, Complex complex2) {
        if ((complex2.r * complex2.r + complex2.i * complex2.i) == 0) {
            throw new ArithmeticException("Nie można dzielić przez 0");
        } else {
            double re = (complex1.r * complex2.r + complex1.i * complex2.i) / (complex2.r * complex2.r + complex2.i * complex2.i);
            double im = (complex1.i * complex2.r - complex1.r * complex2.i) / (complex2.r * complex2.r + complex2.i * complex2.i);
            return new Complex(re, im);
        }
    }

    static double abs(Complex complex) {
        return Math.sqrt(complex.r * complex.r + complex.i * complex.i);
    }

    public static double sqrAbs(Complex complex) {
        return complex.r * complex.r + complex.i * complex.i;
    }

    static double phase(Complex complex) {
        return Math.atan2(complex.i, complex.r);
    }

    static double re(Complex complex) {
        return complex.r;
    }

    static double im(Complex complex) {
        return complex.i;
    }


    public String toString() {
        if (i == 0.0) return round(r, 2) + "";
        if (r == 0.0) return round(i, 2) + "i";
        if (i < 0.0) return round(r, 2) + "-" + (-(round(i, 2))) + "i";
        return round(r, 2) + "+" + round(i, 2) + "i";
    }

    static Complex valueOf(String complex) {
        return new Complex(complex);
    }

    public void setRe(double r) {
        this.r = r;
    }

    public void setIm(double i) {
        this.i = i;
    }

    public void setVal(Complex complex) {
        this.r = complex.r;
        this.i = complex.i;
    }

    void setVal(double r, double i) {
        this.r = r;
        this.i = i;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}