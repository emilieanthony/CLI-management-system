package Utility;

import static View.ScrumMasterView.proName;

public class PrintUtility
{

    /*-----------------------------------------Print code to reuse --------------------------------------------------*/

    public int mainMenu()
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
                "5. Change project.\n" +
                "6. Save and Exit system.\n");

        return option;
    }

    public static void defaultMessage()
    {
        Scan.print("\nInvalid input, please enter another option\n");
    }

    public static void projectNotFound()
    {
        Scan.print("\nProject not found, please create a project before you proceed.\n");
    }

    public static void movedObject()
    {
        Scan.print("\nYou have successfully moved the object!\n");
    }

    public static void removeObject()
    {
        Scan.print("\n You have successfully removed the object.\n ");
    }

    public static void taskNotFound()
    {
        Scan.print("\nTask not found, please create a task before you proceed.\n");
    }

    public static void objectEdited()
    {
        Scan.print("\n You have successfully edited the object. \n ");
    }
}