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
					createSprint(controllerAll);
					break;
				case 3:
					createTask(controllerAll);
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
					contProOwner.viewBacklogs(controllerAll);
					break;
				case 8:
					viewTeamMembers(controllerAll);
					break;
				case 9:
					importFile.importProjects(controllerAll);
					break;
				case 10:
					getProjectName();
					break;
				case 11:
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

	}

	/*------------------------------------------Methods for tasks------------------------------------------------*/

	private void createTask(ControllerAll controllerAll){
			Task newTask = getTaskInfo();
			Project project = controllerAll.whichProject();
			project.getAllTasks().add(newTask);
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
		int id =  Scan.readInt("ID: ");
		int startYear =  Scan.readInt("Start date (YYYY): ");
		int startMonth = Scan.readInt("Start date (MM): ");
		int startDay = Scan.readInt("Start date (DD): ");
		int endYear = Scan.readInt("End date (YYYY): ");
		int endMonth = Scan.readInt("End date (MM): ");
		int endDay = Scan.readInt("End date (DD): ");


		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		Project project = new Project(id, name, startDate, endDate);
		controllerAll.getAllProjects().add(project);

		Scan.print("You have successfully created the following project:\n\n" + project.toString());
	}


	/*------------------------------------Methods etc for sprints-------------------------------------------*/

	public void createSprint(ControllerAll controllerAll)
	{
		Scan.print("\nEnter the name, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new " +
				"sprintProductBacklog:");
		String name = Scan.readLine("Name:");
		int startYear = Scan.readInt("Start date (YYYY):");
		int startMonth = Scan.readInt("Start date (MM):");
		int startDay = Scan.readInt("Start date (DD):");
		int endYear = Scan.readInt("End date (YYYY):");
		int endMonth = Scan.readInt("End date (MM):");
		int endDay = Scan.readInt("End date (DD):");

		//create exception here for non valid input

		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		Project project = controllerAll.whichProject();
		SprintBacklog sprintProductBacklog = new SprintBacklog(name, startDate, endDate);
		project.getAllSprintBacklogs().add(sprintProductBacklog);

		//export ArrayList to a file

		Scan.print("You have successfully created the following sprintProductBacklog:\n\n" +
				sprintProductBacklog.toString());

	}

}



