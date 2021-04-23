package mandelbrot;

import complex.Complex;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;

public class Controller {
    public Canvas canvas;                                        // "Płótno" do rysowania
    private GraphicsContext gc;                                    // Kontekst graficzny do "płótna"
    private double x1, y1, x2, y2;                                // Współrzędne ramki
    private Complex start = new Complex(-2.0, -1.25);
    private Complex end = new Complex(0.5, 1.25);
    public int w = 512;
    public int h = 512;
    public int r = 4;
    public TextField zakresStart;
    public TextField zakresEnd;
    public TextField parametrR;
    public TextField szerokoscW;
    public TextField wysokoscH;

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clear(gc);
    }

    private void clear(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void rect(GraphicsContext gc) {       // Metoda rysuje prostokat o rogach (x1, y1) i (x2, y2)
        double x = x1;
        double y = y1;
        double w = x2 - x1;
        double h = y2 - y1;

        if (w < 0) {
            x = x2;
            w = -w;
        }

        if (h < 0) {
            y = y2;
            h = -h;
        }

        gc.strokeRect(x + 0.5, y + 0.5, w, h);
    }

    public void mouseMoves(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        gc.setGlobalBlendMode(BlendMode.DIFFERENCE);
        gc.setStroke(Color.WHITE);
        rect(gc);
        x2 = x;
        y2 = y;
        rect(gc);
    }

    public void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
        x2 = x1;
        y2 = y1;
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        //odwracam wartości x1 i y1 jeśli rysuje prostokat od prawej strony lub od dołu
        if (x1 > x2) {
            double temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if (y1 > y2) {
            double temp = y1;
            y1 = y2;
            y2 = temp;
        }

        zoom(); //skaluje odpowiednio żuka
    }

    public void clearCanvas(ActionEvent actionEvent) {
        clear(gc);
        start = new Complex(-2.0, -1.25);
        end = new Complex(0.5, 1.25);
        r = 4;
        w = 512;
        h = 512;
    }

    public void draw() {
        clear(gc);
        WritableImage wr = new WritableImage(w, h);
        PixelWriter pw = wr.getPixelWriter();
        MandelFractal mandel = new MandelFractal();

        mandel.draw(pw, start, end, w, h, r);

        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, w, h);
    }

    private void zoom() {
        double startSkalowaneRe = start.re() + (x1 * ((end.re() - start.re()) / w));
        double startSkalowaneIm = start.im() + (y1 * ((end.im() - start.im()) / h));

        double endSkalowaneRe = end.re() - ((w - x2) * ((end.re() - start.re()) / w));
        double endSkalowaneIm = end.im() - ((h - y2) * ((end.im() - start.im()) / h));

        start.setVal(new Complex(startSkalowaneRe, startSkalowaneIm));
        end.setVal(new Complex(endSkalowaneRe, endSkalowaneIm));
        draw();
    }

    public void wczytajParametrR(ActionEvent actionEvent) {
        r = Integer.parseInt(parametrR.getText());
    }

    public void wczytajSzerokoscW(ActionEvent actionEvent) {
        w = Integer.parseInt(szerokoscW.getText());
    }

    public void wczytajWysokoscH(ActionEvent actionEvent) {
        h = Integer.parseInt(wysokoscH.getText());
    }

    public void wczytajZakresStart(ActionEvent actionEvent) {
        start = new Complex(zakresStart.getText());
    }

    public void wczytajZakresEnd() {
        end = new Complex(zakresEnd.getText());
    }
}