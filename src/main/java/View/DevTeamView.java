package View;

import Models.Task;
import Utility.Scan;
import java.util.Scanner;

import static View.ScrumMasterView.proName;

public class DevTeamView {

    /*-------------------------------------------Menu Development Team ----------------------------------------------*/

    public static int menuTeamMember() throws Exception {
        int option = Scan.readInt("\n\nWelcome development team member!\n" +
                "You're working on Project " + proName +"." + "\n\n" +
                "Please enter an option below\n" +
                "1. View my own tasks\n" +
                "2. View assigned tasks\n" +
                "3. change Task status\n" +
                "4. change User Story status\n" +
                "5. Choose project\n" +
                "6. Go back to main menu\n");
        return option;
    }

    public static int getTaskMenu() {
        int option = Scan.readInt("\n\nPlease enter an option below\n" +
                "1. Set Task as complete and enter actual hrs\n" +
                "2. Go back to previous menu\n");
        return option;
    }

    public static void invalidInputPrint(){
        Scan.print("There was a problem entering input data.");
    }

    public static int getDeveloperId(){

        int id = Scan.readInt("Please enter the developer's id");
        return id;
    }

    public static int getTaskId(){

        int id = Scan.readInt("Please enter the task's id");
        return id;
    }

    public static void noNamePrint(){
        Scan.print("Names cannot be empty.");
    }

    public static void negativeIDPrint() {
        Scan.print("ID cannot be negative.");
    }

    public static void negativeNumberPrint() {
        Scan.print("Number cannot be negative.");
    }

    public static int getUserStoryNumber(){

        int id = Scan.readInt("Please enter the User Story's number");
        return id;
    }

    public int getProjectId(){
        int number = Scan.readInt("Enter project ID:");
        return number;
    }

    public static int getActualHrs(){
        int actualHrs = Scan.readInt("Enter actual hours of task: ");
        return actualHrs;
    }

    public int getActualVelocity(){
        int actualVelocity = Scan.readInt("Enter actual velocity: ");
        return actualVelocity;
    }


    public int getUserId(){
        int id = Scan.readInt("Enter your user ID: ");
        return id;
    }

    public int getUStoryId(){
        int id = Scan.readInt("Enter User Story ID: ");
        return id;
    }

    public void printTasks(String assignedTasks){
        Scan.print(assignedTasks);
    }


}

