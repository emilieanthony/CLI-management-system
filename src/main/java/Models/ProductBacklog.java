package Models;

import Utility.DataManagement;
import View.ScrumMasterView;
import java.util.ArrayList;
import java.util.Collections;

public class ProductBacklog
{
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<UserStory> allUserStories;
    private ArrayList<Task> tasks;

    //Empty constructor for data exporting and importing.
    public ProductBacklog(){}

    // Constructor
    public ProductBacklog(String name, String startDate, String endDate)
    {
        this.name = name;
        if(DataManagement.stringToLocalDate(startDate).isAfter(DataManagement.stringToLocalDate(endDate)))
        {
            ScrumMasterView.wrongDatePrint();
        } else {
            this.startDate = startDate;
            this.endDate = endDate;
        }
        this.allUserStories = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Task getTask(int id)
    {
        for(Task task : tasks)
        {
            if(task.getId()==id)
            {
                return task;
            }
        }
        return null;
    }

    public UserStory getUserStory(int number)
    {
        for(UserStory userStory : allUserStories)
        {
            if(userStory.getId()==number)
            {
                return userStory;
            }
        }
        return null;
    }

    public ArrayList<UserStory> getAllUserStories()
    {
        return allUserStories;
    }

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }

    public String printAssignedUStories(Developer member)
    {
        String output = "";

        if (allUserStories.isEmpty())
        {
            output = "You have no tasks assigned.";
        }
        else
        {
            for (UserStory userStory : allUserStories)
            {
                if (userStory.isAssigned(member))
                {
                    output = output + userStory.toString() + "\n";
                }
            }
        }
        return output;
    }

    //ToString:-
    @Override
    public String toString()
    {
        Collections.sort(tasks);
        Collections.sort(allUserStories);

        return "\n\nBacklog: \n" +
                "Name = " + name + '\n' +
                "Start Date = " + startDate + '\n' +
                "End Date = " + endDate + "\n\n" +
                "All tasks:\n " + tasks + "\n" +
                "\nAll User Stories:\n " + allUserStories + ".\n\n";
    }
}