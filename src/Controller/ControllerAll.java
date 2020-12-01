package Controller;

import Models.*;
import Utility.PrintUtility;
import Utility.Scan;
import View.DevTeamView;
import View.ProductOwnerView;
import View.ScrumMasterView;

import java.util.ArrayList;
import java.util.Iterator;

public class ControllerAll
{

    //attributes

    private ArrayList<Project> allProjects;
    private ArrayList<Sprint> allSprints;
    private ArrayList<Backlog> allBacklogs;

    public ControllerAll()
    {
        allProjects = new ArrayList<>();
        allSprints = new ArrayList<>();
        allBacklogs = new ArrayList<>();
    }

    public ArrayList<Project> getAllProjects() {
        return allProjects;
    }
    public ArrayList<Sprint> getAllSprints() {
        return allSprints;
    }
    public ArrayList<Backlog> getAllBacklogs() {
        return allBacklogs;
    }


    //methods
    /*--------------------------------------------Main menu -----------------------------------------------------*/

    public int mainMenu()
    {

        int option = Scan.readInt("\n\nWelcome to Codelicode, your project management tool" +
                ".\nPlease " +
                "enter a option below:\n" +
                "1. Scrum master\n" +
                "2. Product owner\n" +
                "3. Development team member\n" +
                "4. Exit system\n");
        return option;
    }


    public void menuMain(ControllerAll controllerAll, ControllerScrumMaster contScrum ,
                         ControllerProductOwner contProOwner,
                         ControllerDeveloper contDeveloper, ScrumMasterView scrumView ,
                         ProductOwnerView proOwnerView, DevTeamView developerView
            ,Project project)
    {
        boolean running = true;
        do
        {
            int option = mainMenu();
            switch (option)
            {
                case 1:
                    contScrum.scrumMasterMenu(scrumView, proOwnerView,contProOwner,controllerAll);
                    break;
                case 2:
                    contProOwner.backlogMenu(proOwnerView,controllerAll,contProOwner);
                    break;
                case 3:
                    contDeveloper.teamMemberMenu(developerView,controllerAll,project);
                    break;
                case 4: running = false; // Exit system.
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }
    public Project whichProject(ControllerAll controllerAll){
        int projectId = Scan.readInt("Enter project id: ");
        Project project = controllerAll.findProjectById(projectId);
        return project;
    }
    public Project findProjectById(int id){
        Project project = null;

        Iterator<Project> iterator = allProjects.iterator();

        while (iterator.hasNext() && project==null){
            Project currentProject = iterator.next();
            if(currentProject.getId() == id){
                project = currentProject;
            }
        }
        return project;

    }


}
