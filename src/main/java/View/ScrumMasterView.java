package View;

import Controller.ControllerAll;
import Models.*;
import Models.Task;
import Utility.DataManagement;
import Utility.Scan;

import java.time.LocalDate;
import java.util.ArrayList;

import static Controller.ControllerScrumMaster.sprintName;

public class ScrumMasterView
{
	public static String proName;

	public static int menuScrumMaster() throws NumberFormatException
	{
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
				"9. Set deadline for user story\n" +
				"10. View sprint end dates\n" +
				"11. View task deadlines\n" +
				"12. View user story deadlines\n" +
				"13. View product backlog\n" +
				"14. Edit task menu\n" +
				"15. Edit user story in sprint backlog menu\n" +
				"16. View completed tasks\n" +
				"17. View completed user stories\n" +
				"18. View all development Team Members\n" +
				"19. Move task or user story to sprint backlog\n" +
				"20. Move task or user story to product backlog\n" +
				"21. View sprint backlog\n" +
				"22. Calculate average velocity\n" +
				"23. Switch project\n" +
				"24. Create a task of a user story located in sprint backlog.\n" + //or name this "Break down a user story in sprint backlog into tasks"
				"25. Menu for edit tasks in User Story\n" +
				"26. Show implemented story points in sprint backlogs.\n" +
				"27. Show average Velocity.\n" +
				"28. Go back to main menu\n");

		return option;
	}

	//----------------------------------------------------------Edit task menu---------------------------------------------

	public static int menuEditTask() throws NumberFormatException
	{
		int option = Scan.readInt
				("\n\nEdit Task Menu. \n " +
						"Which part of the task do you want to edit, enter a number:\n\n" +
						"1- Edit Task Priority Number.\n" +
						"2- Edit Task Status.\n" +
						"3- Set task deadline\n" +
						"4- Remove Task from Sprint Backlog.\n" +
						"5- Remove Task from Product Backlog.\n" +
						"6- Back to your menu.\n");
		return option;
	}

	public static int menuEditUStoryInSBL() throws NumberFormatException{
		int option = Scan.readInt
				("\n\nEdit User story in sprint backlog menu. \n " +
						"Enter a number:\n\n" +
						"1. Edit user story points\n" +
						"2. Edit priority number of user story\n" +
						"3. Change user story status\n" +
						"4. Set user story deadline\n" +
						"5. Remove user story from Sprint Backlog\n" +
						"6. Back to your menu.\n");
		return option;
	}

	//-----------------------------------------------------------------------------------------------------------

	public static Project projectInput() throws NumberFormatException
	{
		Scan.print("\nEnter the name, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new " +
				"project:");
		String name = Scan.readLine("Name: ");
		int id = Scan.readInt("ID: ");

		proName = name;

		String startDate = getStartDate();
		String endDate = getEndDate();

		while (!DataManagement.stringToLocalDate(endDate).isAfter(DataManagement.stringToLocalDate(startDate)))
		{
			Scan.print("The start date you entered is after the end date you entered. Try again: ");
			startDate = getStartDate();
			endDate = getEndDate();

		}

		Project project = new Project(id, name, startDate, endDate);
		return project;
	}

	public static String getStartDate()
	{
		int startYear = Scan.readInt("Start date (YYYY): ");
		int startMonth = Scan.readInt("Start date (MM): ");
		int startDay = Scan.readInt("Start date (DD): ");
		String startDate = String.valueOf(startYear + "-" + startMonth + "-" + startDay);
		return startDate;
	}

	public static String getEndDate()
	{
		int endYear = Scan.readInt("End date (YYYY): ");
		int endMonth = Scan.readInt("End date (MM): ");
		int endDay = Scan.readInt("End date (DD): ");
		String endDate = String.valueOf(endYear + "-" + endMonth + "-" + endDay);
		return endDate;
	}

	public static void nullTaskPrint()
	{
		Scan.print("This task does not exist, please try again.");
		menuScrumMaster();
	}

	public static void invalidTaskPrint()
	{
		Scan.print("The ID of the task was not found. Please try again");
		menuScrumMaster();
	}

	public static void invalidSprintBacklog()
	{
		Scan.print("The name of the sprint was not found. Please try again");
		menuScrumMaster();
	}

	public static int specifyTask()
	{
		int idTask = Scan.readInt("Write the ID of the task you want to move: ");

		return idTask;
	}

	public static String specifySprint()
	{
		sprintName = Scan.readLine("Write the name of the sprint you want to move your task to: ");

		return sprintName;
	}

	public static SprintBacklog createSprintInfo() throws Exception
	{

		Scan.print("\nEnter the sprintName, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new " +
				"sprintBacklog:");
		String name = Scan.readLine("Name:");
		sprintName = name;

		String startDate = ScrumMasterView.getStartDate();
		String endDate = ScrumMasterView.getEndDate();

		SprintBacklog sprintBacklog = new SprintBacklog(name, startDate, endDate);
		return sprintBacklog;
	}

	public static void successfulSprintLog(SprintBacklog sprintBacklog)
	{
		Scan.print("You have successfully created the following sprintBacklog:\n\n"
				+ sprintBacklog.toString());
	}

	public static void invalidNumberPrint()
	{
		Scan.print("Priority number must be between 1-5. Please try again.");
		menuScrumMaster();
	}

	public static void noSprintPrint()
	{
		Scan.print("No sprint exists with that name, please try again.");
	}

	public static void createProjectPrint(Project project)
	{
		Scan.print("You have successfully created the following project:\n\n" + project.toString());
	}

	public static void numberFormatMessage()
	{
		Scan.print("There was a problem entering input data.");
	}

	public static void registerTaskFail()
	{
		Scan.print("There was a problem trying to register a new task, please try again.");
	}

	public static void registerProOwnerFail()
	{
		Scan.print("There was a problem trying to register a new product owner, please try again.");
	}

	public static void registerDeveloperFail()
	{
		Scan.print("There was a problem registering a new developer, please try again.");
	}

	public static void backlogFail()
	{
		Scan.print("There was a problem trying to register a backlog, please try again.");
	}

	public static int newPriorityNumberTask()
	{
		int newPriorityNumberTask = Scan.readInt("\nEnter the new priority number between 1 - 5:\n");

		return newPriorityNumberTask;
	}

	public static void wrongDatePrint()
	{
		Scan.print("Error. You entered a start date that is after the end date.\n");
	}

	public static int newStatusTask()
	{

		int newStatusTask = Scan.readInt("\nEnter the number for the new status; \n" +
				"1 = Open\n " +
				"2 = Assigned\n" +
				"3 = Work in progress\n " +
				"4 = Complete.");
		return newStatusTask;
	}

	//-------------------------------------------------------------------------------------------------------------------

	public static Task getTaskInfo(int id) throws Exception
	{
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

	public static void getProjectName(ControllerAll controllerAll)

	{
		viewProjectMenu(controllerAll);
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
		int idTask = Scan.readInt("Write the ID of the task you want to move: ");
		return idTask;
	}

	public static int IdTaskEdit()
	{
		int idTaskEdit = Scan.readInt("Write the ID of the task you want to edit: ");
		return idTaskEdit;
	}

	public static int IdTaskRemove()
	{
		int idTaskRemove = Scan.readInt("Write the ID of the task you want to remove: ");
		return idTaskRemove;
	}

	public static String sprintNameToMovePrint()
	{
		String sprintName = Scan.readLine("Write the name of the sprint you want to move your task from: ");
		return sprintName;
	}

	public static int numberUsToMove()
	{
		int usNumber = Scan.readInt("Write the the number of the user story you want to move: ");
		return usNumber;
	}

	public static String sprintNameToMovePrintUS()
	{
		String sprintNameInput = Scan.readLine("Write the name of the sprint you want to move your user story to: ");
		return sprintNameInput;
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
		String input = Scan.readLine("Please enter the velocity for each sprint separated by " +
				"a comma without spaces (e.g. 19,27,23):");
		return input;
	}

	public static void printVelocity(int averageVelocity)
	{
		Scan.print("The average velocity is: " + averageVelocity);
	}

	public static void showImplementedStoryPoints(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();
		Scan.print("Total implemented story points till now for each Sprint backlog: ");

		for (SprintBacklog sprintBacklog : project.getAllSprintBacklogs())
		{

			Scan.print("\nName: " + sprintBacklog.getName() + "\nTotal story points: " +
					sprintBacklog.calcTotalStoryPoints());
		}
	}

	public static void showAverageVelocity(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();

		int averageVelocity = 0;
		int numberOfSprints = project.getAllSprintBacklogs().size();

		for (SprintBacklog sprintBacklog : project.getAllSprintBacklogs())
		{
			averageVelocity = averageVelocity + sprintBacklog.getTotalStoryPoints() / numberOfSprints;

		}
		Scan.print("The average velocity for all sprints until now is: " + averageVelocity);
	}

	public static void invalidOption()
	{
		Scan.print("\nYou have entered an invalid option, choose between 1-5.");
	}

	public static void removedTaskInUserStory()
	{
		Scan.print("\nYou have successfully removed the task.");
	}

	public static int menuEditTaskInUserStory() throws IllegalArgumentException
	{
		int option = Scan.readInt
				("\n\nEdit Task Menu. \n " +
						"Which part of the task do you want to edit, enter a number:\n\n" +
						"1- Edit Task Priority Number.\n" +
						"2- Edit Task Status.\n" +
						"3- Remove Task from a user story.\n" +
						"4- Back to your menu.\n");
		return option;
	}

	public static void printCompleteTasks(ArrayList<Task> completedTasks)
	{
		if (completedTasks.isEmpty())
		{
			Scan.print("There are no completed tasks yet");
		}
		else
		{
			Scan.print("Completed tasks");
			for (Task task : completedTasks)
			{
				Scan.print(task.toString());
			}
		}
	}

	public static void printCompleteUStories(ArrayList<UserStory> completedStories)
	{
		if (completedStories.isEmpty())
		{
			Scan.print("There are no completed user stories yet");
		}
		else
		{
			Scan.print("Completed user stories");
			for (UserStory userStory : completedStories)
			{
				Scan.print(userStory.toString());
			}
		}
	}

	public static void printDeadlines(ArrayList<SprintBacklog> sortedSprints){
		for (SprintBacklog sprint : sortedSprints){
			Scan.print("Sprint name: " + sprint.getName() + " end date: " + sprint.getEndDate() + "\n");
		}
	}

	public static void taskCreatedToPBacklog()
	{
		Scan.print("Task successfully created to product backlog!");
	}

	public static void taskCreatedToSBacklog()
	{
		Scan.print("Task successfully created to sprint backlog!");
	}

	public static void printTaskDeadlines(ArrayList<Task> wDeadlines, ArrayList<Task> wODeadlines){
		for (Task task : wDeadlines){
			Scan.print(task.getDeadline() + " Task ID: " + task.getId() + " Name: " + task.getName());
		}

		if (!wODeadlines.isEmpty()){
			Scan.print("\n\nThe following tasks do not have any deadlines");
			for (Task task : wODeadlines){
				Scan.print("ID: " + task.getId() + " Name: " + task.getName());
			}
		}

	}

	public static void printUStoryDeadlines(ArrayList<UserStory> wDeadlines, ArrayList<UserStory> wODeadlines){
		for (UserStory userStory : wDeadlines){
			Scan.print(userStory.getDeadline() + " Number: " + userStory.getNumber() + " Name: " + userStory.getName());
		}

		if (!wODeadlines.isEmpty()){
			Scan.print("\n\nThe following user stories do not have any deadlines");
			for (UserStory userStory : wODeadlines){
				Scan.print("Number: " + userStory.getNumber() + " Name: " + userStory.getName());
			}
		}

	}

	public static void setTaskDeadlineReceipt(Task task){
		Scan.print("The following deadline: " + task.getDeadline() + " for task with the ID: " + task.getId() + " with " +
				" the name: " + task.getName());
	}

	public static void setUStoryDeadlineReceipt(UserStory userStory){
		Scan.print("The following deadline: " + userStory.getDeadline() + " for user story with the number: " +
				userStory.getNumber() + " with the name: " + userStory.getName());
	}

	public static int getUSPoints(){
		int points = Scan.readInt("Enter the amount of user story points this user story shall have: ");
		return points;
	}

	public static boolean removingUSMsg(UserStory userStory) {
		boolean confirmation = false;
		int option = Scan.readInt("Removing the following user story: \n" + userStory.toString()
				+ "\nEnter 1 to proceed, or enter 2 to cancel");
		if (option == 1) {
			confirmation = true;
		}
		return confirmation;
	}

}



