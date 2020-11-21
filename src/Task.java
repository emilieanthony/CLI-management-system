import java.util.ArrayList;

public class Task
{
	private int id;
	private int priorityNumber;
	private String status;
	private String name;
	private String description;
	private ArrayList<TeamMember> assignedTeamMembers;

	public Task(int id, int priorityNumber, String name, String description)
	{
		this.id = id;
		this.priorityNumber = priorityNumber;
		this.status = "Open";
		this.name = name;
		this.description = description;
		this.assignedTeamMembers = new ArrayList<>();
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

	public ArrayList<TeamMember> getAssignedTeamMembers()
	{
		return assignedTeamMembers;
	}

	public boolean isAssigned(TeamMember member)
	{
		return assignedTeamMembers.contains(member);
	}

	public int getId()
	{
		return id;
	}

	public String toString()
	{
		String output;

		output = "ID: " + id + " Priority number: " + priorityNumber + "  Status: " + status + "  Name: " + name +
				"  Description: " + description + "\nAssigned Team Members:";

		if(assignedTeamMembers == null)
		{
			output = output + "\nNone";
		}
		else
		{
			for (TeamMember member : assignedTeamMembers)
			{
				output = output + "\n" + member.toString();
			}
		}

		return output;
	}

}
