package Main;

import Domain.DomainController;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.abs;

public class CanvasController {
    private static CanvasController instance;
    private final DomainController domainController = DomainController.getInstance();
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private double scale;
    private final Dimension2D ALLOWED_CANVAS_PIXELS = new Dimension2D(650,565);
    private final Dimension2D INITIAL_CANVAS_LAYOUT = new Dimension2D(150,35);

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
        if ((pixelsAllowedInX - width) < (pixelsAllowedInY - height)){
            scale = (pixelsAllowedInX / width);
        } else {
            scale = (pixelsAllowedInY / height);
        }
    }

    private void setDimensionCanvas(double width, double height){
        // +2 for the outer lines
        double newWidth = width * scale + 2;
        double newHeight = height * scale + 2;

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
}
