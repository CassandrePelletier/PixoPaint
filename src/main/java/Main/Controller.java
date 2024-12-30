package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    private Color activeColor;

    public void changeColor(ActionEvent e) {
        activeColor = colorPicker.getValue();
        System.out.println("Active Color changed : " + activeColor);
    }
}
