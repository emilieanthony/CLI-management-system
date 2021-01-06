package Controller;

import Exceptions.*;
import Models.*;
import Utility.Scan;
import View.AllView;

import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.*;
import static View.DevTeamView.*;
import static View.DevTeamView.getUserStoryNumber;
import static View.ScrumMasterView.*;

public class ControllerScrumMaster
{
	public static String sprintName;
	public static int option;

	public void scrumMasterMenu(ControllerAll controllerAll,ControllerProductOwner contProOwner,
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
						USOptionsMenu(controllerAll);
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
						getProjectName(controllerAll);
						break;
					case 13:
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

	private void USOptionsMenu(ControllerAll controllerAll)
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
						createTaskOfUsInSBL(controllerAll);
						break;
					case 4:
						editTaskInUserStoryMenu(controllerAll);
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
						setUserStoryDeadline(controllerAll);
						break;
					case 2:
						setTaskDeadline(controllerAll);
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
						moveTaskOrUSToSprintBacklog(contProOwner,controllerAll);
						break;
					case 2:
						moveTaskOrUSToProductBacklog(controllerAll);
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
						setTaskDeadline(controllerAll);
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

		task.setDeadline(deadline);

		setTaskDeadlineReceipt(task);

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

	public boolean checkForSprintBacklog(Project project){
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

	private void moveTaskOrUSToSprintBacklog(ControllerProductOwner contProOwner, ControllerAll controllerAll)
	{
		contProOwner.viewProBacklog(controllerAll);

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
				int idTask = specifyTask();
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
				int usName = numberUsToMove();
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
				sprintName = sprintNameToMovePrint();

				Task taskToMove = findSprintBacklogByName(controllerAll).getTask(idTask);
				project.getProductBacklog().getTasks().add(taskToMove);
				findSprintBacklogByName(controllerAll).getAllTasks().remove(taskToMove);
				controllerAll.saveData();
				movedObject();
			}

			if (input.equals("2"))
			{
				int usNumber = numberUsToMove();
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

	//------------------------------------------Methods product owner------------------------------------------------//

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

	//------------------------------------Methods create development member-----------------------------------------//

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

	private void createProject(ControllerAll controllerAll)
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
		viewSprints(project);

		sprintName = getSprintBacklogByName();

		if (project == null)
		{
			projectNotFound();
		}
		else
		{
			SprintBacklog sprint = findSprintBacklogByName(controllerAll);

			if (sprint == null)
			{
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
		boolean foundIt = false;

		while (sprintBacklog == null && iterator.hasNext())
		{
			SprintBacklog foundBacklog = iterator.next();
			if (foundBacklog.getName().equalsIgnoreCase(sprintName))
			{
				sprintBacklog = foundBacklog;
				foundIt = true;
			}
		}

		if(!foundIt)
		{
			invalidSprintBacklog();
		}
		return sprintBacklog;
	}

	public void editUSInSprintBLMenu(ControllerAll controllerAll){

		sprintName = getSprintBacklogByName();

		int USNumber = getUserStoryNumber();

		UserStory userStory = findUStoryByNumberSBL(USNumber, controllerAll);

		boolean running = true;

		do
		{
			try
			{
				option = menuEditUStoryInSBL();
				switch (option)
				{
					case 1:
						controllerAll.editUSStoryPoints(userStory);//Edit user story point
						break;
					case 2:
						controllerAll.editUSPriority(userStory);//edit priority number
						break;
					case 3:
						controllerAll.changeUSStatus(userStory);//change user story status
						break;
					case 4:
						setUserStoryDeadline(controllerAll);//set user story deadline
						break;
					case 5:
						removeUSFromSBL(userStory, findSprintBacklogByName(controllerAll));//remove user story from sprint backlog
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

	public UserStory findSBLUStory(ControllerAll controllerAll){
		sprintName = getSprintBacklogByName();

		int USNumber = getUserStoryNumber();

		UserStory userStory = findUStoryByNumberSBL(USNumber, controllerAll);

		return userStory;
	}

	public void removeUSFromSBL(UserStory userStory, SprintBacklog sprintBacklog){
		boolean confirmRemoval = removingUSMsg(userStory);
		if (confirmRemoval){
			sprintBacklog.getUserStories().remove(userStory);
		}

	}


	//------------------------------------Methods for velocity-------------------------------------------//

	// Shall we remove it.
	/*private int[] arrayOfVelocity(String input)
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

	private void velocity()
	{
		String input = getVelocity();
		int[] numbers = arrayOfVelocity(input);
		int averageVelocity = getAverageVelocity(numbers);

		printVelocity(averageVelocity);
	}*/

	//------------------------------------Methods for Assigning object-------------------------------------------//

	private void assignTask(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}

		showAllTasks(controllerAll);

		int idTask = assignTaskPrintIdTask();
		showAllSprintBacklogs(project);
		sprintName = assignTaskPrintSprintName();
		Task task = findSprintBacklogByName(controllerAll).getTask(idTask);

		if (task == null)
		{
			nullTaskPrint();
		}

		else
			{
				showAllTeamMembers(project);
				Developer developer = controllerAll.findDeveloperByID();
				if (project.getAllTeamMembers().isEmpty()){
					noDeveloperYet();
					createDevelopmentMember(controllerAll);
				}else if (!(project.getAllTeamMembers().contains(developer))){
					invalidDeveloperId();
				}else{
					task.getAssignedDevelopers().add(developer);
					task.setAssigned();
					controllerAll.saveData();
					assignmentCompleted();
				}

			}
	}

	private void assignUserStory(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();

		if (project == null)
		{
			projectNotFound();
		}

		showAllUserStories(controllerAll);

		int number = assignUsPrintIdUs();
		showAllSprintBacklogs(project);
		sprintName = assignUsPrintSprintName();
		UserStory userStory = findSprintBacklogByName(controllerAll).getUserStory(number);

		if (userStory == null)
		{
			nullUserStoryPrint();
		}

		else
		{
			showAllTeamMembers(project);
			Developer developer = controllerAll.findDeveloperByID();
			if (project.getAllTeamMembers().isEmpty()){
				noDeveloperYet();
				createDevelopmentMember(controllerAll);
			}else if (!(project.getAllTeamMembers().contains(developer))){
				invalidDeveloperId();
			}else{
				userStory.getAssignedDevelopers().add(developer);
				userStory.setAssigned();
				controllerAll.saveData();
				assignmentCompleted();
			}

		}
	}

	//--------------------------------------Edit task in US menu ------------------------------//

	public void editTaskInUserStoryMenu(ControllerAll controllerAll)
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
			} catch (Exception e)
			{
				invalidInputPrint();
			}
		} while (running);
	}

	//---------------------------------------------------------------------------------------------//

	private UserStory findUStoryByNumberSBL(int number, ControllerAll controllerAll)
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

	private void viewSprintBacklogT(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();
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

	private void createTaskOfUsInSBL(ControllerAll controllerAll)
	{
		sprintName = getSprintBacklogByName();
		viewSprintBacklogT(controllerAll);

		int UsNumber = getUserStoryNumber();
		int id = taskUSIdGenerator(controllerAll);

		Task task = null;

		try
		{
			task = getTaskInfo(id);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		UserStory userStory = findUStoryByNumberSBL(UsNumber, controllerAll);
		userStory.getUserStoryTasks().add(task);
		checkUStoryStatus(userStory, controllerAll);
		controllerAll.saveData();
	}

	//--------------------------------------Methods for edit User Stories ------------------------------//

	private void checkUStoryStatus(UserStory userStory, ControllerAll controllerAll)
	{
		userStory.getBinary().clear();
		for (Task foundTasks : userStory.getUserStoryTasks()) {

			if (foundTasks.getStatus().equalsIgnoreCase("Done")) {
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
		UserStory userStory = findUStoryByNumberSBL(UsNumber, controllerAll);
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

	private void editUStorySBLTaskPriority(ControllerAll controllerAll)
	{
		sprintName = getSprintBacklogByName();
		int UsNumber = getUserStoryNumber();

		Task task = findTaskInUserSSBL(UsNumber, controllerAll);
		int newPriorityNumber = newPriorityNumberTask();

		task.setPriorityNumber(newPriorityNumber);
		Scan.print(task.toString());

		objectEdited();
	}

	private void editUStorySBLTaskStatus(ControllerAll controllerAll)
	{
		sprintName = getSprintBacklogByName();
		int UsNumber = getUserStoryNumber();
		UserStory userStory = findUStoryByNumberSBL(UsNumber, controllerAll);
		Task task = findTaskInUserSSBL(UsNumber, controllerAll);
		int option = newStatusTask();

		if (option == 1)
		{
			task.setOpen();
			Scan.print(task.toString());
			objectEdited();
		}
		else if (option == 2)
		{
			task.setAssigned();
			Scan.print(task.toString());
			objectEdited();
		}
		else if (option == 3)
		{
			task.setInProgress();
			Scan.print(task.toString());
			objectEdited();
		}
		else if (option == 4)
		{
			task.setComplete();
			checkUStoryStatus(userStory, controllerAll);
			Scan.print(task.toString());
			objectEdited();
			controllerAll.saveData();
		}
		else
		{
			invalidOption();
		}
	}

	private void removeUserStorySBLTask(ControllerAll controllerAll)
	{
		sprintName = getSprintBacklogByName();
		int UsNumber = getUserStoryNumber();

		UserStory userStory = findUStoryByNumberSBL(UsNumber, controllerAll);
		Task task = findTaskInUserSSBL(UsNumber, controllerAll);
		userStory.getUserStoryTasks().remove(task);
		Scan.print(userStory.toString());

		removedTaskInUserStory();
	}

	private void setUserStoryDeadline(ControllerAll controllerAll){

		UserStory userStory = controllerAll.findUStoryByNumber();

		String deadline = getEndDate();

		userStory.setDeadline(deadline);

		setUStoryDeadlineReceipt(userStory);

	}
}