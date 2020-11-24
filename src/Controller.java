import java.util.Scanner;

public class Controller
{
	private Scanner scanner;
	private Project project;

	public Controller()
	{
		scanner = new Scanner(System.in);
		project = new Project();
	}

	public void runApplication()		//Temporary for testing of code
	{
		menuScrumMaster();
		menuTeamMember();
		menuTeamMember();
	}

	public void menuScrumMaster()
	{
		System.out.println("Welcome scrum master!\n" +
				"Please enter an option below\n" +
				"1. Create a new project\n" +
				"2. Create a new sprint\n" +
				"3. Create a new task\n" +
				"4. Create a new Development team member\n" +
				"5. Create a new Product owner\n" +
				"6. Assign a task to development team member\n" +  //Our task to solve
				"7. View product backlog\n" +
				"8. Go back to main menu");

		int option = scanner.nextInt();

		switch (option)
		{
			case 1:
				break;
			case 2:
				break;
			case 3:

				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				System.out.println("Write the ID of the task: ");
				int idTask = scanner.nextInt();
				System.out.println("Write the ID of Development team member: ");
				int idMember = scanner.nextInt();

				project.assignTask(idMember, idTask);

				System.out.println("Task is now assigned to development team member!");

				break;
			case 7:
				break;
			case 8:
				break;

		}
	}

	public void menuTeamMember()
	{
		System.out.println("Welcome development team member!\n" +
				"Please enter an option below\n" +
				"1. View my own tasks\n" +			//Our task to solve
				"2. View assigned tasks\n" +		//Not our task but
				"3. Add a new task to myself\n" +
				"4. Go back to main menu");

		int option = scanner.nextInt();

		switch (option)
		{
			case 1:
				System.out.println("Write your ID: ");
				int id = scanner.nextInt();
				System.out.println("----YOUR ASSIGNED TASK(S)----");
				System.out.print(project.printTasks(project.getTeamMember(id)));
				System.out.println("-----------------------------");
				break;
			case 2:
				System.out.println("------ALL ASSIGNED TASKS-----");
				System.out.print(project.printTasks());
				System.out.println("-----------------------------");
				break;
			case 3:
				break;
			case 4:
				break;
		}
	}


}
