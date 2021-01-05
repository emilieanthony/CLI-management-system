package Controller;

import Exceptions.*;
import Models.*;
import Utility.Scan;
import View.AllView;

import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.*;
import static Controller.ControllerAll.*;
import static View.DevTeamView.*;
import static View.DevTeamView.getUserStoryNumber;
import static View.ScrumMasterView.*;

public class ControllerScrumMaster
{
	public static String sprintName;

	public void scrumMasterMenu(ControllerProductOwner contProOwner, ControllerAll controllerAll,
								ControllerScrumMaster contScrum)
	{

		boolean running = true;
		do
		{
			int option;

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
						setUserStoryDeadline(controllerAll);
						break;
					case 10:
						controllerAll.viewSprintDeadlines();
						break;
					case 11:
						controllerAll.viewTaskDeadlines();
						break;
					case 12:
						controllerAll.viewUStoryDeadlines();
						break;
					case 13:
						contProOwner.viewProBacklog(controllerAll);
						break;
					case 14:
						scrumMasterEditTaskMenu(controllerAll, contScrum);
						break;
					case 15:
						controllerAll.viewCompletedTasks();
						break;
					case 16:
						controllerAll.viewCompletedUStories();
						break;
					case 17:
						viewTeamMembers(controllerAll);
						break;
					case 18:
						moveTaskOrUSToSprintBacklog(contProOwner, controllerAll);
						break;
					case 19:
						moveTaskOrUSToProductBacklog(controllerAll);
						break;
					case 20:
						viewSprintBacklog(controllerAll);
						break;
					case 21:
						velocity();
						break;
					case 22:
						getProjectName(controllerAll);
						break;
					case 23:
						createTaskOfUsInSBL(controllerAll);
						break;
					case 24:
						editTaskInUserStoryMenu(controllerAll);
						break;
					case 25:
						showImplementedStoryPoints(controllerAll);
						break;
					case 26:
						showAverageVelocity(controllerAll);
						break;
					case 27:
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

	//-----------------------------------------------------Second Switch----------------------------------------------

	private void scrumMasterEditTaskMenu(ControllerAll controllerAll, ControllerScrumMaster contScrum)
	{
		boolean running = true;

		do
		{
			int option;

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
				Task newTask = getTaskInfo(id);
				createdTaskReceipt(newTask);
				sprintName = getSprintBacklogName();
				findSprintBacklogByName(controllerAll).getAllTasks().add(newTask);
				controllerAll.saveData();
				taskCreatedToSBacklog();

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

		} catch (EmptyName e)
		{
			emptyName();
		}catch (NegativeId n){
			negativeId();
		}catch (Exception e){
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

	private void velocity()
	{
		String input = getVelocity();
		int[] numbers = arrayOfVelocity(input);
		int averageVelocity = getAverageVelocity(numbers);

		printVelocity(averageVelocity);
	}

	//------------------------------------Methods for Assigning object-------------------------------------------//

	private void assignTask(ControllerAll controllerAll)
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
		if (task == null)
		{
			nullTaskPrint();
		}
		else
		{
			task.getAssignedDevelopers().add(developer);
			task.setStatus("In progress");
			controllerAll.saveData();

			assignmentCompleted();
		}
	}

	private void assignUserStory(ControllerAll controllerAll)
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

	//--------------------------------------Edit task in US menu ------------------------------//

	public void editTaskInUserStoryMenu(ControllerAll controllerAll)
	{
		boolean running = true;

		do
		{
			int option;

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
		for (Task foundTasks : userStory.getUserStoryTasks())
		{
			if (foundTasks.getStatus().equalsIgnoreCase("Done"))
			{
				userStory.getBinary().add(true);
			}
			else
			{
				userStory.getBinary().add(false);
			}
			if (!(userStory.getBinary().contains(false)))
			{
				userStory.setComplete();
			}
			else
			{
				//userStory.setOpen();
			}
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