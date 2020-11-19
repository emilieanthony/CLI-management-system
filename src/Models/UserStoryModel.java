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

    public void setPriority(int priority) {
        this.priority = priority;
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

    public void setStatus(String status) {
        this.status = status;
    }


    // ToString;

    @Override
    public String toString() {
        return "Models.UserStoryModel{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", sprint='" + sprint + '\'' +
                ", priority=" + priority +
                ", storyPoints=" + storyPoints +
                ", content='" + content + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

