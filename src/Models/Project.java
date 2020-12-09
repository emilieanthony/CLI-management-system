package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Project implements Serializable
{
    //attributes
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
   //private ArrayList<Task> allTasks;
    private ArrayList<Developer> allTeamMembers;
    private ArrayList<ProductOwner> allProductOwners;
    private ArrayList<Backlog> projectBacklog;
    private ArrayList<Sprint> sprintBacklog;


    //constructor
    public Project(int id, String name, LocalDate startDate, LocalDate endDate)
    {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
      //  this.allTasks = new ArrayList<>();
        this.allTeamMembers = new ArrayList<>();
        this.allProductOwners = new ArrayList<>();
        this.projectBacklog = new ArrayList<>();
        this.sprintBacklog = new ArrayList<>();
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

    public Task getTaskFromSprintBacklog(int id)
    {
        for(Sprint sprint : sprintBacklog)
        {
            return sprint.getTask(id);
        }
        return null;
    }

    public String printTasksFromSprintBacklog()
    {
        for(Sprint sprint : sprintBacklog)
        {
            return sprint.printTasks();
        }
        return null;
    }

    public String printPersonalTasks(Developer member)
    {
        for(Sprint sprint : sprintBacklog)
        {
            return sprint.printPersonalTasks(member);
        }
        return null;
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
        for(Sprint sprint : sprintBacklog)
        {
            assignTask(getTeamMember(memberID), sprint.getTask(taskID));
        }
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
        for(Sprint sprint : sprintBacklog)
        {
            return sprint.getAllTasks();
        }
        return null;
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
