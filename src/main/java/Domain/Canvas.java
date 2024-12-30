package Domain;

import java.awt.*;
import java.util.HashMap;

public class Canvas {
    private final Dimension dimension;
    private HashMap<Point,Color> pixels;

    public Canvas(Dimension dimension){
        this.dimension = dimension ;
        this.pixels = new HashMap<>();
        initializePixels();
    }

    private void initializePixels(){
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                Point coordinates = new Point(i,j);
                this.pixels.put(coordinates, Color.WHITE);
            }
        }
    }

    public HashMap<Point,Color> getPixels(){
        return pixels;
    }

    public void modifyPixelColor(Point coordinates, Color color){
        pixels.put(coordinates, color);
    }
}
