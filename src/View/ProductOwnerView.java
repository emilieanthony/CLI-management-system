package View;

import Models.ProductBacklog;
import Models.UserStory;
import Utility.Scan;

public class ProductOwnerView
{

    /*-----------------------------------1st Menu - menu for Product owner--------------------------------------------*/

    public int menuProductOwner()
    {
        int option = Scan.readInt("\n\nWelcome product owner!\n" +
                "Please enter an option below\n" +
                "1. Create a new product backlog\n" +
                "2. View product backlog\n" +
                "3. Edit product backlog\n" +
                "4. Go back to main menu\n");
        return option;

    }

    public ProductBacklog createBacklog()
    {
        String backlogName = Scan.readLine("Please enter product backlog name:");
        String startDate = Scan.readLine("Please enter start date: ex (yyyy/mm/dd)");
        String endDate = Scan.readLine("Please enter end date: ex (yyyy/mm/dd)");
        return new ProductBacklog(backlogName, startDate, endDate);
    }


    /*----------------------------------------------2nd Menu---------------------------------------------------------*/

    public int menuEditBacklog()
    {
        int option;
        option = Scan.readInt("Please enter the number of which part of the " +
                "backlog you want to " +
                "edit:\n" +
                "1- Edit Backlog name\n" +
                "2- Edit Backlog start date\n" +
                "3- Edit Backlog end date\n" +
                "4- Edit Backlog user stories\n" +
                "5- Add User Story\n" +
                "6- Remove User Story \n" +
                "7- Back to your menu");
        return option;
    }
    public String chooseBacklog(){
        String name = Scan.readLine("Please enter the name of the Backlog:");
        return name;
    }
    public String getBacklogName()
    {
        String nameBacklog = Scan.readLine("Please enter a new name for the backlog:");
        return nameBacklog;
    }

    public String getBacklogSDate() {
        String startDate = Scan.readLine("Please enter a new start date for the backlog ex" +
                "(day/Nov/2020):");
        return startDate;
    }

    public String getBacklogEDate() {
        String endDate = Scan.readLine("Please enter a new End date for the backlog ex" +
                "(day/Nov/2020):");
        return endDate;
    }

    public UserStory getUSInfo() {
        Scan.print("Add new user story");
        String name = Scan.readLine("Name: ");
        int number = Scan.readInt("User story number: ");
        String sprint = Scan.readLine("Sprint: ");
        int priority = Scan.readInt("Priority: ");
        int storyPoints = Scan.readInt("Story points: ");
        String content = Scan.readLine("Content: ");
        String acceptanceCriteria = Scan.readLine("Acceptance criteria: ");
        Scan.print("You have now added a new user story!");
        return new UserStory(name, number, sprint, priority, storyPoints, content, acceptanceCriteria);

    }

    public int getUSNumber()
    {
        Scan.print("Remove a user story");
        int number = Scan.readInt("Enter the number of the user story you want to remove:");
        return number;
    }


    /*----------------------------------------------3rd Menu---------------------------------------------------------*/
    public int menuEditUserStory()
    {
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

    public int getStoryNumber() {
        int number = Scan.readInt("Please enter the user story number you want to edit.");
        return number;

    }

    public int getNewUSNumber() {
        int newUSNumber = Scan.readInt("Enter a new number for the user story.");
        return newUSNumber;
    }

    public String getNewUSName() {
        String newUSName = Scan.readLine("Enter a new name for the user story.");
        return newUSName;
    }

    public String getNewUSSprint() {
        String newUSSprint = Scan.readLine("Enter a new sprint for the user story.");
        return newUSSprint;
    }

    public int getNewUSPriority() {
        int newUSPriority = Scan.readInt("Enter a new priority for the user story.");
        return newUSPriority;
    }

    public int getNewUSStoryPoints() {
        int newUSStoryPoints = Scan.readInt("Enter new story points for the user story.");
        return newUSStoryPoints;
    }

    public String getNewUSContent() {
        String newUSContent = Scan.readLine("Enter a new content for the user story.");
        return newUSContent;
    }

    public String getNewUSAcceptanceC() {
        String newUSAcceptanceC = Scan.readLine("Enter new acceptance criteria for the user " +
                "story.");
        return newUSAcceptanceC;
    }

    public String getNewUSStatus() {
        String newUSStatus = Scan.readLine("Enter new Status for the user " +
                "story.");
        return newUSStatus;
    }
    public void printRemoved(){
        Scan.print("\nThis User Story has been removed");
    }

}


