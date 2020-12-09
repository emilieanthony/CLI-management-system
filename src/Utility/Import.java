package Utility;

import Controller.ControllerAll;
import Models.*;

import java.io.*;
import java.time.LocalDate;

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

                    int sYear = Integer.parseInt(projectInfo[3]);
                    int sMonth = Integer.parseInt(projectInfo[4]);
                    int sDay = Integer.parseInt(projectInfo[5]);

                    int eYear = Integer.parseInt(projectInfo[6]);
                    int eMonth = Integer.parseInt(projectInfo[7]);
                    int eDay = Integer.parseInt(projectInfo[8]);

                    LocalDate startDate = LocalDate.of(sYear,sMonth,sDay);
                    LocalDate endDate = LocalDate.of(eYear, eMonth, eDay);

                    Project project = new Project(number,name,startDate,endDate);
                    controllerAll.getAllProjects().add(project);
                    Scan.print(project.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("Backlog")){
                    String name = projectInfo[1];
                    String startDate = projectInfo[2];
                    String endDate = projectInfo[3];
                    ProductBacklog productBacklog = new ProductBacklog(name,startDate,endDate);
                    String projectName = projectInfo[4];
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.setProductBacklog(productBacklog);
                    Scan.print(productBacklog.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("SprintBacklog")){
                    String name = projectInfo[1];
                    int sYear = Integer.parseInt(projectInfo[2]);
                    int sMonth = Integer.parseInt(projectInfo[3]);
                    int sDay = Integer.parseInt(projectInfo[4]);

                    int eYear = Integer.parseInt(projectInfo[5]);
                    int eMonth = Integer.parseInt(projectInfo[6]);
                    int eDay = Integer.parseInt(projectInfo[7]);

                    LocalDate startDate = LocalDate.of(sYear,sMonth,sDay);
                    LocalDate endDate = LocalDate.of(eYear, eMonth, eDay);
                    SprintBacklog sprintBacklog = new SprintBacklog(name,startDate,endDate);
                    String projectName = projectInfo[8];
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getAllSprintBacklogs().add(sprintBacklog);
                    Scan.print(sprintBacklog.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("Task")){
                    int id = Integer.parseInt(projectInfo[1]);
                    int priorityN = Integer.parseInt(projectInfo[2]);
                    String name = projectInfo[4];
                    String description = projectInfo[5];
                    int estimatedH = Integer.parseInt(projectInfo[6]);
                    Task task = new Task(id,priorityN,estimatedH,name,description);
                    String projectName = projectInfo[7];
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getAllTasks().add(task);
                    String status = projectInfo[3];
                    task.setStatus(status);
                    Scan.print(task.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("US")){
                    String name = projectInfo[1];
                    int number =  Integer.parseInt(projectInfo[2]);
                    String sprint = projectInfo[3];
                    int priorityN = Integer.parseInt(projectInfo[4]);
                    int storyPoints = Integer.parseInt(projectInfo[5]);
                    String content = projectInfo[6];
                    String acceptanceCriteria = projectInfo[7];
                    UserStory userStory = new UserStory(name,number,sprint,priorityN,storyPoints
                            ,content,acceptanceCriteria);
                    String projectName = projectInfo[9];
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getProductBacklog().getAllUserStories().add(userStory);
                    //Set status
                    String status = projectInfo[8];
                    userStory.setStatus(status);
                    Scan.print(userStory.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("Developer")){
                    String name = projectInfo[1];
                    int id = Integer.parseInt(projectInfo[2]);
                    Developer developer = new Developer(name,id);
                    String projectName = projectInfo[3];
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getAllTeamMembers().add(developer);
                    Scan.print(developer.toString() + "\n");
                }
                if (projectInfo[0].equalsIgnoreCase("Productowner")){
                    String name = projectInfo[1];
                    int id = Integer.parseInt(projectInfo[2]);
                    ProductOwner productOwner = new ProductOwner(name,id);
                    String projectName = projectInfo[3];
                    Project foundProject = controllerAll.findProjectImport(projectName);
                    foundProject.getAllProductOwners().add(productOwner);
                    Scan.print(productOwner.toString() + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
