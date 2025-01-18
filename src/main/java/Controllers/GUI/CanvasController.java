package Controllers.GUI;

import Controllers.DomainController;
import static Util.CanvasConversion.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.HashMap;

public class CanvasController {
    private static CanvasController instance = null;
    private final DomainController domainController = DomainController.getInstance();
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;
    private boolean isGridHidden = false;

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

    private void setDimensionCanvas(double width, double height){
        Dimension2D dimension = calculateCanvasDimension(width, height);
        canvas.setWidth(dimension.getWidth());
        canvas.setHeight(dimension.getHeight());
    }

    private void centerCanvas(){
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        Dimension2D layout = calculateCanvasLayout(width, height);

        canvas.setLayoutX(layout.getWidth());
        canvas.setLayoutY(layout.getHeight());
    }

    public void drawGrid(){
        graphicsContext.setStroke(Color.DARKGRAY);
        graphicsContext.setLineWidth(1);

        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double scale = getScale();

        for (double i = 1; i < width; i+=scale) {
            for (double j = 1; j < height; j+=scale) {
                // Start (x1, y1) and finish (x2, y2)
                graphicsContext.strokeLine(i, 1, i, height - 1);
                graphicsContext.strokeLine(1, j, width - 1, j);
            }
        }
    }

    public void modifyPixelColor(MouseEvent event, Color activeColor){
        Point2D canvasPixel = new Point2D(event.getX(), event.getY());
        Point2D pixel = canvasToPixel(canvasPixel);
        domainController.modifyPixelColor(pixel, activeColor);
        updateCanvas();
    }

    private void updateCanvas(){
        clearCanvas();
        HashMap<Point2D, Color> pixels = domainController.getPixels();
        pixels.forEach(this::drawPixel);

        if (!isGridHidden){
            drawGrid();
        }
    }

    private void clearCanvas(){
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawPixel(Point2D point, Color color){
        Point2D canvasPixel = pixelToCanvas(point);

        double width = getScale();
        double height = getScale();

        graphicsContext.setFill(color);
        graphicsContext.fillRect(canvasPixel.getX(), canvasPixel.getY(), width, height);
    }

    public void changeStateGrid(boolean isGridHidden){
        this.isGridHidden = isGridHidden;
        updateCanvas();
    }
}
