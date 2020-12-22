package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable
{
    //attributes
    private int id;
    private String name;
    private String startDate;
    private String endDate;

    private ProductBacklog productBacklog;
    private SprintBacklog sprintBacklog;
    private ProductOwner productOwner;
    private Developer developer;
    private Task task;

    private ArrayList<Developer> allTeamMembers;
    private ArrayList<ProductOwner> allProductOwners;
    private ArrayList<SprintBacklog> allSprintBacklogs;


    //Empty constructor for data exporting and importing.
    public Project(){

    }

    //constructor
    public Project(int id, String name,String startDate,String endDate)
    {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;

        productBacklog = new ProductBacklog(null,null,null);
        sprintBacklog = new SprintBacklog(null,null,null);
        productOwner = new ProductOwner(null,0);
        developer = new Developer(null,0);
        task = new Task(0,0,0,null,null);

        allTeamMembers = new ArrayList<>();
        allProductOwners = new ArrayList<>();
        allSprintBacklogs = new ArrayList<>();

    }

    //all getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate( String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public Task getTaskFromSprintBacklog(int id)
    {
        for(SprintBacklog sprintBacklog : allSprintBacklogs)
        {
            return sprintBacklog.getTask(id);
        }
        return null;

    }

    public SprintBacklog getSprintBacklog() {
        return sprintBacklog;
    }

    public ProductOwner getProductOwner() {
        return productOwner;
    }

    public String printTasksFromSprintBacklog()
    {
        for(SprintBacklog sprintBacklog : allSprintBacklogs)
        {
            return sprintBacklog.printTasks();
        }
        return null;
    }

    public String printPersonalTasks(Developer member)
    {
        for(SprintBacklog sprintBacklog : allSprintBacklogs)
        {
            return sprintBacklog.printPersonalTasks(member);
        }
        return null;
    }
    public Developer getDeveloper() {
        return developer;
    }

    public Task getTask() {
        return task;
    }



    public Developer getTeamMember(int id)
    {
        for (Developer member : allTeamMembers)
        {
            if (member.getId() == id)
            {
                return member;
            }
        }
        return null;
    }

    public void assignTask(int memberID, int taskID)        //Used in method in ControllerScrumMaster - this one also is using method below
    {
        for(SprintBacklog sprintBacklog : allSprintBacklogs)
        {
            assignTaskAdd(getTeamMember(memberID), sprintBacklog.getTask(taskID));
        }
    }
    public ArrayList<Developer> getAllTeamMembers() {

        return allTeamMembers;
    }

    public void assignTaskAdd(Developer member, Task task) { // method used in assign task method
        task.getAssignedDevelopers().add(member);
    }

    public ArrayList<ProductOwner> getAllProductOwners()
    {
        return allProductOwners;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }

    public ArrayList<Developer> getAllDevelopmentMembers()
    {
        return allTeamMembers;
    }
    public ArrayList<SprintBacklog> getAllSprintBacklogs() {
        return allSprintBacklogs;
    }

    public ArrayList<Task> getAllTasks()
    {
        for(SprintBacklog sprintBacklog : allSprintBacklogs)
        {
            return sprintBacklog.getAllTasks();
        }
        return null;
    }

    public ArrayList<SprintBacklog> getAllSprints()
    {
        return allSprintBacklogs;
    }

    public ProductBacklog getProductBacklog()
    {
        return productBacklog;
    }


    public void viewAllProductOwners()
    {
        for (ProductOwner productOwner : allProductOwners)
        {
            System.out.println(productOwner.toString());
        }
    }

    public void viewAllDevelopmentMembers()
    {
        for (Developer developer : allTeamMembers)
        {
            System.out.println(developer.toString());
        }
    }
    //toString

    @Override
    public String toString() {
        return "\nProject: "+ id +
                "\nName: " + name +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate +
                "\nProduct Backlog: " + productBacklog +
                //"\nSprint Backlog: " + sprintBacklog +
                //"\nProduct Owner: " + productOwner +
                //"\nDeveloper: " + developer +
                "\nTeam Members: " + allTeamMembers +
                "\nProduct Owners: " + allProductOwners +
                "\nSprintBacklog Backlogs: " + allSprintBacklogs;
    }
}
