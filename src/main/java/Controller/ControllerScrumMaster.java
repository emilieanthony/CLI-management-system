package Controller;

import Models.*;
import Utility.Scan;

import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.*;

import static View.DevTeamView.*;
import static View.DevTeamView.getUserStoryNumber;
import static View.ScrumMasterView.*;

public class ControllerScrumMaster
{
	public static String sprintName;

	public void scrumMasterMenu(ControllerProductOwner contProOwner, ControllerAll controllerAll,
								ControllerScrumMaster contScrum) {

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
						getProjectName(controllerAll);
						break;
					case 17:
						createTaskOfUsInSBL(controllerAll);
						break;
					case 18:
						editTaskInUserStoryMenu(controllerAll);
						break;
					case 19:
						showImplementedStoryPoints(controllerAll);
						 break;
					case 20:
						showAverageVelocity(controllerAll);
						break;
					case 21:
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

	public void scrumMasterEditTaskMenu(ControllerAll controllerAll, ControllerScrumMaster contScrum) {

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
	private void viewTeamMembers(ControllerAll controllerAll) {
		Project project = controllerAll.whichProject();
		membersView();
		for (Developer developer : project.getAllTeamMembers()) {
			Scan.print(developer.toString());
		}
	}


	/*------------------------------------------Methods for tasks------------------------------------------------*/


	private void createTaskToProductBacklog(ControllerAll controllerAll) {
		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		} else {

			int id = taskUSIdGenerator(controllerAll);

			try {
				Task newTask = getTaskInfo(id);
				createdTaskReceipt(newTask);
				project.getProductBacklog().getTasks().add(newTask);
				controllerAll.saveData();
			} catch (Exception e) {
				registerTaskFail();
			}
		}
	}

	private void createTaskToSprint(ControllerAll controllerAll) {

		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		} else {

			int id = taskUSIdGenerator(controllerAll);

			try {
				Task newTask = getTaskInfo(id);
				createdTaskReceipt(newTask);
				sprintName = getSprintBacklogName();
				findSprintBacklogByName(controllerAll).getAllTasks().add(newTask);
				controllerAll.saveData();
			} catch (Exception e) {
				registerTaskFail();
			}
		}
	}

	public int taskUSIdGenerator(ControllerAll controllerAll)
	{
		// initialize int variable for ID
		Project project = controllerAll.whichProject();
		int id = project.getId() * 1000 + 1;

		ArrayList<Task> tasks = collectAllTasks(controllerAll);
		ArrayList<UserStory> stories = collectAllStories(project);


		if (!tasks.isEmpty()) {
			for (Task task : tasks) {
				if (task.getId() == id) {
					id++;
				}
			}
		}

		if (!stories.isEmpty()) {
			for (UserStory userStory : stories) {
				if (userStory.getNumber() == id) {
					id++;
				}
			}
		}


		return id;
	}


	public ArrayList<Task> collectAllTasks(ControllerAll controllerAll) {

		Project project = controllerAll.whichProject();
		//put all tasks in one and the same arrayList
		ArrayList<Task> allTasks = new ArrayList<>();

		//fetch tasks from product backlog
		ArrayList<Task> productBLTasks = project.getProductBacklog().getTasks();

		for (Task task : productBLTasks) {
			allTasks.add(task);
		}

		//fetch tasks from sprint BL
		ArrayList<SprintBacklog> sprintBLs = project.getAllSprintBacklogs();

		for (SprintBacklog sprintBL : sprintBLs) {
			ArrayList<Task> sprintTasks = sprintBL.getAllTasks();
			for (Task task : sprintTasks) {
				allTasks.add(task);
			}
		}
		//fetch tasks from user stories in sprint BL
		for (SprintBacklog sprintBacklog: project.getAllSprintBacklogs())
		{
			for (UserStory userStory: sprintBacklog.getUserStories()) {
				ArrayList<Task> UserStoryTasks = userStory.getUserStoryTasks();
				for (Task task : UserStoryTasks)
				{
					allTasks.add(task);
				}
			}
		}

		return allTasks;
	}

	private ArrayList<UserStory> collectAllStories(Project project) {

		//put all user stories in one and the same ArrayList
		ArrayList<UserStory> allStories = new ArrayList<>();

		// fetch all user stories from product backlog
		ArrayList<UserStory> productBLStories = project.getProductBacklog().getAllUserStories();

		for (UserStory story : productBLStories) {
			allStories.add(story);
		}

		//fetch user story from sprint BL
		ArrayList<SprintBacklog> sprintBLs = project.getAllSprintBacklogs();

		for (SprintBacklog sprintBL : sprintBLs) {
			//
			ArrayList<UserStory> sprintBLStories = sprintBL.getUserStories();
			for (UserStory story : sprintBLStories) {
				allStories.add(story);
			}
		}

		return allStories;
	}

	private void moveTaskOrUSToSprintBacklog(ControllerProductOwner contProOwner, ControllerAll controllerAll) {
		contProOwner.viewProBacklog(controllerAll);

		String input = moveObjectToBacklogPrint();

		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		} else {
			if (input.equals("1")) {
				int idTask = specifyTask();
				sprintName = specifySprint();

				Task taskInBacklog = project.getProductBacklog().getTask(idTask);

				if (taskInBacklog == null) {
					invalidTaskPrint();
				} else if (!project.getAllSprintBacklogs().contains(sprintName)) {
					invalidSprintBacklog();
				} else {
					taskInBacklog.setSprintName(sprintName);
					Task taskToMove = taskInBacklog;
					findSprintBacklogByName(controllerAll).getAllTasks().add(taskToMove);
					project.getProductBacklog().getTasks().remove(taskToMove);
					controllerAll.saveData();
					movedObject();
				}


			}

			if (input.equals("2")) {
				int usName = Scan.readInt("Write the the number of the user story you want to" +
						" move: "); // Move to view class.
				sprintName = Scan.readLine("Write the sprintName of the sprint you want to move your user story to: ");

				project.getProductBacklog().getUserStory(usName).setSprintName(sprintName);
				UserStory userStoryToMove = project.getProductBacklog().getUserStory(usName);
				findSprintBacklogByName(controllerAll).getUserStories().add(userStoryToMove);
				project.getProductBacklog().getAllUserStories().remove(userStoryToMove);
				controllerAll.saveData();
				movedObject();
			} else {
				return;
			}
		}
	}

	private void moveTaskOrUSToProductBacklog(ControllerAll controllerAll) {
		viewSprintBacklog(controllerAll);

		String input = moveObjectToBacklogPrint();

		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		} else {
			if (input.equals("1")) {
				int idTask = IdTaskToMovePrint();
				sprintName = sprintNameToMovePrint();

				findSprintBacklogByName(controllerAll).getTask(idTask).setSprintName("");
				Task taskToMove = findSprintBacklogByName(controllerAll).getTask(idTask);
				project.getProductBacklog().getTasks().add(taskToMove);
				findSprintBacklogByName(controllerAll).getAllTasks().remove(taskToMove);
				controllerAll.saveData();
				movedObject();
			}

			if (input.equals("2")) {
				int usNumber = numerUsToMove();
				sprintName = sprintNameToMove();

				findSprintBacklogByName(controllerAll).getUserStory(usNumber).setSprintName("");
				UserStory userStoryToMove = findSprintBacklogByName(controllerAll).getUserStory(usNumber);
				project.getProductBacklog().getAllUserStories().add(userStoryToMove);
				findSprintBacklogByName(controllerAll).getUserStories().remove(userStoryToMove);
				controllerAll.saveData();
				movedObject();
			} else {
				return;
			}
		}
	}


	private void editPriorityNumberTask(ControllerAll controllerAll) {
		final int PRIORITY_LOWEST = 1;
		final int PRIORITY_HIGHEST = 5;
		Project project = controllerAll.whichProject();
		if (project == null) {
			projectNotFound();
		} else {
			ArrayList<Task> tasks = collectAllTasks(controllerAll);
			int idTaskEdit = IdTaskEdit();
			for (Task task : tasks) {
				if (task.getId() == idTaskEdit) {
					int newPriorityNumberTask = newPriorityNumberTask();
					if ((newPriorityNumberTask >= PRIORITY_LOWEST) && (newPriorityNumberTask <= PRIORITY_HIGHEST)) {
						task.setPriorityNumber(newPriorityNumberTask);
						objectEdited();
					} else {
						defaultMessage();
					}

					return;
				}

				taskNotFound();
			}
		}
	}

	private void editStatusTask(ControllerAll controllerAll) {

		Project project = controllerAll.whichProject();
		if (project == null) {
			projectNotFound();
		} else {

			ArrayList<Task> tasks = collectAllTasks(controllerAll);


			int idTaskEdit = IdTaskEdit();

			for (Task task : tasks) {
				if (task.getId() == idTaskEdit) {
					int newStatusTask = newStatusTask();

					if (newStatusTask == 1) {
						task.setStatus("Open");
						objectEdited();

					} else if (newStatusTask == 2) {
						task.setStatus("Work in Progress");
						objectEdited();

					} else if (newStatusTask == 3) {
						task.setStatus("Complete");
						objectEdited();

					} else {
						defaultMessage();
					}

					return;
				}
			}
			taskNotFound();
		}
	}


	public void removeTaskProductBacklog(ControllerAll controllerAll) {

		Project project = controllerAll.whichProject();
		if (project == null) {
			projectNotFound();
		} else {

			int idTaskRemove = IdTaskRemove();

			ArrayList<Task> tasks = collectAllTasks(controllerAll);

			for (Task task : tasks) {
				if (task.getId() == idTaskRemove) {

					project.getProductBacklog().getTasks().remove(project.getProductBacklog().getTask(idTaskRemove));
					controllerAll.saveData();
					removeObject();
				} else {

					defaultMessage();

				}
				return;
			}
			taskNotFound();
		}
	}

	public void removeTaskSprintBacklog(ControllerAll controllerAll, ControllerScrumMaster contScrum) {

		sprintName = getSprintBacklogByName();

		SprintBacklog sprintBacklog = findSprintBacklogByName(controllerAll);
		Task task = findTaskByIdSprint(controllerAll, contScrum);

		if (task == null) {
			taskNotFound();
		} else  {
			sprintBacklog.getAllTasks().remove(task);
			controllerAll.saveData();
			removeObject();
		}

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
		try {
			Project project = projectInput();
			controllerAll.getAllProjects().add(project);

			controllerAll.saveData();
			createProjectPrint(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/*------------------------------------Methods etc for sprints-------------------------------------------*/

	public void createSprintBacklog(ControllerAll controllerAll)
	{
		try {
			SprintBacklog sprintBacklog = createSprintInfo();
			Project project = controllerAll.whichProject();
			project.getAllSprintBacklogs().add(sprintBacklog); //project here gives null pointer.
			controllerAll.saveData();
			successfulSprintLog(sprintBacklog);
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

		sprintName = getSprintBacklogByName();
		if (project == null)
		{
			projectNotFound();
		}
		else
		{

			SprintBacklog sprint = findSprintBacklogByName(controllerAll);
			if(sprint == null){
				noSprintPrint();
				menuScrumMaster();
			}
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
			if (foundBacklog.getName().equalsIgnoreCase(sprintName))
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

		Iterator<Task> iterator = project.getProductBacklog().getTasks().iterator();
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

	public void velocity() {
		String input = getVelocity();
		int[] numbers = arrayOfVelocity(input);
		int averageVelocity = getAverageVelocity(numbers);
		printVelocity(averageVelocity);
	}

	/*------------------------------------Methods for Assigning object-------------------------------------------*/
	public void assignTask(ControllerAll controllerAll)
	{
		int idTask = assignTaskPrintIdTask();
		sprintName = assignTaskPrintSprintName();
		Developer developer = controllerAll.findDeveloperByID();

		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}

		Task task = findSprintBacklogByName(controllerAll).getTask(idTask);
		if(task == null){
			nullTaskPrint();
		}else{
			task.getAssignedDevelopers().add(developer);
			task.setStatus("In progress");
			controllerAll.saveData();
			assignmentCompleted();
		}

	}

	public void assignUserStory(ControllerAll controllerAll)
	{
		int number = assignUsPrintIdUs();
		sprintName = assignUsPrintSprintName();
		Developer developer = controllerAll.findDeveloperByID();

		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}

		UserStory userStory = findSprintBacklogByName(controllerAll).getUserStory(number);
		userStory.getAssignedDevelopers().add(developer);
		userStory.setInProgress();
		controllerAll.saveData();

		assignmentCompleted();

	}
	//-------------------------------------------TASK USER STORY -------------------------------//

	public void editTaskInUserStoryMenu(ControllerAll controllerAll)
	{

		boolean running = true;
		do {

			int option;

			try {
				option = menuEditTaskInUserStory();
				switch (option) {
					case 1:
						editUStorySBLTaskPriority(controllerAll);
						controllerAll.saveData();
						break;
					case 2:
						editUStorySBLTaskStatus(controllerAll);
						controllerAll.saveData();
						break;
					case 3:
						removeUserStorySBLTask(controllerAll);
						controllerAll.saveData();
						break;
					case 4:
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

	public UserStory findUStoryByNumberSBL(int number, ControllerAll controllerAll)
	{
		UserStory userStory = null;
		SprintBacklog sprintBacklog = findSprintBacklogByName(controllerAll);
		Iterator<UserStory> iterator = sprintBacklog.getUserStories().iterator();
		while (userStory == null && iterator.hasNext())
		{
			UserStory foundUserStory = iterator.next();
			if (foundUserStory.getNumber() == number)
			{
				userStory = foundUserStory;
				Scan.print(userStory.toString());
			}
		}
		return userStory;
	}
	private void viewSprintBacklogT(ControllerAll controllerAll) {
		Project project = controllerAll.whichProject();
		if (project == null) {
			projectNotFound();
		} else {

			SprintBacklog sprint = findSprintBacklogByName(controllerAll);
			Scan.print(sprint.toString());
		}
	}


	public void createTaskOfUsInSBL(ControllerAll controllerAll){
		sprintName = getSprintBacklogByName();
		viewSprintBacklogT(controllerAll);
		int UsNumber = getUserStoryNumber();
		int id = taskUSIdGenerator(controllerAll);
		Task task = null;
		try {
			task = getTaskInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserStory userStory = findUStoryByNumberSBL(UsNumber,controllerAll);
		userStory.getUserStoryTasks().add(task);
		checkUStoryStatus(userStory,controllerAll);
		controllerAll.saveData();

	}


	public void checkUStoryStatus(UserStory userStory, ControllerAll controllerAll){
		userStory.getBinary().clear();
		for (Task foundTasks: userStory.getUserStoryTasks()) {
			if (foundTasks.getStatus().equalsIgnoreCase("Done")) {
				userStory.getBinary().add(true);
			}else {
				userStory.getBinary().add(false);
			}
			if (!(userStory.getBinary().contains(false))){
				userStory.setDone();
			}else {
				userStory.setOpen();
			}
		}controllerAll.saveData();
	}


	public Task findTaskInUserSSBL(int UsNumber, ControllerAll controllerAll)
	{
		Task task = null;
		int taskId = getTaskId();
		UserStory userStory = findUStoryByNumberSBL(UsNumber,controllerAll);
		Iterator<Task> iterator = userStory.getUserStoryTasks().iterator();
		while (task == null && iterator.hasNext())
		{
			Task foundTask = iterator.next();
			if (foundTask.getId() == taskId)
			{
				task = foundTask;
				Scan.print(task.toString());
			}
		}
		return task;
	}

	public void editUStorySBLTaskPriority(ControllerAll controllerAll){

		sprintName = getSprintBacklogByName();
		int UsNumber = getUserStoryNumber();
		Task task = findTaskInUserSSBL(UsNumber,controllerAll);
		int newPriorityNumber = newPriorityNumberTask();
		task.setPriorityNumber(newPriorityNumber);
		Scan.print(task.toString());
		objectEdited();
	}

	public void editUStorySBLTaskStatus(ControllerAll controllerAll){

		sprintName = getSprintBacklogByName();
		int UsNumber = getUserStoryNumber();
		UserStory userStory = findUStoryByNumberSBL(UsNumber,controllerAll);
		Task task = findTaskInUserSSBL(UsNumber,controllerAll);
		int option = newStatusTask();
		if (option == 1){
			task.setOpen();
			Scan.print(task.toString());
			objectEdited();
		}else if (option ==2){
			task.setInProgress();
			Scan.print(task.toString());
			objectEdited();
		}else if (option == 3){
			task.setDone();
			checkUStoryStatus(userStory,controllerAll);
			Scan.print(task.toString());
			objectEdited();
			controllerAll.saveData();
		}else{
			invalidOption();
		}

	}

	public void removeUserStorySBLTask(ControllerAll controllerAll){

		sprintName = getSprintBacklogByName();
		int UsNumber = getUserStoryNumber();
		UserStory userStory = findUStoryByNumberSBL(UsNumber,controllerAll);
		Task task = findTaskInUserSSBL(UsNumber,controllerAll);
		userStory.getUserStoryTasks().remove(task);
		Scan.print(userStory.toString());
		removedTaskInUserStory();

	}


}







