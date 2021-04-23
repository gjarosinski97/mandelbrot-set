package complex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex implements Field<Complex> {
    private double r, i;

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

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

    // .*? - dzieki ? .* nie jest zachlanne
    public Complex(String complex) {
        Matcher m = Pattern.compile("(-?.*?)([+-].*?)i").matcher(complex);
        if (m.matches()) {
            r = Double.parseDouble(m.group(1));
            i = Double.parseDouble(m.group(2));
        }
    }

    /* Poniższe metody modyfikują aktualny obiekt i zwracają 'this' */
    //Dodawanie
    public Complex add(Complex complex) {
        this.r = this.r + complex.r;
        this.i = this.i + complex.i;
        return this;
    }

    //Odejmowanie
    public Complex sub(Complex complex) {
        double re = this.r - complex.r;
        double im = this.i - complex.i;
        this.r = re;
        this.i = im;
        return this;
    }

    //Mnożenie
    public Complex mul(Complex complex) {
        double re = this.r * complex.r - this.i * complex.i;
        double im = this.r * complex.i + this.i * complex.r;
        this.r = re;
        this.i = im;
        return this;
    }

    //Dzielenie
    public Complex div(Complex complex) {
        if ((complex.r * complex.r + complex.i * complex.i) == 0) {
            throw new ArithmeticException("Nie można dzielić przez 0"); //dziala
        } else {
            double re = (this.r * complex.r + this.i * complex.i) / (complex.r * complex.r + complex.i * complex.i);
            double im = (this.i * complex.r - this.r * complex.i) / (complex.r * complex.r + complex.i * complex.i);
            this.r = re;
            this.i = im;
            return this;
        }
    }

    //Moduł
    public double abs() {
        return Math.sqrt(this.r * this.r + this.i * this.i);
    }

    //Kwadrat modułu
    public double sqrAbs() {
        return this.r * this.r + this.i * this.i;
    }

    //Faza
    double phase() {
        return Math.atan2(this.i, this.r);
    }

    //Część rzeczywista
    public double re() {
        return this.r;
    }

    //Część urojona
    public double im() {
        return this.i;
    }

    /* Metody statyczne dla powyższych operacji */

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

    /* Dodatkowe metody */

    public String toString() {
    /* Zwraca String z zapisaną
        liczbą zespoloną formacie "-1.23+4.56i" */
        if (i == 0.0) return round(r, 2) + "";
        if (r == 0.0) return round(i, 2) + "i";
        if (i < 0.0) return round(r, 2) + "-" + (-(round(i, 2))) + "i";
        return round(r, 2) + "+" + round(i, 2) + "i";
    }

    /* Zwraca String z zapisaną
    liczbą zespoloną formacie "-1.23+4.56i" */
    static Complex valueOf(String complex) {
        return new Complex(complex);
    }

    /* Przypisuje podaną wartość części rzeczywistej */
    public void setRe(double r) {
        this.r = r;
    }

    /* Przypisuje podaną wartość części urojonej */
    public void setIm(double i) {
        this.i = i;
    }

    public void setVal(Complex complex) {
        this.r = complex.r;
        this.i = complex.i;
    }

    /* Przypisuje podaną wartość */
    void setVal(double r, double i) {
        this.r = r;
        this.i = i;
    }

}