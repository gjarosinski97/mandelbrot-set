package complex;


public interface Field<T> {
    // a.mul(a).add(b) --> a = a^2 + b
    T add(T x);

    T sub(T x);

    T mul(T x);

    T div(T x);

    String toString();
}