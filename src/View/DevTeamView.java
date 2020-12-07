package View;

import Utility.Scan;
import java.util.Scanner;

import static View.ScrumMasterView.proName;

public class DevTeamView {

    /*-------------------------------------------Menu Development Team ----------------------------------------------*/

    public static int menuTeamMember() {
        int option = Scan.readInt("\n\nWelcome development team member!\n" +
                "You're working on Project " + proName +"." + "\n\n" +
                "Please enter an option below\n" +
                "1. View my own tasks\n" +
                "2. View assigned tasks\n" +
                "3. Choose project\n" +
                "4. Go back to main menu\n");
        return option;
    }

    public static int getDeveloperId(){

        int id = Scan.readInt("Please enter the developer's id");
        return id;
    }

    public static int getTaskId(){

        int id = Scan.readInt("Please enter the task's id");
        return id;
    }

}

