package View;

import Controller.ControllerAll;
import Models.*;
import Models.Task;
import Utility.Scan;

import java.util.ArrayList;

import static Controller.ControllerScrumMaster.sprintName;

public class ScrumMasterView
{

    public static String proName;
    public static int menuScrumMaster() throws NumberFormatException{

		int option = Scan.readInt("\n\nWelcome Scrum Master!\n" +
				"You're working on Project " + proName + "." + "\n\n" +
				"Please enter an option below\n\n" +
				"1. Create a new project\n" +
				"2. Create a new sprint backlog\n" +
				"3. Create a new task for product backlog\n" +
				"4. Create a new task for sprint backlog\n" +
				"5. Create a new Development Team Member\n" +
				"6. Create a new Product owner\n" +
				"7. Assign a task to Development Team Member\n" +
				"8. Assign a user story to Development Team Member\n" +
				"9. View product backlog\n" +
				"10. Edit Task Menu\n" +
				"11. View all development Team Members\n" +
				"12. Move task or user story to sprint backlog\n" +
				"13. Move task or user story to product backlog\n" +
				"14. View sprint backlog\n" +
				"15. Calculate average velocity\n" +
				"16. Switch project\n" +
				//"17. Import file\n" +
				"17. Go back to main menu\n");


		return option;
	}


    //----------------------------------------------------------Edit task menu---------------------------------------------
    public static int menuEditTask() throws Exception
    {
        int option = Scan.readInt
                ("\n\nEdit Task Menu. \n " +
                        "Which part of the task do you want to edit, enter a number:\n\n" +
                        "1- Edit Task Priority Number.\n" +
                        "2- Edit Task Status.\n" +
                        "3- Remove Task from Sprint Backlog.\n" +
                        "4- Remove Task from Product Backlog.\n" +
                        "5- Back to your menu.\n");
        return option;
    }

    public static Project projectInput()throws Exception{
		Scan.print("\nEnter the name, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new " +
				"project:");
		String name = Scan.readLine("Name: ");
		int id = Scan.readInt("ID: ");
		int startYear = Scan.readInt("Start date (YYYY): ");
		int startMonth = Scan.readInt("Start date (MM): ");
		int startDay = Scan.readInt("Start date (DD): ");
		int endYear = Scan.readInt("End date (YYYY): ");
		int endMonth = Scan.readInt("End date (MM): ");
		int endDay = Scan.readInt("End date (DD): ");

		String startDate = startYear + "-" + startMonth + "-" + startDay;
		String endDate = endYear + "-" + endMonth + "-" + endDay;
		Project project = new Project(id, name, startDate, endDate);
		return project;
	}

	public static void invalidTaskPrint(){
    	Scan.print("The ID of the task was not found. Please try again");
    	menuScrumMaster();
	}

	public static void invalidSprintBacklog(){
		Scan.print("The name of the sprint was not found. Please try again");
		menuScrumMaster();
	}

	public static int specifyTask(){
		int idTask = Scan.readInt("Write the ID of the task you want to move: ");// Move to view class.

		return idTask;

	}

	public static String specifySprint(){
		name = Scan.readLine("Write the name of the sprint you want to move your task to: ");

		return name;

	}

	public static void noSprintPrint(){
    	Scan.print("No sprint exists with that name, please try again.");
	}

	public static void createProjectPrint(Project project){
		Scan.print("You have successfully created the following project:\n\n" + project.toString());
	}

    public static void numberFormatMessage() {
        Scan.print("There was a problem entering input data.");
    }

    public static void registerProjectFail() {
        Scan.print("There was a problem trying to register a new project, please try again.");
    }

    public static void registerTaskFail() {
        Scan.print("There was a problem trying to register a new task, please try again.");
    }

    public static void registerProOwnerFail(){
        Scan.print("There was a problem trying to register a new product owner, please try again.");
    }

    public static void registerDeveloperFail() {
        Scan.print("There was a problem registering a new developer, please try again.");
    }

    public static void backlogFail(){
        Scan.print("There was a problem trying to register a backlog, please try again.");
    }

    public static int newPriorityNumberTask (){
        int newPriorityNumberTask = Scan.readInt("\nEnter the new priority number between 1 - 5:\n");

		return newPriorityNumberTask;
	}

	public static int newStatusTask()
	{

		int newStatusTask = Scan.readInt("\nEnter the number for the new status; \n" +
				"1 = Open\n " +
				"2 = Work in progress\n " +
				"3 = Complete.");
		return newStatusTask;

	}


	//-------------------------------------------------------------------------------------------------------------------

	public static String getFileName()
	{

		String fileName = Scan.readLine("Please enter the file path: ex " +
				"\"C:\\\\User\\\\user\\\\OneDrive\\\\desktop\\\\file name.txt\". \"remember the " +
				"double \\\\\"");
		return fileName;
	}

    public static Task getTaskInfo(int id) throws Exception {
       // int id = Scan.readInt("Please enter task id:");
        String name = Scan.readLine("Please enter name of task:");
        int priorityNumber = Scan.readInt("Please enter priority number 1-5:");
        int estimatedHours = Scan.readInt("Please enter estimated hours:");
        String description = Scan.readLine("Please enter a description of the task:");

		return new Task(id, priorityNumber, estimatedHours, name, description);
	}

	public static void viewProjectMenu(ControllerAll contAll)
	{

		int order = 1;
		Scan.print("\n\nWelcome to Codelicode: \n\nProject List:\n");
		for (Project project : contAll.getAllProjects())
		{
			Scan.print(order++ + ". " + project.getName() + ".\n");
		}
	}

	public static void Start()
	{
		proName = Scan.readLine("\nWhich project you want " +
				"to access? Enter the name of the project or press enter to ignore: ");
	}

	public static void getProjectName()
	{
		proName = Scan.readLine("\nWhich project you want to access: Please enter the project " +
				"name:\n");
	}

	public static void membersView()
	{
		Scan.print("These are the team developers for this project: \n");
	}

	public static String getProOwnerInfo()
	{
		String name = Scan.readLine("Creating a new product owner:\nName: ");
		return name;
	}

	public static void createdProOwner()
	{
		Scan.print("You have successfully created a new product owner.");
	}

	public static String getDeveloperInfo()
	{
		String name = Scan.readLine("Creating a new developer:\nName: ");
		return name;
	}

	public static void createdDeveloper()
	{
		Scan.print("You have successfully created a new developer.\n");
	}

	public static String getSprintBacklogName()
	{
		String nameSprintBacklog = Scan.readLine("\nPlease enter the name of the sprint backlog you want to add the task to:");
		return nameSprintBacklog;
	}

	public static String getSprintBacklogByName()
	{
		sprintName = Scan.readLine("Write the name of the sprint: ");
		return sprintName;
	}

	public static void printSprintBacklog(ArrayList<UserStory> allUserStories, ArrayList<Task> allTasks)
	{
		if (allUserStories.isEmpty() && allTasks.isEmpty())
		{ //what happens if one of them is not
			// empty!
			Scan.print("The sprint backlog is empty.");
		}
		for (UserStory story : allUserStories)
		{
			Scan.print(story.toString());
		}
		for (Task task : allTasks)
		{
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

	public static void createdTaskReceipt(Task task)
	{
		Scan.print("\nThe following task has been created:\n " + task.toString() + "\n");

	}

	public static String getVelocity()
	{
		String input = Scan.readLine("Please enter the velocity for each sprint separated by a comma without spaces (e.g. 19,27,23):");
		return input;
	}
}



