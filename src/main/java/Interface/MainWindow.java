package Interface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;

public class MainWindow extends Scene {
    Group root ;
    public MainWindow(Group root) {
        super(root);
        this.root = root;
        initializeChildren();
    }

    private void initializeChildren() {
        Text text = new Text();
        text.setText("Very nice");
        text.setX(100);
        text.setY(100);
        text.setFill(Color.DARKGRAY);
        root.getChildren().add(text);
    }
}
