package Controller;

import Models.*;
import Utility.Export;
import Utility.Scan;
import java.util.Iterator;

import static Utility.PrintUtility.defaultMessage;
import static Utility.PrintUtility.projectNotFound;
import static View.ProductOwnerView.*;
import static View.ScrumMasterView.backlogName;
import static View.ScrumMasterView.getProjectName;


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

            int option = menuProductOwner();
            switch (option)
            {
                case 1:
                    createBacklog(controllerAll);
                    break;
                case 2:
                    viewBacklog(controllerAll);
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
        } while (running);
    }

   public void createBacklog(ControllerAll controllerAll) { //this method creates null pointer exceptions.
        Project project = controllerAll.whichProject();
        ProductBacklog backlog = getBacklogInfo();
        project.setProductBacklog(backlog);
        Export.exportObject(backlog);
    }


    //*-----------------------------------2nd Menu - menu for editing backlog------------------------------------------*//
    public void editBacklog(ControllerAll controllerAll, ControllerScrumMaster controllerScrumMaster)
    {
        boolean running = true;
        do
        {
            int option = menuEditBacklog();
            switch (option)
            {
                case 1:
                    editBacklogName(controllerAll);
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
        } while (running);
    }

   public void addUserStory(ControllerAll controllerAll, ControllerScrumMaster controllerScrumMaster)
    {
        Project project = controllerAll.whichProject();
        int number = controllerScrumMaster.taskUSIdGenerator(project);
        UserStory newUserStory = getUSInfo(number);
        project.getProductBacklog().getAllUserStories().add(newUserStory);
        Export.exportObject(newUserStory);
        createdUStoryReceipt(newUserStory);
    }

    public void removeUserStory(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();
        int number = getUSNumber();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        project.getProductBacklog().getAllUserStories().remove(userStory);
        printRemoved();
    }
    public void viewBacklog(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();
        if (project==null){
            projectNotFound();
        }else{
            Scan.print(project.getProductBacklog().toString());
        }

    }

    public void editBacklogName(ControllerAll controllerAll){

        String nameBacklog = getBacklogName();
        Project project = controllerAll.whichProject();
        project.getProductBacklog().setName(nameBacklog);
        backlogName = nameBacklog;
    }
    public void editBacklogSDate(ControllerAll controllerAll)
    {

        Project project = controllerAll.whichProject();
        String startDate = getBacklogSDate();
        project.getProductBacklog().setStartDate(startDate);
    }
    public void editBacklogEDate(ControllerAll controllerAll)
    {
        Project project = controllerAll.whichProject();
        String endDate = getBacklogEDate();
        project.getProductBacklog().setEndDate(endDate);
    }

    //**//*--------------------------------3rd Menu - menu for editing user stories---------------------------------------*//
    public void editUserStory(ControllerAll controllerAll)
    {

        boolean running = true;
        viewBacklog(controllerAll);
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
    }

    public void editUSName(int number,ControllerAll controllerAll)
    {

        String newUSName = getNewUSName();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setName(newUSName);

    }

    public void editUSSprint(int number,ControllerAll controllerAll)
    {
        String newUSSprint = getNewUSSprint();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setSprint(newUSSprint);

    }

    public void editUSPriority(int number,ControllerAll controllerAll)
    {
        int newUSPriority = getNewUSPriority();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setPriorityNumber(newUSPriority);
    }

    public void editUSStoryPoints(int number,ControllerAll controllerAll)
    {
        int newUSSPoints = getNewUSStoryPoints();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setStoryPoints(newUSSPoints);
    }

    public void editUSContent(int number, ControllerAll controllerAll)
    {
        String newUSContent = getNewUSContent();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setContent(newUSContent);
    }

    public void editUSAcceptanceC(int number,ControllerAll controllerAll)
    {
        String newUSAcceptanceC = getNewUSAcceptanceC();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setAcceptanceCriteria(newUSAcceptanceC);

    }

    public void editUSStatus(int number,ControllerAll controllerAll)
    {
        String newUSStatus = getNewUSStatus();
        UserStory userStory = findUStoryByNumber(number,controllerAll);
        userStory.setStatus(newUSStatus);
    }

}







