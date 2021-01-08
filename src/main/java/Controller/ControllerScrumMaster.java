package Controller;

import Exceptions.*;
import Models.*;
import Utility.DataManagement;
import Utility.Scan;
import View.AllView;
import View.ProductOwnerView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.*;
import static View.DevTeamView.*;
import static View.DevTeamView.getUserStoryID;
import static View.ProductOwnerView.*;
import static View.ScrumMasterView.*;

public class ControllerScrumMaster
{
	public static String sprintName;
	public static int option;

	public void scrumMasterMenu(ControllerAll controllerAll, ControllerProductOwner contProOwner,
								ControllerScrumMaster contScrum)
	{

		boolean running = true;
		do
		{
			try
			{
				option = menuScrumMaster();

				switch (option)
				{
					case 1:
						createProject(controllerAll);
						break;
					case 2:
						createSprintBacklog(controllerAll);
						break;
					case 3:
						createProductOwner(controllerAll);
						break;
					case 4:
						createDevelopmentMember(controllerAll);
						break;
					case 5:
						USOptionsMenu(controllerAll,contScrum);
						break;
					case 6:
						TasksOptionsMenu(controllerAll,contScrum);
						break;
					case 7:
						deadlinesOptionsMenu(controllerAll);
						break;
					case 8:
						viewProjectPartsMenu(controllerAll,contProOwner);
						break;
					case 9:
						moveOptionsScrumMenu(controllerAll,contProOwner);
						break;
					case 10:
						showImplementedStoryPoints(controllerAll);
						break;
					case 11:
						showAverageVelocity(controllerAll);
						break;
					case 12:
						velocityCalculator();
						break;
					case 13:
						controllerAll.switchProject(controllerAll);
						break;
					case 14:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (NumberFormatException e)
			{
				numberFormatMessage();
			}
		} while (running);
	}


	private void USOptionsMenu(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		boolean running = true;

		do
		{
			try
			{
				option = userStoryOptionsMenu();
				switch (option)
				{
					case 1:
						assignUserStory(controllerAll);
						break;
					case 2:
						editUSInSprintBLMenu(controllerAll);
						break;
					case 3:
						createTaskOfUsInSBL(controllerAll,contScrum);
						break;
					case 4:
						editTaskInUserStoryMenu(controllerAll,contScrum);
						break;
					case 5:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (Exception e)
			{
				invalidInputPrint();
			}
		} while (running);
	}

	private void TasksOptionsMenu(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		boolean running = true;

		do
		{
			try
			{
				option = taskOptionsMenu();
				switch (option)
				{
					case 1:
						createTaskToProductBacklog(controllerAll);
						break;
					case 2:
						createTaskToSprint(controllerAll);
						break;
					case 3:
						assignTask(controllerAll);
						break;
					case 4:
						scrumMasterEditTaskMenu(controllerAll,contScrum);
						break;
					case 5:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (Exception e)
			{
				invalidInputPrint();
			}
		} while (running);
	}

	private void deadlinesOptionsMenu(ControllerAll controllerAll)
	{
		boolean running = true;

		do
		{
			try
			{
				option = deadlinesMenu();
				switch (option)
				{
					case 1:
						viewAndSetTaskDeadline(controllerAll);
						break;
					case 2:
						viewAndSetUSDeadline(controllerAll);
						break;
					case 3:
						controllerAll.viewSprintDeadlines();
						break;
					case 4:
						controllerAll.viewTaskDeadlines();
						break;
					case 5:
						controllerAll.viewUStoryDeadlines();
						break;
					case 6:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (Exception e)
			{
				invalidInputPrint();
			}
		} while (running);
	}

	private void viewProjectPartsMenu(ControllerAll controllerAll, ControllerProductOwner contProOwner)
	{
		boolean running = true;

		do
		{
			try
			{
				option = viewProPartsMenu();
				switch (option)
				{

					case 1:
						contProOwner.viewProBacklog(controllerAll);
						break;
					case 2:
						viewSprintBacklog(controllerAll);
						break;
					case 3:
						viewTeamMembers(controllerAll);
						break;
					case 4:
						controllerAll.viewCompletedTasks();
						break;
					case 5:
						controllerAll.viewCompletedUStories();
						break;
					case 6:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (Exception e)
			{
				invalidInputPrint();
			}
		} while (running);
	}


	private void moveOptionsScrumMenu(ControllerAll controllerAll,
									  ControllerProductOwner contProOwner)
	{
		boolean running = true;

		do
		{
			try
			{
				option = moveOptionsMenu();
				switch (option)
				{
					case 1:
						moveTaskOrUStoryToSBL(contProOwner,controllerAll);
						break;
					case 2:
						moveTaskOrUStoryToPBL(controllerAll);
						break;
					case 3:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (Exception e)
			{
				invalidInputPrint();
			}
		} while (running);
	}

	//-----------------------------------------------------Second Switch----------------------------------------------

	private void scrumMasterEditTaskMenu(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		boolean running = true;

		do
		{
			try
			{
				option = menuEditTask();
				switch (option)
				{
					case 1:
						editPriorityNumberTask(controllerAll);
						break;
					case 2:
						editStatusTask(controllerAll);
						break;
					case 3:
						viewAndSetTaskDeadline(controllerAll);
						break;
					case 4:
						removeTaskSprintBacklog(controllerAll, contScrum);
						break;
					case 5:
						removeTaskProductBacklog(controllerAll);
					case 6:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (Exception e)
			{
				invalidInputPrint();
			}
		} while (running);
	}

	//-------------------------------------------------------------------------------------------------------------------

	private void viewTeamMembers(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();
		membersView();

		for (Developer developer : project.getAllTeamMembers())
		{
			Scan.print(developer.toString());
		}
	}

	//------------------------------------------Methods for tasks------------------------------------------------//

	private void setTaskDeadline(ControllerAll controllerAll){

		Task task = controllerAll.findTaskById();

		String deadline = getEndDate();

		while (!legalDate(deadline, controllerAll)) {
			dateIsBeforeOrAfterProjectDate();
			deadline = getEndDate();
		}

		task.setDeadline(deadline);

		setTaskDeadlineReceipt(task);

		controllerAll.saveData();

	}

	private void viewAndSetTaskDeadline(ControllerAll controllerAll){
		controllerAll.viewTaskDeadlines();
		setTaskDeadline(controllerAll);
	}

	private boolean legalDate(String deadline, ControllerAll controllerAll){
		Project project = controllerAll.whichProject();
		String projectEndDate = project.getEndDate();
		LocalDate proEndDate = DataManagement.stringToLocalDate(projectEndDate);
		String projectStartDate = project.getStartDate();
		LocalDate proStartDate = DataManagement.stringToLocalDate(projectStartDate);

		LocalDate localDeadline = DataManagement.stringToLocalDate(deadline);

		boolean legal;

		if(localDeadline.isAfter(proEndDate) || localDeadline.isBefore(proStartDate)){
			legal = false;
		} else{
			legal = true;
		}

		return legal;
	}

	private void createTaskToProductBacklog(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{
			int id = taskUSIdGenerator(controllerAll);

			try
			{
				Task newTask = getTaskInfo(id);
				createdTaskReceipt(newTask);
				project.getProductBacklog().getTasks().add(newTask);
				controllerAll.saveData();
				taskCreatedToPBacklog();


			} catch (NegativeId n){
				negativeIDPrint();

			} catch (InvalidPriorityNumber i){
				invalidNumberPrint();

			} catch (EstimatedHours e) {
				negativeNumberPrint();

			} catch (EmptyName e){
				emptyName();

			} catch (EmptyDescription e){
				AllView.errorEmptyDescription();

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
			int id = taskUSIdGenerator(controllerAll);

			try
			{
				boolean thereIsASprintBacklog = checkForSprintBacklog(project);

				if (thereIsASprintBacklog){
					Task newTask = getTaskInfo(id);
					createdTaskReceipt(newTask);

					printSprints(project);
					sprintName = getSprintBacklogName();
					findSprintBacklogByName(controllerAll).getAllTasks().add(newTask);

					controllerAll.saveData();
					taskCreatedToSBacklog();

				} else {
					noSprintBacklogYet();
					createSprintBacklog(controllerAll);
				}


			} catch (NegativeId n){
				negativeIDPrint();

			} catch (InvalidPriorityNumber i){
				invalidNumberPrint();

			} catch (EstimatedHours e) {
				negativeNumberPrint();

			} catch (EmptyName e){
				emptyName();

			} catch (EmptyDescription e){
				AllView.errorEmptyDescription();

			} catch (Exception e) {
				registerTaskFail();
			}
		}
	}

	private boolean checkForSprintBacklog(Project project){
		if(project.getAllSprintBacklogs().isEmpty()){
			return false;
		} else {
			return true;
		}
	}

	public int taskUSIdGenerator(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();
		int id = project.getId() * 1000 + 1;

		ArrayList<Task> tasks = controllerAll.collectAllTasks();
		ArrayList<UserStory> stories = controllerAll.collectAllStories();

		ArrayList<Integer> ids = new ArrayList<>();

		if (!tasks.isEmpty())
		{
			for (Task task : tasks)
			{
				ids.add(task.getId());
			}
		}

		if (!stories.isEmpty())
		{
			for (UserStory userStory : stories) {

				ids.add(userStory.getId());

			}
		}

		if (!ids.isEmpty()) {
			id = ids.get(ids.size() - 1) + 1;

			while (ids.contains(id)){
				id++;
			}
		}


		return id;
	}

	public void moveTaskOrUStoryToSBL(ControllerProductOwner contProOwner, ControllerAll controllerAll)
	{

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
				contProOwner.viewProBacklog(controllerAll);
				int idTask = specifyTask();
				printSprints(project);
				sprintName = specifySprint();

				Task taskInBacklog = project.getProductBacklog().getTask(idTask);

				if (taskInBacklog == null)
				{
					invalidTaskPrint();
				}
				else
				{
					Task taskToMove = taskInBacklog;
					findSprintBacklogByName(controllerAll).getAllTasks().add(taskToMove);
					project.getProductBacklog().getTasks().remove(taskToMove);
					controllerAll.saveData();
					movedObject();
				}
			}

			if (input.equals("2"))
			{
				contProOwner.viewProBacklog(controllerAll);
				int usName = idUsToMove();
				printSprints(project);
				sprintName = sprintNameToMovePrintUS();

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

	private void moveTaskOrUStoryToPBL(ControllerAll controllerAll)
	{
		String input = moveObjectToBacklogPrint();
		viewSprintBacklog(controllerAll);
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
				sprintName = sprintNameToMovePrint();

				Task taskToMove = findSprintBacklogByName(controllerAll).getTask(idTask);
				project.getProductBacklog().getTasks().add(taskToMove);
				findSprintBacklogByName(controllerAll).getAllTasks().remove(taskToMove);
				controllerAll.saveData();
				movedObject();
			}

			if (input.equals("2"))
			{
				int usNumber = idUsToMove();
				sprintName = sprintNameToMove();

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
			ArrayList<Task> tasks = controllerAll.collectAllTasks();
			controllerAll.viewAllTasks();
			int idTaskEdit = IdTaskEdit();

			for (Task task : tasks)
			{
				if (task.getId() == idTaskEdit)
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
			ArrayList<Task> tasks = controllerAll.collectAllTasks();
			controllerAll.viewAllTasks();
			int idTaskEdit = IdTaskEdit();

			for (Task task : tasks)
			{
				if (task.getId() == idTaskEdit)
				{
					int newStatusTask = newStatusTask();

					if (newStatusTask == 1)
					{
						task.setStatus("Open");
						objectEdited();
					}
					else if(newStatusTask == 2)
					{
						task.setStatus("Assigned");
						objectEdited();
					}
					else if (newStatusTask == 3)
					{
						task.setStatus("Work in Progress");
						objectEdited();
					}
					else if (newStatusTask == 4)
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

	private void removeTaskProductBacklog(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{
			Scan.print(project.getProductBacklog().toString());
			int idTaskRemove = IdTaskRemove();
			ArrayList<Task> tasks = controllerAll.collectAllTasks();

			for (Task task : tasks)
			{
				if (task.getId() == idTaskRemove)
				{
					project.getProductBacklog().getTasks().remove(project.getProductBacklog().getTask(idTaskRemove));
					controllerAll.saveData();
					removeObject();
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

	private void removeTaskSprintBacklog(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		Project project = controllerAll.whichProject();

		showAllSprintBacklogs(project, "tasks");
		sprintName = getSprintBacklogByName();

		SprintBacklog sprintBacklog = findSprintBacklogByName(controllerAll);
		Task task = findTaskByIdSprint(controllerAll, contScrum);

		if (task == null)
		{
			taskNotFound();
		}
		else
		{
			sprintBacklog.getAllTasks().remove(task);
			controllerAll.saveData();
			removeObject();
		}
	}

	//------------------------------------------Methods to create product owner------------------------------------------------//

	private void createProductOwner(ControllerAll controllerAll)
	{
		String name = getProOwnerInfo();
		int id = createIdProductOwner(controllerAll);
		try
		{
			ProductOwner newProOwner = new ProductOwner(name, id);
			Project project = controllerAll.whichProject();
			project.getAllProductOwners().add(newProOwner);
			controllerAll.saveData();
			createdProOwner();

		}catch (EmptyName e){
			emptyName();
		}catch (NegativeId n){
			negativeId();
		}
		catch (Exception e)
		{
			AllView.errorPrint();
		}
	}

	private int createIdProductOwner(ControllerAll controllerAll)
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

	//------------------------------------Methods to create development member-----------------------------------------//

	private void createDevelopmentMember(ControllerAll controllerAll)
	{
		String name = getDeveloperInfo();
		int id = createIdDevelopmentMember(controllerAll);
		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{
			try
			{
				Developer developer = new Developer(name, id);
				project.getAllTeamMembers().add(developer);
				controllerAll.saveData();
				createdDeveloper();

			}
			catch (EmptyName e){
				emptyName();
			}

			catch (Exception e)
			{
				registerDeveloperFail();
			}
		}
	}

	private int createIdDevelopmentMember(ControllerAll controllerAll)
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

	//------------------------------------Methods for projects-------------------------------------------//

	public void createProject(ControllerAll controllerAll)
	{
		try
		{
			Project project = projectInput(controllerAll);
			controllerAll.getAllProjects().add(project);
			controllerAll.saveData();
			createProjectPrint(project);

		} catch (EmptyName e) {
			emptyName();

		} catch (NegativeId n){
			negativeId();

		} catch (WrongDate w){
			wrongDatePrint();

		} catch (Exception e){
			AllView.errorPrint();
		}
	}

	//------------------------------------Methods etc for sprints-------------------------------------------//

	private void createSprintBacklog(ControllerAll controllerAll)
	{
		try
		{
			SprintBacklog sprintBacklog = createSprintInfo();
			Project project = controllerAll.whichProject();

			project.getAllSprintBacklogs().add(sprintBacklog);
			controllerAll.saveData();

			successfulSprintLog(sprintBacklog);

		} catch (EmptyName e) {
			emptyName();

		} catch (WrongDate w){
			wrongDatePrint();

		} catch (Exception e) {
			AllView.errorPrint();
		}
	}

	private Task findTaskByIdSprint(ControllerAll controllerAll, ControllerScrumMaster contScrum)
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

		printSprints(project);
		sprintName = getSprintBacklogName();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{
			SprintBacklog sprint = findSprintBacklogByName(controllerAll);

			if (sprint == null)
			{
				menuScrumMaster();

			} else {

				printSprint(sprint);

			}
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

		if(sprintBacklog == null)
		{
			invalidSprintBacklog();
		}
		return sprintBacklog;
	}

	public UserStory getUSFromSBL(ControllerAll controllerAll){
		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}

		showAllSprintBacklogs(project, "user stories");

		sprintName = getSprintBacklogByName();

		int USid = getUserStoryID();

		UserStory userStory = findUStoryByIdSBL(USid, controllerAll);

		return userStory;
	}

	public void editUSInSprintBLMenu(ControllerAll controllerAll){

		boolean running = true;

		UserStory userStory = getUSFromSBL(controllerAll);
		printUStoryInfo(userStory);

		if (userStory == null) {
			nullUserStoryPrint();
		} else {
			do
			{
				try
				{
					option = menuEditUStoryInSBL();
					switch (option)
					{
						case 1:
							editUSStoryPoints( userStory, controllerAll);//Edit user story points
							break;
						case 2:
							editUSPriority(userStory, controllerAll);//edit priority number
							break;
						case 3:
							changeUSStatus(userStory,controllerAll);//change user story status
							break;
						case 4:
							setUserStoryDeadline(userStory, controllerAll);//set user story deadline
							break;
						case 5:
							removeUSFromSBL(userStory, controllerAll);//remove user story from sprint backlog
						case 6:
							running = false;
							break;
						default:
							defaultMessage();
					}
				} catch (Exception e)
				{
					e.printStackTrace();

					invalidInputPrint();
				}
			} while (running);
		}


	}

	public void editUSStoryPoints(UserStory userStory, ControllerAll controllerAll) {

		int newUSSPoints = getNewUSStoryPoints();

		while (newUSSPoints < 0){
			negativeNumberPrint();
			newUSSPoints = getNewUSStoryPoints();
		}

		userStory.setStoryPoints(newUSSPoints);
		controllerAll.saveData();
		userStoryEditConf(userStory);

	}

	public void editUSPriority(UserStory userStory, ControllerAll controllerAll) {

		int newUSPriority = getNewUSPriority();

		while (newUSPriority < 0 || newUSPriority > 5){
			wrongPrioNumber();
			newUSPriority = getNewUSPriority();
		}

		userStory.setPriorityNumber(newUSPriority);
		controllerAll.saveData();
		userStoryEditConf(userStory);

	}

	public void changeUSStatus(UserStory userStory, ControllerAll controllerAll){

		int newUSStatus = ProductOwnerView.getNewUSStatus();

		if (newUSStatus == 1) {
			setUSOpen(userStory, controllerAll);

		} else if (newUSStatus == 2) {
			setUSAssigned(userStory, controllerAll);

		} else if (newUSStatus == 3) {
			controllerAll.setUSInProgress(userStory);

		} else if (newUSStatus == 4) {
			controllerAll.setUSCompleted(userStory);

		} else {
			invalidOption();
		}


	}

	public void setUSOpen(UserStory userStory, ControllerAll controllerAll){
		userStory.setOpen();
		controllerAll.saveData();
		userStoryEditConf(userStory);
	}

	public void setUSAssigned(UserStory userStory, ControllerAll controllerAll){
		userStory.setAssigned();
		controllerAll.saveData();
		userStoryEditConf(userStory);
	}


	public void removeUSFromSBL(UserStory userStory, ControllerAll controllerAll){

		SprintBacklog sprintBacklog = findSprintBacklogByName(controllerAll);

		boolean confirmRemoval = removingUSMsg(userStory);
		if (confirmRemoval){
			sprintBacklog.getUserStories().remove(userStory);
		}

	}

	//------------------------------------Methods for velocity-------------------------------------------//


	private int[] arrayOfVelocity(String input)
	{
		String[] strArray = input.split(",");
		int[] intArray = new int[strArray.length];

		for (int i = 0; i < strArray.length; i++)
		{
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}

	private int getAverageVelocity(int[] numbers)
	{
		int sum = 0;

		for (int i = 0; i < numbers.length; i++)
		{
			sum = sum + numbers[i];
		}
		return (sum / numbers.length);
	}

	private void velocityCalculator()
	{
		String input = getVelocity();
		int[] numbers = arrayOfVelocity(input);
		int averageVelocity = getAverageVelocity(numbers);

		printVelocity(averageVelocity);
	}

	//------------------------------------Methods for Assigning object-------------------------------------------//

	private void assignTask(ControllerAll controllerAll)
	{
		printAllTaskIDAndName(controllerAll.collectAllTasks());

		Task task = controllerAll.findTaskById();

		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();

		} else if (task == null) {
			nullTaskPrint();

		} else {
			Developer developer = getDeveloper(project, controllerAll);

			if (!(developer==null)) {

				task.getAssignedDevelopers().add(developer);
				task.setAssigned();
				controllerAll.saveData();
				assignmentCompleted();
			}
		}
	}

	private void assignUserStory(ControllerAll controllerAll)
	{
		showAllUserStories(controllerAll);
		UserStory userStory = controllerAll.findUStoryByNumber();

		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();

		} else if (userStory == null) {
			nullUserStoryPrint();

		} else {
			Developer developer = getDeveloper(project, controllerAll);

			if (!(developer==null)) {
				userStory.getAssignedDevelopers().add(developer);
				userStory.setAssigned();
				controllerAll.saveData();
				assignmentCompleted();
			}
		}
	}

	public Developer getDeveloper(Project project, ControllerAll controllerAll){
		showAllTeamMembers(project);
		Developer developer = controllerAll.findDeveloperByID();

		if (project.getAllTeamMembers().isEmpty())
		{
			noDeveloperYet();
			createDevelopmentMember(controllerAll);
		}
		else if (!(project.getAllTeamMembers().contains(developer)))
		{
			invalidDeveloperId();
		}
		return developer;
	}

	//--------------------------------------Edit task in US menu ------------------------------//

	public void editTaskInUserStoryMenu(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		boolean running = true;

		do
		{
			try
			{
				option = menuEditTaskInUserStory();

				switch (option)
				{
					case 1:
						editUStorySBLTaskPriority(controllerAll,contScrum);
						controllerAll.saveData();
						break;
					case 2:
						editUStorySBLTaskStatus(controllerAll,contScrum);
						controllerAll.saveData();
						break;
					case 3:
						removeUserStorySBLTask(controllerAll,contScrum);
						controllerAll.saveData();
						break;
					case 4:
						running = false;
						break;
					default:
						defaultMessage();
				}
			} catch (Exception e)
			{
				invalidInputPrint();
			}
		} while (running);
	}

	//---------------------------------------------------------------------------------------------//

	public UserStory findUStoryByIdSBL(int id, ControllerAll controllerAll)
	{
		UserStory userStory = null;
		SprintBacklog sprintBacklog = findSprintBacklogByName(controllerAll);
		Iterator<UserStory> iterator = sprintBacklog.getUserStories().iterator();


		while (userStory == null && iterator.hasNext())
		{
			UserStory foundUserStory = iterator.next();
			if (foundUserStory.getId() == id)
			{

				userStory = foundUserStory;

				printUStoryInfo(userStory);

			}
		}
		return userStory;
	}


	private void createTaskOfUsInSBL(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		{
			Project project = controllerAll.whichProject();
			if (project == null) {
				projectNotFound();
			}

			showAllSprintBacklogs(project, "user stories");
			sprintName = getSprintBacklogByName();

			int USNumber = getUserStoryID();

			UserStory userStory = contScrum.findUStoryByIdSBL(USNumber, controllerAll);
			if (userStory == null) {
				nullUserStoryPrint();
			} else {
				Scan.print(userStory.toString());

				int id = taskUSIdGenerator(controllerAll);
				Task task = null;

				try {
					taskCreation();
					task = getTaskInfo(id);
				} catch (Exception e) {
					e.printStackTrace();
				}

				userStory.getUserStoryTasks().add(task);
				createdTaskReceipt(task);
				printTask(task);
				checkUStoryStatus(userStory, controllerAll);
				controllerAll.saveData();
			}
		}
	}

	//--------------------------------------Methods for edit User Stories ------------------------------//

	private void checkUStoryStatus(UserStory userStory, ControllerAll controllerAll)
	{
		userStory.getBinary().clear();
		for (Task foundTasks : userStory.getUserStoryTasks()) {

			if (foundTasks.getStatus().equalsIgnoreCase("Complete")) {
				userStory.getBinary().add(true);
			}
			else {
				userStory.getBinary().add(false);
			}
			if (!(userStory.getBinary().contains(false))) {
				userStory.setComplete(); }
		}
		controllerAll.saveData();
	}

	private Task findTaskInUserSSBL(int UsNumber, ControllerAll controllerAll)
	{
		Task task = null;
		int taskId = getTaskId();
		UserStory userStory = findUStoryByIdSBL(UsNumber, controllerAll);
		Iterator<Task> iterator = userStory.getUserStoryTasks().iterator();

		while (task == null && iterator.hasNext())
		{
			Task foundTask = iterator.next();
			if (foundTask.getId() == taskId)
			{
				task = foundTask;
				printTask(task);
			}
		}
		return task;
	}

	private void editUStorySBLTaskPriority(ControllerAll controllerAll,
										   ControllerScrumMaster contScrum)
	{
		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}

		showAllSprintBacklogs(project, "user stories");
		sprintName = getSprintBacklogByName();

		int USNumber = getUserStoryID();

		UserStory userStory = contScrum.findUStoryByIdSBL(USNumber, controllerAll);
		if (userStory == null)
		{
			nullUserStoryPrint();
		}

		else {
			Task task = findTaskInUserSSBL(USNumber, controllerAll);
			if (task == null) {
				nullTaskPrint();
			} else {

				int newPriorityNumber = newPriorityNumberTask();
				task.setPriorityNumber(newPriorityNumber);
				printTask(task);
				objectEdited();
			}
		}
	}

	private void editUStorySBLTaskStatus(ControllerAll controllerAll,
										 ControllerScrumMaster contScrum)
	{

		Project project = controllerAll.whichProject();
		if (project == null)
		{
			projectNotFound();
		}

		showAllSprintBacklogs(project, "user stories");
		sprintName = getSprintBacklogByName();

		int USNumber = getUserStoryID();

		UserStory userStory = contScrum.findUStoryByIdSBL(USNumber, controllerAll);
		if (userStory == null)
		{
			nullUserStoryPrint();
		}

		else
		{
			Task task = findTaskInUserSSBL(USNumber, controllerAll);
			if (task == null){
				nullTaskPrint();
			}else{
				int option = newStatusTask();

				if (option == 1)
				{
					task.setOpen();
					objectEdited();
				}
				else if (option == 2)
				{
					task.setAssigned();
					objectEdited();
				}
				else if (option == 3)
				{
					task.setInProgress();
					objectEdited();
				}
				else if (option == 4)
				{
					controllerAll.setTaskCompleted(task);
					checkUStoryStatus(userStory, controllerAll);
					objectEdited();
				}
				else
				{
					invalidOption();
				}
				controllerAll.saveData();
				Scan.print(task.toString());
			}

		}

	}

	private void removeUserStorySBLTask(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{


		UserStory userStory = getUSFromSBL(controllerAll);
		if (userStory == null)
		{
			nullUserStoryPrint();
		}

		else {
			Task task = findTaskInUserSSBL(userStory.getId(), controllerAll);
			if (task == null)
			{
				nullTaskPrint();

			}else {

				userStory.getUserStoryTasks().remove(task);

				Scan.print(userStory.toString());

				removedTaskInUserStory();

				controllerAll.saveData();

				printUStoryInfo(userStory);

			}
		}
	}

	private void setUserStoryDeadline(UserStory userStory, ControllerAll controllerAll){


		String deadline = getEndDate();

		while (!legalDate(deadline,controllerAll)){
			dateIsBeforeOrAfterProjectDate();
			deadline = getEndDate();
		}

		userStory.setDeadline(deadline);
		setUStoryDeadlineReceipt(userStory);
		controllerAll.saveData();

	}

	private void viewAndSetUSDeadline(ControllerAll controllerAll){

		controllerAll.viewUStoryDeadlines(); //prints deadlines to user

		UserStory userStory = controllerAll.findUStoryByNumber(); // retrieves user story

		setUserStoryDeadline(userStory, controllerAll); //sets date

		controllerAll.saveData();

	}
}