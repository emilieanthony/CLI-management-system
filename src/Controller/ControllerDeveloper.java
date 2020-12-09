package Controller;

import Models.Developer;
import Models.Task;
import Utility.Scan;
import Models.Project;
import View.DevTeamView;

import java.util.ArrayList;
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
    public Task openTask(ControllerAll controllerAll, DevTeamView devTeamView){

        Project project = controllerAll.whichProject(controllerAll);

        ArrayList<Task> tasks = project.getAllTasks();

        int taskId = devTeamView.getTaskId();

        Task task = findTaskById(taskId, tasks);

        return task;
    }
    public String viewAllTasks(ControllerAll controllerAll) {

        String taskList = "";

        Project project = controllerAll.whichProject(controllerAll);

        Task task = null;

        Iterator<Task> iterator = project.getAllTasks().iterator();

        while (task == null && iterator.hasNext())
        {
            Task foundTask = iterator.next();

            if (!(foundTask.getAssignedTeamMembers().isEmpty()))
            {
                task = foundTask;
                taskList =  taskList + task.toString() + "\n\n";
            }
        }
        return taskList;
    }

    public void viewTasks(ControllerAll controllerAll)
    {

        int id = Scan.readInt("Write your ID: ");
        int idProject =  Scan.readInt("Write the ID of the project you are working on: ");

        for (Project project : controllerAll.getAllProjects())
        {
            if (project.getId() == idProject)
            {
                Scan.print("----YOUR ASSIGNED TASK(S)----\n" + project.printTasks(project.getTeamMember(id)) + "\n" + "-----------------------------");
            }
        }
    }

}
    public void viewMyTasks(ControllerAll controllerAll, DevTeamView devTeamView)
    {   String assignedTasks = "";

        int id = devTeamView.getUserId();
        int idProject =  devTeamView.getProjectId();

        for (Project project : controllerAll.getAllProjects())
        {
            if (project.getId() == idProject)
            {
                Developer teamMember = project.getTeamMember(id);
                assignedTasks = "----YOUR ASSIGNED TASK(S)----\n" + project.printAssignedTasks(teamMember) + "\n" + "-----------------------------";
            }
        }
        devTeamView.printTasks(assignedTasks);
    }


