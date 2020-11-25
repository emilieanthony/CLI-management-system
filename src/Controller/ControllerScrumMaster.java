package Controller;

import Models.*;
import Utility.PrintUtility;
import View.ProductOwnerView;
import View.ScrumMasterView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerScrumMaster {

	private Scanner scanner = new Scanner(System.in);
	//private Projects projects;
	//projects = new Projects();
	private ScrumMasterView viewScrumMaster = new ScrumMasterView();
	private ArrayList<ProjectModel> allProjects;
	private ArrayList<Sprint> allSprints;
	//Must be in method for create DevMember
	//arrayLists = new ArrayLists();

	public ControllerScrumMaster() {
		this.allProjects = new ArrayList<>();
		this.allSprints = new ArrayList<>();
	}

	public void scrumMasterMenu() {
		boolean running = true;
		do {
			int option = viewScrumMaster.menuScrumMaster();
			switch (option) {
				case 1: //Create a new project
					break;
				case 2: //Create a new sprint
					break;
				case 3: //Create a new task
					break;
				case 4:
					//createDevelopmentMember();
					break;
				case 5:
					//createProductOwner();
					break;
				case 6: //Assign task to DevTeam Member:
					/*System.out.println("Write the ID of the task: ");
					int idTask = scanner.nextInt();
					System.out.println("Write the ID of Development team member: ");
					int idMember = scanner.nextInt();
					projects.assignTask(idMember, idTask);
					System.out.println("Models.Task is now assigned to development team member!");*/
					break;
				case 7:// view backlog
					break;
				case 8:
					//project.viewAllDevelopmentMembers();
					break;
				case 9: // View all Product owners
					break;
				case 10:
					running = false; // Go back to main menu
					break;
				default:
					PrintUtility.defaultMessage();
					break;
			}
		} while (running);
	}

	/*------------------------------------------Methods product owner------------------------------------------------*/

	//TO DO - Methods refers to project and arrayLists. Make sure these are connected.
	/*public void createProductOwner() {
		System.out.println("Please type the name of the new Product Owner below: ");
		String name = scanner.nextLine();

		int id = createIdProductOwner();
		String typeId = "P";

		arrayLists.getAllProductOwners().add(new ProductOwner(name, typeId, id));

		System.out.println(" You have successfully created " + name + " as a new Product Owner with the id "
				+ typeId + id);

		arrayLists.viewAllProductOwners();
	}

	public int createIdProductOwner() { // from Dart project

		int id = 1;

		if (arrayLists.getAllProductOwners().isEmpty()) {
			id = 1;
		} else {
			id = arrayLists.getAllProductOwners().get(arrayLists.getAllProductOwners().size() - 1).getId() + 1;
		}
		return id;
	}

	*//*------------------------------------Methods create development member-------------------------------------------*//*

		//TO DO - arrayList
		public void createDevelopmentMember() {

			System.out.println("Please type the name of the new Development member below: ");
			String name = scanner.nextLine();

			int id = createIdDevelopmentMember();

			String typeId = "D";

			arrayLists.getAllDevelopmentMembers().add(new DevelopmentMember(name, typeId, id));

			System.out.println(" You have successfully created " + name + " as a new Development Member with the id "
					+ typeId + id + ".");

			arrayLists.viewAllDevelopmentMembers();

		}


	public int createIdDevelopmentMember() { // from Dart project

		int idNr = 1;

		if (arrayLists.getAllDevelopmentMembers().isEmpty()) {
			idNr = 1;
		} else {
			idNr = arrayLists.getAllDevelopmentMembers().get(arrayLists.getAllDevelopmentMembers().size() - 1).getId() + 1;
		}

		return idNr;

	}
}*/

	/*------------------------------------Methods etc for projects-------------------------------------------*/


	public ArrayList<ProjectModel> getAllProjects() {
		return allProjects;
	}

	public void setAllProjects(ArrayList<ProjectModel> allProjects) {
		this.allProjects = allProjects;
	}

	public void createProject() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter the name, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new project:");
		System.out.println("Name:");
		String name = scanner.nextLine();
		System.out.println("Start date (YYYY):");
		int startYear = scanner.nextInt();
		System.out.println("Start date (MM):");
		int startMonth = scanner.nextInt();
		System.out.println("Start date (DD):");
		int startDay = scanner.nextInt();
		System.out.println("End date (YYYY):");
		int endYear = scanner.nextInt();
		System.out.println("End date (MM):");
		int endMonth = scanner.nextInt();
		System.out.println("End date (DD):");
		int endDay = scanner.nextInt();

		//create exception here for nonvalid input

		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		ProjectModel project = new ProjectModel(name, startDate, endDate);
		allProjects.add(project);

		//export ArrayList to a file

		System.out.println("You have successfully created the following project:");
		System.out.println(project.toString());
	}

		/*------------------------------------Methods etc for sprints-------------------------------------------*/

		public ArrayList<Sprint> getAllSprints() {
			return allSprints;
		}

		public void setAllSprints(ArrayList<Sprint> allSprints) {
			this.allSprints = allSprints;
		}

	public void createSprint() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter the name, start date (YYYY-MM-DD), and end date (YYYY-MM-DD) of the new sprint:");
		System.out.println("Name:");
		String name = scanner.nextLine();
		System.out.println("Start date (YYYY):");
		int startYear = scanner.nextInt();
		System.out.println("Start date (MM):");
		int startMonth = scanner.nextInt();
		System.out.println("Start date (DD):");
		int startDay = scanner.nextInt();
		System.out.println("End date (YYYY):");
		int endYear = scanner.nextInt();
		System.out.println("End date (MM):");
		int endMonth = scanner.nextInt();
		System.out.println("End date (DD):");
		int endDay = scanner.nextInt();

		//create exception here for nonvalid input

		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		Sprint sprint = new Sprint(name, startDate, endDate);
		allSprints.add(sprint);

		//export ArrayList to a file

		System.out.println("You have successfully created the following sprint:");
		System.out.println(sprint.toString());

	}
}



