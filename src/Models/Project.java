package Models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project
{
    //attributes
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    private ProductBacklog productBacklog;
    private SprintBacklog sprintBacklog;
    private ProductOwner productOwner;
    private Developer developer;
    private Task task;

    private ArrayList<Task> allTasks;
    private ArrayList<Developer> allTeamMembers;
    private ArrayList<ProductOwner> allProductOwners;
    private ArrayList<ProductBacklog> allProductBacklogs;
    private ArrayList<SprintBacklog> allSprintBacklogs;



    //constructor


    public Project(int id, String name, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        productBacklog = new ProductBacklog(null,null,null);
        sprintBacklog = new SprintBacklog(null,null,null);
        productOwner = new ProductOwner(null,0);
        developer = new Developer(null,0);
        task = new Task(0,0,0,null,null);
        allTasks = new ArrayList<>();
        allTeamMembers = new ArrayList<>();
        allProductOwners = new ArrayList<>();
        allProductBacklogs = new ArrayList<>();
        allSprintBacklogs = new ArrayList<>();

    }

    //all getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public SprintBacklog getSprintBacklog() {
        return sprintBacklog;
    }

    public ProductOwner getProductOwner() {
        return productOwner;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public Task getTask() {
        return task;
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public ArrayList<Developer> getAllTeamMembers() {
        return allTeamMembers;
    }

    public ArrayList<ProductOwner> getAllProductOwners() {
        return allProductOwners;
    }

    public ArrayList<ProductBacklog> getAllProductBacklogs() {
        return allProductBacklogs;
    }

    public ArrayList<SprintBacklog> getAllSprintBacklogs() {
        return allSprintBacklogs;
    }

    //toString

    @Override
    public String toString() {
        return "\nProject: "+ id +
                "\nName: " + name +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate +
                //"\nProduct Backlog: " + productBacklog +
                //"\nSprint Backlog: " + sprintBacklog +
                //"\nProduct Owner: " + productOwner +
                //"\nDeveloper: " + developer +
                "\nTasks: " + allTasks +
                "\nTeam Members: " + allTeamMembers +
                "\nProduct Owners: " + allProductOwners +
                "\nProduct Backlogs=" + allProductBacklogs +
                "\nSprint Backlogs: " + allSprintBacklogs;
    }
}
