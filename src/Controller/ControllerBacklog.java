package Controller;
import Models.BacklogModel;
import View.ProductOwnerView;

public class ControllerBacklog {


    //attributes
    private BacklogModel backlog;
    private ProductOwnerView viewProductOwner = new ProductOwnerView();

    //methods
    public void backlogMenu(){
        int option = viewProductOwner.menuProductOwner();
        switch (option) {
            case 1: backlog = viewProductOwner.createBacklog();
                break;
            case 2: viewProductOwner.viewBacklog(backlog);
                break;
            case 3: viewProductOwner.editBacklog(backlog);
                break;
            case 4:
                //go back to main menu
        }
    }

    public void createBacklog(){
        viewProductOwner.createBacklog();
    }


}
