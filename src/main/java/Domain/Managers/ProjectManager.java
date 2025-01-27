package Domain.Managers;

import Domain.Project;
import Domain.SaveFileData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class ProjectManager {
    private static ProjectManager instance = null;
    String activeDirectoryPath;
    Project activeProject;
    Gson saveFile = null;

    private ProjectManager(Project activeProject){
        this.activeProject = activeProject;
    }

    public static ProjectManager getInstance(Project activeProject){
        if (instance == null) {
            instance = new ProjectManager(activeProject);
        }
        return instance;
    }

    public void setActiveDirectoryPath(String activeDirectoryPath) {
        this.activeDirectoryPath = activeDirectoryPath + "\\PixoPaint_Save.json";
        System.out.println("Project will be saved at: " + this.activeDirectoryPath);
    }

    public void setActiveProject(Project project){
        this.activeProject = project;
    }

    public void saveProject(){
        if (saveFile == null) {
            createSaveFile();
        }
        try (FileWriter writer = new FileWriter(activeDirectoryPath)) {
            SaveFileData saveFileData = new SaveFileData(activeProject);
            saveFile.toJson(saveFileData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSaveFile(){
        saveFile = new GsonBuilder().setPrettyPrinting().create();
    }
}
