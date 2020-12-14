import Controller.ControllerAll;
import Controller.ControllerDeveloper;
import Controller.ControllerProductOwner;
import Controller.ControllerScrumMaster;


public class Main {


    public static void main(String[] args) {

        ControllerAll controllerAll = new ControllerAll();
        ControllerScrumMaster contScrum = new ControllerScrumMaster();
        ControllerProductOwner contProOwner = new ControllerProductOwner();
        ControllerDeveloper contDeveloper = new ControllerDeveloper();

        controllerAll.menuMain(controllerAll, contScrum, contProOwner, contDeveloper);

    }
}
