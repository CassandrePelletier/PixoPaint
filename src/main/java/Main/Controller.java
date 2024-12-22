package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
    @FXML
    private Rectangle grid;

    public void changeColor(ActionEvent e) {
        System.out.println("Button pressed");
        if (grid.getFill() == Color.AQUA)
            grid.setFill(Color.DARKGRAY);
        else {
            grid.setFill(Color.AQUA);
        }
    }
}
