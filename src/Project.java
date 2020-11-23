import java.util.ArrayList;

public class Project
{
	private ArrayList<Task> tasks;
	private ArrayList<TeamMember> teamMembers;
	private ScrumMaster scrumMaster;
	//Product owner
	//User stories
	//Project backlog

	public Project()
	{
		tasks = new ArrayList<>();
		teamMembers = new ArrayList<>();
		testSetup();
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

	private void testSetup()
	{
		scrumMaster = new ScrumMaster(1,"Lena Andersson");
		teamMembers.add(new TeamMember(2,"Gurra Eriksson"));
		teamMembers.add(new TeamMember(3,"Olle Qvist"));
		teamMembers.add(new TeamMember(4,"Dick James"));
		tasks.add(new Task(1,4,"Menu","Create a main menu"));
		tasks.add(new Task(2,3,"Scrum master","Create a scrum master"));
	}
}
