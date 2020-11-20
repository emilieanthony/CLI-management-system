package Models;


public class UserStoryModel {

    // Attributes:-
    private String name;
    private String number;
    private String sprint;
    private int priority;
    private int storyPoints;
    private String content;
    private String acceptanceCriteria;
    private String status;

    // Constructors:-
    public UserStoryModel(String name, String number, String sprint, int priority, int storyPoints, String content, String acceptanceCriteria) {
        this.name = name;
        this.number = number;
        this.sprint = sprint;
        this.priority = priority;
        this.storyPoints = storyPoints;
        this.content = content;
        this.acceptanceCriteria = acceptanceCriteria;
        this.status = "open";
    }

    // Setters and Getters. Make sure at the end of the project that not used methods are deleted.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority)
    {
        do{
            System.out.println("priority must be between 1 to 5.");
            priority = this.priority;
        }
        while (priority <= 0 || priority >= 6);
    }


    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

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



    // ToString;

    @Override
    public String toString() {
        return "User story number : " + number + '\n' +
                "Name = " + name + '\n' +
                "Sprint = " + sprint + '\n' +
                "Priority = " + priority + '\n' +
                "Story Points = " + storyPoints + '\n' +
                "Content = " + content + '\n' +
                "Acceptance Criteria = " + acceptanceCriteria + '\n' +
                "Status = " + status + '\n' + ".";
    }
}

