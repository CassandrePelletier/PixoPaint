package Controllers.GUI;

import Controllers.Domain.DomainController;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProjectController {
    private static ProjectController instance = null;
    private final DomainController domainController = DomainController.getInstance();

    private ProjectController() {
    }

    public static ProjectController getInstance(){
        if (instance == null) {
            instance = new ProjectController();
        }
        return instance;
    }

    public void saveAll(){
        domainController.saveFile();
    }

    public void saveAs(Stage stage){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Directory");

        File initialDirectory = getDefaultDirectory();
        directoryChooser.setInitialDirectory(initialDirectory);

        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            domainController.setActiveDirectoryPath(selectedDirectory.getAbsolutePath());
        }
    }

    public File getDefaultDirectory(){
        return new File(System.getProperty("user.home"));
    }

    public void setDefaultDirectory(){
        File defaultDirectory = getDefaultDirectory();
        domainController.setActiveDirectoryPath(defaultDirectory.getAbsolutePath());
    }

    public void openProject(){

    }

    public void createNewProject(){

    }

    public void redirectAbout(){

    }
}
