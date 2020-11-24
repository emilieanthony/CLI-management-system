import Models.Task;
import Models.TeamMember;

import java.util.ArrayList;

public class Projects
{
	private ArrayList<Task> tasks;
	private ArrayList<TeamMember> teamMembers;
	//Product owner
	//User stories
	//Controller.Project backlog

	public Projects()
	{
		tasks = new ArrayList<>();
		teamMembers = new ArrayList<>();
	}

	public Task getTask(int id)
	{
		for(Task task : tasks)
		{
			if(task.getId() == id)
			{
				return task;
			}
		}
		return null;
	}

	public void addTask(Task task)
	{
		tasks.add(task);
	}

	public String printTasks()
	{
		String output = "";

		for (Task task : tasks)
		{
			output = output +  task.toString();
		}
		return output;
	}

	public String printTasks(TeamMember member)
	{
		String output = "";

		if (tasks == null)
		{
			output = "You have no tasks assigned.";
		}
		else
		{
			for (Task task : tasks)
			{
				if (task.isAssigned(member))
				{
					output = output + task.toString() + "\n";
				}
			}
		}
		return output;
	}

	public TeamMember getTeamMember(int id)
	{
		for(TeamMember member : teamMembers)
		{
			if(member.getId() == id)
			{
				return member;
			}
		}
		return null;
	}

	public void assignTask(int memberID, int taskID)		//This one is using method below
	{
		assignTask(getTeamMember(memberID), getTask(taskID));
	}

	public void assignTask(TeamMember member, Task task)
	{
		task.getAssignedTeamMembers().add(member);
	}


}
