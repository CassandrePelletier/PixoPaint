package Domain;

import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;
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

    public void addColorToHistory(Color color){
        project.colorHistory.addColor(color);
    }

    public List<Color> getColorHistory(int n){
        return project.colorHistory.getColorHistory(n);
    }
}
