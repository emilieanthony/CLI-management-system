package Controller;

import Models.*;
import Utility.Scan;

import java.util.ArrayList;
import java.util.Iterator;

import static Controller.ControllerScrumMaster.sprintName;
import static Utility.PrintUtility.defaultMessage;

import static View.DevTeamView.*;
import static View.ProductOwnerView.getStoryNumber;
import static View.ScrumMasterView.getProjectName;
import static View.ScrumMasterView.getSprintBacklogByName;


public class ControllerDeveloper {

    public void teamMemberMenu(ControllerAll controllerAll,ControllerProductOwner proCont, ControllerScrumMaster scrumMaster) {
        boolean running = true;
        do {

            int option;

            try {
                option = menuTeamMember();

                switch (option) {
                    case 1:
                        viewMyTasks(controllerAll,scrumMaster);
                        break;
                    case 2:
                        viewAllAssignedTasks(controllerAll,scrumMaster);
                        break;
                    case 3:
                        taskMenu(controllerAll);
                        break;
                    case 4:
                        proCont.viewProBacklog(controllerAll);
                        proCont.editUSStatus(getStoryNumber(),controllerAll);
                        break;
                    case 5:
                        proCont.viewProBacklog(controllerAll);//View product backlog
                        break;
                    case 6:
                        scrumMaster.viewSprintBacklog(controllerAll);//View sprint backlog
                        break;
                    case 7:
                        viewAllTasks(controllerAll, scrumMaster);//View all tasks
                        break;
                    case 8:
                        getProjectName(controllerAll);// Switch project.
                        break;
                    case 9:
                        running = false;
                        break;
                    default:
                        defaultMessage();
                }
            } catch (Exception e) {
                invalidInputPrint();
            }
        } while (running);
    }

    //---------------------------------Method----------------------------------------------//

    public void taskMenu(ControllerAll controllerAll)
    {

        boolean running = true;
        do
        {
            int option = getTaskMenu();
            switch (option)
            {
                case 1:
                    completeTask(controllerAll);
                    break;
                case 2:
                    running = false;
                    break;
                default:
                    defaultMessage();
            }
        } while (running);
    }

    public void viewMyTasks(ControllerAll controllerAll,ControllerScrumMaster contScrum) {
        Task task = findTaskByDeveloper(controllerAll,contScrum);
        Scan.print(task.toString());
    }

    public Task findTaskByDeveloper(ControllerAll controllerAll,ControllerScrumMaster contScrum) {
        Task task = null;
        Developer developer = controllerAll.findDeveloperByID();
        Iterator<Task> iterator = contScrum.collectAllTasks(controllerAll).iterator();
        while (task == null && iterator.hasNext()) {
            Task foundTask = iterator.next();
            if (foundTask.getAssignedDevelopers().contains(developer)) {
                task = foundTask;
            }
        }
        return task;
    }

    public void viewAllAssignedTasks(ControllerAll controllerAll,ControllerScrumMaster contScrum) {

        ArrayList<Task> allTasks = contScrum.collectAllTasks(controllerAll);
        for (Task task:allTasks) {
            if (task == null) {
                noAssignedTasks();
            }else if(task.getStatus().equalsIgnoreCase("In progress")) {
                Scan.print(task.toString());
            }
        }
    }

    public void viewAllTasks(ControllerAll controllerAll, ControllerScrumMaster scrumMaster){
        ArrayList<Task> allTasks = scrumMaster.collectAllTasks(controllerAll);
        printAllTasks(allTasks);
    }


    public Task openTask(ControllerAll controllerAll) {

        Task task = controllerAll.findTaskById(controllerAll);
        Scan.print(task.toString());

        return task;
    }
    public void completeTask(ControllerAll controllerAll){

        Task task = openTask(controllerAll);
        int actualHrs = getActualHrs();
        task.setActualHours( actualHrs );
        task.setDone();

    }
}


