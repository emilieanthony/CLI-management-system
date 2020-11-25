package Controller;

import Models.*;
import Utility.PrintUtility;
import Utility.Scan;
import View.ProductOwnerView;
import java.util.ArrayList;
import java.util.Iterator;


public class ControllerProductOwner {

    //attributes
    private BacklogModel backlog = new BacklogModel();
    private ProductOwnerView viewProductOwner = new ProductOwnerView();

    //methods
    /*-----------------------------------Code to reuse--------------------------------------------*/
    public UserStoryModel findUStoryByNumber(int number, ArrayList<UserStoryModel> allUserStories) {
        UserStoryModel userStory = null;
        Iterator<UserStoryModel> iterator = allUserStories.iterator();
        while (userStory == null && iterator.hasNext()) {
            UserStoryModel foundUserStory = iterator.next();
            if (foundUserStory.getNumber() == number) {
                userStory = foundUserStory;
                Scan.print(userStory.toString());
            }
        }
        return userStory;
    }

    /*-----------------------------------1st Menu - menu for Product owner--------------------------------------------*/
    public void backlogMenu() {
        boolean running = true;
        do {
            int option = viewProductOwner.menuProductOwner();
            switch (option) {
                case 1:
                    backlog = viewProductOwner.createBacklog();
                    break;
                case 2:
                    viewProductOwner.viewBacklog(backlog);
                    break;
                case 3:
                    editBacklog();
                    break;
                case 4:
                    running = false; //go back to main menu
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    public void createBacklog() {

        viewProductOwner.createBacklog();
    }

    /*-----------------------------------2nd Menu - menu for editing backlog------------------------------------------*/
    public void editBacklog() {
        boolean running = true;
        do {
            int option = viewProductOwner.menuEditBacklog();
            switch (option) {
                case 1:
                    viewProductOwner.editBacklogName(backlog);
                    break;
                case 2:
                    viewProductOwner.editBacklogSDate(backlog);
                    break;
                case 3:
                    viewProductOwner.editBacklogEDate(backlog);
                    break;
                case 4:
                    viewProductOwner.menuEditUserStory();
                    break;
                case 5: addUserStory();
                    break;
                case 6: removeUserStory(backlog.getAllUserStories());
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    public void addUserStory(){
        UserStoryModel newUserStory = viewProductOwner.getUSInfo();
        backlog.getAllUserStories().add(newUserStory);
    }

    public void removeUserStory(ArrayList<UserStoryModel> allUserStories){
        int number = viewProductOwner.getUSNumber();
        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        allUserStories.remove(userStory);
    }

    /*--------------------------------3rd Menu - menu for editing user stories---------------------------------------*/
    public void editUserStory() {
        boolean running = true;
        viewProductOwner.viewBacklog(backlog);
        int number = getStoryNumber();
        do {
            int option = viewProductOwner.menuEditUserStory();

            switch (option) {
                case 1:
                    editUSNumber(number,backlog);
                    break;
                case 2:
                    editUSName(number,backlog);
                    break;
                case 3:
                    editUSSprint(number,backlog);
                    break;
                case 4:
                    editUSPriority(number,backlog);
                    break;
                case 5:
                    editUSStoryPoints(number, backlog);
                    break;
                case 6:
                    editUSContent(number, backlog);
                    break;
                case 7:
                    editUSAcceptanceC(number, backlog);
                    break;
                case 8:
                    editUSStatus(number, backlog);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    public int getStoryNumber(){
        int number = Scan.readInt("Please enter the user story number you want to edit.");
        return number;
    }

    public void editUSNumber(int number, BacklogModel backlogModel){
        viewProductOwner.getNewUSNumber();
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        userStory.setNumber(viewProductOwner.getNewUSNumber());
    }
    public void editUSName(int number, BacklogModel backlogModel){
        viewProductOwner.getNewUSName();
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        userStory.setName(viewProductOwner.getNewUSName());

    }
    public void editUSSprint(int number,BacklogModel backlogModel){
        viewProductOwner.getNewUSSprint();
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        userStory.setSprint(viewProductOwner.getNewUSSprint());

    }
    public void editUSPriority(int number,BacklogModel backlogModel){
        viewProductOwner.getNewUSPriority();
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        userStory.setPriority(viewProductOwner.getNewUSPriority());
    }
    public void editUSStoryPoints(int number, BacklogModel backlogModel){
        viewProductOwner.getNewUSStoryPoints();
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        userStory.setStoryPoints(viewProductOwner.getNewUSStoryPoints());
    }
    public void editUSContent(int number, BacklogModel backlogModel){
        viewProductOwner.getNewUSContent();
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        userStory.setContent(viewProductOwner.getNewUSContent());

    }
    public void editUSAcceptanceC(int number, BacklogModel backlogModel){
        viewProductOwner.getNewUSAcceptanceC();
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        userStory.setAcceptanceCriteria(viewProductOwner.getNewUSAcceptanceC());

    }
    public void editUSStatus(int number, BacklogModel backlogModel){
        viewProductOwner.getNewUSStatus();
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        userStory.setStatus(viewProductOwner.getNewUSStatus());
    }
}












