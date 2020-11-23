import Models.BacklogModel;
import Models.UserStoryModel;

import java.util.ArrayList;

public class Main {

    public static void main (String [] args) {

        Controller controller = new Controller();
        controller.runApplication();

        ProductOwnerView menuProductOwner = new ProductOwnerView();
        BacklogModel backlogModel = menuProductOwner.createBacklog();

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



