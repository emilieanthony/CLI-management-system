import Models.BacklogModel;
import Models.UserStoryModel;

import java.util.Scanner;

public class ProductOwnerView {
    Scanner input = new Scanner(System.in);

    public int menuProductOwner() {

        System.out.println("Welcome product owner!\n" +
                "Please enter an option below\n" +
                "1. Create a new product backlog\n" +
                "2. View product backlog\n" +
                "3. Go back to main menu\n");

        int option = input.nextInt();
        switch (option) { //put switch in controller and call methods? Controller: "Accepts input and converts it to commands for the model or view"
            case 1:
                createBacklog();

            case 2:
                //viewBacklog(Backlog);

            case 3:
                // go back to main menu
        }
        return input.nextInt(); // För switchen i controllern

    }

    public BacklogModel createBacklog() {
        System.out.println("Please enter product backlog name:");
        String backlogName = input.nextLine();
        System.out.println("Please enter start date:");
        String startDate = input.nextLine();
        System.out.println("Please enter end date:");
        String endDate = input.nextLine();

        return new BacklogModel(backlogName, startDate, endDate);
    }

    public void viewBacklog(BacklogModel backlogModel){
        System.out.println(backlogModel.toString());

    }
}
