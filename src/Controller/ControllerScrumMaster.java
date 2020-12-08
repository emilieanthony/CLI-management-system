package Controller;

import Models.*;
import Models.SprintBacklog;
import Utility.Import;
import Utility.Scan;

import java.time.LocalDate;

import static Utility.PrintUtility.defaultMessage;
import static View.ScrumMasterView.*;

public class ControllerScrumMaster {

	private Import importFile = new Import();

	public void scrumMasterMenu(ControllerProductOwner contProOwner,ControllerAll controllerAll)
	{

		boolean running = true;
		do
		{
			int option = menuScrumMaster();
			switch (option)
			{
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
					createDevelopmentMember(controllerAll);
					break;
				case 5:
					createProductOwner(controllerAll);
					break;
				case 6:
					assignTask(controllerAll);
					break;
				case 7:
					createProductOwner(controllerAll);
					break;
				case 8:
					assignTask(controllerAll);
					break;
				case 9:
					contProOwner.viewBacklog(controllerAll, proOwnerView);
					break;
				case 10:
					viewTeamMembers(controllerAll);
					break;
				case 11:
					moveTaskOrUSToSprintBacklog(contProOwner, controllerAll, proOwnerView);
					break;
				case 12:
					viewSprintBacklog(controllerAll);
					break;
				case 13:
					importFile.importProjects(controllerAll);
					break;
				case 14:
					getProjectName();
					break;
				case 15:
					running = false; // Go back to main menu
					break;
				default:
					defaultMessage();
			}
		} while (running);
	}

	private void viewTeamMembers(ControllerAll controllerAll)
	{
		Project project = controllerAll.whichProject();
		membersView();
		for (Developer developer: project.getAllTeamMembers()) {
			Scan.print(developer.toString());
		}
		Scan.print("Project not found.\n\n");
	}

	/*------------------------------------------Methods for tasks------------------------------------------------*/

	private void createTaskToProductBacklog(ScrumMasterView scrumMasterView, ControllerAll controllerAll)
	{
		Task newTask = scrumMasterView.createTask();

		int idProject = Scan.readInt("Write the ID of the project: ");

		for (Project project : controllerAll.getAllProjects())
		{
			if (project.getId() == idProject)
			{
				project.getProductBacklog().getTasks().add(newTask);
			}
		}
	}

	private void createTaskToSprint(ScrumMasterView scrumMasterView, ControllerAll controllerAll)
	{
		Task newTask = scrumMasterView.createTask();
		String name = scrumMasterView.getBacklogName();
		int idProject = Scan.readInt("Write the ID of the project: ");

		for (Project project : controllerAll.getAllProjects())
		{
			if (project.getId() == idProject)
			{
				findSprintBacklogByName(name, project.getAllSprints()).getAllTasks().add(newTask);
			}
		}
	}

	private void createUserStoryToProductBacklog(ProductOwnerView proOwnerView, ControllerProductOwner contProOwner,
												 ControllerAll controllerAll)
	{
		contProOwner.addUserStory(proOwnerView, controllerAll);
	}

	private void moveTaskOrUSToSprintBacklog(ControllerProductOwner contProOwner, ControllerAll controllerAll,
											 ProductOwnerView proOwnerView)
	{
		contProOwner.viewBacklog(controllerAll, proOwnerView);

		String input = Scan.readLine("Do you want to move a TASK from product backlog to sprint backlog, type: 1\n" +
				"Do you want to move a USER STORY from product backlog to sprint backlog, type: 2\n");

		int idProject = Scan.readInt("Write the ID of the project: ");

		for (Project project : controllerAll.getAllProjects())
		{
			if (project.getId() == idProject)
			{
				if (input.equals("1"))
				{
					int idTask = Scan.readInt("Write the ID of the task you want to move: ");
					String sprintName = Scan.readLine("Write the name of the sprint you want to move your task to: ");

					project.getProductBacklog().getTask(idTask).setSprintName(sprintName);
					findSprintBacklogByName(sprintName,project.getAllSprints()).getAllTasks().add(project.getProductBacklog().getTask(idTask));
					project.getProductBacklog().getTasks().remove(project.getProductBacklog().getTask(idTask));

					System.out.println("\n\nYou have successfully moved the task to sprint backlog!\n\n");
				}

				if(input.equals("2"))
				{
					int usName = Scan.readInt("Write the the number of the user story you want to move: ");
					String sprintName = Scan.readLine("Write the name of the sprint you want to move your user story to: ");

					project.getProductBacklog().getUserStory(usName).setSprintName(sprintName);
					findSprintBacklogByName(sprintName,project.getAllSprints()).getAllUserStories().add(project.getProductBacklog().getUserStory(usName));
					project.getProductBacklog().getAllUserStories().remove(project.getProductBacklog().getUserStory(usName));

					System.out.println("\n\nYou have successfully moved the user story to sprint backlog!\n\n");
				}

				else
				{
					return;
				}
			}
		}
	}

	private void viewSprintBacklog(ControllerAll controllerAll)
	{
		int idProject = Scan.readInt("Write the ID of the project: ");

		for (Project project : controllerAll.getAllProjects())
		{
			if (project.getId() == idProject)
			{
				String sprintName = Scan.readLine("Write the name of the sprint: ");
				System.out.println(findSprintBacklogByName(sprintName,project.getAllSprints()).getAllUserStories());
				System.out.println("\n\n");
				System.out.println(findSprintBacklogByName(sprintName,project.getAllSprints()).getAllTasks());
			}
		}
	}

	public void assignTask(ControllerAll controllerAll){
		Task task = controllerAll.findTaskById(controllerAll);
		Developer developer = controllerAll.findDeveloperByID();
		task.getAssignedTeamMembers().add(developer);
		task.setStatus("In progress");
	}

	/*------------------------------------------Methods product owner------------------------------------------------*/

	public void createProductOwner(ControllerAll controllerAll)
	{
		String name = getProOwnerInfo();
		int id = createIdProductOwner(controllerAll);
		ProductOwner newProOwner = new ProductOwner(name,id);
		Project project = controllerAll.whichProject();
		project.getAllProductOwners().add(newProOwner);
		createdProOwner();
	}

	public int createIdProductOwner(ControllerAll controllerAll)
	{
		int id = 200;
		Project project = controllerAll.whichProject();
				if (project.getAllProductOwners().isEmpty())
				{
					id = 200;
				}
				else
				{
					id = project.getAllProductOwners().get(project.getAllProductOwners().size() - 1).getId() + 1;
				}
		return id;
	}

	/*------------------------------------Methods create development member-------------------------------------------*/

	public void createDevelopmentMember(ControllerAll controllerAll)
	{

		String name = getDeveloperInfo();
		int id = createIdDevelopmentMember(controllerAll);
		Project project = controllerAll.whichProject();
		Developer developer = new Developer(name,id);
		project.getAllTeamMembers().add(developer);
		createdDeveloper();
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


	public void createProject(ControllerAll controllerAll)
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

		//create exception here for non valid input

		/*LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);*/

		String startDate = startYear + "-" + startMonth + "-" + startDay;
		String endDate = endYear + "-" + endMonth + "-" + endDay;
		Project project = new Project(id, name, startDate, endDate);
		controllerAll.getAllProjects().add(project);

		Scan.print("You have successfully created the following project:\n\n" + project.toString());
	}


	/*------------------------------------Methods etc for sprints-------------------------------------------*/

	public void createSprintAndSprintBacklog(ProductOwnerView proOwnerView, ScrumMasterView scrumMasterView,
											 ControllerProductOwner contProOwner, ControllerAll controllerAll)
	{
		Scan.print("\nEnter the name, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new " +
				"sprint:");
		String name = Scan.readLine("Name:");
		int startYear = Scan.readInt("Start date (YYYY):");
		int startMonth = Scan.readInt("Start date (MM):");
		int startDay = Scan.readInt("Start date (DD):");
		int endYear = Scan.readInt("End date (YYYY):");
		int endMonth = Scan.readInt("End date (MM):");
		int endDay = Scan.readInt("End date (DD):");

		//create exception here for non valid input

		/*LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);*/
		String startDate = startYear + "-" + startMonth + "-" + startDay;
		String endDate = endYear + "-" + endMonth + "-" + endDay;

		Sprint sprint = new Sprint(name, startDate, endDate);
		controllerAll.getSprintBacklog().add(sprint);

		//export ArrayList to a file

		String input = Scan.readLine("You have successfully created the following sprint:\n\n"
				+ sprint.toString() + "\nDo you want to create a user story type: 1\n" +
				"Do you want to create a task type: 2\n" +
				"To exit type: 3\n");

		if (input.equals(1))
		{
			contProOwner.addUserStory(proOwnerView, controllerAll);
		}

		if (input.equals(2))
		{
			createTaskToSprint(scrumMasterView, controllerAll);
		}
		if(input.equals(3))
		{
			return;
		}

	}

	public Sprint findSprintBacklogByName(String name, ArrayList<Sprint> allBacklogs)
	{
		Sprint sprint = null;
		Iterator<Sprint> iterator = allBacklogs.iterator();
		while (sprint == null && iterator.hasNext())
		{
			Sprint foundBacklog = iterator.next();
			if (foundBacklog.getName().equalsIgnoreCase(name))
			{
				sprint = foundBacklog;
			}
		}
		return sprint;
	}
}



