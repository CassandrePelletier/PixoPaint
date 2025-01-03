package Main;

import Domain.DomainController;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;

import static java.lang.Math.floor;

public class CanvasController {
    private static CanvasController instance = null;
    private final DomainController domainController = DomainController.getInstance();
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private double scale;
    private final Dimension2D ALLOWED_CANVAS_PIXELS = new Dimension2D(565,565);
    private final Dimension2D INITIAL_CANVAS_LAYOUT = new Dimension2D(235,35);
    private final int BORDER_BUFFER = 1;

    private CanvasController(Canvas canvas){
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public static CanvasController getInstance(Canvas canvas){
        if (instance == null) {
            instance = new CanvasController(canvas);
        }
        return instance;
    }

    public void initializeCanvas(){
        Dimension2D dimensionsCanvas = domainController.getDimension();

        double width = dimensionsCanvas.getWidth();
        double height = dimensionsCanvas.getHeight();

        setScreenScale(width, height);
        setDimensionCanvas(width, height);
        centerCanvas();
        drawGrid();
    }

    private void setScreenScale(double width, double height){
        double pixelsAllowedInX = ALLOWED_CANVAS_PIXELS.getWidth();
        double pixelsAllowedInY = ALLOWED_CANVAS_PIXELS.getHeight();
        if (width > height){
            scale = (pixelsAllowedInX / width);
        } else {
            scale = (pixelsAllowedInY / height);
        }
    }

    private void setDimensionCanvas(double width, double height){
        double newWidth = width * scale + BORDER_BUFFER * 2;
        double newHeight = height * scale + BORDER_BUFFER * 2;

        canvas.setWidth(newWidth);
        canvas.setHeight(newHeight);
    }

    private void centerCanvas(){
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        double initialLayoutInX = INITIAL_CANVAS_LAYOUT.getWidth();
        double initialLayoutInY = INITIAL_CANVAS_LAYOUT.getHeight();
        double allowedPixelsInX =  ALLOWED_CANVAS_PIXELS.getWidth();
        double allowedPixelsInY =  ALLOWED_CANVAS_PIXELS.getHeight();

        double newLayoutInX = initialLayoutInX + (allowedPixelsInX - width) / 2;
        double newLayoutInY = initialLayoutInY + (allowedPixelsInY - height) / 2;

        canvas.setLayoutX(newLayoutInX);
        canvas.setLayoutY(newLayoutInY);
    }

    public void drawGrid(){
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(1);

        double width = canvas.getWidth();
        double height = canvas.getHeight();

        for (double i = 1; i <= width; i+=scale) {
            for (double j = 1; j <= height; j+=scale) {
                // Start (x1, y1) and finish (x2, y2)
                graphicsContext.strokeLine(i, 0, i, height);
                graphicsContext.strokeLine(0, j, width, j);
            }
        }
    }

    public void modifyPixelColor(MouseEvent event, Color activeColor){
        double pixelX = floor(event.getX() / scale);
        double pixelY = floor(event.getY() / scale);

        Point2D coordinates = new Point2D(pixelX, pixelY);
        domainController.modifyPixelColor(coordinates, activeColor);
        updateCanvas();
    }

    private void updateCanvas(){
        HashMap<Point2D, Color> pixels = domainController.getPixels();
        pixels.forEach(this::drawPixel);
        drawGrid();
    }

    private void drawPixel(Point2D point, Color color){
        graphicsContext.setFill(color);

        double x = point.getX() * scale + BORDER_BUFFER;
        double y = point.getY() * scale + BORDER_BUFFER;
        double width = scale;
        double height = scale;

        graphicsContext.fillRect(x, y, width, height);
    }
}
