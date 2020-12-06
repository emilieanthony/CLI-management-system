package Utility;

import Controller.ControllerAll;
import Models.*;

import java.io.*;
import java.time.LocalDate;

public class Import {

    public void importProjects(ControllerAll controllerAll,Project project, ProductBacklog backlog){
        File file = new File("C:\\Users\\bassa\\OneDrive\\Skrivbord\\Info.txt");
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

                    project = new Project(number,name,startDate,endDate);
                    controllerAll.getAllProjects().add(project);
                }
                if (projectInfo[0].equalsIgnoreCase("Backlog")){
                    String name = projectInfo[1];
                    String startDate = projectInfo[2];
                    String endDate = projectInfo[3];
                    backlog = new ProductBacklog(name,startDate,endDate);
                    controllerAll.getProjectBacklog().add(backlog);
                }
                if (projectInfo[0].equalsIgnoreCase("Sprint")){
                    String name = projectInfo[1];
                    int sYear = Integer.parseInt(projectInfo[2]);
                    int sMonth = Integer.parseInt(projectInfo[3]);
                    int sDay = Integer.parseInt(projectInfo[4]);

                    int eYear = Integer.parseInt(projectInfo[5]);
                    int eMonth = Integer.parseInt(projectInfo[6]);
                    int eDay = Integer.parseInt(projectInfo[7]);

                    LocalDate startDate = LocalDate.of(sYear,sMonth,sDay);
                    LocalDate endDate = LocalDate.of(eYear, eMonth, eDay);
                    Sprint sprint = new Sprint(name,startDate,endDate);
                    controllerAll.getSprintBacklog().add(sprint);
                }
                if (projectInfo[0].equalsIgnoreCase("Task")){
                    int id = Integer.parseInt(projectInfo[1]);
                    int priorityN = Integer.parseInt(projectInfo[2]);
                    String name = projectInfo[4];
                    String description = projectInfo[5];
                    Task task = new Task(id,priorityN,name,description);
                    project.getAllTasks().add(task);
                    //Set status. a setter method should be implemented instead.
                    if (projectInfo[3].equalsIgnoreCase("Open")){
                        task.setOpen();
                    }else if(projectInfo[3].equalsIgnoreCase("In progress")){
                        task.setInProgress();
                    }
                    else if(projectInfo[3].equalsIgnoreCase("Done")){
                        task.setDone();
                    }
                }
                if (projectInfo[0].equalsIgnoreCase("User Story")){
                    String name = projectInfo[1];
                    int number =  Integer.parseInt(projectInfo[2]);
                    String sprint = projectInfo[3];
                    int priorityN = Integer.parseInt(projectInfo[4]);
                    int storyPoints = Integer.parseInt(projectInfo[5]);
                    String content = projectInfo[6];
                    String acceptanceCriteria = projectInfo[7];

                    UserStory userStory = new UserStory(name,number,sprint,priorityN,storyPoints
                    ,content,acceptanceCriteria);
                    backlog.getAllUserStories().add(userStory);
                    //Set status
                    String status = projectInfo[8];
                    userStory.setStatus(status);
                }
                if (projectInfo[0].equalsIgnoreCase("Developer")){
                    String name = projectInfo[1];
                    int id = Integer.parseInt(projectInfo[2]);
                    Developer developer = new Developer(name,id);
                    project.getAllDevelopmentMembers().add(developer);
                }
                if (projectInfo[0].equalsIgnoreCase("Product Owner")){
                    String name = projectInfo[1];
                    int id = Integer.parseInt(projectInfo[2]);
                    ProductOwner productOwner = new ProductOwner(name,id);
                    project.getAllProductOwners().add(productOwner);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
