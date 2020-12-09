package Models;

import java.util.ArrayList;

public class Task
{
	private int id;
	private int priorityNumber;
	private String status;
	private String name;
	private String description;
	private ArrayList<Developer> assignedTeamMembers;
	private int actualHrs;

	public Task(int id, int priorityNumber, String name, String description)
	{
		this.id = id;
		this.priorityNumber = priorityNumber;
		this.status = "Open";
		this.name = name;
		this.description = description;
		this.assignedTeamMembers = new ArrayList<>();
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

	public int getActualHrs() {
		return actualHrs;
	}

	public void setActualHrs(int actualHrs) {
		this.actualHrs = actualHrs;
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

}
