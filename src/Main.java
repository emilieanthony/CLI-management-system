import Models.BacklogModel;
import Models.UserStoryModel;

import java.util.ArrayList;

public class Main {

    public static void main (String [] args) {

        ProductOwnerView menuProductOwner = new ProductOwnerView();
        BacklogModel backlogModel = menuProductOwner.createBacklog();

        }
    }

