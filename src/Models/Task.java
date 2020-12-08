package Models;

import java.util.ArrayList;

public class Task implements Comparable<Task>
{
	private int id;
	private int priorityNumber;
	private int estimatedHours;
	private int actualHours;
	private String status;
	private String name;
	private String description;
	private ArrayList<Developer> assignedTeamMembers;
	private String sprintName;

	public Task(int id, int priorityNumber, int estimatedTime, String name, String description)
	{
		this.id = id;
		this.priorityNumber = priorityNumber;
		this.estimatedHours = estimatedTime;
		this.actualHours = actualHours;
		this.status = "Open";
		this.name = name;
		this.description = description;
		this.assignedTeamMembers = new ArrayList<>();
		this.sprintName = "";
	}

	public Task(int id, int priorityNumber, String name, String description) {
		this.id = id;
		this.priorityNumber = priorityNumber;
		this.status = "Open";
		this.name = name;
		this.description = description;
		this.assignedTeamMembers = new ArrayList<>();
	}
	public int getId() {
		return id;
	}

	public int getPriorityNumber() {
		return priorityNumber;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public int getActualHours() {
		return actualHours;
	}

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<Developer> getAssignedTeamMembers() {
		return assignedTeamMembers;
	}

	public void setId(int id) {
		this.id = id;
	}

	// constructor for creating a new task without knowing actual hours
	public Task(int id, int priorityNumber, int estimatedHours, String name, String description) {
		this.id = id;
		this.priorityNumber = priorityNumber;
		this.estimatedHours = estimatedHours;
		this.status = "Open";
		this.name = name;
		this.description = description;
		this.assignedTeamMembers = new ArrayList<>();
	}
	public void setPriorityNumber(int priorityNumber) {
		this.priorityNumber = priorityNumber;
	}
	public void setActualHours(int actualHours) {
		this.actualHours = actualHours;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public void setPriorityNumber(int priorityNumber)
	{
		if(priorityNumber > 0  && priorityNumber < 6)
		{
			priorityNumber = priorityNumber;
		}
	}

	public void setSprintName(String name)
	{
		sprintName = name;
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

	public ArrayList<Developer> getAssignedTeamMembers()
	{
		return assignedTeamMembers;
	}

	public int getId()
	{
		return id;
	}

	public boolean isAssigned(Developer member)
	{
		return assignedTeamMembers.contains(member);
	}

	public String toString()
	{
		String output;

		output =
				"Tasks: "+ "\nID:" + id + "\nName: " + name + "\nPriority number: " + priorityNumber +
						"\nStatus: " + status+ "\nDescription: " + description + "\nAssigned Team Members:";

		if(assignedTeamMembers == null)
		{
			output = output + "\n";
		}
		else
		{
			for (Developer member : assignedTeamMembers)
			{
				output = output + "\n" + member.toString();
			}
		}

		return output;
	}

	public int compareTo(Task task)
	{
		if(this.priorityNumber < task.priorityNumber)
		{
			return 1;
		}
		else if(this.priorityNumber > task.priorityNumber)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	@Override
	public String toString() {
		String output =
				"Task: "+ "\nID:" + id + "\nName: " + name + "\nPriority number: " +
						priorityNumber + "\nEstimated hours: "+ estimatedHours + "\nActual hours: " +
						"\nStatus: " + status+ "\nDescription: " + description + "\nAssigned Team" +
						" Members:" + assignedTeamMembers;
		return output;
	}

}
