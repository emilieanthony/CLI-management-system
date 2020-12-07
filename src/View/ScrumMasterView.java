package View;

import Models.*;
import Utility.Scan;

import java.util.ArrayList;

import static View.ProductOwnerView.chooseBacklog;


public class ScrumMasterView {


    public static String proName;
    public static String backlogName;
    public static int menuScrumMaster() {

        int option = Scan.readInt("\n\nWelcome Scrum Master!\n" +
                "You're working on Project " + proName +"." + "\n\n" +
                "Please enter an option below\n\n" +
                "1. Create a new project\n" +
                "2. Create a new sprint\n" +
                "3. Create a new task\n" +
                "4. Create a new Development Team Member\n" +
                "5. Create a new Product owner\n" +
                "6. Assign a task to Development Team Member\n" +
                "7. View product backlog\n" +
                "8. View all Development Team Members\n" +
                "9. Import Files\n" +
                "10.Choose project.\n"+
                "11. Go back to main menu\n");

        return option;
    }

    public static String getFileName(){

        String fileName = Scan.readLine("Please enter the file path: ex " +
                "\"C:\\\\User\\\\user\\\\OneDrive\\\\desktop\\\\file name.txt\". \"remember the " +
                "double \\\\\"");
        return fileName;
    }

    public static Task getTaskInfo() {
        int id = Scan.readInt("Please enter task id:");
        String name = Scan.readLine("Please enter name of task:");
        int priorityNumber = Scan.readInt("Please enter priority number:");
        int estimatedHours = Scan.readInt("Please enter estimated hours:");
        String description = Scan.readLine("Please enter a description of the task:");

        return new Task(id,priorityNumber,estimatedHours,name,description);
    }

    public static void Start(){
        proName = Scan.readLine("\n\nWelcome to Codelicode: \nWhich project you want " +
                "to access? Enter the name of the project or press enter to ignore: ");
    }
    public static void getProjectName(){
        proName = Scan.readLine("Which project you want to access: Please enter the project " +
                "name:\n");

    }
    public static void membersView(){
        Scan.print("These are the team developers for this project: \n");
    }

    public static String getProOwnerInfo(){
        String name = Scan.readLine("Creating a new product owner:\nName: ");
        return name;
    }
    public static void createdProOwner(){
        Scan.print("You have successfully created a new product owner.");
    }
    public static String getDeveloperInfo(){
        String name = Scan.readLine("Creating a new developer:\nName: ");
        return name;
    }
    public static void createdDeveloper(){
        Scan.print("You have successfully created a new developer.\n");
    }
}




