package complex;


public interface Field<T> {
    // Poniższe metody modyfikują aktualny obiekt i zwracają "this".
    // Taka konstrukcja pozwala na łańcuchowe łączenie operacji. Np.
    // a.mul(a).add(b) --> a = a^2 + b
    T add(T x);          // dodawanie

    T sub(T x);          // odejmowanie

    T mul(T x);          // mnożenie

    T div(T x);          // dzielenie

    String toString(); // zwraca napis reprezentujący liczbę
}