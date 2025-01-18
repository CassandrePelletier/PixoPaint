package Domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class PixelChange {
    public Point2D coordinates;
    public Color oldColor;
    public Color newColor;

    public PixelChange(Point2D coordinates, Color oldColor, Color newColor){
        this.coordinates = coordinates;
        this.oldColor = oldColor;
        this.newColor = newColor;
    }
}
