package Models;

import Exceptions.EmptyName;
import Exceptions.InvalidPriorityNumber;
import Exceptions.NegativeId;
import Utility.DataManagement;

import java.util.ArrayList;


public class UserStory implements Comparable<UserStory> {

    // Attributes:-
    private String name;
    private int number;
    private int priorityNumber;
    private int storyPoints;
    private String content;
    private ArrayList<String> acceptanceCriteria;
    private String status;
    private ArrayList<Developer> assignedDevelopers;
    private ArrayList<Task> userStoryTasks;
    private ArrayList<Boolean> binary;
    private String deadline;
    private String completedBy;

    // Constructors:-
    public UserStory(String name, int number, int priority,
                     String content, String acceptanceCriteria) throws Exception {

        if (name.isEmpty())
        {
            throw new EmptyName();
        } else {
            this.name = name;
        }

        if(number < 0)
        {
            throw new NegativeId();
        } else {
            this.number = number;
        }

        if (priority < 0 || priority > 5) {
            throw new InvalidPriorityNumber();
        } else {
            this.priorityNumber = priority;
        }

        this.storyPoints = 0;
        this.content = content;
        this.acceptanceCriteria = new ArrayList<>();
        this.acceptanceCriteria.add(acceptanceCriteria);
        this.status = "Open";
        this.assignedDevelopers = new ArrayList<>();
        this.userStoryTasks = new ArrayList<>();
        this.binary = new ArrayList<>();
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

    public ArrayList<Boolean> getBinary() {
        return binary;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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

    public ArrayList<String> getAcceptanceCriteria() {
        return acceptanceCriteria;
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

    public String getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
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

    public int compareByDeadline(UserStory anotherStory){
        return DataManagement.compareDeadlines(this.deadline, anotherStory.getDeadline());
    }

    public boolean isAssigned(Developer member) {
        return assignedDevelopers.contains(member);
    }


    //-------------------------------------To String--------------------------------------------------------------------

    @Override
    public String toString() {
        String output;


        String acceptanceCriteria = "";

        int listLine = 1;
        for (String criteria : this.acceptanceCriteria){
            acceptanceCriteria = acceptanceCriteria + "\n " + listLine + ". " + criteria;
            listLine++;
        }


        output =
                "\nUser Story: " +
                        "\nUser story ID: " + number +
                        "\nName: " + name +
                        "\nPriority: " + priorityNumber +
                        "\nStory Points: " + storyPoints +
                        "\nContent: " + content +
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

        if (!(completedBy==null)){
            output = output + "Set as complete by: " + completedBy + "\n";
        }

        if(!(deadline==null)){
            output = output + "Deadline: " + deadline + "\n";
        }

        return output;
    }
}

