import Models.BacklogModel;
import Models.UserStoryModel;

import java.util.ArrayList;

public class Main {

    public static void main (String [] args) {

        ProductOwnerView menuProductOwner = new ProductOwnerView();
        // Ska in till controller
        BacklogModel backlogModel = menuProductOwner.createBacklog();
        //Ta bort - enbart för test
        UserStoryModel userStoryModel = new UserStoryModel("UserStory 1", "2", "5", 3, 3, "As a user, I want to be happy so that..", "1.1 Be good");
        //Ta bort - enbart för test
        ArrayList <UserStoryModel> userStories = new ArrayList<>();
        //Ta bort - enbart för test
        userStories.add(userStoryModel);
        //Ta bort - enbart för test
        backlogModel.setAllUserStories(userStories);
        //Ta bort - enbart för test
        menuProductOwner.viewBacklog (backlogModel);

        // Ska in till controller
        int option = menuProductOwner.menuProductOwner();
        switch (option) { //put switch in controller and call methods? Controller: "Accepts input and converts it to commands for the model or view"
            case 1:
                menuProductOwner.createBacklog();

            case 2:
                menuProductOwner.viewBacklog(backlogModel);

            case 3:
                // go back to main menu
        }

    }
}
