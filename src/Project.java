import java.util.ArrayList;

public class Project
{
	private ArrayList<Task> tasks;
	private ArrayList<TeamMember> teamMembers;
	private ScrumMaster scrumMaster;

	public Project()
	{
		tasks = new ArrayList<>();
		teamMembers = new ArrayList<>();
		testSetup();
	}

	public void addTask(Task task)
	{
		tasks.add(task);
	}

	public void printTasks()
	{
		for (Task task : tasks)
		{
			System.out.print(task.toString());
		}
	}

	public TeamMember getTeamMembers(TeamMember member)
	{
		return teamMembers.get(member);
	}

	private void testSetup()
	{
		scrumMaster = new ScrumMaster(1,"Lena Andersson");
		teamMembers.add(new TeamMember(2,"Gurra Eriksson"));
		teamMembers.add(new TeamMember(3,"Olle Qvist"));
		teamMembers.add(new TeamMember(4,"Dick James"));
		tasks.add(new Task(4,"Menu","Create a main menu"));
		tasks.add(new Task(3,"Scrum master","Create a scrum master"));
	}

	public void test()
	{
		scrumMaster.assignTask(teamMembers.get(0), tasks.get(0));
		printTasks();
	}
}
