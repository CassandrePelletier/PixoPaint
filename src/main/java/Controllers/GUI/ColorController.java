package Controllers.GUI;

import Controllers.DomainController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorController {
    private final DomainController domainController = DomainController.getInstance();
    private static ColorController instance = null;
    private ColorPicker colorPicker;
    private Color activeColor;
    private ArrayList<Button> colorHistoryButtons;
    private final int COLORS_IN_HISTORY = 15;

    private ColorController(AnchorPane colorPane, ColorPicker colorPicker){
        initializeColorHistoryButtons(colorPane);
        this.colorPicker = colorPicker;
    }

    private void initializeColorHistoryButtons(AnchorPane colorPane){
        colorHistoryButtons = new ArrayList<>();

        for(Node node : colorPane.getChildren()) {
            if (node instanceof Button button) {
                colorHistoryButtons.add(button);
            }
        }
    }

    public static ColorController getInstance(AnchorPane colorPane, ColorPicker colorPicker){
        if (instance == null) {
            instance = new ColorController(colorPane, colorPicker);
        }
        return instance;
    }

    public void changeActiveColor(){
        activeColor = colorPicker.getValue();
        domainController.addColorToHistory(activeColor);
        modifyColorHistory();
    }

    private void modifyColorHistory(){
        List<Color> colorHistory = domainController.getColorHistory(COLORS_IN_HISTORY);
        // TODO : update on gui
    }

    public Color getActiveColor(){
        return activeColor;
    }
}
