package Domain;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import java.util.HashMap;

public class Canvas {
    private final Dimension2D dimension;
    private HashMap<Point2D,Color> pixels;

    public Canvas(Dimension2D dimension){
        this.dimension = dimension ;
        this.pixels = new HashMap<>();
        initializePixels();
    }

    private void initializePixels(){
        for (int i = 0; i < dimension.getWidth(); i++) {
            for (int j = 0; j < dimension.getHeight(); j++) {
                Point2D coordinates = new Point2D(i,j);
                this.pixels.put(coordinates, Color.WHITE);
            }
        }
    }

    public HashMap<Point2D,Color> getPixels(){
        return pixels;
    }

    public void modifyPixelColor(Point2D coordinates, Color color){
        pixels.put(coordinates, color);
    }
}
