package Domain;

import java.awt.*;
import java.util.HashMap;
import java.util.Optional;

public class Canvas {
    private final Dimension dimension;
    private HashMap<Point,Color> pixels;

    public Canvas(Optional<Dimension> dimension){
        Dimension DEFAULT_DIMENSION = new Dimension(650,565);
        this.dimension = dimension.orElse(DEFAULT_DIMENSION);
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
