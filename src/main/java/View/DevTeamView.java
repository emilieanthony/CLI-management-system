package View;

import Models.Project;
import Models.SprintBacklog;
import Models.Task;
import Utility.Scan;

import java.util.ArrayList;
import static View.ScrumMasterView.proName;

public class DevTeamView {

    /*-------------------------------------------Menu Development Team ----------------------------------------------*/

    public static int menuTeamMember() throws Exception {
        int option = Scan.readInt("\n\nWelcome development team member!\n" +
                "You're working on Project " + proName +"." + "\n\n" +
                "Please enter an option below\n" +
                "1. View my own tasks\n" +
                "2. View assigned tasks\n" +
                "3. Set task as completed\n" +
                "4. Change user story status\n" +
                "5. View product backlog\n" +
                "6. View sprint backlog\n" +
                "7. View all tasks\n" +
                "8. View all complete tasks\n" +
                "9. View all complete user stories\n" +
                "10. View task deadlines\n" +
                "11. View user story deadlines\n" +
                "12. Switch projects\n" +
                "13. Go back to main menu\n");
        return option;
    }


    public static void invalidInputPrint(){
        Scan.print("\nThere was a problem entering input data.");
    }

    public static int getDeveloperId(){
        int id = Scan.readInt("Please enter developer ID: ");
        return id;
    }

    public static void noAssignedTasks(){
        Scan.print("There are no assigned tasks found!");
    }

    public static int getTaskId(){
        int id = Scan.readInt("Please enter the ID of the task that you want to access:");
        return id;
    }


    public static void negativeIDPrint() {
        Scan.print("ID cannot be negative.");
    }

    public static void negativeNumberPrint() {
        Scan.print("Number cannot be negative.");
    }

    public static int getUserStoryID(){
        int id = Scan.readInt("Please enter the User Story's ID");
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

    public static void printMyAssignedTasks(ArrayList<Task> assignedTasks){
        if (assignedTasks.isEmpty()){
            Scan.print("\n\nThere are no tasks that are assigned you yet");
        } else {
            Scan.print("\n\nTasks that are assigned to you: ");
            for (Task task : assignedTasks){
                Scan.print(task.toString());
            }
        }
    }

    public static void printAllAssignedTasks(ArrayList<Task> allAssignedTasks){

        if (allAssignedTasks.isEmpty()) {
            noAssignedTasks();
        } else {
            Scan.print("Assigned tasks: ");
            for (Task task : allAssignedTasks) {
                if (task.getStatus().equalsIgnoreCase("Assigned")) {
                    Scan.print(task.toString());
                }
            }
        }
    }

    public static void printTask(Task task){
        Scan.print(task.toString());
    }

    public static int anotherTaskOption(){
        int id = Scan.readInt("Do you want to open another task? If YES enter task ID, if NO just press Enter: ");
        return id;
    }

    public static void printAllTaskIDAndName(ArrayList<Task> tasks){
        Scan.print("All tasks: ");
        for (Task task : tasks){
            Scan.print("ID: " + task.getId() + " name: " + task.getName());
        }
    }

    public static void printSprints(Project project){
        Scan.print("\nSprints: \n" );
        for(SprintBacklog sprintBacklog : project.getAllSprintBacklogs()){
            Scan.print(sprintBacklog.getName());
        }
    }

    public static void taskCompletedReceipt(Task task){
        Scan.print("Task " + task.getName() + " with the ID " + task.getId() + " is now set as completed"
        + "\nEstimated hours: " + task.getEstimatedHours() + " Actual hours: " + task.getActualHours()
        + "\nSet as complete by: " + task.getCompletedBy());
    }

    public static String getNameCompleteTask(){
        return Scan.readLine("Enter name of whoever completed the task: ");
    }

}



