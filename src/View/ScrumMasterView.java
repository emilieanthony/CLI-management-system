package View;

import Models.*;
import Models.Task;
import Utility.Scan;

import java.util.ArrayList;

public class ScrumMasterView {


    public static String proName;
    public static String backlogName;

    public static int menuScrumMaster() {

        int option = Scan.readInt("\n\nWelcome Scrum Master!\n" +
                "You're working on Project " + proName + "." + "\n\n" +
                "Please enter an option below\n\n" +
                "1. Create a new project\n" +
                "2. Create a new sprint and sprint backlog\n" +
                "3. Create a new task for product backlog\n" +
                "4. Create a new task for sprint backlog\n" +
                "5. Create a new Development Team Member\n" +
                "6. Create a new Product owner\n" +
                "7. Assign a task to Development Team Member\n" +
                "8. Assign a user story to Development Team Member\n" +
                "9. View product backlog\n" +
                "10. View all development Team Members\n" +
                "11. Move task or user story to sprint backlog\n" +
                "12. Move task or user story to product backlog\n" +
                "13. View sprint backlog\n" +
                "14. Import file\n" +
                "15. Switch project\n" +
                "16. Calculate average velocity\n"+
                "17. Go back to main menu\n");

        return option;
    }

    public static String getFileName() {

        String fileName = Scan.readLine("Please enter the file path: ex " +
                "\"C:\\\\User\\\\user\\\\OneDrive\\\\desktop\\\\file name.txt\". \"remember the " +
                "double \\\\\"");
        return fileName;
    }

    public static Task getTaskInfo(int id) {
       // int id = Scan.readInt("Please enter task id:");
        String name = Scan.readLine("Please enter name of task:");
        int priorityNumber = Scan.readInt("Please enter priority number:");
        int estimatedHours = Scan.readInt("Please enter estimated hours:");
        String description = Scan.readLine("Please enter a description of the task:");

        return new Task(id, priorityNumber, estimatedHours, name, description);
    }


    public static void Start() {
        proName = Scan.readLine("\n\nWelcome to Codelicode: \nWhich project you want " +
                "to access? Enter the name of the project or press enter to ignore: ");
    }

    public static void getProjectName() {
        proName = Scan.readLine("Which project you want to access: Please enter the project " +
                "name:\n");

    }

    public static void membersView() {
        Scan.print("These are the team developers for this project: \n");
    }

    public static String getProOwnerInfo() {
        String name = Scan.readLine("Creating a new product owner:\nName: ");
        return name;
    }

    public static void createdProOwner() {
        Scan.print("You have successfully created a new product owner.");
    }

    public static String getDeveloperInfo() {
        String name = Scan.readLine("Creating a new developer:\nName: ");
        return name;
    }

    public String getBacklogName() {
        return Scan.readLine("Please enter name of backlog you want to add a task to:");
    }

    public static void createdDeveloper() {
        Scan.print("You have successfully created a new developer.\n");
    }

    public static String getSprintBacklogName() {
        String nameSprintBacklog = Scan.readLine("\nPlease enter the name of the sprint backlog you want to add the task to:");
        return nameSprintBacklog;
    }

    public static String getSprintBacklogByName(){
        return Scan.readLine("Write the name of the sprint: ");
    }

    public static void printSprintBacklog(ArrayList<UserStory> allUserStories, ArrayList<Task> allTasks){
        if(allUserStories.isEmpty()&&allTasks.isEmpty()){
            Scan.print("The sprint backlog is empty.");
        }
        for (UserStory story : allUserStories){
            Scan.print(story.toString());
        }
        for (Task task : allTasks){
            Scan.print(task.toString());
        }

    }

    public static void assignmentCompleted()
    {
        Scan.print("\nAssignment completed!");
    }

    public static int assignTaskPrintIdTask()
    {
        int idTask = Scan.readInt("Write the ID of the task you want to assign: ");
        return idTask;
    }

    public static String assignTaskPrintSprintName()
    {
        String sprintName = Scan.readLine("Write the name of which sprint backlog the task belongs to: ");
        return sprintName;
    }

    public static int assignUsPrintIdUs()
    {
        int number = Scan.readInt("Write the number of the User Story you want to assign: ");
        return number;
    }

    public static String assignUsPrintSprintName()
    {
        String sprintName = Scan.readLine("Write the name of which sprint backlog the User Story belongs to: ");
        return sprintName;
    }

    public static String moveObjectToBacklogPrint()
    {
        String input = Scan.readLine("Do you want to move a TASK from sprint backlog to product backlog, type: 1\n" +
                "Do you want to move a USER STORY from sprint backlog to product backlog, type: " +
                "2\n");
        return input;
    }

    public static int IdTaskToMovePrint()
    {
        int idTask = Scan.readInt("Write the ID of the task you want to move: ");// Move to view class.
        return idTask;
    }

    public static String sprintNameToMovePrint()
    {
        String sprintName = Scan.readLine("Write the name of the sprint you want to move your task from: ");
        return sprintName;
    }

    public static int numerUsToMove()
    {
        int usNumber = Scan.readInt("Write the the number of the user story you want to move: ");
        return usNumber;
    }

    public static String sprintNameToMove()
    {
        String sprintName = Scan.readLine("Write the name of the sprint backlog you want to move your user story from: ");
        return sprintName;
    }

    public static void createdTaskReceipt(Task task){
        Scan.print("\nThe following task has been created:\n " + task.toString() + "\n");

    }

    public static String getVelocity (){
        String input = Scan.readLine("Please enter the velocity for each sprint separated by a comma without spaces (e.g. 19,27,23):");
        return input;
    }
}



