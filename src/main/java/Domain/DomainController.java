package Domain;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;

public class DomainController {
    private static DomainController instance;
    private Project project;

    private DomainController(){
        Dimension2D DEFAULT_DIMENSION = new Dimension2D(650,565);
        this.project = new Project(DEFAULT_DIMENSION);
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
        project.colorHistory.addColor(color);
    }

    public List<Color> getColorHistory(int n){
        return project.colorHistory.getColorHistory(n);
    }

    // Pixels
    public void modifyPixelColor(Point2D coordinates, Color color){
        project.canvas.modifyPixelColor(coordinates, color);
    }

    public HashMap<Point2D, Color> getPixels(){
        return project.canvas.getPixels();
    }

    public Dimension2D getDimension(){
        return project.canvas.getDimension();
    }
}
