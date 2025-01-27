package Domain;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorHistory {
    private final ArrayList<Color> colors;

    public ColorHistory(){
        colors = new ArrayList<>();
    }

    public void addColor(Color color){
        // If the color already exists, it will now be placed as the most recent
        colors.remove(color);
        colors.add(color);
    }

    public List<Color> getNColorHistory(int n){
        int startIndex = Math.max(0, colors.size() - n);
        return colors.subList(startIndex, colors.size()).reversed();
    }

    public ArrayList<Color> getColorHistory(){
        return colors;
    }
}
