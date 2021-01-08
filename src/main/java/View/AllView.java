package View;

import Utility.Scan;

import static View.ScrumMasterView.proName;

public class AllView {

    public AllView(){}

    public static int mainMenu() throws Exception
    {

        int option = Scan.readInt("\n\nWelcome to Codelicode, your project management tool\n\n" +
                "IMPORTANT:- Shift between your projects to be able to make any changes or view " +
                "options,\nYou will find an option in your menu to choose between projects! \n" +
                "\nPlease enter a option below:\n"+
                "You're working on Project " + proName + "." + "\n\n"+
                "1. Scrum master.\n" +
                "2. Product owner.\n" +
                "3. Development team member.\n" +
                "4. View all Projects.\n" +
                "5. Switch project.\n" +
                "6. Save and Exit system.\n");

        return option;
    }

    public static void errorPrint(){
        Scan.print("There was an error, please try again");
    }


    public static void errorEmptyDescription(){
        Scan.print("Error: Description cannot be empty, Please " +
                "try again");
    }
}
