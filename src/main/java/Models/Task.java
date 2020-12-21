package Models;

import java.io.Serializable;
import java.util.ArrayList;

import static View.DevTeamView.*;

public class Task implements Comparable<Task>, Serializable
{
	private int id;
	private int priorityNumber;
	private int estimatedHours;
	private int actualHours;
	private String status;
	private String name;
	private String description;
	private ArrayList<Developer> assignedDevelopers;
	private String sprintName;

	//Empty constructor for data exporting and importing.
	public Task(){

	}

	public Task(int id, int priorityNumber, int estimatedTime, String name, String description) throws Exception
	{
		if (name.isEmpty()) {
			noNamePrint();
		} else {
			this.name = name;
		}

		if(id < 0) {
			negativeIDPrint();
		} else {
			this.id = id;
		}
		if(priorityNumber < 0) {
			negativeNumberPrint();
		} else {
			this.priorityNumber = priorityNumber;
		}
		if(estimatedTime < 0) {
			negativeNumberPrint();
		} else {
			this.estimatedHours = estimatedTime;
		}

		this.actualHours = 0;
		this.status = "Open";
		this.description = description;
		this.assignedDevelopers = new ArrayList<>();
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

	/*public void setPriorityNumber(int priorityNumber) // this affects editing tasks
	{
		if(priorityNumber > 0  && priorityNumber < 6)
		{
			priorityNumber = priorityNumber;
		}
	}*/

	public void setPriorityNumber (int priorityNumber) {this.priorityNumber = priorityNumber; }

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

	public ArrayList<Developer> getAssignedDevelopers()
	{
		return assignedDevelopers;
	}

	public void setAssignedTeamMembers(ArrayList<Developer> assignedTeamMembers) {
		this.assignedDevelopers = assignedTeamMembers;
	}

	public int getId()
	{
		return id;
	}


	public boolean isAssigned(Developer member)
	{
		return assignedDevelopers.contains(member);
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

	public String soloToString() { // had to use a weird sprintName here because original toString was already taken ???? would appreciate an explanation whoever did that //Lina
		return "Task ID: " + id +
				"\nPriority Number: " + priorityNumber +
				"\nEstimated Hours: " + estimatedHours +
				"\nActual Hours: " + actualHours +
				"\nStatus: " + status +
				"\nName: " + name +
				"\nDescription: " + description +
				"\nAssigned team members: " + assignedDevelopers +
				"\nSprint sprintName: " + sprintName ;
	}

	public String toString()
	{
		String output;

		output =
				"Tasks: \n"+
						"ID:" + id +
						"\nName: " + name +
						"\nPriority number: " + priorityNumber +
						"\nStatus: " + status +
						"\nDescription: " + description +
						"\nEstimated hours: " + estimatedHours +
						"\nActual hours: " + actualHours +
						"\nAssigned Team Members:" + "\n";

		if(assignedDevelopers == null)
		{
			output = output + "None\n";
		}
		else
		{
			for (Developer member : assignedDevelopers)
			{
				output = output + "\n" + member.toString();
			}
		}

		return output;
	}

}
