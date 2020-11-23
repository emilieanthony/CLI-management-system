package Controller;
import Models.BacklogModel;
import Models.Task;
import Models.UserStoryModel;
import Utility.Scan;
import View.ProductOwnerView;

import java.util.ArrayList;
import java.util.Iterator;

public class ControllerBacklog {

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

    // Why are these task methods here?
    public Task findTaskById(int id, ArrayList<Task> allTasks){

        Task task = null;
        Iterator<Task> iterator = allTasks.iterator();
        while (task == null && iterator.hasNext()){
            Task foundTask = iterator.next();
            if(foundTask.getId() == id){
                task = foundTask;
                Scan.print(task.toString());
            }
        }
        return task;
    }

    public void viewAssignedTasks(int id, ArrayList<Task> allTasks){
        Task task = findTaskById(id, allTasks);
        if(!(task.getAssignedTeamMembers().isEmpty())){
            Scan.print(task.toString());
        }
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
                    viewProductOwner.defaultMessage();
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
                case 5:
                    running = false;
                    break;
                default:
                    viewProductOwner.defaultMessage();
            }
        } while (running);
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
                    viewProductOwner.defaultMessage();
            }
        } while (running);
    }

    // Code below will be refactored if we choose to separate controller and view
    public int getStoryNumber(){
        int number = Scan.readInt("Please enter the user story number you want to edit.");
        return number;
    }

    public void editUSNumber(int number, BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        int newUSNumber = Scan.readInt("Enter a new number for the user story.");
        userStory.setNumber(newUSNumber);
    }
    public void editUSName(int number, BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        String newUSName = Scan.readLine("Enter a new name for the user story.");
        userStory.setName(newUSName);

    }
    public void editUSSprint(int number,BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        String newUSSprint = Scan.readLine("Enter a new sprint for the user story.");
        userStory.setSprint(newUSSprint);

    }
    public void editUSPriority(int number,BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        int newUSPriority = Scan.readInt("Enter a new priority for the user story.");
        Scan.readLine("");
        userStory.setPriority(newUSPriority);
    }
    public void editUSStoryPoints(int number, BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        int newUSStoryPoints = Scan.readInt("Enter new story points for the user story.");
        Scan.readLine("");
        userStory.setStoryPoints(newUSStoryPoints);
    }
    public void editUSContent(int number, BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        String newUSContent = Scan.readLine("Enter a new content for the user story.");
        userStory.setContent(newUSContent);

    }
    public void editUSAcceptanceC(int number, BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        String newUSAcceptanceC = Scan.readLine("Enter new acceptance criteria for the user " +
                "story.");
        userStory.setAcceptanceCriteria(newUSAcceptanceC);

    }
    public void editUSStatus(int number, BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, backlogModel.getAllUserStories());
        String newUSStatus = Scan.readLine("Enter new Status for the user " +
                "story.");
        userStory.setStatus(newUSStatus);
    }
}






