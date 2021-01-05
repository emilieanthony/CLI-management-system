package Models;

import java.util.ArrayList;
import static View.DevTeamView.*;


public class UserStory implements Comparable<UserStory> {

    // Attributes:-
    private String name;
    private int number;
    private int priorityNumber;
    private int storyPoints;
    private String content;
    private String acceptanceCriteria;
    private String status;
    private ArrayList<Developer> assignedDevelopers;
    private ArrayList<Task> userStoryTasks;
    private ArrayList<Boolean> isCompleted;

    // Constructors:-
    public UserStory(String name, int number, int priority,
                     String content, String acceptanceCriteria) throws Exception {

        if (name.isEmpty()) {
            noNamePrint();
        } else {
            this.name = name;
        }

        if (number < 0) {
            negativeNumberPrint();
        } else {
            this.number = number;
        }

        if (priority < 0) {
            negativeNumberPrint();
        } else {
            this.priorityNumber = priority;
        }

        this.storyPoints = 0;
        this.content = content;
        this.acceptanceCriteria = acceptanceCriteria;
        this.status = "Open";
        this.assignedDevelopers = new ArrayList<>();
        this.userStoryTasks = new ArrayList<>();
        this.isCompleted = new ArrayList<>();
    }

    //Empty constructor for data exporting and importing.
    public UserStory() {

    }

    //------------------------------------Getters & Setters-------------------------------------------------------------

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }



    public int getStoryPoints() {
        return storyPoints;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Task> getUserStoryTasks() {
        return userStoryTasks;
    }

    public ArrayList<Developer> getAssignedDevelopers() {
        return assignedDevelopers;
    }

    public ArrayList<Boolean> getIsCompleted() {
        return isCompleted;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPriorityNumber(int priorityNumber) {
        /*do
        {
            Scan.print("priority must be between 1 to 5.");*/
        this.priorityNumber = priorityNumber;
        /*}
        while (priorityNumber <= 0 || priorityNumber >= 6);*/
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public void setOpen() {
        status = "Open";
    }

    public void setInProgress() {
        status = "In progress";
    }

    public void setComplete() {
        status = "Complete";
    }

    public void setAssigned() {
        status = "Assigned";
    }

    //-----------------------------------------Methods------------------------------------------------------------------

    public int compareTo(UserStory userStory) {
        if (this.priorityNumber < userStory.priorityNumber) {
            return 1;
        } else if (this.priorityNumber > userStory.priorityNumber) {
            return -1;
        } else {
            return 0;
        }
    }

    public boolean isAssigned(Developer member) {
        return assignedDevelopers.contains(member);
    }


    //-------------------------------------To String--------------------------------------------------------------------

    @Override
    public String toString() {
        String output;

        output =
                "\nUser Story: " +
                        "\nUser story ID: " + number +
                        "\nName: " + name +
                        "\nPriority: " + priorityNumber +
                        "\nStory Points: " + storyPoints +
                        "\nContent. " + content +
                        "\nAcceptance Criteria: " + acceptanceCriteria +
                        "\nStatus: " + status +
                        "\nTasks: " + userStoryTasks +
                        "\nAssigned Team Members:";

        if (assignedDevelopers.isEmpty()) {
            output = output + " None\n";
        } else {
            for (Developer member : assignedDevelopers) {
                output = output + "\n" + member.toString() + "\n";
            }
        }

        return output;
    }
}

