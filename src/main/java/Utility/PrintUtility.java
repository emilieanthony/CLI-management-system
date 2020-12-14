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
}
