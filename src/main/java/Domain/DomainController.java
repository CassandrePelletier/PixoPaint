package Domain;

import java.awt.*;

public class DomainController {
    private static DomainController instance;
    private Project project;

    private DomainController(){
        Dimension DEFAULT_DIMENSION = new Dimension(650,565);
        this.project = new Project(DEFAULT_DIMENSION);
    }

    public static DomainController getInstance(){
        if (instance == null) {
            instance = new DomainController();
        }
        return instance;
    }


}
