package Main;

import Interface.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // stage.getIcons().add(new Image("icon.png"));
        stage.setFullScreen(true);
        stage.setScene(new MainWindow());
        stage.setTitle("PixoPaint");
        stage.show();
    }
}