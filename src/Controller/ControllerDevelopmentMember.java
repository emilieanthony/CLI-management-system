package Controller;

import View.DevTeamView;

public class ControllerDevelopmentMember {

    //attributes
    private DevTeamView viewDevTeam = new DevTeamView();

    public void teamMemberMenu() {
        boolean running = true;
        do {
            int option = viewDevTeam.menuTeamMember();
            switch (option) {
                case 1:
                    //viewDevTeam.viewTasks();
                    break;
                case 2:
                    //viewDevTeam.viewAssignedTasks();
                    break;
                case 3: // Add new task to myself
                    break;
                case 4: running = false;
                    break;
            }
        } while (running);
    }

    //Method for a dev to finding task by ID - is this method already created?
    /*public Task findTaskById (int id, ArrayList<Task> allTasks){

        Task task = null;
        Iterator<Task> iterator = allTasks.iterator();
        while (task == null && iterator.hasNext()){
            Task foundTask = iterator.next();
            if(foundTask.getId() == id){
                task = foundTask;
                Scan.print(task.toString());
            }
        }
        return task;
    }
    //Method for a dev to view assigned task
    public void viewAssignedTasks(int id, ArrayList<Task> allTasks){
        Task task = findTaskById(id, allTasks);
        if(!(task.getAssignedTeamMembers().isEmpty())){
            Scan.print(task.toString());
        }
    }*/

    }


