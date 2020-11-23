package View;

import Models.BacklogModel;
import Models.Task;
import Models.UserStoryModel;
import Utility.Scan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ProductOwnerView {
    Scanner input = new Scanner(System.in);

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

    public void defaultMessage(){
        Scan.print("Invalid input, please enter another option\n");
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

    public int menuEditBacklog() {
        int option;
            option = Scan.readInt("Please enter the number of which part of the " +
                    "backlog you want to " +
                    "edit.\n" +
                    "1- Edit Backlog name.\n" +
                    "2- Edit Backlog start date.\n" +
                    "3- Edit Backlog end date.\n" +
                    "4- Edit Backlog user stories.\n" +
                    "5- Back to your menu.");
            return option;
    }

    public void editBacklogName(BacklogModel backlogModel){
        String name = Scan.readLine("Write a new name for the backlog.");
        backlogModel.setName(name);
        menuEditBacklog();
    }
    public void editBacklogSDate(BacklogModel backlogModel){
        String startDate = Scan.readLine("Write a new start date for the backlog, ex" +
                "(day/Nov/2020)" +
                ".");
        backlogModel.setStartDate(startDate);
        menuEditBacklog();
    }
    public void editBacklogEDate(BacklogModel backlogModel){
        String endDate = Scan.readLine("Write a new end date for the backlog, ex(day/Nov/2020)");
        backlogModel.setEndDate(endDate);
        menuEditBacklog();
    }

    public int menuEditUserStory() {
        int option = Scan.readInt
                    ("Which part of the user story you want to edit, enter a " +
                            "number\n" +
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

}
