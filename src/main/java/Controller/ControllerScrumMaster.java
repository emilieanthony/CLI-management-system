package Controller;

import Models.*;
import Utility.Scan;

import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.*;
import static View.DevTeamView.getTaskId;

import static View.DevTeamView.invalidInputPrint;
import static View.DevTeamView.viewSprints;
import static View.ScrumMasterView.*;

public class ControllerScrumMaster
{

	//private Import importFile = new Import();
	static String name;

	public void scrumMasterMenu(ControllerProductOwner contProOwner, ControllerAll controllerAll,
								ControllerScrumMaster contScrum)
	{

		boolean running = true;
		do {

			int option;

			try {
				option = menuScrumMaster();

				switch (option) {
					case 1:
						createProject(controllerAll);
						break;
					case 2:
						createSprintBacklog(controllerAll);
						break;
					case 3:
						createTaskToProductBacklog(controllerAll);
						break;
					case 4:
						createTaskToSprint(controllerAll);
						break;
					case 5:
						createDevelopmentMember(controllerAll);
						break;
					case 6:
						createProductOwner(controllerAll);
						break;
					case 7:
						assignTask(controllerAll);
						break;
					case 8:
						assignUserStory(controllerAll);
						break;
					case 9:
						contProOwner.viewProBacklog(controllerAll);
						break;
					case 10:
						scrumMasterEditTaskMenu(controllerAll, contScrum);
						break;
					case 11:
						viewTeamMembers(controllerAll);
						break;
					case 12:
						moveTaskOrUSToSprintBacklog(contProOwner, controllerAll);
						break;
					case 13:
						moveTaskOrUSToProductBacklog(controllerAll);
						break;
					case 14:
						viewSprintBacklog(controllerAll);
						break;
					case 15:
						velocity();
						break;
					case 16:
						getProjectName();
						break;
					/*case 17:
						importFile.importProjects(controllerAll);
						break;*/
					case 17:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (NumberFormatException e) {
				numberFormatMessage();
			}
		} while (running);
	}

	//-----------------------------------------------------Second Switch----------------------------------------------

	public void scrumMasterEditTaskMenu(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{

		boolean running = true;
		do {

			int option;

			try {
				option = menuEditTask();
				switch (option) {
					case 1:
						editPriorityNumberTask(controllerAll);
						break;
					case 2:
						editStatusTask(controllerAll);
						break;
					case 3:
						removeTaskSprintBacklog(controllerAll, contScrum);
						break;
					case 4:
						removeTaskProductBacklog(controllerAll);
					case 5:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (Exception e) {
				invalidInputPrint();
			}
		} while (running);
	}


	//-------------------------------------------------------------------------------------------------------------------
	private void viewTeamMembers(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();
		membersView();
		for (Developer developer : project.getAllDevelopmentMembers())
		{
			Scan.print(developer.toString());
		}
	}


	/*------------------------------------------Methods for tasks------------------------------------------------*/


	private void createTaskToProductBacklog(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{

			int id = taskUSIdGenerator(project);

			try {
				Task newTask = getTaskInfo(id);
				createdTaskReceipt(newTask);
				project.getProductBacklog().getTasksImport().add(newTask);
				controllerAll.saveData();
			} catch (Exception e) {
				registerTaskFail();
			}
		}
	}

	private void createTaskToSprint(ControllerAll controllerAll)
	{

		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{

			int id = taskUSIdGenerator(project);

			try {
				Task newTask = getTaskInfo(id);
				createdTaskReceipt(newTask);
				name = getSprintBacklogName();
				findSprintBacklogByName(controllerAll).getAllTasks().add(newTask);
				controllerAll.saveData();
			} catch	(Exception e) {
				registerTaskFail();
			}
		}
	}

	public int taskUSIdGenerator(Project project)
	{
		// initialize int variable for ID
		int id = project.getId() * 1000 + 1;

		ArrayList<Task> tasks = collectAllTasks(project);
		ArrayList<UserStory> stories = collectAllStories(project);


		if (!tasks.isEmpty())
		{
			for (Task task : tasks)
			{
				if (task.getId() == id)
				{
					id++;
				}
			}
		}

		if (!stories.isEmpty())
		{
			for (UserStory userStory : stories)
			{
				if (userStory.getNumber() == id)
				{
					id++;
				}
			}
		}


		return id;
	}


	public ArrayList<Task> collectAllTasks(Project project)
	{

		//put all tasks in one and the same arrayList
		ArrayList<Task> allTasks = new ArrayList<>();

		//fetch tasks from product backlog
		ArrayList<Task> productBLTasks = project.getProductBacklog().getTasksImport();

		for (Task task : productBLTasks)
		{
			allTasks.add(task);
		}

		//fetch tasks from sprint BL
		ArrayList<SprintBacklog> sprintBLs = project.getAllSprintBacklogs();

		for (SprintBacklog sprintBL : sprintBLs)
		{
			ArrayList<Task> sprintTasks = sprintBL.getAllTasks();
			for (Task task : sprintTasks)
			{
				allTasks.add(task);
			}
		}

		return allTasks;
	}

	private ArrayList<UserStory> collectAllStories(Project project)
	{

		//put all user stories in one and the same ArrayList
		ArrayList<UserStory> allStories = new ArrayList<>();

		// fetch all user stories from product backlog
		ArrayList<UserStory> productBLStories = project.getProductBacklog().getAllUserStories();

		for (UserStory story : productBLStories)
		{
			allStories.add(story);
		}

		//fetch user story from sprint BL
		ArrayList<SprintBacklog> sprintBLs = project.getAllSprintBacklogs();

		for (SprintBacklog sprintBL : sprintBLs)
		{
			//
			ArrayList<UserStory> sprintBLStories = sprintBL.getUserStories();
			for (UserStory story : sprintBLStories)
			{
				allStories.add(story);
			}
		}

		return allStories;
	}

	private void moveTaskOrUSToSprintBacklog(ControllerProductOwner contProOwner, ControllerAll controllerAll)
	{
		contProOwner.viewProBacklog(controllerAll);

		String input = Scan.readLine("Do you want to move a TASK from product backlog to sprint backlog, type: 1\n" +
				"Do you want to move a USER STORY from product backlog to sprint backlog, type: " +
				"2\n"); // Move to view class.

		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{
			if (input.equals("1"))
			{
				int idTask = Scan.readInt("Write the ID of the task you want to move: ");// Move to view class.
				name = Scan.readLine("Write the name of the sprint you want to move your task to: ");

				project.getProductBacklog().getTask(idTask).setSprintName(name);
				Task taskToMove = project.getProductBacklog().getTask(idTask);
				findSprintBacklogByName(controllerAll).getAllTasks().add(taskToMove);
				project.getProductBacklog().getTasksImport().remove(taskToMove);
				controllerAll.saveData();
				movedObject();
			}

			if (input.equals("2"))
			{
				int usName = Scan.readInt("Write the the number of the user story you want to" +
						" move: "); // Move to view class.
				name = Scan.readLine("Write the name of the sprint you want to move your user story to: ");

				project.getProductBacklog().getUserStory(usName).setSprintName(name);
				UserStory userStoryToMove = project.getProductBacklog().getUserStory(usName);
				findSprintBacklogByName(controllerAll).getUserStories().add(userStoryToMove);
				project.getProductBacklog().getAllUserStories().remove(userStoryToMove);
				controllerAll.saveData();
				movedObject();
			}
			else
			{
				return;
			}
		}
	}

	private void moveTaskOrUSToProductBacklog(ControllerAll controllerAll)
	{
		viewSprintBacklog(controllerAll);

		String input = moveObjectToBacklogPrint();

		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{
			if (input.equals("1"))
			{
				int idTask = IdTaskToMovePrint();
				name = sprintNameToMovePrint();

				findSprintBacklogByName(controllerAll).getTask(idTask).setSprintName("");
				Task taskToMove = findSprintBacklogByName(controllerAll).getTask(idTask);
				project.getProductBacklog().getTasksImport().add(taskToMove);
				findSprintBacklogByName(controllerAll).getAllTasks().remove(taskToMove);
				controllerAll.saveData();
				movedObject();
			}

			if (input.equals("2"))
			{
				int usNumber = numerUsToMove();
				name = sprintNameToMove();

				findSprintBacklogByName(controllerAll).getUserStory(usNumber).setSprintName("");
				UserStory userStoryToMove = findSprintBacklogByName(controllerAll).getUserStory(usNumber);
				project.getProductBacklog().getAllUserStories().add(userStoryToMove);
				findSprintBacklogByName(controllerAll).getUserStories().remove(userStoryToMove);
				controllerAll.saveData();
				movedObject();
			}
			else
			{
				return;
			}
		}
	}


	private void editPriorityNumberTask(ControllerAll controllerAll)
	{
		final int PRIORITY_LOWEST = 1;
		final int PRIORITY_HIGHEST = 5;
		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}
		else
		{
			ArrayList<Task> tasks = collectAllTasks(project);
			int idTask = Scan.readInt("Write the ID of the task you want to edit: ");
			for (Task task : tasks)
			{
				if (task.getId() == idTask)
				{
					int newPriorityNumberTask = newPriorityNumberTask();
					if ((newPriorityNumberTask >= PRIORITY_LOWEST) && (newPriorityNumberTask <= PRIORITY_HIGHEST))
					{
						task.setPriorityNumber(newPriorityNumberTask);
						objectEdited();
					}
					else
					{
						defaultMessage();
					}

					return;
				}

				taskNotFound();
			}
		}
	}

	private void editStatusTask(ControllerAll controllerAll)
	{

		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}
		else
		{

			ArrayList<Task> tasks = collectAllTasks(project);


			int idTask = Scan.readInt("Write the ID of the task you want to edit: ");

			for (Task task : tasks)
			{
				if (task.getId() == idTask)
				{
					int newStatusTask = newStatusTask();

					if (newStatusTask == 1)
					{
						task.setStatus("Open");
						objectEdited();

					}
					else if (newStatusTask == 2)
					{
						task.setStatus("Work in Progress");
						objectEdited();

					}
					else if (newStatusTask == 3)
					{
						task.setStatus("Complete");
						objectEdited();

					}
					else
					{
						defaultMessage();
					}

					return;
				}
			}
			taskNotFound();
		}
	}


	public void removeTaskProductBacklog(ControllerAll controllerAll)
	{

		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}
		else
		{

			int idTask = Scan.readInt("Write the ID of the task you want to remove: ");
			project.getProductBacklog().getTasksImport().remove(project.getProductBacklog().getTask(idTask));
			controllerAll.saveData();
			removeObject();

		}
	}

	public void removeTaskSprintBacklog(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		name = getSprintBacklogByName();
		SprintBacklog sprintBacklog = findSprintBacklogByName(controllerAll);
		Task task = findTaskByIdSprint(controllerAll, contScrum);
		sprintBacklog.getAllTasks().remove(task);
		controllerAll.saveData();
		removeObject();
	}
	/*------------------------------------------Methods product owner------------------------------------------------*/

	public void createProductOwner(ControllerAll controllerAll)
	{
		String name = getProOwnerInfo();
		int id = createIdProductOwner(controllerAll);
		try {
			ProductOwner newProOwner = new ProductOwner(name, id);
			Project project = controllerAll.whichProject();
			project.getAllProductOwners().add(newProOwner);
			controllerAll.saveData();
			createdProOwner();
		} catch (Exception e) {
			registerProOwnerFail();
		}
	}


	public int createIdProductOwner(ControllerAll controllerAll)
	{
		final int FIRST_ID = 1;
		Project project = controllerAll.whichProject();
		if (project != null)
		{
			if (!project.getAllProductOwners().isEmpty())
			{
				return project.getAllProductOwners().get(project.getAllProductOwners().size() - 1).getId() + 1;
			}
		}
		else
		{
			projectNotFound();
		}
		return FIRST_ID;
	}

	/*------------------------------------Methods create development member-------------------------------------------*/

	public void createDevelopmentMember(ControllerAll controllerAll)
	{
		String name = getDeveloperInfo();
		int id = createIdDevelopmentMember(controllerAll);
		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		} else {
			try {
				Developer developer = new Developer(name, id);
				project.getAllTeamMembers().add(developer);
				controllerAll.saveData();
				createdDeveloper();
			} catch (Exception e) {
				registerDeveloperFail();
			}
		}
	}

	public int createIdDevelopmentMember(ControllerAll controllerAll)
	{
		int id = 1;
		Project project = controllerAll.whichProject();
		if (project.getAllTeamMembers().isEmpty())
		{
			id = 1;
		}
		else
		{
			id = project.getAllTeamMembers().get(project.getAllTeamMembers().size() - 1).getId() + 1;
		}

		return id;
	}

	/*------------------------------------Methods etc for projects-------------------------------------------*/


	public void createProject(ControllerAll controllerAll) // Move all Prints to View class-
	{
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

		try {
			Project project = new Project(id, name, startDate, endDate);
			controllerAll.getAllProjects().add(project);
			proName = name;
			controllerAll.saveData();
			Scan.print("You have successfully created the following project:\n\n" + project.toString());
		} catch (Exception e) {

			registerProjectFail();
		}
	}



	/*------------------------------------Methods etc for sprints-------------------------------------------*/

	public void createSprintBacklog(ControllerAll controllerAll)
	{
		Scan.print("\nEnter the name, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new " +
				"sprintBacklog:");
		String name = Scan.readLine("Name:");
		int startYear = Scan.readInt("Start date (YYYY):");
		int startMonth = Scan.readInt("Start date (MM):");
		int startDay = Scan.readInt("Start date (DD):");
		int endYear = Scan.readInt("End date (YYYY):");
		int endMonth = Scan.readInt("End date (MM):");
		int endDay = Scan.readInt("End date (DD):");


		String startDate = startYear + "-" + startMonth + "-" + startDay;
		String endDate = endYear + "-" + endMonth + "-" + endDay;

		try {
			SprintBacklog sprintBacklog = new SprintBacklog(name, startDate, endDate);
			Project project = controllerAll.whichProject();
			project.getAllSprintBacklogs().add(sprintBacklog); //project here gives null pointer.
			controllerAll.saveData();
			Scan.print("You have successfully created the following sprintBacklog:\n\n"
					+ sprintBacklog.toString());
		} catch (NumberFormatException e) {
			numberFormatMessage();
		} catch (Exception e) {
			backlogFail();
		}
	}

	public Task findTaskByIdSprint(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		int id = getTaskId();
		Task task = null;
		SprintBacklog sprintBacklog = contScrum.findSprintBacklogByName(controllerAll);
		Iterator<Task> iterator = sprintBacklog.getAllTasks().iterator();
		while (task == null && iterator.hasNext())
		{
			Task foundTask = iterator.next();
			if (foundTask.getId() == id)
			{
				task = foundTask;
			}
		}
		return task;
	}

	public void viewSprintBacklog(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();
		viewSprints(project);

		name = getSprintBacklogByName();
		if (project == null)
		{
			projectNotFound();
		}
		else
		{

			SprintBacklog sprint = findSprintBacklogByName(controllerAll);
			Scan.print(sprint.toString());
		}
	}

	public SprintBacklog findSprintBacklogByName(ControllerAll controllerAll)
	{
		SprintBacklog sprintBacklog = null;
		Project project = controllerAll.whichProject();
		Iterator<SprintBacklog> iterator = project.getAllSprintBacklogs().iterator();
		while (sprintBacklog == null && iterator.hasNext())
		{
			SprintBacklog foundBacklog = iterator.next();
			if (foundBacklog.getName().equalsIgnoreCase(name))
			{
				sprintBacklog = foundBacklog;
			}
		}
		return sprintBacklog;
	}

	public Task findTaskByIdProductBacklog(ControllerAll controllerAll)
	{ // in product backlog


		int id = getTaskId();
		Task task = null;

		Project project = controllerAll.whichProject();

		Iterator<Task> iterator = project.getProductBacklog().getTasksImport().iterator();
		while (task == null && iterator.hasNext())
		{
			Task foundTask = iterator.next();
			if (foundTask.getId() == id)
			{
				task = foundTask;
			}
		}
		return task;
	}


	/*------------------------------------Methods for velocity-------------------------------------------*/

	public int[] arrayOfVelocity(String input)
	{
		String[] strArray = input.split(",");
		int[] intArray = new int[strArray.length];
		for (int i = 0; i < strArray.length; i++)
		{
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}

	public int getAverageVelocity(int[] numbers)
	{
		int sum = 0;
		for (int i = 0; i < numbers.length; i++)
		{
			sum = sum + numbers[i];
		}
		return (sum / numbers.length);
	}

	public void velocity()
	{   //Call this one in menu
		String input = getVelocity();
		int[] numbers = arrayOfVelocity(input);
		int averageVelocity = getAverageVelocity(numbers);
		Scan.print("The average velocity is: " + averageVelocity);
	}

	/*------------------------------------Methods for Assigning object-------------------------------------------*/
	public void assignTask(ControllerAll controllerAll)
	{
		int idTask = assignTaskPrintIdTask();
		String sprintName = assignTaskPrintSprintName();
		Developer developer = controllerAll.findDeveloperByID();

		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();  
		}

		Task task = findSprintBacklogByName(controllerAll).getTask(idTask);
		task.getAssignedDevelopers().add(developer);
		task.setStatus("In progress");

		assignmentCompleted();
	}

	public void assignUserStory(ControllerAll controllerAll)
	{
		int number = assignUsPrintIdUs();
		String sprintName = assignUsPrintSprintName();
		Developer developer = controllerAll.findDeveloperByID();

		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}

		UserStory userStory = findSprintBacklogByName(controllerAll).getUserStory(number);
		userStory.getAssignedDevelopers().add(developer);
		userStory.setStatus("In progress");

		assignmentCompleted();

	}
}




