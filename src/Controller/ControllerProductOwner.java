package Controller;

import Models.*;
import Utility.Export;
import Utility.PrintUtility;
import Utility.Scan;
import View.ProductOwnerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class ControllerProductOwner
{

    //methods
    /*-----------------------------------Code to reuse--------------------------------------------*/
    public UserStory findUStoryByNumber(int number, ArrayList<UserStory> allUserStories)
    {
        UserStory userStory = null;
        Iterator<UserStory> iterator = allUserStories.iterator();
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

    /*-----------------------------------1st Menu - menu for Product owner--------------------------------------------*/
    public void backlogMenu(ProductOwnerView proOwnerView,ControllerAll controllerAll,ControllerProductOwner contProOwner)
    {
        boolean running = true;
        do
        {
            int option = proOwnerView.menuProductOwner();
            switch (option)
            {
                case 1:
                    createBacklog(proOwnerView,controllerAll);
                    break;
                case 2:
                    viewBacklog(controllerAll,proOwnerView);
                    break;
                case 3:
                    editBacklog(proOwnerView,controllerAll,contProOwner);
                    break;
                case 4:
                    running = false; //go back to main menu
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    public void createBacklog(ProductOwnerView proOwnerView, ControllerAll controllerAll) {
        Backlog backlog = proOwnerView.createBacklog();
        Export.exportObject(backlog);
        controllerAll.getProjectBacklog().add(backlog);
    }

    /*-----------------------------------2nd Menu - menu for editing backlog------------------------------------------*/
    public void editBacklog(ProductOwnerView proOwnerView,ControllerAll controllerAll,
                            ControllerProductOwner contProOwner)
    {
        boolean running = true;
        do
        {
            int option = proOwnerView.menuEditBacklog();
            switch (option)
            {
                case 1:
                    editBacklogName(controllerAll, proOwnerView, contProOwner);
                    break;
                case 2:
                    editBacklogSDate(controllerAll, proOwnerView, contProOwner);
                    break;
                case 3:
                    editBacklogEDate(controllerAll, proOwnerView, contProOwner);
                    break;
                case 4:
                    editUserStory(proOwnerView,controllerAll);
                    break;
                case 5:
                    addUserStory(proOwnerView,controllerAll);
                    break;
                case 6:
                    removeUserStory(proOwnerView,controllerAll);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    public void addUserStory(ProductOwnerView proOwnerView, ControllerAll controllerAll)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        UserStory newUserStory = proOwnerView.getUSInfo();
        Export.exportObject(newUserStory);
        backlog.getAllUserStories().add(newUserStory);
    }

    public void removeUserStory(ProductOwnerView proOwnerView,ControllerAll controllerAll)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        int number = proOwnerView.getUSNumber();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        backlog.getAllUserStories().remove(userStory);
        proOwnerView.printRemoved();
    }
    public void viewBacklog(ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        Scan.print(backlog.toString());
    }


    public Backlog findBacklogByName(String name,ArrayList<Backlog> allBacklogs){
        Backlog backlog = null;
        Iterator<Backlog> iterator = allBacklogs.iterator();
        while (backlog == null && iterator.hasNext())
        {
            Backlog foundBacklog = iterator.next();
            if (foundBacklog.getName().equalsIgnoreCase(name))
            {
                backlog = foundBacklog;
            }
        }
        return backlog;
    }
    public void editBacklogName(ControllerAll controllerAll, ProductOwnerView viewProOwner,
                                ControllerProductOwner contProOwner){
        String nameBacklog = viewProOwner.getBacklogName();
        String name = viewProOwner.chooseBacklog();
        Backlog backlog = contProOwner.findBacklogByName(name, controllerAll.getProjectBacklog());
        backlog.setName(nameBacklog);
    }
    public void editBacklogSDate(ControllerAll controllerAll, ProductOwnerView viewProOwner,
                                 ControllerProductOwner contProOwner)
    {
        String name = viewProOwner.chooseBacklog();
        Backlog backlog = contProOwner.findBacklogByName(name, controllerAll.getProjectBacklog());
        String startDate = viewProOwner.getBacklogSDate();
        backlog.setStartDate(startDate);
    }
    public void editBacklogEDate(ControllerAll controllerAll, ProductOwnerView viewProOwner,
                                 ControllerProductOwner contProOwner)
    {
        String name = viewProOwner.chooseBacklog();
        Backlog backlog = contProOwner.findBacklogByName(name, controllerAll.getProjectBacklog());
        String endDate = viewProOwner.getBacklogEDate();
        backlog.setEndDate(endDate);
    }


    /*--------------------------------3rd Menu - menu for editing user stories---------------------------------------*/
    public void editUserStory(ProductOwnerView proOwnerView,ControllerAll controllerAll)
    {

        boolean running = true;
        viewBacklog(controllerAll,proOwnerView);
        int number = proOwnerView.getStoryNumber();

        do
        {
            int option = proOwnerView.menuEditUserStory();

            switch (option)
            {
                case 1:
                    editUSNumber(number,controllerAll, proOwnerView);
                    break;
                case 2:
                    editUSName(number,controllerAll, proOwnerView);
                    break;
                case 3:
                    editUSSprint(number,controllerAll, proOwnerView);
                    break;
                case 4:
                    editUSPriority(number,controllerAll, proOwnerView);
                    break;
                case 5:
                    editUSStoryPoints(number,controllerAll, proOwnerView);
                    break;
                case 6:
                    editUSContent(number,controllerAll, proOwnerView);
                    break;
                case 7:
                    editUSAcceptanceC(number,controllerAll, proOwnerView);
                    break;
                case 8:
                    editUSStatus(number,controllerAll, proOwnerView);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    PrintUtility.defaultMessage();
            }
        } while (running);
    }

    public void editUSNumber(int number,ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        int newUSNumber = proOwnerView.getNewUSNumber();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        userStory.setNumber(newUSNumber);
    }

    public void editUSName(int number,ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        String newUSName = proOwnerView.getNewUSName();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        userStory.setName(newUSName);

    }

    public void editUSSprint(int number,ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        String newUSSprint = proOwnerView.getNewUSSprint();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        userStory.setSprint(newUSSprint);

    }

    public void editUSPriority(int number,ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        int newUSPriority = proOwnerView.getNewUSPriority();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        userStory.setPriority(newUSPriority);
    }

    public void editUSStoryPoints(int number,ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        int newUSSPoints = proOwnerView.getNewUSStoryPoints();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        userStory.setStoryPoints(newUSSPoints);
    }

    public void editUSContent(int number, ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        String newUSContent = proOwnerView.getNewUSContent();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        userStory.setContent(newUSContent);
    }

    public void editUSAcceptanceC(int number,ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        String newUSAcceptanceC = proOwnerView.getNewUSAcceptanceC();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        userStory.setAcceptanceCriteria(newUSAcceptanceC);

    }

    public void editUSStatus(int number,ControllerAll controllerAll, ProductOwnerView proOwnerView)
    {
        String name = proOwnerView.chooseBacklog();
        Backlog backlog = findBacklogByName(name, controllerAll.getProjectBacklog());
        String newUSStatus = proOwnerView.getNewUSStatus();
        UserStory userStory = findUStoryByNumber(number, backlog.getAllUserStories());
        userStory.setStatus(newUSStatus);
    }

}







