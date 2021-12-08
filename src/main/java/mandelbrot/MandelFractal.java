package mandelbrot;

import complex.Complex;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MandelFractal implements ComplexDrawable {

    @Override
    public void draw(PixelWriter pixelWriter, Complex start, Complex end, int w, int h, int r) {
        var numberOfIterations = 100;

        var p = new Complex();
        var zn = new Complex();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                p.setVal(new Complex(start.re() + x * (end.re() - start.re()) / w,
                        start.im() + y * (end.im() - start.im()) / h));

//                p.setVal(new Complex(start.re() + (x / w) * (end.re() - start.re()),
//                        start.im() + (y / h) * (end.im() - start.im())));
                zn.setVal(p);

                int iterations = 0;
                while (iterations < numberOfIterations) {
                    zn.mul(zn).add(p); // zn^2+p
                    if (zn.sqrAbs() >= r * r) {
                        break;
                    }
                    iterations++;
                }
                //kolorowanie
                if (iterations < numberOfIterations) {
                    pixelWriter.setColor(x, y, Color.color(iterations * 2.5 / 255, 0, iterations / 255));
//                      pixelWriter.setArgb(x, y, (int) (0xff000000 + (0x00ff0000 + 4 * Math.sin(numberOfIterations - iterations*2) + (0xffff00ff + (numberOfIterations * iterations) * 13) + 0xffffff00 * (numberOfIterations - iterations))));
                } else
                    pixelWriter.setColor(x, y, Color.BLACK);
//                    pw.setArgb(x, y, 0xff000000);
            }
        }
    }
}