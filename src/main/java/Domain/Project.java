package Domain;

import java.awt.*;

public class Project {
    public Canvas canvas;
    public ColorHistory colorHistory;
    public Project(){
        Dimension DEFAULT_DIMENSION = new Dimension(650,565);
        this.canvas = new Canvas(DEFAULT_DIMENSION);
        this.colorHistory = new ColorHistory();
    }
}
