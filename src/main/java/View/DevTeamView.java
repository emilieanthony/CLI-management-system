package View;

import Models.Project;
import Models.SprintBacklog;
import Models.Task;
import Utility.Scan;

import java.util.ArrayList;
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
                "3. Change Task status\n" +
                "4. Change User Story status\n" +
                "5. View product backlog\n" +
                "6. View sprint backlog\n" +
                "7. View all tasks\n" +
                "8. Switch projects\n" +
                "6. Go back to main menu\n");
        return option;
    }

    public static int getTaskMenu() {
        int option = Scan.readInt("\n\nPlease enter an option below\n" +
                "1. Set Task as complete and enter actual hrs\n" +
                "2. Go back to previous menu\n");
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

    public static void printAllTasks(ArrayList<Task> tasks){
        for (Task task : tasks){
            Scan.print(task.toString());
        }
    }

    public static void viewSprints(Project project){
        Scan.print("Sprints: \n" );
        for(SprintBacklog sprintBacklog : project.getAllSprints()){
            Scan.print(sprintBacklog.getName());
        }
    }
}



