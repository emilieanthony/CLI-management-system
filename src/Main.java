import Controller.ControllerAll;
import Controller.ControllerDeveloper;
import Controller.ControllerProductOwner;
import Controller.ControllerScrumMaster;
import Models.*;
import Utility.Import;
import View.DevTeamView;
import View.ProductOwnerView;
import View.ScrumMasterView;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args) {

        ControllerAll controllerAll = new ControllerAll();
        ControllerScrumMaster contScrum = new ControllerScrumMaster();
        ControllerProductOwner contProOwner = new ControllerProductOwner();
        ControllerDeveloper contDeveloper = new ControllerDeveloper();
        ScrumMasterView scrumView = new ScrumMasterView();
        ProductOwnerView proOwnerView = new ProductOwnerView();
        DevTeamView developerView = new DevTeamView();
        Project project = new Project(
                1,
                "Test Project",
                LocalDate.now(),
                LocalDate.now().plusDays(10));
        Backlog backlog = new Backlog("","","");
        Import importFile = new Import();
        importFile.importProjects(controllerAll,project,backlog);


//________________________TEST__________________________//



        /*Project project2 = new Project(
                1,
                "Test Project",
                LocalDate.now(),
                LocalDate.now().plusDays(10));
        ArrayList<Project> allProjects = controllerAll.getAllProjects();
        allProjects.add(project);
        allProjects.add(project2);



        Sprint sprint = new Sprint("sprint1",
                LocalDate.now(),
                LocalDate.now().plusDays(10));
        Sprint sprint2 = new Sprint("sprint1",
                LocalDate.now(),
                LocalDate.now().plusDays(10));
        ArrayList<Sprint> allSprints = controllerAll.getAllSprints();
        allSprints.add(sprint);
        allSprints.add(sprint2);


        Developer dev = new Developer("Bassam",1);

        Task task = new Task(1,2,"task 1"," A new task to be done soon.");
        Task task2 = new Task(2,5,"task 2"," A very new task to be done very soon.");
        task.getAssignedTeamMembers().add(dev);
        task2.getAssignedTeamMembers().add(dev);

        project.getAllTasks().add(task);
        project.getAllTasks().add(task2);

        Backlog backlog = new Backlog("Backlog1","1/12","11/12");
        ArrayList<Backlog> allBacklogs = controllerAll.getAllBacklogs();
        allBacklogs.add(backlog);

        UserStory US1 = new UserStory("US1",1,"1",3,6," A new USer Story","Here are the " +
                "Acceptance Criteria:");
        UserStory US2 = new UserStory("US2",2,"1",3,3," A new USer Story","Here are the " +
                "Acceptance Criteria:");
        UserStory US3 = new UserStory("US3",3,"1",6,7," A new USer Story","Here are the " +
                "Acceptance Criteria:");
        UserStory US4 = new UserStory("US4",4,"1",5,3," A new USer Story","Here are the " +
                "Acceptance Criteria:");
        UserStory US5 = new UserStory("US5",5,"1",4,9," A new USer Story","Here are the " +
                "Acceptance Criteria:");
        UserStory US6 = new UserStory("US6",6,"1",2,4," A new USer Story","Here are the " +
                "Acceptance Criteria:");
        ArrayList<UserStory> allUserStories = backlog.getAllUserStories();
        allUserStories.add(US1);
        allUserStories.add(US2);
        allUserStories.add(US3);
        allUserStories.add(US4);
        allUserStories.add(US5);
        allUserStories.add(US6);*/

//_________________________________________________ end test _____________________________//

        controllerAll.menuMain(controllerAll, contScrum, contProOwner, contDeveloper, scrumView,
                proOwnerView, developerView,project);

    }
}
