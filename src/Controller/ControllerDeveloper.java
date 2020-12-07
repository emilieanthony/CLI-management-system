package Controller;

import Models.Developer;
import Models.Task;
import Utility.Scan;
import Models.Project;

import java.util.Iterator;

import static Utility.PrintUtility.defaultMessage;
import static View.DevTeamView.menuTeamMember;
import static View.ScrumMasterView.getProjectName;

public class ControllerDeveloper
{

    public void teamMemberMenu(ControllerAll controllerAll)
    {
        boolean running = true;
        do
        {

            int option = menuTeamMember();
            switch (option)
            {
                case 1:
                    viewMyTasks(controllerAll);
                    break;
                case 2:
                    viewAllAssignedTasks(controllerAll);
                    break;
                case 3:
                    getProjectName();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    defaultMessage();
            }
        } while (running);
    }

    //---------------------------------Method----------------------------------------------//

    public void viewMyTasks(ControllerAll controllerAll){
        Task task = findTaskByDeveloper(controllerAll);
        Scan.print(task.toString());
    }

    public Task findTaskByDeveloper(ControllerAll controllerAll)
    {
        Task task = null;
        Developer developer = controllerAll.findDeveloperByID();
        Project project = controllerAll.whichProject();
        Iterator<Task> iterator = project.getAllTasks().iterator();
        while (task == null && iterator.hasNext())
        {
            Task foundTask = iterator.next();
            if (foundTask.getAssignedTeamMembers().contains(developer))
            {
                task = foundTask;
            }
        }
        return task;
    }

    public void viewAllAssignedTasks(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();

        for (Task task : project.getAllTasks()) {
            if (task.getStatus().equalsIgnoreCase("In progress")){
                Scan.print(task.toString());
            }
        }
    }

}


