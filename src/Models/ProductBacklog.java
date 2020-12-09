package Models;

import Models.UserStory;

import java.util.ArrayList;
import java.util.Collections;

public class ProductBacklog {

    //Attributes:-
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<UserStory> allUserStories;
    private ArrayList<Task> tasks;


    // Constructor:-
    public ProductBacklog(String name, String startDate, String endDate)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allUserStories = new ArrayList<>();
        this.tasks = new ArrayList<>(); // No tasks in product backlog
    }


    //Setters and Getters:-Make sure at the end of the project that not used methods are deleted.

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
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
            if(userStory.getNumber()==number)
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
                "name = " + name + '\n' +
                "Start Date = " + startDate + '\n' +
                "End Date = " + endDate + "\n\n" +
                "All tasks:\n " + tasks + "\n" +
                "All User Stories:\n " + allUserStories + ".\n\n";
    }
}
