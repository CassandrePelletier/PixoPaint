package Controllers.GUI;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    @FXML
    private CheckBox hideGridCheckBox;

    private CanvasController canvasController = null;
    private ColorController colorController = null;

    public void initialize() {
        canvasController = CanvasController.getInstance(canvas);
        colorController = ColorController.getInstance(colorPane, colorPicker);
        canvasController.initializeCanvas();
    }

    public void modifyPixelColor(MouseEvent event){
        Color activeColor = colorController.getActiveColor();
        canvasController.modifyPixelColor(event, activeColor);
    }

    public void changeActiveColor() {
        colorController.changeActiveColor();
    }

    public void changeActiveColorToHistory(MouseEvent event){
        Button clickedButton = (Button) event.getSource();
        colorController.changeActiveColorToHistory(clickedButton);
    }

    public void changeStateGrid(){

    }
}
