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
        System.out.println("Welcome product owner!\n" +
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
        System.out.println("Please enter product backlog name:");
        String backlogName = input.nextLine();
        System.out.println("Please enter start date:");
        String startDate = input.nextLine();
        System.out.println("Please enter end date:");
        String endDate = input.nextLine();

        return new BacklogModel(backlogName, startDate, endDate);
    }

    public void viewBacklog(BacklogModel backlogModel) {
        System.out.println(backlogModel.toString());
    }

    //controller
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

    public int menuEditBacklog(BacklogModel backlogModel) {
        int option;
        do {
            option = Scan.readInt("Please enter the number of which part of the " +
                    "backlog you want to " +
                    "edit.\n" +
                    "1- Edit Backlog name.\n" +
                    "2- Edit Backlog start date.\n" +
                    "3- Edit Backlog end date.\n" +
                    "4- Edit Backlog user stories.\n" +
                    "5- Back to your menu.");
            Scan.readLine("");
            return option;
        } while (option <= 0 || option >= 6);
    }

    public void editBacklogName(BacklogModel backlogModel){
        String name = Scan.readLine("Write a new name for the backlog.");
        backlogModel.setName(name);
        menuEditBacklog(backlogModel);
    }
    public void editBacklogSDate(BacklogModel backlogModel){
        String startDate = Scan.readLine("Write a new start date for the backlog, ex" +
                "(day/Nov/2020)" +
                ".");
        backlogModel.setStartDate(startDate);
        menuEditBacklog(backlogModel);
    }
    public void editBacklogEDate(BacklogModel backlogModel){
        String endDate = Scan.readLine("Write a new end date for the backlog, ex(day/Nov/2020)");
        backlogModel.setEndDate(endDate);
        menuEditBacklog(backlogModel);
    }

    public void editUserStoryMenu(BacklogModel backlogModel){

        viewBacklog(backlogModel);

        int option;
        int number = Scan.readInt("Please enter the user story number you want to edit.");
        Scan.readLine("");

        do {

        findUStoryByNumber(number,backlogModel.getAllUserStories());

        option = Scan.readInt("Which part of the user story you want to edit, enter a " +
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
        Scan.readLine("");

            switch (option) {
                case 1: editUSNumber(number, backlogModel.getAllUserStories(),backlogModel);
                    break;
                case 2: editUSName(number,backlogModel.getAllUserStories(),backlogModel);
                    break;
                case 3: editUSSprint(number,backlogModel.getAllUserStories(),backlogModel);
                    break;
                case 4: editUSPriority(number,backlogModel.getAllUserStories(),backlogModel);
                    break;
                case 5: editUSStoryPoints(number,backlogModel.getAllUserStories(),backlogModel);
                    break;
                case 6: editUSContent(number,backlogModel.getAllUserStories(),backlogModel);
                    break;
                case 7: editUSAcceptanceC(number,backlogModel.getAllUserStories(),backlogModel);
                    break;
                case 8: editUSStatus(number,backlogModel.getAllUserStories(),backlogModel);
                    break;
                case 9: menuProductOwner();
                    break;
                default: Scan.print("Invalid choice, please enter a number between 1 to 9.\n");
            }
        }while (option <=0 || option >=10);

    }
    public void editUSNumber(int number, ArrayList<UserStoryModel> allUserStories,
                             BacklogModel backlogModel){

        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        int newUSNumber = Scan.readInt("Enter a new number for the user story.");
        userStory.setNumber(newUSNumber);
        editUserStoryMenu(backlogModel);
    }
    public void editUSName(int number,ArrayList<UserStoryModel> allUserStories,
                           BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        String newUSName = Scan.readLine("Enter a new name for the user story.");
        userStory.setName(newUSName);
        editUserStoryMenu(backlogModel);
    }
    public void editUSSprint(int number,ArrayList<UserStoryModel> allUserStories,BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        String newUSSprint = Scan.readLine("Enter a new sprint for the user story.");
        userStory.setSprint(newUSSprint);
        editUserStoryMenu(backlogModel);
    }
    public void editUSPriority(int number,ArrayList<UserStoryModel> allUserStories,BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        int newUSPriority = Scan.readInt("Enter a new priority for the user story.");
        Scan.readLine("");
        userStory.setPriority(newUSPriority);
        editUserStoryMenu(backlogModel);
    }
    public void editUSStoryPoints(int number,ArrayList<UserStoryModel> allUserStories,BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        int newUSStoryPoints = Scan.readInt("Enter new story points for the user story.");
        Scan.readLine("");
        userStory.setStoryPoints(newUSStoryPoints);
        editUserStoryMenu(backlogModel);
    }
    public void editUSContent(int number,ArrayList<UserStoryModel> allUserStories,BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        String newUSContent = Scan.readLine("Enter a new content for the user story.");
        userStory.setContent(newUSContent);
        editUserStoryMenu(backlogModel);
    }
    public void editUSAcceptanceC(int number,ArrayList<UserStoryModel> allUserStories,BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        String newUSAcceptanceC = Scan.readLine("Enter new acceptance criteria for the user " +
                "story.");
        userStory.setAcceptanceCriteria(newUSAcceptanceC);
        editUserStoryMenu(backlogModel);
    }
    public void editUSStatus(int number,ArrayList<UserStoryModel> allUserStories,BacklogModel backlogModel){
        UserStoryModel userStory = findUStoryByNumber(number, allUserStories);
        String newUSStatus = Scan.readLine("Enter new Status for the user " +
                "story.");
        userStory.setStatus(newUSStatus);
        editUserStoryMenu(backlogModel);
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
