package Utility;

import Controller.ControllerAll;
import Models.*;
import java.io.*;


import static View.ScrumMasterView.getFileName;

public class Import {

    public void importProjects(ControllerAll controllerAll){

        String fileName = getFileName();
        File file = new File(fileName);

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine())!=null){

                String[] projectInfo = line.split(";");

                if (projectInfo[0].equalsIgnoreCase("Project")){
                    int number = Integer.parseInt(projectInfo[1]);
                    String name = projectInfo[2];
                    String startDate = projectInfo[3];
                    String endDate = projectInfo[4];
                    Project project = new Project(number,name,startDate,endDate);
                    controllerAll.getAllProjects().add(project);
                    Scan.print(project.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("Backlog")){

                    String name = projectInfo[1];
                    String startDate = projectInfo[2];
                    String endDate = projectInfo[3];
                    String projectName = projectInfo[4];
                    ProductBacklog productBacklog = new ProductBacklog(name,startDate,endDate);
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.setProductBacklog(productBacklog);
                    Scan.print(productBacklog.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("SprintBacklog")){
                    String name = projectInfo[1];
                    String startDate = projectInfo[2];
                    String endDate = projectInfo[3];
                    String projectName = projectInfo[4];
                    SprintBacklog sprintBacklog = new SprintBacklog(name,startDate,endDate);
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getAllSprintBacklogs().add(sprintBacklog);
                    Scan.print(sprintBacklog.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("Task")){
                    int id = Integer.parseInt(projectInfo[1]);
                    int priorityN = Integer.parseInt(projectInfo[2]);
                    int estimatedH = Integer.parseInt(projectInfo[3]);
                    String name = projectInfo[4];
                    String description = projectInfo[5];
                    String projectName = projectInfo[6];
                    Task task = new Task(id,priorityN,estimatedH,name,description);
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getAllTasks().add(task);
                    Scan.print(task.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("US")){
                    String name = projectInfo[1];
                    int number =  Integer.parseInt(projectInfo[2]);
                    String sprint = projectInfo[3];
                    int priorityN = Integer.parseInt(projectInfo[4]);
                    String content = projectInfo[5];
                    String acceptanceCriteria = projectInfo[6];
                    String projectName = projectInfo[7];
                    UserStory userStory = new UserStory(name,number,sprint,priorityN,content,
                            acceptanceCriteria);
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getProductBacklog().getAllUserStories().add(userStory);
                    Scan.print(userStory.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("Developer")){
                    String name = projectInfo[1];
                    int id = Integer.parseInt(projectInfo[2]);
                    String projectName = projectInfo[3];
                    Developer developer = new Developer(name,id);
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getAllTeamMembers().add(developer);
                    Scan.print(developer.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("Productowner")){
                    String name = projectInfo[1];
                    int id = Integer.parseInt(projectInfo[2]);
                    String projectName = projectInfo[3];
                    ProductOwner productOwner = new ProductOwner(name,id);
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getAllProductOwners().add(productOwner);
                    Scan.print(productOwner.toString() + "\n");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
