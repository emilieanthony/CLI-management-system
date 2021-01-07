package Models;

import Exceptions.EmptyName;
import Exceptions.NegativeId;
import Exceptions.WrongDate;
import Utility.DataManagement;
import java.util.ArrayList;


public class Project
{
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private ProductBacklog productBacklog;
    private ArrayList<Developer> allTeamMembers;
    private ArrayList<ProductOwner> allProductOwners;
    private ArrayList<SprintBacklog> allSprintBacklogs;

    //Empty constructor for data exporting and importing.
    public Project(){}

    //constructor
    public Project(int id, String name, String startDate, String endDate) throws Exception
    {
        if (name.isEmpty())
        {
            throw new EmptyName();
        } else {
            this.name = name;
        }


        if(id < 0)
        {
            throw new NegativeId();
        } else {
            this.id = id;
        }

        if(DataManagement.stringToLocalDate(startDate).isAfter(DataManagement.stringToLocalDate(endDate)))
        {
            throw new WrongDate();

        } else {
            this.startDate = startDate;
            this.endDate = endDate;
        }
        productBacklog = new ProductBacklog(name + " product backlog", startDate, endDate);
        allTeamMembers = new ArrayList<>();
        allProductOwners = new ArrayList<>();
        allSprintBacklogs = new ArrayList<>();
    }

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

    public String getEndDate()
    {
        return endDate;
    }

    public ArrayList<Developer> getAllTeamMembers() { return allTeamMembers; }

    public ArrayList<ProductOwner> getAllProductOwners()
    {
        return allProductOwners;
    }

    public ArrayList<SprintBacklog> getAllSprintBacklogs() {
        return allSprintBacklogs;
    }

    public ProductBacklog getProductBacklog()
    {
        return productBacklog;
    }

    @Override
    public String toString() {
        return "\nProject: "+ id +
                "\nName: " + name +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate +
                "\nProduct Backlog: " + productBacklog +
                "\nTeam Members: " + allTeamMembers +
                "\nProduct Owners: " + allProductOwners +
                "\nSprintBacklog Backlogs: " + allSprintBacklogs;
    }
}
