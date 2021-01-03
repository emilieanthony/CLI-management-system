package Models;

import Utility.Scan;

import java.io.Serializable;
import java.util.ArrayList;

import static View.DevTeamView.*;


public class UserStory implements Comparable<UserStory>, Serializable
{

    // Attributes:-
    private String name;
    private int number;
    private String sprint;
    private int priorityNumber;
    private int storyPoints;
    private String content;
    private String acceptanceCriteria;
    private String status;
    private String sprintName;
    private ArrayList<Developer> assignedDevelopers;
    private ArrayList<Task> userStoryTasks;
    private ArrayList<Boolean> binary;

    //Empty constructor for data exporting and importing.
    public UserStory(){

    }


    // Constructors:-
    public UserStory(String name, int number, String sprint, int priority,
                     String content, String acceptanceCriteria) throws Exception
    {
        if (name.isEmpty()) {
            noNamePrint();
        } else {
            this.name = name;
        }

        if(number < 0) {
            negativeNumberPrint();
        } else {
            this.number = number;
        }

        this.sprint = sprint;

        if(priority < 0) {
            negativeNumberPrint();
        } else {
            this.priorityNumber = priority;
        }
        this.storyPoints = 0;
        this.content = content;
        this.acceptanceCriteria = acceptanceCriteria;
        this.status = "open";
        this.sprintName = "";
        this.assignedDevelopers = new ArrayList<>();
        this.userStoryTasks = new ArrayList<>();
        this.binary = new ArrayList<>();
    }

    // Setters and Getters. Make sure at the end of the project that not used methods are deleted.

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSprintName(String name)
    {
        sprintName = name;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getSprint()
    {
        return sprint;
    }

    public void setSprint(String sprint)
    {
        this.sprint = sprint;
    }

    public int getPriorityNumber()
    {
        return priorityNumber;
    }

    public void setPriorityNumber(int priorityNumber)
    {
        /*do
        {
            Scan.print("priority must be between 1 to 5.");*/
        this.priorityNumber = priorityNumber;
        /*}
        while (priorityNumber <= 0 || priorityNumber >= 6);*/
    }

    public int getStoryPoints()
    {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints)
    {
        this.storyPoints = storyPoints;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getAcceptanceCriteria()
    {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria)
    {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Task> getUserStoryTasks() {
        return userStoryTasks;
    }

    public void setUserStoryTasks(ArrayList<Task> userStoryTasks) {
        this.userStoryTasks = userStoryTasks;
    }

    public ArrayList<Boolean> getBinary() {
        return binary;
    }

    public void setBinary(ArrayList<Boolean> binary) {
        this.binary = binary;
    }

    //public void setStatus(String status)
    /*{
        this.status = status;
    }*/
    public void setOpen()
    {
        status = "Open";
    }

    public void setInProgress()
    {
        status = "In progress";
    }

    public void setDone()
    {
        status = "Done";
    }

    public int compareTo(UserStory userStory)
    {
        if(this.priorityNumber < userStory.priorityNumber)
        {
            return 1;
        }
        else if(this.priorityNumber > userStory.priorityNumber)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
    public ArrayList<Developer> getAssignedDevelopers() {
        return assignedDevelopers;
    }

    public boolean isAssigned(Developer member)
    {
        return assignedDevelopers.contains(member);
    }



    // ToString;

    @Override
    public String toString()
    {
        String output;

        output =
                "\nUser Story: " +
                        "\nUser story number : " + number +
                        "\nName = " + name +
                        "\nSprintBacklog = " + sprint +
                        "\nPriority = " + priorityNumber +
                        "\nStory Points = " + storyPoints +
                        "\nContent = " + content +
                        "\nAcceptance Criteria = " + acceptanceCriteria +
                        "\nStatus = " + status +
                        "\nTasks: " + userStoryTasks +
                        "\nAssigned Team Members:" ;

        if(assignedDevelopers.isEmpty())
        {
            output = output + " None\n";
        }
        else
        {
            for (Developer member : assignedDevelopers)
            {
                output = output + "\n" + member.toString() + "\n";
            }
        }

        return output;
    }
}

