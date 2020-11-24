package View;

import Controller.Controller;
import Controller.ControllerBacklog;
import Utility.Scan;
import java.util.Scanner;

public class MainMenuView {

    //Controller for backlog operations as an object
    private ControllerBacklog controllerBacklog = new ControllerBacklog();

    // ProductOwnerView as an object
    private ProductOwnerView viewProductOwner = new ProductOwnerView();

    //Controller for ScrumMaster operations as an object
    private Controller controller = new Controller();


    public void mainMenu() {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        Scan.print("Welcome to Codelicode, your project management tool.\nPlease enter a option below\n" +
                "1. Scrum master\n" +
                "2. Product owner\n" +
                "3. Development team member\n");


        int option = input.nextInt();

        do {
            switch (option) {
                case 1:
                    controller.menuScrumMaster();
                    break;
                case 2:
                    controllerBacklog.backlogMenu();
                    break;
                case 3:
                    // MenuTeamMember.menuTeamMember();
                    break;
                default: viewProductOwner.defaultMessage();
            }
        } while (running) ;
    }
}


