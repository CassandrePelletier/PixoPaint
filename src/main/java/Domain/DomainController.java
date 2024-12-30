package Domain;

public class DomainController {
    private static DomainController instance;
    private DomainController(){

    }

    public static DomainController getInstance(){
        if (instance == null) {
            instance = new DomainController();
        }
        return instance;
    }


}
