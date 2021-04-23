package mandelbrot;

import complex.Complex;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MandelFractal implements ComplexDrawable {

    @Override
    public void draw(PixelWriter pw, Complex start, Complex end, int w, int h, int r) {
        int maxIteracje = 100;

        Complex p = new Complex();
        Complex zn = new Complex();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                p.setVal(new Complex(start.re() + x * (end.re() - start.re()) / w,
                        start.im() + y * (end.im() - start.im()) / h));

//                p.setVal(new Complex(start.re() + (x / w) * (end.re() - start.re()),
//                        start.im() + (y / h) * (end.im() - start.im())));
                zn.setVal(p);

                int iteracje = 0;
                while (iteracje < maxIteracje) {
                    zn.mul(zn).add(p); // zn^2+p
                    if (zn.sqrAbs() >= r * r) {
                        break;
                    }
                    iteracje++;
                }
                //kolorowanie
                if (iteracje < maxIteracje) {
                    pw.setColor(x, y, Color.color(iteracje * 2.5 / 255, 0, iteracje / 255));
//                      pw.setArgb(x, y, (int) (0xff000000 + (0x00ff0000 + 4 * Math.sin(maxIteracje - iteracje*2) + (0xffff00ff + (maxIteracje * iteracje) * 13) + 0xffffff00 * (maxIteracje - iteracje))));
                } else
                    pw.setColor(x, y, Color.BLACK);
//                    pw.setArgb(x, y, 0xff000000);
            }
        }
    }
}