package Controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import Models.*;
import Utility.DataManagement;
import Utility.Scan;

import static Utility.PrintUtility.defaultMessage;
import static View.AllView.*;
import static View.DevTeamView.*;
import static View.ScrumMasterView.*;

public class ControllerAll
{
    //attributes
    private ArrayList<Project> allProjects;
    private DataManagement data;

    //Constructor:
    public ControllerAll()
    {
        allProjects = new ArrayList<>();
        data = new DataManagement();
    }

    //Getters & setters:
    public ArrayList<Project> getAllProjects()
    {
        return allProjects;
    }
    public void setAllProjects(ArrayList<Project> allProjects)
    {
        this.allProjects = allProjects;
    }

    //methods
    /*--------------------------------------------Main menu -----------------------------------------------------*/

    public void menuMain(ControllerAll controllerAll, ControllerScrumMaster contScrum ,
                         ControllerProductOwner contProOwner,
                         ControllerDeveloper contDeveloper)
    {
        boolean running = true;
        loadData();
        viewProjectMenu(controllerAll);
        Start();

        if (proName.isBlank() ) {
            contScrum.createProject(controllerAll);

        }else if (!legalProject()){

            while (!legalProject()){
                wrongProjectNameInput();
                Start();
            }
        }

        do
        {
            int option;
            try
            {
                option = mainMenu();
                switch (option)
                {
                    case 1:
                        contScrum.scrumMasterMenu(controllerAll, contProOwner, contScrum);
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
                        switchProject(controllerAll);
                        break;
                    case 6:
                        saveData();
                        Scan.closeScanner();
                        running = false; // Exit system.
                        break;
                    default:
                        defaultMessage();
                }
            } catch (Exception e)
            {
                numberFormatMessage();
            }
        } while (running);
    }

    public void switchProject(ControllerAll controllerAll){
        getProjectName(controllerAll);

        while (!legalProject()){
            wrongProjectNameInput();
            getProjectName(controllerAll);
        }
    }

    private void viewProjects()
    {
        for (Project project:allProjects)
        {
            Scan.print(project.toString());
        }
    }

    public boolean checkLegalId(int id) {
        boolean legal = true;

        Iterator<Project> iterator = allProjects.iterator();
        while (legal == true && iterator.hasNext()) {
            Project foundProject = iterator.next();
            if (foundProject.getId() == id) {
                legal = false;
            }
        }
        return legal;
    }

    public DataManagement getData() {
        return data;
    }

    public Project whichProject()
    {
        Project project = findProjectByName();
        return project;
    }

    public Project findProjectByName()
    {
        Project project = null;
        Iterator<Project> iterator = allProjects.iterator();
        while (iterator.hasNext() && project==null)
        {
            Project currentProject = iterator.next();
            if(currentProject.getName().equalsIgnoreCase(proName))
            {
                project = currentProject;
            }
        }
        return project;
    }

    public Task findTaskById()
    {
        int id = getTaskId();
        Task task = null;

        Iterator<Task> iterator = collectAllTasks().iterator();
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

    public UserStory findUStoryByNumber(){

        int number = getUserStoryID();
        UserStory userStory = null;

        Iterator<UserStory> iterator = collectAllStories().iterator();
        while (userStory == null && iterator.hasNext())
        {
            UserStory foundStory = iterator.next();
            if (foundStory.getId() == number)
            {
                userStory = foundStory;
            }
        }
        return userStory;

    }

    public Developer findDeveloperByID()
    {
        int id = getDeveloperId();
        Project project = whichProject();
        Developer developer = null;
        Iterator<Developer> iterator = project.getAllTeamMembers().iterator();
        while (developer == null && iterator.hasNext())
        {
            Developer foundDeveloper = iterator.next();
            if (foundDeveloper.getId() == id)
            {
                developer = foundDeveloper;
            }
        }
        return developer;
    }

    public ArrayList<Task> collectAllTasks()
    {
        Project project = whichProject();
        //put all tasks in one and the same arrayList
        ArrayList<Task> allTasks = new ArrayList<>();
        //fetch tasks from product backlog
        ArrayList<Task> productBLTasks = project.getProductBacklog().getTasks();

        for (Task task : productBLTasks)
        {
            allTasks.add(task);
        }

        //fetch tasks from sprint backlog
        ArrayList<SprintBacklog> sprintBLs = project.getAllSprintBacklogs();

        for (SprintBacklog sprintBL : sprintBLs)
        {
            ArrayList<Task> sprintTasks = sprintBL.getAllTasks();
            for (Task task : sprintTasks)
            {
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

    public void viewAllTasks(ControllerAll controllerAll){
        showAllTasks(controllerAll);
    }

    public ArrayList<UserStory> collectAllStories()
    {
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

        for (SprintBacklog sprintBL : sprintBLs)
        {
            //
            ArrayList<UserStory> sprintBLStories = sprintBL.getUserStories();
            for (UserStory story : sprintBLStories)
            {
                allStories.add(story);
            }
        }


        return allStories;
    }


    public void loadData()
    {
        setAllProjects(data.importProject(allProjects));
    }

    public void saveData()
    {
        data.exportProject(allProjects);
    }

    public void viewCompletedTasks()
    {
        ArrayList<Task> allTasks = collectAllTasks();
        ArrayList<Task> completedTasks = new ArrayList<>();
        for (Task task : allTasks)
        {
            if(task.getStatus().equals("Complete"))
            {
                completedTasks.add(task);
            }
        }
        printCompleteTasks(completedTasks);
    }

    public void viewCompletedUStories()
    {
        ArrayList<UserStory> allStories = collectAllStories();
        ArrayList<UserStory> completedStories = new ArrayList<>();
        for (UserStory userStory : allStories)
        {
            if(userStory.getStatus().equals("Complete"))
            {
                completedStories.add(userStory);
            }
        }
        printCompleteUStories(completedStories);
    }


    public void viewSprintDeadlines(){

        Project project = whichProject();

        ArrayList<SprintBacklog> allSprintBLs = project.getAllSprintBacklogs();

        Collections.sort(allSprintBLs, SprintBacklog::compareTo);


        printDeadlines(allSprintBLs);

    }

    public void viewTaskDeadlines(){
        ArrayList<Task> allTasks = collectAllTasks();

        ArrayList<Task> tasksWDeadlines = new ArrayList<>();
        ArrayList<Task> tasksWODeadlines = new ArrayList<>();

        for (Task task : allTasks){
            if (!(task.getDeadline()==null)){
                tasksWDeadlines.add(task);
            } else {
                tasksWODeadlines.add(task);
            }
        }

        Collections.sort(tasksWDeadlines, Task::compareByDeadline );

        printTaskDeadlines(tasksWDeadlines, tasksWODeadlines);

    }


    public void viewUStoryDeadlines(){
        ArrayList<UserStory> allStories = collectAllStories();

        ArrayList<UserStory> storiesWDeadlines = new ArrayList<>();
        ArrayList<UserStory> storiesWODeadlines = new ArrayList<>();

        for (UserStory userStory : allStories){
            if (!(userStory.getDeadline()==null)){
                storiesWDeadlines.add(userStory);
            } else {
                storiesWODeadlines.add(userStory);
            }
        }

        Collections.sort(storiesWDeadlines, UserStory::compareByDeadline );

        printUStoryDeadlines(storiesWDeadlines, storiesWODeadlines);


    }

    public boolean legalProject(){
        boolean existingProject;
        if (findProjectByName()==null){
            existingProject = false;
        } else {
            existingProject = true;
        }
        return existingProject;
    }

}
