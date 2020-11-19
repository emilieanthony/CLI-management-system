import java.util.Scanner;

public class Controller
{
	private Scanner scanner;
	private Project project;
	private ScrumMaster scrumMaster;

	public Controller()
	{
		scanner = new Scanner(System.in);
		project = new Project();
		scrumMaster = new ScrumMaster();
	}

	public static void menuScrumMaster()
	{
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome scrum master!\n" +
				"Please enter an option below\n" +
				"1. Create a new project\n" +
				"2. Create a new sprint\n" +
				"3. Create a new task\n" +
				"4. Create a new Development team member\n" +
				"5. Create a new Product owner\n" +
				"6. Assign a task to development team member\n" + //Our task to solve
				"7. View product backlog\n" +
				"8. Go back to main menu");

		int option = input.nextInt();

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
				ScrumMaster
				break;
			case 7:
				break;
			case 8:
				break;


		}

	}

	public static void menuTeamMember()
	{
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome development team member!\n" +
				"Please enter an option below\n" +
				"1. View my own tasks\n" +
				"2. View assigned tasks\n" +		//Our task to solve
				"3. Add a new task to myself\n" +
				"4. Go back to main menu");

		int option = input.nextInt();

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
		}
	}


}
