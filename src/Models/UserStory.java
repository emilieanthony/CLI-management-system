package Models;

import Utility.Scan;

import java.util.ArrayList;

public class UserStory
{

    // Attributes:-
    private String name;
    private int number;
    private String sprint;
    private int priority;
    private int storyPoints;
    private String content;
    private String acceptanceCriteria;
    private String status;
    private ArrayList<Developer> assignedDevelopers;
    private int actualVelocity;


    // Constructors:-
    public UserStory(String name, int number, String sprint, int priority, int storyPoints,
                     String content, String acceptanceCriteria)
    {
        this.name = name;
        this.number = number;
        this.sprint = sprint;
        this.priority = priority;
        this.storyPoints = storyPoints;
        this.content = content;
        this.acceptanceCriteria = acceptanceCriteria;
        this.status = "open";
        this.assignedDevelopers = new ArrayList<>();
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

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        do
        {
            Scan.print("priority must be between 1 to 5.");
            priority = this.priority;
        }
        while (priority <= 0 || priority >= 6);
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

    public void setStatus(String status)
    {
        this.status = status;
    }

    public ArrayList<Developer> getAssignedDevelopers() {
        return assignedDevelopers;
    }

    public boolean isAssigned(Developer member)
    {
        return assignedDevelopers.contains(member);
    }

    public int getActualVelocity() {
        return actualVelocity;
    }

    public void setActualVelocity(int actualVelocity) {
        this.actualVelocity = actualVelocity;
    }

    // ToString;

    @Override
    public String toString()
    {
        return "\n\nUser story number : " + number + '\n' +
                "Name = " + name + '\n' +
                "Sprint = " + sprint + '\n' +
                "Priority = " + priority + '\n' +
                "Story Points = " + storyPoints + '\n' +
                "Content = " + content + '\n' +
                "Acceptance Criteria =\n" + acceptanceCriteria + '\n' +
                "Status = " + status;
    }
}

