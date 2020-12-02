package Models;

import java.util.ArrayList;

public class Backlog
{

    //Attributes:-
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<UserStory> allUserStories;
    private ArrayList<Task> tasks;



    // Constructor:-
    public Backlog(String name, String startDate, String endDate)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allUserStories = new ArrayList<>();
        this.tasks = new ArrayList<>();
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

    //ToString:-

    @Override
    public String toString()
    {
        return "\n\nBacklog: \n" +
                "name = " + name + '\n' +
                "Start Date = " + startDate + '\n' +
                "End Date = " + endDate + '\n' +
                "All tasks:\n " + tasks + "\n" +
                "All User Stories:\n " + allUserStories + ".";
    }
}
