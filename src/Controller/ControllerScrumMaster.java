package Controller;

import Models.*;
import Utility.PrintUtility;
import View.ProductOwnerView;
import View.ScrumMasterView;
import Utility.Scan;

import java.time.LocalDate;

public class ControllerScrumMaster {


	public void scrumMasterMenu(ScrumMasterView scrumView, ProductOwnerView proOwnerView,
								ControllerProductOwner contProOwner, ControllerAll controllerAll)
	{

		boolean running = true;
		do
		{
			int option = scrumView.menuScrumMaster();
			switch (option)
			{
				case 1:
					createProject(controllerAll);
					break;
				case 2:
					createSprint(controllerAll);
					break;
				case 3:
					//Method for creating a new sprint backlog
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
					contProOwner.viewBacklog(controllerAll,proOwnerView);
					break;
				case 8:
					viewTeamMembers(controllerAll);
					break;
				case 9:
					running = false; // Go back to main menu
					break;
				default:
					PrintUtility.defaultMessage();
			}
		} while (running);
	}

	private void viewTeamMembers(ControllerAll controllerAll)
	{
		int idProject = Scan.readInt("Write the ID of the project: ");

		for (Project project : controllerAll.getAllProjects())
		{
			if (project.getId() == idProject)
			{
				project.viewAllDevelopmentMembers();
				return;
			}
		}
		Scan.print("Project not found.\n\n");
	}

	private void assignTask(ControllerAll controllerAll)
	{
		int idTask = Scan.readInt("Write the ID of the task: ");
		int idMember = Scan.readInt("Write the ID of Development team member: ");
		int idProject = Scan.readInt("Write the ID of the project: ");

		for (Project project : controllerAll.getAllProjects())
		{
			if (project.getId() == idProject)
			{
				project.assignTask(idMember, idTask);
				Scan.print("Task is now assigned to development team member!\n\n");
				return;
			}
		}
		Scan.print("Project not found.\n\n");
	}

	/*------------------------------------------Methods product owner------------------------------------------------*/

	public void createProductOwner(ControllerAll controllerAll)
	{
		String name = Scan.readLine("Please type the name of the new Product Owner below: ");

		//System.out.println("Please type the id of the new Product Owner below: ");
		//int id = scanner.nextInt();

		int idProject = Scan.readInt("Write the ID of the project that the product owner will belong to:" +
				" ");
		int id = createIdProductOwner(idProject,controllerAll);

		for (Project project : controllerAll.getAllProjects())
		{
			if (project.getId() == idProject)
			{
				project.getAllProductOwners().add(new ProductOwner(name, id));


			}
		}
		Scan.print(" You have successfully created " + name +
				" as a new Product Owner with the id " + id );
	}

	public int createIdProductOwner(int idProject, ControllerAll controllerAll)
	{
		int id = 200;

		for (Project project : controllerAll.getAllProjects())        //To get the project needed to create unique
		{                                               //id for product owner
			if (project.getId() == idProject)
			{
				if (project.getAllProductOwners().isEmpty())
				{
					id = 200;
				}
				else
				{
					id = project.getAllProductOwners().get(project.getAllProductOwners().size() - 1).getId() + 1;
				}
			}
		}
		return id;
	}

	/*------------------------------------Methods create development member-------------------------------------------*/

	public void createDevelopmentMember(ControllerAll controllerAll)
	{
		String name = Scan.readLine("Please type the name of the new Development member below: \n");
		int idProject = Scan.readInt("Write the ID of the project that the Development member will belong to:" +
				" ");
		int id = createIdDevelopmentMember(idProject,controllerAll);


		for (Project project : controllerAll.getAllProjects())
		{
			if (project.getId() == idProject)
			{
				project.getAllDevelopmentMembers().add(new Developer(name, id));
			}
		}
		Scan.print(" You have successfully created " + name +
				" as a new Development Member with the id " + id);
	}

	public int createIdDevelopmentMember(int idProject, ControllerAll controllerAll)
	{
		int id = 1;

		for (Project project : controllerAll.getAllProjects())        //To get the project needed to create unique
		{                                               //id for product owner
			if (project.getId() == idProject)
			{
				if (project.getAllDevelopmentMembers().isEmpty())
				{
					id = 1;
				}
				else
				{
					id = project.getAllDevelopmentMembers().get(project.getAllDevelopmentMembers().size() - 1).getId() + 1;
				}
			}
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

		//create exception here for non valid input

		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		Project project = new Project(id, name, startDate, endDate);
		controllerAll.getAllProjects().add(project);

		//export ArrayList to a file

		Scan.print("You have successfully created the following project:\n\n" + project.toString());
	}



	/*------------------------------------Methods etc for sprints-------------------------------------------*/

	public void createSprint(ControllerAll controllerAll)
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

		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		Sprint sprint = new Sprint(name, startDate, endDate);
		controllerAll.getSprintBacklog().add(sprint);

		//export ArrayList to a file

		Scan.print("You have successfully created the following sprint:\n\n" + sprint.toString());

	}
}



