import java.util.ArrayList;

public class Task
{
	private int priorityNumber;
	private String status;
	private String name;
	private String description;
	private ArrayList<TeamMember> assignedTeamMembers;

	public Task(int priorityNumber, String name, String description)
	{
		this.priorityNumber = priorityNumber;
		this.status = "Open";
		this.name = name;
		this.description = description;
		assignedTeamMembers = new ArrayList<>();
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

	public String toString()
	{
		String output;

		output = "Priority number: " + priorityNumber + "  Status: " + status + "  Name: " + name +
				"  Description: " + description + "\nAssigned Team Members:\n";

		if(assignedTeamMembers == null)
		{
			output = output + "None\n";
		}
		else
		{
			for (TeamMember member : assignedTeamMembers)
			{
				output = output + member.toString() + "\n";
			}
		}

		return output + "\n";
	}

}
