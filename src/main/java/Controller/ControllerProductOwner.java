package Controller;

import Models.*;
import Utility.Scan;
import java.util.Iterator;
import static Utility.PrintUtility.defaultMessage;
import static Utility.PrintUtility.projectNotFound;
import static View.DevTeamView.invalidInputPrint;
import static View.ProductOwnerView.*;
import static View.ScrumMasterView.*;


public class ControllerProductOwner
{

    //methods
    //*-----------------------------------Code to reuse--------------------------------------------*//
    public UserStory findUStoryByNumber(int number, ControllerAll controllerAll)
    {
        UserStory userStory = null;
        Project project = controllerAll.whichProject();
        Iterator<UserStory> iterator = project.getProductBacklog().getAllUserStories().iterator();
        while (userStory == null && iterator.hasNext())
        {
            UserStory foundUserStory = iterator.next();
            if (foundUserStory.getNumber() == number)
            {
                userStory = foundUserStory;
                Scan.print(userStory.toString());
            }
        }
        return userStory;
    }

    //*-----------------------------------1st Menu - menu for Product owner--------------------------------------------*//
    public void productOwnerMenu(ControllerAll controllerAll, ControllerScrumMaster controllerScrumMaster)
    {
        boolean running = true;
        do
        {

            int option;
            try {
                option = menuProductOwner();

                switch (option) {
                    case 1:
                        createBacklog(controllerAll);
                        break;
                    case 2:
                        viewProBacklog(controllerAll);
                        break;
                    case 3:
                        editBacklog(controllerAll, controllerScrumMaster);
                        break;
                    case 4:
                        getProjectName();
                        break;
                    case 5:
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


    public void createBacklog(ControllerAll controllerAll) { //this method creates null pointer exceptions.
        try {
            Project project = controllerAll.whichProject();
            ProductBacklog ProBacklog = getBacklogInfo();
            project.setProductBacklog(ProBacklog);
            Scan.print(ProBacklog.toString());
            controllerAll.saveData();
            proBacklogCreationConf();
        }
        catch(Exception e) {
            backlogFail();
        }
    }


    //*-----------------------------------2nd Menu - menu for editing backlog------------------------------------------*//
    public void editBacklog(ControllerAll controllerAll, ControllerScrumMaster controllerScrumMaster)
    {
        boolean running = true;
        do
        {
            int option;

            try {
                option = menuEditBacklog();

                switch (option) {
                    case 1:
                        editProductBacklogName(controllerAll);
                        break;
                    case 2:
                        editBacklogSDate(controllerAll);
                        break;
                    case 3:
                        editBacklogEDate(controllerAll);
                        break;
                    case 4:
                        editUserStory(controllerAll);
                        break;
                    case 5:
                        addUserStory(controllerAll, controllerScrumMaster);
                        break;
                    case 6:
                        removeUserStory(controllerAll);
                        break;
                    case 7:
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

   public void addUserStory(ControllerAll controllerAll, ControllerScrumMaster controllerScrumMaster)
    {
        Project project = controllerAll.whichProject();
        int number = controllerScrumMaster.taskUSIdGenerator(project);
        try {
            UserStory newUserStory = getUSInfo(number);
            project.getProductBacklog().getAllUserStories().add(newUserStory);
            createdUStoryReceipt(newUserStory);
            controllerAll.saveData();
            Scan.print(newUserStory.toString());
        } catch (Exception e) {
            userStoryFail();
        }
    }

    public void removeUserStory(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();
        int number = getUSNumber();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        project.getProductBacklog().getAllUserStories().remove(userStory);
        controllerAll.saveData();
        printRemoved();
    }

    public void viewProBacklog(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();
        if (project==null){
            projectNotFound();
        }else{
            Scan.print(project.getProductBacklog().toString());
        }

    }

    public void editProductBacklogName(ControllerAll controllerAll){

        String nameBacklog = getProBacklogName();
        Project project = controllerAll.whichProject();
        project.getProductBacklog().setName(nameBacklog);
        proBacklogEditConf();
        Scan.print(project.getProductBacklog().toString());

    }
    public void editBacklogSDate(ControllerAll controllerAll)
    {

        Project project = controllerAll.whichProject();
        String startDate = getBacklogSDate();
        project.getProductBacklog().setStartDate(startDate);
        proBacklogEditConf();
        Scan.print(project.getProductBacklog().toString());
    }
    public void editBacklogEDate(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();
        String endDate = getBacklogEDate();
        project.getProductBacklog().setEndDate(endDate);
        proBacklogEditConf();
        Scan.print(project.getProductBacklog().toString());
    }

    //**//*--------------------------------3rd Menu - menu for editing user stories---------------------------------------*//
    public void editUserStory(ControllerAll controllerAll)
    {

        boolean running = true;
        viewProBacklog(controllerAll);
        int number = getStoryNumber();

        do
        {
            int option = menuEditUserStory();

            switch (option)
            {
                case 1:
                    editUSNumber(number,controllerAll);
                    break;
                case 2:
                    editUSName(number,controllerAll);
                    break;
                case 3:
                    editUSSprint(number,controllerAll);
                    break;
                case 4:
                    editUSPriority(number,controllerAll);
                    break;
                case 5:
                    editUSStoryPoints(number,controllerAll);
                    break;
                case 6:
                    editUSContent(number,controllerAll);
                    break;
                case 7:
                    editUSAcceptanceC(number,controllerAll);
                    break;
                case 8:
                    editUSStatus(number,controllerAll);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    defaultMessage();
            }
        } while (running);
    }

    public void editUSNumber(int number,ControllerAll controllerAll)
    {

        int newUSNumber = getNewUSNumber();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setNumber(newUSNumber);
        userStoryEditConf();
        Scan.print(userStory.toString());

    }

    public void editUSName(int number,ControllerAll controllerAll)
    {

        String newUSName = getNewUSName();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setName(newUSName);
        userStoryEditConf();
        Scan.print(userStory.toString());

    }

    public void editUSSprint(int number,ControllerAll controllerAll)
    {
        String newUSSprint = getNewUSSprint();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setSprint(newUSSprint);
        userStoryEditConf();
        Scan.print(userStory.toString());

    }

    public void editUSPriority(int number,ControllerAll controllerAll)
    {
        int newUSPriority = getNewUSPriority();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setPriorityNumber(newUSPriority);
        userStoryEditConf();
        Scan.print(userStory.toString());
    }

    public void editUSStoryPoints(int number,ControllerAll controllerAll)
    {
        int newUSSPoints = getNewUSStoryPoints();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setStoryPoints(newUSSPoints);
        userStoryEditConf();
        Scan.print(userStory.toString());
    }

    public void editUSContent(int number, ControllerAll controllerAll)
    {
        String newUSContent = getNewUSContent();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setContent(newUSContent);
        userStoryEditConf();
        Scan.print(userStory.toString());
    }

    public void editUSAcceptanceC(int number,ControllerAll controllerAll)
    {
        String newUSAcceptanceC = getNewUSAcceptanceC();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setAcceptanceCriteria(newUSAcceptanceC);
        userStoryEditConf();
        Scan.print(userStory.toString());

    }

    public void editUSStatus(int number,ControllerAll controllerAll)
    {
        int newUSStatus = getNewUSStatus();

        UserStory userStory = findUStoryByNumber(number,controllerAll);
        if (newUSStatus == 1){
            userStory.setOpen();
            userStoryEditConf();
        }
        else if (newUSStatus == 2){
            userStory.setInProgress();
            userStoryEditConf();
        }
        else if (newUSStatus == 3){
            userStory.setDone();
            userStoryEditConf();
        } else {
            changeStatusMessage();
        }

        Scan.print(userStory.toString());
    }

}