package View;

import Controller.ControllerAll;
import Models.*;
import Models.Task;
import Utility.DataManagement;
import Utility.Scan;

import java.util.ArrayList;

import static Controller.ControllerScrumMaster.sprintName;

public class ScrumMasterView
{
	public static String proName;

	public static int menuScrumMaster() throws NumberFormatException
	{
		int option = Scan.readInt("\n\nWelcome Scrum Master!\n" +
				"You're working on Project " + proName + "." + "\n\n" +
				"Please enter an option below.\n\n" +
				"1. Create a new Project.\n" +
				"2. Create a new Sprint backlog.\n" +
				"3. Create a new Product Owner account.\n" +
				"4. Create a new Developer account.\n" +
				"5. User Story Options Menu.\n" +
				"6. Task Options Menu.\n" +
				"7. Deadlines.\n" +
				"8. View Backlogs, Members or completed User Stories/Tasks Menu.\n" +
				"9. Move a User Story or Task between Product and Sprint Backlogs.\n" +
				"10. Show your Implemented Story Points.\n" +
				"11. Show your average Velocity.\n" +
				"12. Velocity calculator.\n" +
				"13. Switch Project.\n" +
				"14. Go back to Main Menu.\n");

		return option;
	}

	public static int userStoryOptionsMenu() throws NumberFormatException
	{
		int option = Scan.readInt
				("\n\nUser Story options Menu. \n" +

						"1. Assign a user story to a Developer.\n" +
						"2. Edit User Story in Sprint Backlog Menu\n" +
						"3. Break down a User Story in Sprint Backlog into tasks.\n" +
						"4. Edit User Story's tasks Menu\n" +
						"5. Back to your menu.\n");
		return option;
	}
	public static int taskOptionsMenu() throws NumberFormatException
	{
		int option = Scan.readInt
				("\n\nTask options Menu.\n" +

						"1. Create a new task for product backlog\n" +
						"2. Create a new task for sprint backlog\n" +
						"3. Assign a task to a Developer\n" +
						"4. Edit task menu\n" +
						"5. Back to your menu.\n");
		return option;
	}
	public static int deadlinesMenu() throws NumberFormatException
	{
		int option = Scan.readInt
				("\n\nDeadlines options Menu. \n" +

						"1. Set Task deadline\n" +
						"2. Set User Story deadline\n" +
						"3. View Sprint end dates\n" +
						"4. View Task deadlines\n" +
						"5. View User Story deadlines\n" +
						"6. Back to your menu.\n");
		return option;
	}

	public static int viewProPartsMenu() throws NumberFormatException
	{
		int option = Scan.readInt
				("\n\nView options Menu. \n" +

						"1. View product backlog\n" +
						"2. View sprint backlog\n" +
						"3. View all Developers\n" +
						"4. View completed tasks\n" +
						"5. View completed user stories\n" +
						"6. Back to your menu.\n");
		return option;
	}

	public static int moveOptionsMenu() throws NumberFormatException
	{
		int option = Scan.readInt
				("\n\nMove options Menu. \n" +

						"1. Move task or user story to sprint backlog\n" +
						"2. Move task or user story to product backlog\n" +
						"3. Back to your menu.\n");
		return option;
	}

	//----------------------------------------------------------Edit task menu---------------------------------------------

	public static int menuEditTask() throws NumberFormatException
	{
		int option = Scan.readInt
				("\n\nEdit Task Menu. \n " +
						"Which part of the task do you want to edit, enter a number:\n\n" +
						"1. Edit Task Priority Number.\n" +
						"2. Edit Task Status.\n" +
						"3. Set task deadline\n" +
						"4. Remove Task from Sprint Backlog.\n" +
						"5. Remove Task from Product Backlog.\n" +
						"6. Back to your menu.\n");
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

	public static Project projectInput(ControllerAll controllerAll) throws Exception
	{
		Scan.print("Creating a new project\nEnter the name, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new " +
				"project:");
		String name = Scan.readLine("Name: ");
		int id = Scan.readInt("ID: ");


		while (!controllerAll.checkLegalId(id)){
			id = Scan.readInt("You entered an ID that is already taken. Please enter a new one: ");
		}

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
		String startDate = (startYear + "-" + startMonth + "-" + startDay);
		return startDate;
	}

	public static String getEndDate()
	{
		int endYear = Scan.readInt("End date (YYYY): ");
		int endMonth = Scan.readInt("End date (MM): ");
		int endDay = Scan.readInt("End date (DD): ");
		String endDate = endYear + "-" + endMonth + "-" + endDay;
		return endDate;
	}

	public static void nullTaskPrint()
	{
		Scan.print("This task does not exist, please try again.");

	}
	public static void nullUserStoryPrint()
	{
		Scan.print("This User Story does not exist, please try again.");

	}

	public static void invalidTaskPrint()
	{
		Scan.print("The ID of the task was not found. Please try again");

	}

	public static void invalidSprintBacklog()
	{
		Scan.print("\n\nThe name of the sprint was not found. Please try again\n\n");

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

	public static void wrongPrioNumber(){
		Scan.print("Error. You entered an invalid priority number.");
	}

	public static void wrongDatePrint() {
		Scan.print("Error. You entered a start or end date that is after the start or end date. Please try again\n");

	}

	public static void dateIsBeforeOrAfterProjectDate(){
		Scan.print("Error. The date you entered is either before or after the duration of the project. Please try again.\n");
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
		proName = Scan.readLine("\nWhich project do you want " +
				"to access? Enter the name of the project or press enter to create a new project: ");

	}

	public static void getProjectName(ControllerAll controllerAll)

	{
		viewProjectMenu(controllerAll);
		proName = Scan.readLine("\nWhich project do you want to access: Please enter the project " +
				"name:\n");
	}

	public static void wrongProjectNameInput(){
		Scan.print("The project you entered does not exist. Try again.");
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
	public static void taskCreation()
	{
		Scan.print("Creating a task of a User Story.\n");
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

	public static void showAllTasks(ControllerAll controllerAll){
		Scan.print("Below you find all your tasks. \n\n");
		Scan.print(controllerAll.collectAllTasks().toString());
	}
	public static void showAllUserStories(ControllerAll controllerAll){
		Scan.print("Below you find all user stories. \n\n");
		Scan.print(controllerAll.collectAllStories().toString());
	}

	public static void showAllSprintBacklogs(Project project, String objectTypePlural){
		Scan.print("Below you find all your sprint backlogs and its " + objectTypePlural);
		for (SprintBacklog sprint : project.getAllSprintBacklogs()){
			Scan.print("Name of sprint: " + sprint.getName());

			if (objectTypePlural=="user stories"){
			for (UserStory userStory : sprint.getUserStories()){
				Scan.print("-    User story number: " + userStory.getId() + " " + userStory.getName());
			}
			}else if (objectTypePlural=="tasks"){
				for(Task task : sprint.getAllTasks())
				Scan.print("-    Task ID: " + task.getId() + " " + task.getName());
			}

		}

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
		String input = Scan.readLine("Do you want to move a TASK from Product backlog to Sprint " +
				"backlog, type: 1\n" +
				"Do you want to move a USER STORY from Product backlog to Sprint backlog, type: " +
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
		int usNumber = Scan.readInt("Write the number of the user story you want to move: ");
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
			Scan.print(userStory.getDeadline() + " Number: " + userStory.getId() + " Name: " + userStory.getName());
		}

		if (!wODeadlines.isEmpty()){
			Scan.print("\n\nThe following user stories do not have any deadlines");
			for (UserStory userStory : wODeadlines){
				Scan.print("Number: " + userStory.getId() + " Name: " + userStory.getName());
			}
		}

	}

	public static void setTaskDeadlineReceipt(Task task){
		Scan.print("The following deadline: " + task.getDeadline() + " for task with the ID: " + task.getId() + " with " +
				"the name: " + task.getName());
	}

	public static void setUStoryDeadlineReceipt(UserStory userStory){
		Scan.print("The following deadline: " + userStory.getDeadline() + " for user story with the number: " +
				userStory.getId() + " with the name: " + userStory.getName());
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

	public static void emptyName(){
		Scan.print("\n\nError: Name cannot be empty, Please " +
				"try again\n\n");
	}
	public static void negativeId(){
		Scan.print("\n\nError: Id cannot be negative, Please " +
				"try again\n\n");
	}

	public static void noSprintBacklogYet(){
		Scan.print("\n\nThere isn't yet any sprint backlog for you to add any tasks to, please create one first.\n\n");
	}

	public static void noDeveloperYet(){
		Scan.print("\n\nThere is no any development team members in the system." +
				" Please register a new development team member first below.\n\n");
	}
	public static void invalidDeveloperId(){
		Scan.print("\n\nThere is no Developer that matches your given input. \n\n");
	}

	public static void showAllTeamMembers(Project project){
		String name = project.getName();
		Scan.print("\nBelow you find all development team members working on project " + name);
		System.out.println("\n" + project.getAllTeamMembers());
	}

	public static void printUStoryInfo(UserStory userStory){
		Scan.print(userStory.toString());
	}

}



