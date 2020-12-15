package Utility;

public class PrintUtility {

    /*-----------------------------------------Print code to reuse --------------------------------------------------*/

    public static void defaultMessage() {

        Scan.print("\nInvalid input, please enter another option\n");
    }

    public static void errorImport() {
        Scan.print("\nInvalid project info, You can't have duplicated project info such as names " +
                "or id's\n");
    }

    public static void projectNotFound(){
        Scan.print("\nProject not found, please create a project before you proceed.\n");
    }

    public static void movedObject(){
        Scan.print("\nYou have successfully moved the object!\n");
    }

    public static void removeObject() {Scan.print("\n You have successfully removed the object.\n ");
    }

    public static void taskNotFound(){
        Scan.print("\nTask not found, please create a task before you proceed.\n");
    }

}
