package Domain;

import javafx.geometry.Dimension2D;

public class Project {
    public Canvas canvas;
    public ColorHistory colorHistory;

    public Project(Dimension2D canvasDimension){
        this.canvas = new Canvas(canvasDimension);
        this.colorHistory = new ColorHistory();
    }
}
