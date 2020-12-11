package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Comparable<Task>, Serializable
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
		this.actualHours = 0;
		this.status = "Open";
		this.name = name;
		this.description = description;
		this.assignedTeamMembers = new ArrayList<>();
		this.sprintName = "";
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
	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
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
	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
