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
                "2. View all tasks\n" +
                "3. Open Task\n" +
                "4. View my User Stories\n" +
                "5. View all User Stories\n" +
                "6. Open User Story\n" +
                "7. Go back to main menu\n");
        return option;
    }

    public int taskMenu(Task task) {
        int option = Scan.readInt("\n\n Task ID:"  + task.getId() + "Task name: " + task.getName() + "\n" +
                "Please enter an option below\n" +
                "1. Set Task as complete and enter actual hrs\n" +
                "2. \n" +
                "3. Go back to previous menu\n");
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

    public int getProjectId(){
        int id = Scan.readInt("Enter project ID:");
        return id;
    }

    public int getActualHrs(){
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

