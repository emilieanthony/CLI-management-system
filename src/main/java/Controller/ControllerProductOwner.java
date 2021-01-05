package Controller;

import Models.*;
import Utility.Scan;
import View.ScrumMasterView;

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
                        deleteExistingAndCreateNewBacklog(controllerAll);
                        break;
                    case 7:
                        getProjectName(controllerAll);
                        break;
                    case 8:
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
            createdUStoryReceipt(newUserStory);
            controllerAll.saveData();
        } catch (Exception e) {
            userStoryFail();
        }
    }


    public void deleteUserStory(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();

        int number = getUSNumber();

        UserStory userStory = findUStoryByNumberPBL(number,controllerAll);

        ArrayList<UserStory> userStories = project.getProductBacklog().getAllUserStories();

        if (userStories.contains(userStory)){

            userStories.remove(userStory);

            controllerAll.saveData();

            printDeleted();

        } else if (userStory == null || !userStories.contains(userStory)){

            nonExistentUStory();

        }
    }


    public void deleteExistingAndCreateNewBacklog(ControllerAll controllerAll) { //this method creates null pointer exceptions.
        try {
            Project project = controllerAll.whichProject();
            ProductBacklog ProBacklog = getBacklogInfo();
            project.setProductBacklog(ProBacklog);
            proBacklogCreationConf();
            Scan.print(ProBacklog.toString());
            controllerAll.saveData();
        } catch (Exception e) {
            backlogFail();
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
                        editBacklogStartDate(controllerAll);
                        break;
                    case 3:
                        editBacklogEndDate(controllerAll);
                        break;
                    case 4:
                        editUserStory(controllerAll);
                        break;
                    case 5:
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
        Project project = controllerAll.whichProject();
        project.getProductBacklog().setName(nameBacklog);
        controllerAll.saveData();
        proBacklogEditConf();
        Scan.print(project.getProductBacklog().toString());

    }

    public void editBacklogStartDate(ControllerAll controllerAll) {

        Project project = controllerAll.whichProject();
        String startDate = ScrumMasterView.getStartDate();
        project.getProductBacklog().setStartDate(startDate);
        controllerAll.saveData();
        proBacklogEditConf();
        Scan.print(project.getProductBacklog().toString());
    }

    public void editBacklogEndDate(ControllerAll controllerAll) {
        Project project = controllerAll.whichProject();
        String endDate = getBacklogEDate();
        project.getProductBacklog().setEndDate(endDate);
        controllerAll.saveData();
        proBacklogEditConf();
        Scan.print(project.getProductBacklog().toString());
    }



    //--------------------------------3rd Menu - menu for editing user stories---------------------------------------
    public void editUserStory(ControllerAll controllerAll) {

        boolean running = true;
        viewProBacklog(controllerAll);
        int number = getStoryNumber();
        UserStory userStory = findUStoryByNumberPBL(number, controllerAll);


        do {
            int option = menuEditUserStory();

            switch (option) {
                case 1:
                    controllerAll.editUSNumber(userStory);
                    break;
                case 2:
                    controllerAll.editUSName(userStory);
                    break;
                case 3:
                    controllerAll.editUSPriority(userStory);
                    break;
                case 4:
                    controllerAll.editUSStoryPoints(userStory);
                    break;
                case 5:
                    controllerAll.editUSContent(userStory);
                    break;
                case 6:
                    controllerAll.editUSAcceptanceC(userStory);
                    break;
                case 7:
                    controllerAll.changeUSStatus(userStory);
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    defaultMessage();
            }
        } while (running);
    }

    //---------------------------------------Methods for 3rd Menu - editing User Stories--------------------------------


    public void changeUSStatusInPBL(int number, ControllerAll controllerAll) {

        UserStory userStory = findUStoryByNumberPBL(number, controllerAll);

        controllerAll.changeUSStatus(userStory);
    }

    //*-----------------------------------Code to reuse--------------------------------------------*//
    public UserStory findUStoryByNumberPBL(int number, ControllerAll controllerAll) {
        UserStory userStory = null;

        Project project = controllerAll.whichProject();
        Iterator<UserStory> iterator = project.getProductBacklog().getAllUserStories().iterator();
        while (userStory == null && iterator.hasNext()) {
            UserStory foundUserStory = iterator.next();

            if (foundUserStory.getNumber() == number) {
                userStory = foundUserStory;
            }
        }
        return userStory;
    }

}