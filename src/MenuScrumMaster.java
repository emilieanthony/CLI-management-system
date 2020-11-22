import java.util.Scanner;

public class MenuScrumMaster {

    private ControllerProductOwner controllerProductOwner = new ControllerProductOwner();
    private ControllerDevelopmentMember controllerDevelopmentMember = new ControllerDevelopmentMember();
    private Project project = new Project();
    private boolean running = true;


    public void menuScrumMaster() {
        while (running) {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome Scrum Master!\n" +
                    "Please enter an option below\n" +
                    "1. Create a new project\n" +
                    "2. Create a new sprint\n" +
                    "3. Create a new task\n" +
                    "4. Create a new Development Team Member\n" +
                    "5. Create a new Product owner\n" +
                    "6. Assign a task to Development Team Member\n" +
                    "7. View product backlog\n" +
                    "8. View all Development Team Members\n" +
                    "9. View all Product Owners\n" +
                    "10. Go back to main menu");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    controllerDevelopmentMember.createDevelopmentMember();
                    break;
                case 5:
                    controllerProductOwner.createProductOwner();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    project.viewAllDevelopmentMembers();
                    break;
                case 9:
                    project.viewAllProductOwners();
                    break;
                case 10:
                    break;
                //Menu.menu();
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }

        }

    }
}


