package Domain.Managers;

import Domain.PixelChange;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Stack;

public class UndoRedoManager {
    private final Stack<PixelChange> undoStack;
    private final Stack<PixelChange> redoStack;

    public UndoRedoManager(){
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void addPixelChange(Point2D coordinates, Color oldColor, Color newColor){
        PixelChange pixelChange = new PixelChange(coordinates, oldColor, newColor);
        undoStack.push(pixelChange);
        redoStack.clear();
    }

    public PixelChange undo(){
        if (!undoStack.isEmpty()) {
            PixelChange pixelChange = undoStack.pop();
            redoStack.push(pixelChange);
            return pixelChange;
        }
        return null;
    }

    public PixelChange redo(){
        if (!redoStack.isEmpty()){
            PixelChange pixelChange = redoStack.pop();
            undoStack.push(pixelChange);
            return pixelChange;
        }
        return null;
    }

    public Stack<PixelChange> getUndoStack() {
        return undoStack;
    }

    public Stack<PixelChange> getRedoStack() {
        return redoStack;
    }
}
