package Main;

import Domain.DomainController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.List;

public class MainController {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    private Color activeColor;
    private final DomainController domainController = DomainController.getInstance();
    private CanvasController canvasController = null;
    private int colorsInHistory = 5;

    @FXML
    public void initialize() {
        canvasController = CanvasController.getInstance(canvas);
        canvasController.initializeCanvas();
    }

    @FXML
    public void modifyPixelColor(MouseEvent event){
        canvasController.modifyPixelColor(event, activeColor);
    }

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
}
