package mandelbrot;

import javafx.scene.image.PixelWriter;
import complex.Complex;

interface ComplexDrawable {
    void draw(PixelWriter pixelWriter, Complex a, Complex b, int w, int h, int r);
}