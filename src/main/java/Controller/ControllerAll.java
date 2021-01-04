package Controller;

import Models.*;
import Utility.DataManagement;
import Utility.Scan;
import View.ScrumMasterView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.defaultMessage;
import static View.DevTeamView.*;
import static View.ScrumMasterView.*;

public class ControllerAll
{

    //attributes
    private ArrayList<Project> allProjects;
    private DataManagement Data;

    //Constructor:
    public ControllerAll()
    {
        allProjects = new ArrayList<>();
        Data = new DataManagement();

    }

    //Getters:
    public ArrayList<Project> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(ArrayList<Project> allProjects) {
        this.allProjects = allProjects;
    }
    //methods
    /*--------------------------------------------Main menu -----------------------------------------------------*/

    public int mainMenu()
    {

        int option = Scan.readInt("\n\nWelcome to Codelicode, your project management tool\n\n" +
                "IMPORTANT:- Shift between your projects to be able to make any changes or view " +
                "options,\nYou will find an option in your menu to choose between projects! \n" +
                "\nPlease enter a option below:\n"+
                "You're working on Project " + proName + "." + "\n\n"+
                "1. Scrum master.\n" +
                "2. Product owner.\n" +
                "3. Development team member.\n" +
                "4. View all Projects.\n" +
                "5. Change project.\n" +
                "6. Save and Exit system.\n");

        return option;
    }


    public void menuMain(ControllerAll controllerAll, ControllerScrumMaster contScrum ,
                         ControllerProductOwner contProOwner,
                         ControllerDeveloper contDeveloper)
    {
        boolean running = true;
        loadData();
        viewProjectMenu(controllerAll);
        Start();
        do
        {
            int option;
            try {
                option = mainMenu();
                switch (option) {
                    case 1:
                        contScrum.scrumMasterMenu(contProOwner, controllerAll, contScrum);
                        break;
                    case 2:
                        contProOwner.productOwnerMenu(controllerAll, contScrum);
                        break;
                    case 3:
                        contDeveloper.teamMemberMenu(controllerAll, contProOwner, contScrum);
                        break;
                    case 4:
                        viewProjects();
                        break;
                    case 5:
                        getProjectName(controllerAll);
                        break;
                    case 6:
                        saveData();
                        running = false; // Exit system.
                        break;
                    default:
                        defaultMessage();
                }
            } catch (NumberFormatException e) {
                numberFormatMessage();
            }
        } while (running);
    }
    private void viewProjects() {
        for (Project project:allProjects) {
            Scan.print(project.toString());
        }
    }

    public Project whichProject(){
        Project project = findProjectByName();
        return project;
    }

    public Project findProjectByName(){

        Project project = null;

        Iterator<Project> iterator = allProjects.iterator();

        while (iterator.hasNext() && project==null){
            Project currentProject = iterator.next();
            if(currentProject.getName().equalsIgnoreCase(proName)){
                project = currentProject;
            }
        }
        return project;
    }

   /* public Project findProjectImport(String sprintName){


        Project project = null;

        Iterator<Project> iterator = allProjects.iterator();

        while (iterator.hasNext() && project==null){
            Project currentProject = iterator.next();
            if(currentProject.getName().equalsIgnoreCase(sprintName)){
                project = currentProject;
            }
        }
        return project;

    }*/

    public Task findTaskById(ControllerAll controllerAll)
    {
        int id = getTaskId();
        Task task = null;
        Project project = controllerAll.whichProject();

        Iterator<Task> iterator = project.getProductBacklog().getTasks().iterator();
        while (task == null && iterator.hasNext())
        {
            Task foundTask = iterator.next();
            if (foundTask.getId() == id)
            {
                task = foundTask;
            }
        }
        return task;
    }

    public UserStory findUserStoryById(ControllerAll controllerAll)
    {
        int number = getUserStoryNumber();     //Do we want to sort by the User story's number?
        UserStory userStory = null;
        Project project = controllerAll.whichProject();
        Iterator<UserStory> iterator = project.getProductBacklog().getAllUserStories().iterator();
        while (userStory == null && iterator.hasNext())
        {
            UserStory foundUserStory = iterator.next();
            if (foundUserStory.getNumber() == number)
            {
                userStory = foundUserStory;
            }
        }
        return userStory;
    }

    public Developer findDeveloperByID() {
        int id = getDeveloperId();
        Project project = whichProject();
        Developer developer = null;
        Iterator<Developer> iterator = project.getAllTeamMembers().iterator();
        while (developer == null && iterator.hasNext()) {
            Developer foundDeveloper = iterator.next();
            if (foundDeveloper.getId() == id) {
                developer = foundDeveloper;
            }
        }
        return developer;
    }

    public ArrayList<Task> collectAllTasks( ) {

        Project project = whichProject();
        //put all tasks in one and the same arrayList
        ArrayList<Task> allTasks = new ArrayList<>();

        //fetch tasks from product backlog
        ArrayList<Task> productBLTasks = project.getProductBacklog().getTasks();

        for (Task task : productBLTasks) {
            allTasks.add(task);
        }

        //fetch tasks from sprint BL
        ArrayList<SprintBacklog> sprintBLs = project.getAllSprintBacklogs();

        for (SprintBacklog sprintBL : sprintBLs) {
            ArrayList<Task> sprintTasks = sprintBL.getAllTasks();
            for (Task task : sprintTasks) {
                allTasks.add(task);
            }
        }
        //fetch tasks from user stories in sprint BL
        for (SprintBacklog sprintBacklog: project.getAllSprintBacklogs())
        {
            for (UserStory userStory: sprintBacklog.getUserStories()) {
                ArrayList<Task> UserStoryTasks = userStory.getUserStoryTasks();
                for (Task task : UserStoryTasks)
                {
                    allTasks.add(task);
                }
            }
        }

        return allTasks;
    }

    public ArrayList<UserStory> collectAllStories() {

        Project project = whichProject();
        //put all user stories in one and the same ArrayList
        ArrayList<UserStory> allStories = new ArrayList<>();

        // fetch all user stories from product backlog
        ArrayList<UserStory> productBLStories = project.getProductBacklog().getAllUserStories();

        for (UserStory story : productBLStories) {
            allStories.add(story);
        }

        //fetch user story from sprint BL
        ArrayList<SprintBacklog> sprintBLs = project.getAllSprintBacklogs();

        for (SprintBacklog sprintBL : sprintBLs) {
            //
            ArrayList<UserStory> sprintBLStories = sprintBL.getUserStories();
            for (UserStory story : sprintBLStories) {
                allStories.add(story);
            }
        }

        return allStories;
    }

    public void loadData(){
        setAllProjects(Data.importProData(allProjects));
    }

    public void saveData(){
        Data.exportProData(allProjects);
    }

    public void viewCompleteTasks() {
        ArrayList<Task> allTasks = collectAllTasks();
        ArrayList<Task> completedTasks = new ArrayList<>();
        for (Task task : allTasks){
            if(task.getStatus() == "Done"){
                completedTasks.add(task);
            }
        }

        printCompleteTasks(completedTasks);

    }

    public void viewCompleteUStories() {
        ArrayList<UserStory> allStories = collectAllStories();
        ArrayList<UserStory> completedStories = new ArrayList<>();
        for (UserStory userStory : allStories){
            if(userStory.getStatus() == "Done"){
                completedStories.add(userStory);
            }
        }

        printCompleteUStories(completedStories);

    }


}
