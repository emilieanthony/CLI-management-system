package Controller;

import Models.*;
import Utility.DataManagement;
import Utility.Scan;
import View.ScrumMasterView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import static Utility.PrintUtility.defaultMessage;
import static Utility.PrintUtility.projectNotFound;
import static View.DevTeamView.invalidInputPrint;
import static View.ProductOwnerView.*;
import static View.ScrumMasterView.*;


public class ControllerProductOwner {


    //*-----------------------------------1st Menu - menu for Product owner--------------------------------------------*//
    public void productOwnerMenu(ControllerAll controllerAll, ControllerScrumMaster controllerScrumMaster) {
        boolean running = true;
        do {

            int option;
            try {
                option = menuProductOwner();

                switch (option) {
                    case 1:
                        viewProBacklog(controllerAll);
                        break;
                    case 2:
                        editProductBacklog(controllerAll, controllerScrumMaster);
                        break;
                    case 3:
                        createUserStory(controllerAll, controllerScrumMaster);
                        break;
                    case 4:
                        deleteUserStory(controllerAll);
                        break;
                    case 5:
                        controllerAll.viewCompletedUStories();
                        break;
                    case 6:
                        controllerAll.switchProject(controllerAll);
                        break;
                    case 7:
                        running = false; //go back to main menu
                        break;
                    default:
                        defaultMessage();
                }
            } catch (NumberFormatException e) {
                numberFormatMessage();
            }
        } while (running);
    }

    //---------------------------------------Methods for 1st Menu ------------------------------------------------------

    public void viewProBacklog(ControllerAll controllerAll) {

        Project project = controllerAll.whichProject();
        if (project == null) {
            projectNotFound();
        } else {
            Scan.print(project.getProductBacklog().toString());
        }

    }

    public void createUserStory(ControllerAll controllerAll, ControllerScrumMaster controllerScrumMaster) {

        Project project = controllerAll.whichProject();

        int number = controllerScrumMaster.taskUSIdGenerator(controllerAll);

        try {
            UserStory newUserStory = getUSInfo(number);
            project.getProductBacklog().getAllUserStories().add(newUserStory);

            String criteria = getAnotherACriteria();

            while (!criteria.isBlank()){
                newUserStory.getAcceptanceCriteria().add(criteria);
                criteria = getAnotherACriteria();
            }

            createdUStoryReceipt(newUserStory);

            controllerAll.saveData();

        } catch (Exception e) {
            userStoryFail();
        }
    }


    public void deleteUserStory(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();

        viewProBacklog(controllerAll);
        int number = getUSid();

        UserStory userStory = findUStoryByIdPBL(number,controllerAll);

        ArrayList<UserStory> userStories = project.getProductBacklog().getAllUserStories();

        if (userStories.contains(userStory)){

            userStories.remove(userStory);

            controllerAll.saveData();

            printDeleted();

        } else if (userStory == null || !userStories.contains(userStory)){

            nonExistentUStory();

        }
    }


    //*-----------------------------------2nd Menu - menu for editing backlog------------------------------------------*//
    public void editProductBacklog(ControllerAll controllerAll, ControllerScrumMaster controllerScrumMaster) {
        boolean running = true;
        do {
            int option;

            try {
                option = menuEditBacklog();

                switch (option) {
                    case 1:
                        editProductBacklogName(controllerAll);
                        break;
                    case 2:
                        editUserStory(controllerAll);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        defaultMessage();
                }
            } catch (Exception e) {
                invalidInputPrint();
            }
        } while (running);
    }

    //-----------------------------------Methods for second menu - editing backlog--------------------------------------

    public void editProductBacklogName(ControllerAll controllerAll) {

        String nameBacklog = getProBacklogName();

        while (nameBacklog.isBlank()){
            emptyName();
            nameBacklog = getProBacklogName();
        }

        Project project = controllerAll.whichProject();

        project.getProductBacklog().setName(nameBacklog);

        controllerAll.saveData();

        proBacklogEditConf(project.getProductBacklog());


    }


    //--------------------------------3rd Menu - menu for editing user stories---------------------------------------
    public void editUserStory(ControllerAll controllerAll) {

        boolean running = true;
        viewUStoriesPBL(controllerAll);
        int number = getStoryID();
        UserStory userStory = findUStoryByIdPBL(number, controllerAll);

        do {
            int option = menuEditUserStory();

            switch (option) {
                case 1:
                    editUSName(userStory, controllerAll);
                    break;
                case 2:
                    editUSContent(userStory, controllerAll);
                    break;
                case 3:
                    editUSAcceptanceC(userStory, controllerAll);
                    break;
                case 4:
                    removeUSAcceptanceC(userStory, controllerAll);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    defaultMessage();
            }
        } while (running);
    }

    //---------------------------------------Methods for 3rd Menu - editing User Stories--------------------------------

    public void viewUStoriesPBL(ControllerAll controllerAll){
        Project project = controllerAll.whichProject();
        ArrayList<UserStory> userStories = project.getProductBacklog().getAllUserStories();

        printPBLUStories(userStories);

    }


    public void editUSName(UserStory userStory, ControllerAll controllerAll) {

        String newUSName = getNewUSName();

        while (newUSName.isBlank()){
            emptyName();
            newUSName = getNewUSName();
        }

        userStory.setName(newUSName);
        controllerAll.saveData();
        userStoryEditConf(userStory);

    }

    public void editUSContent(UserStory userStory, ControllerAll controllerAll) {
        String newUSContent = getNewUSContent();

        while (newUSContent.isBlank()){
            emptyContent();
            newUSContent = getNewUSContent();
        }

        userStory.setContent(newUSContent);
        controllerAll.saveData();
        userStoryEditConf(userStory);
    }

    public void editUSAcceptanceC(UserStory userStory, ControllerAll controllerAll) {

        printUStoryACriteria(userStory);

        String anotherCriteria = getAnotherACriteria();

        while (!anotherCriteria.isBlank()){
            userStory.getAcceptanceCriteria().add(anotherCriteria);
            anotherCriteria = getAnotherACriteria();
        }

        controllerAll.saveData();

        userStoryEditConf(userStory);

    }

    public void removeUSAcceptanceC(UserStory userStory, ControllerAll controllerAll){
        printUStoryACriteria(userStory);

        if (!userStory.getAcceptanceCriteria().isEmpty()){

            int index = optionRemoveACriteria() - 1;

            if (!((userStory.getAcceptanceCriteria().get(index))==null)){

                userStory.getAcceptanceCriteria().remove(index);
                printUStoryACriteria(userStory);

                controllerAll.saveData();

            } else {

                invalidIndexPrint();

            }
        }

    }


    //*-----------------------------------Code to reuse--------------------------------------------*//
    public UserStory findUStoryByIdPBL(int id, ControllerAll controllerAll) {
        UserStory userStory = null;

        Project project = controllerAll.whichProject();
        Iterator<UserStory> iterator = project.getProductBacklog().getAllUserStories().iterator();
        while (userStory == null && iterator.hasNext()) {
            UserStory foundUserStory = iterator.next();

            if (foundUserStory.getId() == id) {
                userStory = foundUserStory;
                Scan.print(userStory.toString());
            }
        }
        return userStory;
    }

}