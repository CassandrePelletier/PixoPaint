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

        // Dessine une ligne : coordonnées de départ (x1, y1) et de fin (x2, y2)
        graphicsContext.strokeLine(0, 0, width, height);
    }
}
