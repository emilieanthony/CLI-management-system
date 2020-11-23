import Controller.Controller;
import Controller.ControllerBacklog;
import Models.BacklogModel;
import View.ProductOwnerView;

public class Main {

    public static void main (String [] args) {

        //Controller for backlog operations
        ControllerBacklog controllerBacklog = new ControllerBacklog();

        //Controller for ScrumMaster operations
        Controller controller = new Controller();
        controller.runApplication();


        // Test:
        /*BacklogModel backlog = new BacklogModel("backlog1", "11/12", "13/12");
        ArrayList<UserStoryModel> allUserStories = backlog.getAllUserStories();

        UserStoryModel UserStory1 = new UserStoryModel("Bassam",2,"3",5,8,"A user story content",
                "1- Acceptance criteria ");
        UserStoryModel UserStory2 = new UserStoryModel("Bassam copy",1,"4",5,8,"Another user " +
                "story " +
                "content","1- Another criteria");

        allUserStories.add(UserStory1);
        allUserStories.add(UserStory2);

        menuProductOwner.viewBacklog(backlog);
        menuProductOwner.editBacklog(backlog);
        menuProductOwner.viewBacklog(backlog);
    }*/
    }
}



