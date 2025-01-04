package Controllers.GUI;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MainController {
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane colorPane;
    @FXML
    private ColorPicker colorPicker;

    private CanvasController canvasController = null;
    private ColorController colorController = null;

    @FXML
    public void initialize() {
        canvasController = CanvasController.getInstance(canvas);
        colorController = ColorController.getInstance(colorPane, colorPicker);

        canvasController.initializeCanvas();
    }

    @FXML
    public void modifyPixelColor(MouseEvent event){
        Color activeColor = colorController.getActiveColor();
        canvasController.modifyPixelColor(event, activeColor);
    }

    public void changeActiveColor() {
        colorController.changeActiveColor();
    }
}
