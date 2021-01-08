package View;

import Models.ProductBacklog;
import Models.SprintBacklog;
import Models.UserStory;
import Utility.Scan;

import java.util.ArrayList;

import static View.ScrumMasterView.proName;

public class ProductOwnerView {

    /*-----------------------------------1st Menu - menu for Product owner--------------------------------------------*/

    public static int menuProductOwner() throws NumberFormatException {
        int option = Scan.readInt("\n\nWelcome Product Owner!\n\n" +
                "You are working on project " + proName + "." + "\n\n" +
                "Please enter an option below\n" +
                "1. View product backlog\n" +
                "2. Edit product backlog\n" +
                "3. Create user story to product backlog\n" +
                "4. Delete user story from product backlog\n" +
                "5  View all completed user stories\n" +
                "6. Switch project\n" +
                "7. Go back to main menu\n");
        return option;

    }

    public static void printPBLUStories(ArrayList<UserStory> userStories){
        Scan.print("The following user stories exist in the product backlog:");
        for (UserStory userStory : userStories){
            Scan.print(userStory.getId() + " " + userStory.getName());
        }
    }

    public static void userStoryFail() {
        Scan.print("There was a problem in creating the user story, please try again.");
    }

    public static void proBacklogCreationConf() {
        Scan.print("You have successfully created a new product backlog.");
    }

    public static ProductBacklog getBacklogInfo() throws Exception {
        String backlogName = Scan.readLine("Please enter product backlog name:");

        String startDate = ScrumMasterView.getStartDate();
        String endDate = ScrumMasterView.getEndDate();

        return new ProductBacklog(backlogName, startDate, endDate);
    }


    /*----------------------------------------------2nd Menu---------------------------------------------------------*/

    public static int menuEditBacklog() throws Exception {
        int option;
        option =
                Scan.readInt("\n\n You are working on project " + proName + "." +
                        "\n\nPlease enter the number of which part of the product backlog you want to edit:\n\n" +
                        "1. Edit product backlog name\n" +
                        "2. Edit product backlog start date\n" +
                        "3. Edit product backlog end date\n" +
                        "4. Edit product backlog user stories\n" +
                        "5. Back to your menu");
        return option;
    }

    public static String getProBacklogName() {
        String nameBacklog = Scan.readLine("\nPlease enter a new name for the product backlog:");
        return nameBacklog;
    }

    public static String getBacklogEDate() {

        String endDate = ScrumMasterView.getEndDate();
        return endDate;
    }

    public static UserStory getUSInfo(int id) throws Exception {
        Scan.print("Create new user story");
        String name = Scan.readLine("Name: ");
        //int number = Scan.readInt("User story number: ");
        int priority = Scan.readInt("Priority: ");
        String content = Scan.readLine("Content: ");
        String acceptanceCriteria = Scan.readLine("Acceptance criteria: ");
        return new UserStory(name, id, priority, content, acceptanceCriteria);

    }

    public static String getAnotherACriteria(){
        String criteria = Scan.readLine("Do you want to add another acceptance criteria? If YES enter the " +
                "acceptance criteria, if NO just press enter.");
        return criteria;
    }

    public static void createdUStoryReceipt(UserStory userStory) {
        Scan.print("\nYou have now created the following user story to your product backlog: \n" + userStory.toString());
    }

    public static int getUSid() {
        Scan.print("\nDelete a user story");
        int id = Scan.readInt("\nEnter the ID of the user story you want to delete from the product backlog:");
        return id;
    }


    /*----------------------------------------------3rd Menu---------------------------------------------------------*/
    public static int menuEditUserStory() {
        int option = Scan.readInt
                ("\n\nPlease enter the number of which part of the user story you want to edit:\n\n" +
                        "1. Edit user story name.\n" +
                        "2. Edit user story content.\n" +
                        "3. Edit user story acceptance criteria\n" +
                        "4. Remove user story acceptance criteria\n" +
                        "5. Back to your menu.\n");
        return option;
    }

    public static int getStoryID() {
        int id = Scan.readInt("\nPlease enter the ID of the user story you want to edit.");
        return id;

    }

    public static int getNewUSid() {
        int newUSid = Scan.readInt("\nEnter a new ID for the user story.");
        return newUSid;
    }

    public static String getNewUSName() {
        String newUSName = Scan.readLine("\nEnter a new name for the user story.");
        return newUSName;
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

  /*  public static String getNewUSAcceptanceC() {
        String newUSAcceptanceC = Scan.readLine("\nEnter new acceptance criteria for the user " +
                "story.");
        return newUSAcceptanceC;
    }*/

    public static int getNewUSStatus() {
        int newUSStatus = Scan.readInt("\nChoose the updated status of the user story:\n" +
                "1. Open\n" +
                "2. In progress\n" +
                "3. Complete \n" +
                "4. Assigned\n");

        return newUSStatus;
    }

    public static void printDeleted() {
        Scan.print("\n\n\nThis user story has been deleted\n");
    }

    public static void proBacklogEditConf() {
        Scan.print("\nYou have successfully edited the product backlog.");
    }

    public static void userStoryEditConf(UserStory userStory) {
        Scan.print("\nYou have successfully edited the following user story:  \n" + userStory.toString());
    }

    public static void changeStatusMessage() {
        Scan.print("\nThe user story has not been edited, You have to enter a number between 1 - 3.");
    }

    public static void changePriorityMessage() {
        Scan.print("\nThe user story has not been edited, You have to enter a number between 1 - 5.");
    }
    public static void nonExistentUStory(){
        Scan.print("The ID you entered does not match with any user story in the product backlog.");
    }

    public static void printSprint(SprintBacklog sprintBacklog){
        Scan.print(sprintBacklog.toString());
    }

    public static void emptyContent(){
        Scan.print("Content can't be blank. Try again.");
    }

    public static void printUStoryACriteria(UserStory userStory){
        if (!userStory.getAcceptanceCriteria().isEmpty()){
        Scan.print("This user story now has the following acceptance criteria");
        int listLine = 1;
        for (String criteria : userStory.getAcceptanceCriteria()){
            Scan.print(listLine + ". " + criteria);
            listLine++;
        }
        }else {
            Scan.print("This user story does not have any acceptance criteria yet. ");
        }
    }

    public static int optionRemoveACriteria(){
        int option = Scan.readInt("Enter the number of the acceptance criteria that you wish to remove: ");
        return option;
    }

    public static void invalidIndexPrint(){
        Scan.print("Error. The index you entered is invalid. Try again.");
    }

}


