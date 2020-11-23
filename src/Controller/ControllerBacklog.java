package Controller;
import Models.BacklogModel;
import Models.UserStoryModel;
import Utility.Scan;
import View.ProductOwnerView;

public class ControllerBacklog {

    //attributes
    private BacklogModel backlog;
    private ProductOwnerView viewProductOwner = new ProductOwnerView();

    //methods
    public void backlogMenu() {
        boolean running = true;
        do {
            int option = viewProductOwner.menuProductOwner();
            switch (option) {
                case 1:
                    backlog = viewProductOwner.createBacklog();
                    break;
                case 2:
                    viewProductOwner.viewBacklog(backlog);
                    break;
                case 3:
                    viewProductOwner.menuEditBacklog();
                    break;
                case 4:
                    running = false; //go back to main menu
                    break;
                default:
                    viewProductOwner.defaultMessage();
            }
        } while (running);
    }

    public void createBacklog() {
        viewProductOwner.createBacklog();
    }

    public void editBacklog() {
        boolean running = true;
        do {
            int option = viewProductOwner.menuEditBacklog();
            switch (option) {
                case 1:
                    viewProductOwner.editBacklogName(backlog);
                    break;
                case 2:
                    viewProductOwner.editBacklogSDate(backlog);
                    break;
                case 3:
                    viewProductOwner.editBacklogEDate(backlog);
                    break;
                case 4:
                    viewProductOwner.menuEditUserStory();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    viewProductOwner.defaultMessage();
            }
        } while (running);
    }

    public void editUserStory() {
        boolean running = true;
        viewProductOwner.viewBacklog(backlog);
        int number = viewProductOwner.getStoryNumber();
        do {
            int option = viewProductOwner.menuEditUserStory();

            switch (option) {
                case 1:
                    viewProductOwner.editUSNumber(number,backlog);
                    break;
                case 2:
                    viewProductOwner.editUSName(number,backlog);
                    break;
                case 3:
                    viewProductOwner.editUSSprint(number,backlog);
                    break;
                case 4:
                    viewProductOwner.editUSPriority(number,backlog);
                    break;
                case 5:
                    viewProductOwner.editUSStoryPoints(number, backlog);
                    break;
                case 6:
                    viewProductOwner.editUSContent(number, backlog);
                    break;
                case 7:
                    viewProductOwner.editUSAcceptanceC(number, backlog);
                    break;
                case 8:
                    viewProductOwner.editUSStatus(number, backlog);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    viewProductOwner.defaultMessage();
            }
        } while (running);
    }
}





