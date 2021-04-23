module mandelbrot.set {
    requires javafx.controls;
    requires javafx.fxml;

    opens mandelbrot to javafx.fxml;
    exports mandelbrot;
}