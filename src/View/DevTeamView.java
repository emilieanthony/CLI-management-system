package View;

import Controller.ControllerAll;
import Controller.ControllerDeveloper;
import Models.Backlog;
import Models.Task;
import Models.UserStory;
import Utility.Scan;

import java.util.ArrayList;
import java.util.Scanner;

public class DevTeamView {

    /*-------------------------------------------Menu Development Team ----------------------------------------------*/

    public int menuTeamMember() {
        int option = Scan.readInt("\n\nWelcome development team member!\n" +
                "Please enter an option below\n" +
                "1. View my own tasks\n" +
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

    public int uStoryMenu(UserStory userStory) {
        int option = Scan.readInt("\n\n User story ID:"  + userStory.getNumber() + "\nUser story name: " +
                userStory.getName() + "\n" +
                "Please enter an option below\n" +
                "1. Set User Story as complete and enter actual velocity\n" +
                "2. \n" +
                "3. Go back to previous menu\n");
        return option;
    }

    public void printUserStories (Backlog backlog){
        ArrayList<UserStory> userStories = backlog.getAllUserStories();

        for ( UserStory userStory : userStories){
            Scan.print(userStory.toString());
        }

    }

    public int getTaskId(){
        int id = Scan.readInt("Enter task ID:");
        return id;
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

