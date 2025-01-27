package Domain;

import Domain.Records.DimensionRecord;
import Domain.Records.PixelChangeRecord;
import Domain.Records.PointRecord;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SaveFileData {
    public DimensionRecord canvasDimension;
    public HashMap<PointRecord, String> canvasPixels;
    public ArrayList<String> colorHistory;
    public ArrayList<PixelChangeRecord> undoList;
    public ArrayList<PixelChangeRecord> redoList;


    public SaveFileData(Project project){
        createDimensionRecord(project.getDimension());
        createCanvasPixels(project.getPixels());
        createColorHistory(project.getColorHistory());
        createUndoList(project.getUndoStack());
        createRedoList(project.getRedoStack());
    }

    private void createDimensionRecord(Dimension2D dimension){
        double width = dimension.getWidth();
        double height = dimension.getHeight();
        canvasDimension = new DimensionRecord(width, height);
    }

    private void createCanvasPixels(HashMap<Point2D, Color> pixels){
        canvasPixels = new HashMap<>();
        pixels.forEach((point, color)-> {
            PointRecord pointRecord = new PointRecord(point.getX(), point.getY());
            canvasPixels.put(pointRecord, color.toString());
        });
    }

    private void createColorHistory(ArrayList<Color> colors){
        colorHistory = new ArrayList<>();
        colors.forEach((color)-> colorHistory.add(color.toString()));
    }

    private void createUndoList(Stack<PixelChange> undoStack){
        this.undoList = new ArrayList<>();
        undoStack.forEach(pixelChange -> {
            PixelChangeRecord pixelChangeRecord = createPixelChangeRecord(pixelChange);
            undoList.add(pixelChangeRecord);
        });
    }

    private PixelChangeRecord createPixelChangeRecord(PixelChange pixelChange){
        double x = pixelChange.coordinates.getX();
        double y = pixelChange.coordinates.getY();
        PointRecord pointRecord = new PointRecord(x,y);

        String oldColorString = pixelChange.oldColor.toString();
        String newColorString = pixelChange.newColor.toString();

        return new PixelChangeRecord(pointRecord, oldColorString, newColorString);
    }

    private void createRedoList(Stack<PixelChange> redoStack){
        this.redoList = new ArrayList<>();
        redoStack.forEach(pixelChange -> {
            PixelChangeRecord pixelChangeRecord = createPixelChangeRecord(pixelChange);
            redoList.add(pixelChangeRecord);
        });
    }
}
