package Models;

import Models.UserStory;
import View.ScrumMasterView;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import static View.DevTeamView.noNamePrint;

public class ProductBacklog implements Serializable {

    //Attributes:-
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<UserStory> allUserStories;
    private ArrayList<Task> tasks;


    //Empty constructor for data exporting and importing.
    public ProductBacklog(){

    }
    // Constructor:-
    public ProductBacklog(String name, LocalDate startDate, LocalDate endDate) //throws Exception
    {

        this.name = name;
        if (startDate.isAfter(endDate)){
            ScrumMasterView.wrongDatePrint();
        } else {
            this.startDate = startDate;
            this.endDate = endDate;
        }

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

    /*public ArrayList<Task> getTasksImport() {
        return tasks;
    }*/
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
                "Name = " + name + '\n' +
                "Start Date = " + startDate + '\n' +
                "End Date = " + endDate + "\n\n" +
                "All tasks:\n " + tasks + "\n" +
                "\nAll User Stories:\n " + allUserStories + ".\n\n";
    }
}
