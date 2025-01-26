package Domain;

import Domain.Managers.UndoRedoManager;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;

public class Project {
    private final Canvas canvas;
    private final ColorHistory colorHistory;
    private final UndoRedoManager undoRedoManager;

    public Project(Dimension2D canvasDimension){
        this.canvas = new Canvas(canvasDimension);
        this.colorHistory = new ColorHistory();
        this.undoRedoManager = new UndoRedoManager();
    }

    // Color History
    public void addColorToHistory(Color color){
        colorHistory.addColor(color);
    }

    public List<Color> getColorHistory(int n){
        return colorHistory.getColorHistory(n);
    }

    // Pixels
    public void modifyPixelColor(Point2D coordinates, Color newColor){
        Color oldColor = canvas.getPixelColor(coordinates);

        undoRedoManager.addPixelChange(coordinates, oldColor, newColor);
        canvas.modifyPixelColor(coordinates, newColor);
    }

    public HashMap<Point2D, Color> getPixels(){
        return canvas.getPixels();
    }

    public Dimension2D getDimension(){
        return canvas.getDimension();
    }

    // Undo/Redo
    public void undo(){
        PixelChange pixelChange = undoRedoManager.undo();
        if (pixelChange != null) {
            canvas.modifyPixelColor(pixelChange.coordinates, pixelChange.oldColor);
        }
    }

    public void redo(){
        PixelChange pixelChange =  undoRedoManager.redo();
        if (pixelChange != null) {
            canvas.modifyPixelColor(pixelChange.coordinates, pixelChange.newColor);
        }
    }
}
