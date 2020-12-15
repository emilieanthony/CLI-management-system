package Controller;

import Models.*;
import Utility.Scan;

import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.defaultMessage;
import static Utility.PrintUtility.projectNotFound;
import static View.DevTeamView.getDeveloperId;
import static View.DevTeamView.getTaskId;
import static View.ScrumMasterView.*;

public class ControllerAll
{

    //attributes
    private ArrayList<Project> allProjects;

    //Constructor:
    public ControllerAll()
    {
        allProjects = new ArrayList<>();

    }

    //Getters:
    public ArrayList<Project> getAllProjects() {
        return allProjects;
    }

    //methods
    /*--------------------------------------------Main menu -----------------------------------------------------*/

    public int mainMenu()
    {

        int option = Scan.readInt("\n\nWelcome to Codelicode, your project management tool\n\n" +
                "Shift between your projects to be able to make any changes or view options,\nYou will find an option in your " +
                        "menu to choose between projects! \n" + "\nPlease enter a option below:\n" +
                "1. Scrum master\n" +
                "2. Product owner\n" +
                "3. Development team member\n" +
                "4. Exit system\n");
        return option;
    }


    public void menuMain(ControllerAll controllerAll, ControllerScrumMaster contScrum ,
                         ControllerProductOwner contProOwner,
                         ControllerDeveloper contDeveloper)
    {
        boolean running = true;
        Start();
        do
        {
            int option = mainMenu();
            switch (option)
            {
                case 1:
                    contScrum.scrumMasterMenu(contProOwner,controllerAll);
                    break;
                case 2:
                    contProOwner.productOwnerMenu(controllerAll);
                    break;
                case 3:
                    contDeveloper.teamMemberMenu(controllerAll,contProOwner);
                    break;
                case 4:
                    running = false; // Exit system.
                    break;
                default:
                    defaultMessage();
            }
        } while (running);
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

}
