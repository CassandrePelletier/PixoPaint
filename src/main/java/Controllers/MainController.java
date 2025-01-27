package Controllers;

import Controllers.GUI.CanvasController;
import Controllers.GUI.ColorController;
import Controllers.GUI.ProjectController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private ProjectController projectController = null;

    public void initialize() {
        canvasController = CanvasController.getInstance(canvas);
        colorController = ColorController.getInstance(colorPane, colorPicker);
        projectController = ProjectController.getInstance();
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
        boolean isGridHidden = hideGridCheckBox.isSelected();
        canvasController.changeStateGrid(isGridHidden);
    }

    public void undo(){
        canvasController.undo();
    }

    public void redo(){
        canvasController.redo();
    }

    public void saveAll(){
        projectController.saveAll();
    }

    public void saveAs(){
        Stage stage = getStage();
        projectController.saveAs(stage);
    }

    private Stage getStage() {
        return (Stage) colorPane.getScene().getWindow();
    }

    public void openProject(){
        projectController.openProject();
    }

    public void createNewProject(){
        projectController.createNewProject();
    }

    public void redirectAbout(){
        projectController.redirectAbout();
    }
}
