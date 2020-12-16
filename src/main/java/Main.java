import Controller.ControllerAll;
import Controller.ControllerDeveloper;
import Controller.ControllerProductOwner;
import Controller.ControllerScrumMaster;
import Models.*;


public class Main {


    public static void main(String[] args) {

        ControllerAll controllerAll = new ControllerAll();
        ControllerScrumMaster contScrum = new ControllerScrumMaster();
        ControllerProductOwner contProOwner = new ControllerProductOwner();
        ControllerDeveloper contDeveloper = new ControllerDeveloper();

        testSetup(controllerAll);

        controllerAll.menuMain(controllerAll, contScrum, contProOwner, contDeveloper);


    }

    public static void testSetup(ControllerAll controllerAll)
    {
        Project project = new Project(1,"Test project","2020-01-01","2020-02-01");
        controllerAll.getAllProjects().add(project);
        ProductBacklog productBacklog = new ProductBacklog("backlog","2020/12/12","2021/12/12");
        SprintBacklog sprintBacklog = new SprintBacklog("sprint backlog", "2020/12/12","020/12/30");
        Developer developer1 = new Developer("Arne",1);
        Developer developer2 = new Developer("Åsa",2);
        Task task = new Task(1,2,3,"menu","make a menu");
        UserStory userStory = new UserStory("menu",1,"1",2,"menu","none");
        project.setProductBacklog(productBacklog);
        project.getAllDevelopmentMembers().add(developer1);
        project.getAllDevelopmentMembers().add(developer2);
        sprintBacklog.getAllTasks().add(task);
        sprintBacklog.getAllUserStories().add(userStory);
        project.getAllSprintBacklogs().add(sprintBacklog);
    }
}
