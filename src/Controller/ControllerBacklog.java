package Controller;
import Models.BacklogModel;
import Utility.Scan;
import View.ProductOwnerView;

public class ControllerBacklog {


    //attributes
    private BacklogModel backlog;
    private ProductOwnerView viewProductOwner = new ProductOwnerView();

    //methods
    public void backlogMenu(){
        boolean running = true;
        do {
            int option = viewProductOwner.menuProductOwner();
            switch (option) {
                case 1: backlog = viewProductOwner.createBacklog();
                    break;
                case 2: viewProductOwner.viewBacklog(backlog);
                    break;
                case 3: viewProductOwner.menuEditBacklog(backlog);
                    break;
                case 4:
                    running = false; //go back to main menu
                    break;
                default: viewProductOwner.defaultMessage();
            }
        } while (running);
    }

    public void createBacklog(){
        viewProductOwner.createBacklog();
    }

    public void editBacklog(){
    int option = viewProductOwner.menuEditBacklog(backlog);
            switch (option) {
                case 1: viewProductOwner.editBacklogName(backlog);
                    break;
                case 2: viewProductOwner.editBacklogSDate(backlog);
                    break;
                case 3: viewProductOwner.editBacklogEDate(backlog);
                    break;
                case 4: viewProductOwner.editUserStoryMenu(backlog);  //Method to another menu to edit user
                    // stories.
                    break;
                case 5: viewProductOwner.menuProductOwner();
                    break;
                default: viewProductOwner.defaultMessage();
            }

    }


}


