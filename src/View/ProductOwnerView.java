package View;

import Models.BacklogModel;
import Utility.Scan;
import java.util.Scanner;

public class ProductOwnerView {

    Scanner input = new Scanner(System.in);

    /*-----------------------------------1st Menu - menu for Product owner--------------------------------------------*/

    public int menuProductOwner() {
        //this method returns the user input as an int and is used in the method backlogMenu in ControllerBacklog
        Scan.print("Welcome product owner!\n" +
                "Please enter an option below\n" +
                "1. Create a new product backlog\n" +
                "2. View product backlog\n" +
                "3. Edit product backlog\n" +
                "4. Go back to main menu\n");

        int option = input.nextInt();
        return option;
    }

    public BacklogModel createBacklog() {
        input.nextLine();
        Scan.print("Please enter product backlog name:");
        String backlogName = input.nextLine();
        Scan.print("Please enter start date:");
        String startDate = input.nextLine();
        Scan.print("Please enter end date:");
        String endDate = input.nextLine();
        return new BacklogModel(backlogName, startDate, endDate);
    }

    public void viewBacklog(BacklogModel backlogModel) {
        Scan.print(backlogModel.toString());
    }


    /*----------------------------------------------2nd Menu---------------------------------------------------------*/

    public int menuEditBacklog() {
        int option;
        option = Scan.readInt("Please enter the number of which part of the " +
                "backlog you want to " +
                "edit:\n" +
                "1- Edit Backlog name\n" +
                "2- Edit Backlog start date\n" +
                "3- Edit Backlog end date\n" +
                "4- Edit Backlog user stories\n" +
                "5- Back to your menu");
        return option;
    }

    public void editBacklogName(BacklogModel backlogModel) {
        String name = Scan.readLine("Please enter a new name for the backlog:");
        backlogModel.setName(name);
    }

    public void editBacklogSDate(BacklogModel backlogModel) {
        String startDate = Scan.readLine("Please enter a new start date for the backlog ex" +
                "(day/Nov/2020):");
        backlogModel.setStartDate(startDate);
    }

    public void editBacklogEDate(BacklogModel backlogModel) {
        String endDate = Scan.readLine("Please enter a new end date for the backlog, ex(day/Nov/2020):");
        backlogModel.setEndDate(endDate);
    }

    /*----------------------------------------------3rd Menu---------------------------------------------------------*/
    public int menuEditUserStory() {
        int option = Scan.readInt
                ("Which part of the user story you want to edit, enter a " +
                        "number:\n" +
                        "1- Edit user story number.\n" +
                        "2- Edit user story name.\n" +
                        "3- Edit user story sprint.\n" +
                        "4- Edit user story priority.\n" +
                        "5- Edit user story story points.\n" +
                        "6- Edit user story content.\n" +
                        "7- Edit user story acceptance criteria\n" +
                        "8- Edit user story status.\n" +
                        "9- Back to your menu.");
        return option;
    }
}


