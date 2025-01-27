package Controllers.Domain;

import Domain.Managers.ProjectManager;
import Domain.Project;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;

public class DomainController {
    private static DomainController instance = null;
    private ProjectManager projectManager = null;
    private Project project;

    private DomainController(){
        // Hardcoded
        Dimension2D DEFAULT_DIMENSION = new Dimension2D(25,25);
        project = new Project(DEFAULT_DIMENSION);

        projectManager = ProjectManager.getInstance(project);
    }

    public static DomainController getInstance(){
        if (instance == null) {
            instance = new DomainController();
        }
        return instance;
    }

    public void createNewProject(Dimension2D dimension){
        project = new Project(dimension);
    }

    // Color History
    public void addColorToHistory(Color color){
        project.addColorToHistory(color);
    }

    public List<Color> getNColorHistory(int n){
        return project.getNColorHistory(n);
    }

    // Pixels
    public void modifyPixelColor(Point2D coordinates, Color newColor){
        project.modifyPixelColor(coordinates, newColor);
    }

    public HashMap<Point2D, Color> getPixels(){
        return project.getPixels();
    }

    public Dimension2D getDimension(){
        return project.getDimension();
    }

    // Undo/Redo
    public void undo(){
        project.undo();
    }

    public void redo(){
        project.redo();
    }

    // Save File
    public void saveFile(){
        projectManager.saveProject();
    }

    public void setActiveDirectoryPath(String activeDirectoryPath) {
        projectManager.setActiveDirectoryPath(activeDirectoryPath);
    }
}
