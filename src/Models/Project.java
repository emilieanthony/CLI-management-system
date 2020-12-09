package Models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project
{
    //attributes
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Task> allTasks;
    private ArrayList<Developer> allTeamMembers;
    private java.util.ArrayList<ProductOwner> allProductOwners;
    private Backlog backlog;

    //constructor
    public Project(int id, String name, LocalDate startDate, LocalDate endDate, Backlog backlog)
    {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allTasks = new ArrayList<>();
        this.allTeamMembers = new ArrayList<>();
        this.allProductOwners = new java.util.ArrayList<>();
        this.backlog = backlog;
    }

    //all getters & setters
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public Task getTask(int id)
    {
        for (Task task : allTasks)
        {
            if (task.getId() == id)
            {
                return task;
            }
        }
        return null;
    }

    public String printTasks()
    {
        String output = "";

        for (Task task : allTasks)
        {
            output = output + task.toString();
        }
        return output;
    }

    public String printAssignedTasks(Developer member)
    {
        String output = "";

        if (allTasks == null)
        {
            output = "You have no tasks assigned.";
        }
        else
        {
            for (Task task : allTasks)
            {
                if (task.isAssigned(member))
                {
                    output = output + task.toString() + "\n";
                }
            }
        }
        return output;
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

    public void assignTask(int memberID, int taskID)        //This one is using method below
    {
        assignTask(getTeamMember(memberID), getTask(taskID));
    }

    public void assignTask(Developer member, Task task)
    {
        task.getAssignedTeamMembers().add(member);
    }

    public ArrayList<ProductOwner> getAllProductOwners()
    {
        return allProductOwners;
    }

    public ArrayList<Developer> getAllDevelopmentMembers()
    {
        return allTeamMembers;
    }

    public ArrayList<Task> getAllTasks()
    {
        return allTasks;
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
    public String toString()
    {
        return "Project ID: " + id +
                "\nName: " + name +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate;
    }
}
