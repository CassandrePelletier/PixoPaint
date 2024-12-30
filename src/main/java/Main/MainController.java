package Main;

import Domain.DomainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;

public class MainController {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    private Color activeColor;
    private final DomainController domainController = DomainController.getInstance();
    private int colorsInHistory = 5;

    public void changeActiveColor(){
        activeColor = colorPicker.getValue();
        domainController.addColorToHistory(activeColor);
        modifyColorHistory();
    }

    private void setColorsInHistory(int n){
        colorsInHistory = n;
    }

    private void modifyColorHistory(){
        List<Color> colorHistory = domainController.getColorHistory(colorsInHistory);
        // TODO : update on gui
    }

    private void modifyPixelColor(Point2D coordinates){
        domainController.modifyPixelColor(coordinates, activeColor);
        updateCanvas();
    }

    private void updateCanvas(){
        HashMap<Point2D, Color> pixels = domainController.getPixels();
        // TODO : update on gui
    }
}
