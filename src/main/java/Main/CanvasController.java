package Main;

import Domain.DomainController;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasController {
    private static CanvasController instance;
    private final DomainController domainController = DomainController.getInstance();
    private GraphicsContext graphicsContext;

    private CanvasController(Canvas canvas){
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public static CanvasController getInstance(Canvas canvas){
        if (instance == null) {
            instance = new CanvasController(canvas);
        }
        return instance;
    }

    public void initializeCanvas(){
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(1);

        Dimension2D dimensionsCanvas = domainController.getDimension();

        int width = (int)dimensionsCanvas.getWidth();
        int height = (int)dimensionsCanvas.getHeight();

        initializeGrid(width, height);
    }

    public void initializeGrid(int width, int height){
        // +10 to be able to see individual lines (temporary)
        // TODO : zoom and scale-to-screen with fewer pixels
        for (int i = 0; i <= width; i+=10) {
            for (int j = 0; j <= height; j+=10) {
                // Start (x1, y1) and finish (x2, y2)
                graphicsContext.strokeLine(i, 0, i, height);
                graphicsContext.strokeLine(0, j, width, j);
            }
        }
    }
}
