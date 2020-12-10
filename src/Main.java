import Controller.ControllerAll;
import Controller.ControllerDeveloper;
import Controller.ControllerProductOwner;
import Controller.ControllerScrumMaster;
import Models.*;
import View.DevTeamView;
import View.ProductOwnerView;
import View.ScrumMasterView;


public class Main
{

    public static <Sprint> void main(String[] args) {

        ControllerAll controllerAll = new ControllerAll();
        ControllerScrumMaster contScrum = new ControllerScrumMaster();
        ControllerProductOwner contProOwner = new ControllerProductOwner();
        ControllerDeveloper contDeveloper = new ControllerDeveloper();

        controllerAll.menuMain(controllerAll, contScrum, contProOwner, contDeveloper);


        // Code for testing
        /*UserStory userStory1 = new UserStory ("UserStory", 1, "1", 1, "Very important US", "1. The code works");
        UserStory userStory2 = new UserStory ("UserStory77", 2, "1", 5, "Hello", "No");
        Task task1 = new Task (1, 2, 6,"Name1", "Hello");
        Task task2 = new Task (2, 4,3,"Hello,", "he");
        SprintBacklog sprint = new SprintBacklog("1","2020-01-01", "2020-01-14");

        ScrumMasterView scrumView = new ScrumMasterView();
        ProductOwnerView proOwnerView = new ProductOwnerView();
        DevTeamView developerView = new DevTeamView();
        Project project = new Project(1,"Codelicode", "2020-01-01","2021-01-01");
        controllerAll.getAllProjects().add(project);
        //ProductBacklog backlog = new Product
        ProductBacklog backlog = new ProductBacklog("name","","");
        project.getAllSprints().add(sprint);
        project.getProductBacklog().getAllUserStories().add(userStory1);
        project.getProductBacklog().getAllUserStories().add(userStory2);
        project.getProductBacklog().getTasks().add(task1);
        project.getProductBacklog().getTasks().add(task2);*/

    }
}
