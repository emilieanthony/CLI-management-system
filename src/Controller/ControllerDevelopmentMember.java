package Controller;

import Models.Task;
import Utility.Scan;
import View.DevTeamView;

import java.util.ArrayList;
import java.util.Iterator;

public class ControllerDevelopmentMember {

    //attributes
    private DevTeamView viewDevTeam = new DevTeamView();

    public void teamMemberMenu() {
        boolean running = true;
        do {
            int option = viewDevTeam.menuTeamMember();
            switch (option) {
                case 1:
                    //viewDevTeam.viewTasks(); //US 7 - should only show what's assigned to you
                    break;
                case 2:
                    //viewAllAssignedTasks(); // US11 - should show ALL assigned tasks and persons
                    break;
                case 3: // Add new task to myself
                    break;
                case 4: running = false;
                    break;
            }
        } while (running);
    }


    public Task findTaskById (int id, ArrayList<Task> allTasks){
        Task task = null;
        Iterator<Task> iterator = allTasks.iterator();
        while (task == null && iterator.hasNext()){
            Task foundTask = iterator.next();
            if(foundTask.getId() == id){
                task = foundTask;
            }
        }
        return task;
    }

    public void viewAllAssignedTasks(ArrayList<Task> allTasks) {
        Task task = null;
        Iterator<Task> iterator = allTasks.iterator();
        while (task == null && iterator.hasNext()) {
            Task foundTask = iterator.next();
            if (!(foundTask.getAssignedTeamMembers().isEmpty())){
                task = foundTask;
                Scan.print(task.toString());
            }
        }
    }
}


