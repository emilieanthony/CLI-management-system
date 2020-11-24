package View;

import java.util.Scanner;

public class ScrumMasterView {
    Scanner input = new Scanner(System.in);

    public int menuScrumMaster() {
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
        return option;
    }
}



