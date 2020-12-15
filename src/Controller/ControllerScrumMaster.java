package Controller;

import Models.*;
import Utility.Import;
import Utility.Export;
import Utility.Scan;

import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.*;
import static View.DevTeamView.getTaskId;
import static View.ProductOwnerView.*;
import static View.ScrumMasterView.*;

public class ControllerScrumMaster {

	private Import importFile = new Import();
	static String name;

	public void scrumMasterMenu(ControllerProductOwner contProOwner, ControllerAll controllerAll) {

		boolean running = true;
		do {
			int option = menuScrumMaster();
			switch (option) {
				case 1:
					createProject(controllerAll);
					break;
				case 2:
					createSprintAndSprintBacklog(controllerAll);
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
					assignATask(controllerAll);
					break;
				case 8:
					scrumMasterEditTaskMenu(controllerAll);
					break;
				case 9:
					contProOwner.viewBacklog(controllerAll);
					break;
				case 10:
					viewTeamMembers(controllerAll);
					break;
				case 11:
					moveTaskOrUSToSprintBacklog(contProOwner, controllerAll);
					break;
				case 12:
					viewSprintBacklog(controllerAll);
					break;
				case 13:
					importFile.importProjects(controllerAll);
					break;
				case 14:
					getProjectName();// Switch project.
					break;
				case 15:
					running = false;
					break;
				default:
					defaultMessage();
			}
		} while (running);
	}

	//-----------------------------------------------------Second Switch----------------------------------------------

	public void scrumMasterEditTaskMenu(ControllerAll controllerAll) {

		boolean running = true;
		do {
			int option = menuEditTask();
			switch (option) {
				case 1:
					editPriorityNumberTask(controllerAll);
					break;
				case 2:
					editStatusTask(controllerAll);
					break;
				case 3:
					removeTask(controllerAll);
					break;
				case 4:
					running = false;
					break;
				default:
					defaultMessage();
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
		projectNotFound();
	}


	/*------------------------------------------Methods for tasks------------------------------------------------*/

	private void editPriorityNumberTask(ControllerAll controllerAll) {

		Project project = controllerAll.whichProject();
		if (project == null) {
			projectNotFound();
		} else {

			Task task = findTaskById(controllerAll);

			if (task == null) {
				taskNotFound();
			} else {

				int priorityNumber = newPriorityNumberTask();
				task.setPriorityNumber(priorityNumber);
			}
		}
	}

	private void editStatusTask(ControllerAll controllerAll) {

		Project project = controllerAll.whichProject();
		if (project == null) {
			projectNotFound();
		} else {

			Task task = findTaskById(controllerAll);

			if (task == null) {
				taskNotFound();
			} else {


				String newStatusTask = newStatusTask();
				task.setStatus(newStatusTask);
			}
		}
	}


	private void removeTaskSprint(ControllerAll controllerAll) {

		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		} else {

			name = getSprintBacklogName();
			findSprintBacklogByName(project.getAllSprintBacklogs());
			SprintBacklog sprintBacklog = findSprintBacklogByName();
			Task task = findTaskByIdSprint(controllerAll); // letar upp vilken sprint

			if (task == null) {
				taskNotFound();
			} else {


				sprintBacklog.getAllTasks().remove(task);


				project.getSprintBacklog().getAllTasks().remove(task);
				//project.getAllTasks().remove(task);          // arraylist sprint backlog, if else?
				removeObject();
			}
		}
	}

	private void removeTaskProductBacklog(ControllerAll controllerAll) {

		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		} else {

			Task task = findTaskByIdProductBacklog(controllerAll);

			if (task == null) {
				taskNotFound();
			} else {

				project.getSprintBacklog().getAllTasks().remove(task);
				//project.getAllTasks().remove(task);          // arraylist sprint backlog, if else?
				removeObject();
			}
		}
	}

	private void createTaskToProductBacklog(ControllerAll controllerAll) {
		Task newTask = getTaskInfo();
		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		}

		project.getProductBacklog().getTasksImport().add(newTask);
	}

	private void createTaskToSprint(ControllerAll controllerAll) {
		Task newTask = getTaskInfo();
		String name = getSprintBacklogName();
		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		}

		findSprintBacklogByName(name, project.getAllSprints()).getAllTasks().add(newTask);
	}


	private void moveTaskOrUSToSprintBacklog(ControllerProductOwner contProOwner, ControllerAll controllerAll) {
		contProOwner.viewBacklog(controllerAll);

		String input = Scan.readLine("Do you want to move a TASK from product backlog to sprint backlog, type: 1\n" +
				"Do you want to move a USER STORY from product backlog to sprint backlog, type: " +
				"2\n"); // Move to view class.

		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();
		} else {
			if (input.equals("1")) {
				int idTask = Scan.readInt("Write the ID of the task you want to move: ");// Move to view class.
				String sprintName = Scan.readLine("Write the name of the sprint you want to move your task to: ");

				project.getProductBacklog().getTask(idTask).setSprintName(sprintName);
				findSprintBacklogByName(sprintName, project.getAllSprints()).getAllTasks().add(project.getProductBacklog().getTask(idTask));
				project.getProductBacklog().getTasksImport().remove(project.getProductBacklog().getTask(idTask));

				movedObject();
			}

			if (input.equals("2")) {
				int usName = Scan.readInt("Write the the number of the user story you want to" +
						" move: "); // Move to view class.
				String sprintName = Scan.readLine("Write the name of the sprint you want to move your user story to: ");

				project.getProductBacklog().getUserStory(usName).setSprintName(sprintName);
				findSprintBacklogByName(sprintName, project.getAllSprints()).getAllUserStories().add(project.getProductBacklog().getUserStory(usName));
				project.getProductBacklog().getAllUserStories().remove(project.getProductBacklog().getUserStory(usName));

				movedObject();
			} else {
				return;
			}
		}
	}

	public void assignATask(ControllerAll controllerAll) {
		Developer developer = controllerAll.findDeveloperByID();
		Task task = controllerAll.findTaskById(controllerAll);
		task.getAssignedTeamMembers().add(developer);
		task.setStatus("In progress");

	}
	/*------------------------------------------Methods product owner------------------------------------------------*/

	public void createProductOwner(ControllerAll controllerAll) {
		String name = getProOwnerInfo();
		int id = createIdProductOwner(controllerAll);
		ProductOwner newProOwner = new ProductOwner(name, id);
		Project project = controllerAll.whichProject();
		project.getAllProductOwners().add(newProOwner);
		Export.exportObject(newProOwner);
		createdProOwner();
	}


	public int createIdProductOwner(ControllerAll controllerAll) {
		int FIRST_ID = 1;
		Project project = controllerAll.whichProject();
		if (project != null) {
			if (!project.getAllProductOwners().isEmpty()) {
				return project.getAllProductOwners().get(project.getAllProductOwners().size() - 1).getId() + 1;
			}
		} else {
			projectNotFound();
		}
		return FIRST_ID;
	}

	/*------------------------------------Methods create development member-------------------------------------------*/

	public void createDevelopmentMember(ControllerAll controllerAll) {
		String name = getDeveloperInfo();
		int id = createIdDevelopmentMember(controllerAll);
		Project project = controllerAll.whichProject();
		if (project == null) {
			projectNotFound();
		} else {
			Developer developer = new Developer(name, id);
			project.getAllTeamMembers().add(developer);
			Export.exportObject(developer);
			createdDeveloper();
		}
	}

	public int createIdDevelopmentMember(ControllerAll controllerAll) {
		int id = 1;
		Project project = controllerAll.whichProject();
		if (project.getAllTeamMembers().isEmpty()) {
			id = 1;
		} else {
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
		Project project = new Project(id, name, startDate, endDate);
		controllerAll.getAllProjects().add(project);
		proName = name;
		Export.exportObject(project);

		Scan.print("You have successfully created the following project:\n\n" + project.toString());
	}



	/*------------------------------------Methods etc for sprints-------------------------------------------*/

	public void createSprintAndSprintBacklog(ControllerAll controllerAll) {
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

		SprintBacklog sprintBacklog = new SprintBacklog(name, startDate, endDate);
		Project project = controllerAll.whichProject();
		project.getAllSprintBacklogs().add(sprintBacklog);
		Export.exportObject(sprintBacklog);

		Scan.print("You have successfully created the following sprintBacklog:\n\n"
				+ sprintBacklog.toString());

	}

	private void viewSprintBacklog(ControllerAll controllerAll) {
		Project project = controllerAll.whichProject();

		if (project == null) {
			projectNotFound();

		} else {

			SprintBacklog sprint = findSprintBacklogByName(controllerAll);
			printSprintBacklog(sprint.getAllUserStories(), sprint.getAllTasks()
			);
		}
	}

	public SprintBacklog findSprintBacklogByName(ControllerAll controllerAll) {
		SprintBacklog sprintBacklog = null;
		String name = getSprintBacklogByName();
		Project project = controllerAll.whichProject();
		Iterator<SprintBacklog> iterator = project.getAllSprintBacklogs().iterator();
		while (sprintBacklog == null && iterator.hasNext()) {
			SprintBacklog foundBacklog = iterator.next();
			if (foundBacklog.getName().equalsIgnoreCase(name)) {
				sprintBacklog = foundBacklog;
			}
		}
		return sprintBacklog;
	}

	public Task findTaskByIdProductBacklog(ControllerAll controllerAll) { // in product backlog


		int id = getTaskId();
		Task task = null;

		Project project = controllerAll.whichProject();

			Iterator<Task> iterator = project.getProductBacklog().getTasksImport().iterator();
			while (task == null && iterator.hasNext()) {
				Task foundTask = iterator.next();
				if (foundTask.getId() == id) {
					task = foundTask;
				}
			}
	//	}
			return task;
		}


	public Task findTaskByIdSprint(ControllerAll controllerAll) {


		int id = getTaskId();
		Task task = null;

		Project project = controllerAll.whichProject();

		Iterator<Task> iterator = project.getSprintBacklog().getAllTasks().iterator();
		while (task == null && iterator.hasNext()) {
			Task foundTask = iterator.next();
			if (foundTask.getId() == id) {
				task = foundTask;
			}
		}
		//	}
		return task;
	}
}


