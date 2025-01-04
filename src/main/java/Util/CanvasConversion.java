package Util;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import static java.lang.Math.floor;

public class CanvasConversion {
    private static double scale;
    private static final Dimension2D ALLOWED_CANVAS_PIXELS = new Dimension2D(565,565);
    private static final Dimension2D INITIAL_CANVAS_LAYOUT = new Dimension2D(235,35);
    private static final int BORDER_BUFFER = 1;

    public static void setScreenScale(double width, double height){
        double pixelsAllowedInX = ALLOWED_CANVAS_PIXELS.getWidth();
        double pixelsAllowedInY = ALLOWED_CANVAS_PIXELS.getHeight();
        if (width > height){
            scale = (pixelsAllowedInX / width);
        } else {
            scale = (pixelsAllowedInY / height);
        }
    }

    public static Dimension2D calculateCanvasDimension(double width, double height) {
        double newWidth = width * scale + BORDER_BUFFER * 2;
        double newHeight = height * scale + BORDER_BUFFER * 2;
        return new Dimension2D(newWidth, newHeight);
    }

    public static Dimension2D calculateCanvasLayout(double width, double height){
        double initialLayoutInX = INITIAL_CANVAS_LAYOUT.getWidth();
        double initialLayoutInY = INITIAL_CANVAS_LAYOUT.getHeight();
        double allowedPixelsInX =  ALLOWED_CANVAS_PIXELS.getWidth();
        double allowedPixelsInY =  ALLOWED_CANVAS_PIXELS.getHeight();

        double newLayoutInX = initialLayoutInX + (allowedPixelsInX - width) / 2;
        double newLayoutInY = initialLayoutInY + (allowedPixelsInY - height) / 2;

        return new Dimension2D(newLayoutInX, newLayoutInY);
    }

    public static Point2D canvasToPixel(Point2D canvasPixel){
        double pixelX = floor(canvasPixel.getX() / scale);
        double pixelY = floor(canvasPixel.getY() / scale);
        return new Point2D(pixelX, pixelY);
    }

    public static Point2D pixelToCanvas(Point2D pixel){
        double x = pixel.getX() * scale + BORDER_BUFFER;
        double y = pixel.getY() * scale + BORDER_BUFFER;
        return new Point2D(x,y);
    }

    public static double getScale(){
        return scale;
    }
}
