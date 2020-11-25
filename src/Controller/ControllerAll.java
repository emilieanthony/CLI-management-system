package Controller;

import Models.BacklogModel;
import Utility.PrintUtility;
import Utility.Scan;
import View.ProductOwnerView;

import java.util.Scanner;

public class ControllerAll {

    //attributes
    //Controller for ScrumMaster operations as an object
    private ControllerScrumMaster controllerScrumMaster = new ControllerScrumMaster();

    //Controller for controller Team members as an object
    private ControllerDevelopmentMember controllerDevTeam = new ControllerDevelopmentMember();

    //Controller for controller product owner as an object
    private ControllerProductOwner controllerProductOwner = new ControllerProductOwner();

    //methods
    /*--------------------------------------------Main menu -----------------------------------------------------*/

        public int mainMenu() {
            Scanner input = new Scanner(System.in);
            Scan.print("Welcome to Codelicode, your project management tool.\nPlease enter a option below:\n" +
                    "1. Scrum master\n" +
                    "2. Product owner\n" +
                    "3. Development team member\n"+
                    "4. Exit system\n");
            int option = input.nextInt();
            return option;
        }

        public void menuMain(){
        boolean running = true;
            do {
            int option = mainMenu();
            switch (option) {
                case 1:
                    controllerScrumMaster.scrumMasterMenu();
                    break;
                case 2:
                    controllerProductOwner.backlogMenu();
                    break;
                case 3:
                    controllerDevTeam.teamMemberMenu();
                    break;
                case 4: running = false;
                    break;
                default: PrintUtility.defaultMessage();
                }
        } while (running) ;
    }
}
