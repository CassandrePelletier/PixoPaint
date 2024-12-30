package Domain;

import java.awt.*;

public class Project {
    public Canvas canvas;
    public ColorHistory colorHistory;

    public Project(Dimension canvasDimension){
        this.canvas = new Canvas(canvasDimension);
        this.colorHistory = new ColorHistory();
    }
}
