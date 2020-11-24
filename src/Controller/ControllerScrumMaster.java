package Controller;

import Models.ProductOwner;
import View.ScrumMasterView;

import java.util.Scanner;

public class ControllerScrumMaster {

	private Scanner scanner;
	private Projects projects;
	private ScrumMasterView viewScrumMaster = new ScrumMasterView();

	public ControllerScrumMaster() {
		scanner = new Scanner(System.in);
		projects = new Projects();
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
				case 6: //Assign task to DevTeam Member
					System.out.println("Write the ID of the task: ");
					int idTask = scanner.nextInt();
					System.out.println("Write the ID of Development team member: ");
					int idMember = scanner.nextInt();
					projects.assignTask(idMember, idTask);
					System.out.println("Models.Task is now assigned to development team member!");
					break;
				case 7: // View product backlog
					break;
				case 8:
					//project.viewAllDevelopmentMembers();
					break;
				case 9: // View all Product owners
					break;
				case 10:
					running = false; // Go back to main menu
					break;
			}
		} while (running = true);
	}

	/*------------------------------------------Methods product owner------------------------------------------------*/
	/*private Projects projects;
	private Scanner scanner;

	public ControllerProductOwner() {
		projects = new Projects();
		scanner = new Scanner(System.in);
	}

	public void createProductOwner() {
		System.out.println("Please type the name of the new Product Owner below: ");
		String name = scanner.nextLine();

		// System.out.println("Please type the id of the new Product Owner below: ");
		// int id = scanner.nextInt();

		int id = createIdProductOwner();

		projects.getAllProductOwners().add(new ProductOwner(name, id));

		System.out.println(" You have successfully created " + name + " as a new Product Owner with the id " + id);

		projects.viewAllProductOwners();

	}

	public int createIdProductOwner() {

		int id = 200;

		if (projects.getAllProductOwners().isEmpty()) {
			id = 200;
		} else {
			id = projects.getAllProductOwners().get(projects.getAllProductOwners().size() - 1).getId() + 1;
		}
		return id;
	} */

	/*---------------------------------------Methods development team------------------------------------------------*/

// Missing method for getAllDevelopmentMembers

            /*public int createIdDevelopmentMember () {
                int id = 1;

                if (project.getAllDevelopmentMembers().isEmpty()) {
                    id = 1;
                } else {
                    id = project.getAllDevelopmentMembers().get(project.getAllDevelopmentMembers().size() - 1).getId() + 1;
                }
                return id;
            }*/

	// Add this method in ControllerScrumMaster - Case 4 menu Switch.

    /*public void createDevelopmentMember(){
        System.out.println("Please type the name of the new Development member below: ");
        String name = scanner.nextLine();

        int id = createIdDevelopmentMember();
        //System.out.println("Please type the id of the new Development member below: ");
        //int id = scanner.nextInt();

        project.getAllDevelopmentMembers().add(new DevelopmentMember(name, id));

        System.out.println(" You have successfully created " + name + " as a new Development Member with the id " + id);

        project.viewAllDevelopmentMembers();
    }*/

}
