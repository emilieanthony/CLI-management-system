package Controller;

import Models.Task;
import Utility.PrintUtility;
import Utility.Scan;
import View.DevTeamView;
import Models.Project;

import java.util.ArrayList;
import java.util.Iterator;

public class ControllerDeveloper
{


    public void teamMemberMenu(DevTeamView DeveloperView, ControllerAll controllerAll,Project project)
    {
        boolean running = true;
        do
        {
            int option = DeveloperView.menuTeamMember();
            switch (option)
            {
                case 1:
                    viewTasks(controllerAll);
                    break;
                case 2:
                    viewAllAssignedTasks(controllerAll);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    //Method for a dev to finding task by ID.

    public Task findTaskById(int id, ArrayList<Task> allTasks)
    {
        Task task = null;
        Iterator<Task> iterator = allTasks.iterator();
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



    public void viewAllAssignedTasks(ControllerAll controllerAll)
    {

        Project project = controllerAll.whichProject(controllerAll);
        Task task = null;
        Iterator<Task> iterator = project.getAllTasks().iterator();
        while (task == null && iterator.hasNext())
        {
            Task foundTask = iterator.next();
            if (!(foundTask.getAssignedTeamMembers().isEmpty()))
            {
                task = foundTask;
                Scan.print(task.toString());
            }
        }
    }

    public void viewTasks(ControllerAll controllerAll)
    {

        int id = Scan.readInt("Write your ID: ");
        int idProject =  Scan.readInt("Write the ID of the project you are working on: ");

        for (Project project : controllerAll.getAllProjects())
        {
            if (project.getId() == idProject)
            {
                Scan.print("----YOUR ASSIGNED TASK(S)----\n" + project.printPersonalTasks(project.getTeamMember(id)) + "\n" + "-----------------------------");
            }
        }
    }

}


