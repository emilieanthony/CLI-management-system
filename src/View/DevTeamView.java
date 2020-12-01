package View;

import Utility.Scan;
import java.util.Scanner;

public class DevTeamView {

    /*-------------------------------------------Menu Development Team ----------------------------------------------*/

    public int menuTeamMember() {
        int option = Scan.readInt("\n\nWelcome development team member!\n" +
                "Please enter an option below\n" +
                "1. View my own tasks\n" +
                "2. View assigned tasks\n" +
                "3. Go back to main menu\n");
        return option;
    }

}

