package Controller;

import Models.*;
import Utility.PrintUtility;
import Utility.Scan;
import View.DevTeamView;

import java.util.ArrayList;
import java.util.Iterator;

public class ControllerDeveloper
{


    public void teamMemberMenu(ControllerDeveloper controllerDev, DevTeamView developerView, ControllerAll controllerAll,Project project)
    {
        boolean running = true;
        do
        {
            int option = developerView.menuTeamMember();
            switch (option)
            {
                case 1:
                    viewMyTasks(controllerAll, developerView);
                    break;
                case 2:
                    viewAllTasks(controllerAll);
                    break;
                case 3:
                    Task task = openTask(controllerAll, developerView);

                    taskMenu(developerView,controllerAll,task);
                    break;
                case 4:
                    viewMyUStory(controllerAll, developerView);
                case 5:
                    developerView.printUserStories(project.getBacklog());
                case 6:
                    UserStory uStory = openUStory(controllerAll, developerView);
                    uStoryMenu(developerView, controllerAll, uStory);
                case 7:
                    running = false;
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    public void taskMenu(DevTeamView developerView, ControllerAll controllerAll,Task task)
    {

        boolean running = true;
        do
        {
            int option = developerView.taskMenu( task );
            switch (option)
            {
                case 1:
                    completeTask(controllerAll, developerView, task);
                    break;
                case 2:
                    //keeping open for future things
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    public void uStoryMenu(DevTeamView developerView, ControllerAll controllerAll,UserStory userStory)
    {

        boolean running = true;
        do
        {
            int option = developerView.uStoryMenu( userStory );
            switch (option)
            {
                case 1:
                    completeUStory(controllerAll, developerView, userStory);
                    break;
                case 2:
                    //keeping it open for future things
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

    public Task openTask(ControllerAll controllerAll, DevTeamView devTeamView){

        Project project = controllerAll.whichProject(controllerAll);

        ArrayList<Task> tasks = project.getAllTasks();

        int taskId = devTeamView.getTaskId();

        Task task = findTaskById(taskId, tasks);

        return task;
    }

    public UserStory openUStory(ControllerAll controllerAll, DevTeamView devTeamView){

        Project project = controllerAll.whichProject(controllerAll);

        ArrayList<UserStory> userStories = project.getBacklog().getAllUserStories();

        int uStoryNo = devTeamView.getUStoryId();

        UserStory userStory = findUStoryById(uStoryNo, userStories);

        return userStory;
    }

    public UserStory findUStoryById(int uStoryNo, ArrayList<UserStory> userStories){
        UserStory userStory = null;
        for (UserStory uStory : userStories){
            if (uStory.getNumber()==uStoryNo){
                userStory = uStory;
            }
        }
        return userStory;
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

    public void completeTask(ControllerAll controllerAll, DevTeamView devTeamView, Task task){

        int actualHrs = devTeamView.getActualHrs();

        task.setActualHrs( actualHrs );
        task.setDone();

    }

    public void completeUStory(ControllerAll controllerAll, DevTeamView devTeamView, UserStory userStory){

        int actualHrs = devTeamView.getActualVelocity();

        userStory.setActualVelocity( actualHrs );
        userStory.setStatus("completed");

    }


    public void viewMyUStory(ControllerAll controllerAll, DevTeamView devTeamView)
    {

        int id = devTeamView.getUserId();
        int idProject =  devTeamView.getProjectId();

        for (Project project : controllerAll.getAllProjects())
        {
            if (project.getId() == idProject) {
                Backlog backlog = project.getBacklog();
                Developer teamMember = project.getTeamMember(id);

                Scan.print("----YOUR ASSIGNED USER STORY(IES)----\n" + backlog.printAssignedUStories(teamMember) + "\n" + "-----------------------------");
            }
        }
    }

}


