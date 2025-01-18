package Util;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public record PixelChange(Point2D point, Color oldColor, Color newColor) {
}
