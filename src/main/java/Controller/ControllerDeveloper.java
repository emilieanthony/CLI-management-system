package Controller;

import Models.*;
import Utility.Scan;
import java.util.ArrayList;

import static Utility.PrintUtility.defaultMessage;
import static View.DevTeamView.*;
import static View.ScrumMasterView.emptyName;


public class ControllerDeveloper {

    public void teamMemberMenu(ControllerAll controllerAll,ControllerProductOwner proCont, ControllerScrumMaster scrumMaster) {
        boolean running = true;
        do {

            int option;

            try {
                option = menuTeamMember();

                switch (option) {
                    case 1:
                        viewMyTasks(controllerAll);
                        break;
                    case 2:
                        viewAllAssignedTasks(controllerAll);
                        break;
                    case 3:
                        completeTask(controllerAll);
                        break;
                    case 4:
                        UserStory userStory = controllerAll.findUStoryByNumber();
                        scrumMaster.changeUSStatus(userStory, controllerAll);;
                        break;
                    case 5:
                        proCont.viewProBacklog(controllerAll);
                        break;
                    case 6:
                        scrumMaster.viewSprintBacklog(controllerAll);
                        break;
                    case 7:
                        controllerAll.viewAllTasks(controllerAll);
                        break;
                    case 8:
                        controllerAll.viewCompletedTasks();
                        break;
                    case 9:
                        controllerAll.viewCompletedUStories();
                        break;
                    case 10:
                        controllerAll.viewTaskDeadlines();
                        break;
                    case 11:
                        controllerAll.viewUStoryDeadlines();
                        break;
                    case 12:
                        controllerAll.switchProject(controllerAll);// Switch project.
                        break;
                    case 13:
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

    //---------------------------------Methods----------------------------------------------//


    public void viewMyTasks(ControllerAll controllerAll) {
        ArrayList<Task> assignedTasks = new ArrayList<>();
        Developer developer = controllerAll.findDeveloperByID();

        for (Task task : controllerAll.collectAllTasks())
        {
            for (Developer dev : task.getAssignedDevelopers())
            {
                if(dev.getId() == developer.getId())
                {
                    assignedTasks.add(task);
                }
            }
        }

        printMyAssignedTasks(assignedTasks);

    }

    public void viewAllAssignedTasks(ControllerAll controllerAll) {
        ArrayList<Task> allTasks = controllerAll.collectAllTasks();
        printAllAssignedTasks(allTasks);
    }

    public Task openTask(ControllerAll controllerAll) {
        Task task = controllerAll.findTaskById();
        Scan.print(task.toString());
        return task;
    }

    public void completeTask(ControllerAll controllerAll){


        Task task = openTask(controllerAll);

        int actualHrs = getActualHrs();

        while (actualHrs<0){
            negativeNumberPrint();
            actualHrs = getActualHrs();
        }

        String name = getNameCompleteTask();

        while (name.isBlank()){
            emptyName();
            name = getNameCompleteTask();
        }

        task.setActualHours( actualHrs );

        task.setComplete();

        task.setCompletedBy(name);

        taskCompletedReceipt(task);
    }
}


