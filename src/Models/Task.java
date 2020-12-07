package Models;

import java.util.ArrayList;

public class Task
{
	private int id;
	private int priorityNumber;
	private int estimatedHours;
	private int actualHours;
	private String status;
	private String name;
	private String description;
	private ArrayList<Developer> assignedTeamMembers;

	public Task(int id, int priorityNumber, int estimatedTime, String name, String description)
	{
		this.id = id;
		this.priorityNumber = priorityNumber;
		this.estimatedHours = estimatedTime;
		this.actualHours = 0;
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

	public void setPriorityNumber(int priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public void setActualHours(int actualHours) {
		this.actualHours = actualHours;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
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
