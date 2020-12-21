package Controller;

import Models.*;
import Utility.DataManagement;
import Utility.Scan;

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
            int option = mainMenu();
            switch (option)
            {
                case 1:
                    contScrum.scrumMasterMenu(contProOwner,controllerAll,contScrum);
                    break;
                case 2:
                    contProOwner.productOwnerMenu(controllerAll, contScrum);
                    break;
                case 3:
                    contDeveloper.teamMemberMenu(controllerAll,contProOwner, contScrum);
                    break;
                case 4:
                    viewProjects();
                    break;
                case 5:
                    getProjectName();
                    break;
                case 6:
                    saveData();
                    running = false; // Exit system.
                    break;
                default:
                    defaultMessage();
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

    public Project findProjectImport(String name){


        Project project = null;

        Iterator<Project> iterator = allProjects.iterator();

        while (iterator.hasNext() && project==null){
            Project currentProject = iterator.next();
            if(currentProject.getName().equalsIgnoreCase(name)){
                project = currentProject;
            }
        }
        return project;

    }

    public Task findTaskById(ControllerAll controllerAll)
    {
        int id = getTaskId();
        Task task = null;
        Project project = controllerAll.whichProject();

        Iterator<Task> iterator = project.getProductBacklog().getTasksImport().iterator();
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

    public void loadData(){

        setAllProjects(Data.importProData(allProjects));
    }

    public void saveData(){
        Data.exportProData(allProjects);
    }

}
