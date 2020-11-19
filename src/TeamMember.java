import java.util.ArrayList;

public class TeamMember
{
	private int id;
	private String name;

	public TeamMember(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public void printMytask(ArrayList<Task> taskList)
	{
		String output = "";

		if (taskList == null)
		{
			output = "You have no tasks assigned.";
		}
		else
		{
			for (Task task : taskList)
			{
				if (task.isAssigned(this))
				{
					output = output + task.toString() + "\n";
				}
			}
		}
		System.out.println(output);
	}

	public String toString()
	{
		return "ID: " + id + " Name: " + name;
	}

}
