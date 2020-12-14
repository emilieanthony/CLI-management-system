package View;

import Models.ProductBacklog;
import Models.UserStory;
import Utility.Scan;

import static View.ScrumMasterView.backlogName;
import static View.ScrumMasterView.proName;

public class ProductOwnerView
{

    /*-----------------------------------1st Menu - menu for Product owner--------------------------------------------*/

    public static int menuProductOwner()
    {
        int option = Scan.readInt("\n\nWelcome product owner!\n\n" +
                "You're working on Project " + proName +"." + "\n\n" +
                "Please enter an option below\n" +
                "1. Create a new product backlog\n" +
                "2. View product backlog\n" +
                "3. Edit product backlog\n" +
                "4. Choose project\n" +
                "5. Go back to main menu\n");
        return option;

    }

    public static ProductBacklog getBacklogInfo()
    {
        String backlogName = Scan.readLine("Please enter product backlog name:");
        String startDate = Scan.readLine("Please enter start date: ex (yyyy/mm/dd)");
        String endDate = Scan.readLine("Please enter end date: ex (yyyy/mm/dd)");
        return new ProductBacklog(backlogName, startDate, endDate);
    }


    /*----------------------------------------------2nd Menu---------------------------------------------------------*/

    public static int menuEditBacklog()
    {
        int option;
        option =
                Scan.readInt("\n\nNow you're accessing " + backlogName + " Backlog in "+  proName + " project!" +
                        "\n\nPlease enter the number of which part of the backlog you want to edit:\n\n" +
                "1- Edit Product Backlog name\n" +
                "2- Edit Product Backlog start date\n" +
                "3- Edit Product Backlog end date\n" +
                "4- Edit Product Backlog user stories\n" +
                "5- Add User Story\n" +
                "6- Remove User Story \n" +
                "7- Back to your menu");
        return option;
    }
    public String chooseBacklog(){
        String name = Scan.readLine("Please enter the name of the Backlog:");
        return name;
    }
    public static String getBacklogName()
    {
        String nameBacklog = Scan.readLine("\nPlease enter a new name for the backlog:");
        return nameBacklog;
    }

    public static String getBacklogSDate() {
        String startDate = Scan.readLine("\nPlease enter a new start date for the backlog ex" +
                "(day/Nov/2020):");
        return startDate;
    }

    public static String getBacklogEDate() {
        String endDate = Scan.readLine("\nPlease enter a new End date for the backlog ex" +
                "(day/Nov/2020):");
        return endDate;
    }

    public static UserStory getUSInfo() {
        Scan.print("Add new user story");
        String name = Scan.readLine("Name: ");
        int number = Scan.readInt("User story number: ");
        String sprint = Scan.readLine("SprintBacklog: ");
        int priority = Scan.readInt("Priority: ");
        String content = Scan.readLine("Content: ");
        String acceptanceCriteria = Scan.readLine("Acceptance criteria: ");
        Scan.print("You have now added a new user story!");
        return new UserStory(name, number, sprint, priority,content, acceptanceCriteria);

    }

    public static int getUSNumber()
    {
        Scan.print("\nRemove a user story");
        int number = Scan.readInt("\nEnter the number of the user story you want to remove:");
        return number;
    }


    /*----------------------------------------------3rd Menu---------------------------------------------------------*/
    public static int menuEditUserStory()
    {
        int option = Scan.readInt
                ("\n\nNow you're accessing a "+ backlogName + "'s backlog User story." + "\nWhich" +
                        " part of the user story you want to edit, enter a number:\n\n" +
                        "1- Edit user story number.\n" +
                        "2- Edit user story name.\n" +
                        "3- Edit user story sprint.\n" +
                        "4- Edit user story priority.\n" +
                        "5- Edit user story story points.\n" +
                        "6- Edit user story content.\n" +
                        "7- Edit user story acceptance criteria\n" +
                        "8- Edit user story status.\n" +
                        "9- Back to your menu.\n");
        return option;
    }

    public static int getStoryNumber() {
        int number = Scan.readInt("\nPlease enter the user story number you want to edit.");
        return number;

    }

    public static int getNewUSNumber() {
        int newUSNumber = Scan.readInt("\nEnter a new number for the user story.");
        return newUSNumber;
    }

    public static String getNewUSName() {
        String newUSName = Scan.readLine("\nEnter a new name for the user story.");
        return newUSName;
    }

    public static String getNewUSSprint() {
        String newUSSprint = Scan.readLine("\nEnter a new sprint for the user story.");
        return newUSSprint;
    }

    public static int getNewUSPriority() {
        int newUSPriority = Scan.readInt("\nEnter a new priority for the user story.");
        return newUSPriority;
    }

    public static int getNewUSStoryPoints() {
        int newUSStoryPoints = Scan.readInt("\nEnter new story points for the user story.");
        return newUSStoryPoints;
    }

    public static String getNewUSContent() {
        String newUSContent = Scan.readLine("\nEnter a new content for the user story.");
        return newUSContent;
    }

    public static String getNewUSAcceptanceC() {
        String newUSAcceptanceC = Scan.readLine("\nEnter new acceptance criteria for the user " +
                "story.");
        return newUSAcceptanceC;
    }

    public static String getNewUSStatus() {
        String newUSStatus = Scan.readLine("\nEnter new Status for the user " +
                "story.");
        return newUSStatus;
    }
    public static void printRemoved(){
        Scan.print("\nThis User Story has been removed");
    }

}


