package Models;

import java.util.ArrayList;

public class BacklogModel {

    //Attributes:-
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList <UserStoryModel> allUserStories;


    // Constructor:-
    public BacklogModel(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allUserStories = new ArrayList<>();
    }

    //Setters and Getters:-Make sure at the end of the project that not used methods are deleted.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<UserStoryModel> getAllUserStories() {
        return allUserStories;
    }

    public void setAllUserStories(ArrayList<UserStoryModel> allUserStories) {
        this.allUserStories = allUserStories;
    }

    //ToString:-

    @Override
    public String toString() {
        return "Models.BacklogModel{" +
                "name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", allUserStories=" + allUserStories +
                '}';
    }
}
