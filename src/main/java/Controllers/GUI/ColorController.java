package Controllers.GUI;

import Controllers.Domain.DomainController;
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
    private final ColorPicker colorPicker;
    private Color activeColor;
    private ArrayList<Button> colorHistoryButtons;

    private ColorController(AnchorPane colorPane, ColorPicker colorPicker){
        initializeColorHistoryButtons(colorPane);
        this.colorPicker = colorPicker;
    }

    private void initializeColorHistoryButtons(AnchorPane colorPane){
        colorHistoryButtons = new ArrayList<>();

        for(Node node : colorPane.getChildren()) {
            if (node instanceof Button button) {
                button.setVisible(false);
                button.setManaged(false);
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
        int COLORS_IN_HISTORY = 15;
        List<Color> colorHistory = domainController.getColorHistory(COLORS_IN_HISTORY);
        for (int i = 0; i < colorHistory.size(); i++) {
            String id = "color" + i;
            Color color = colorHistory.get(i);

            colorHistoryButtons.stream()
                    .filter(b -> id.equals(b.getId()))
                    .findFirst()
                    .ifPresent(button -> setButtonColor(button, color));
        }
    }

    public void changeActiveColorToHistory(Button button){
        activeColor = getButtonColor(button);
        colorPicker.setValue(activeColor);
    }

    private Color getButtonColor(Button button){
        return (Color) button.getBackground().getFills().getFirst().getFill();
    }

    private void setButtonColor(Button button, Color color){
        String hexColor = parseColorToHex(color);
        button.setStyle("-fx-background-color: " + hexColor + ";");
        button.setVisible(true);
        button.setManaged(true);
    }

    private String parseColorToHex(Color color){
        return String.format("#%02x%02x%02x",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public Color getActiveColor(){
        return activeColor;
    }
}
