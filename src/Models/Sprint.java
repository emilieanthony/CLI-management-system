package Models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Sprint
{

	//attributes
	private String name;
	private /*LocalDate*/ String startDate;
	private /*LocalDate*/ String endDate;
	private ArrayList<Task> allTasks;      //sprintBacklog is allTasks and userStories
	private ArrayList<UserStory> userStories;

	//constructor
	public Sprint(String name, /*LocalDate*/ String startDate, /*LocalDate*/ String endDate)
	{
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.allTasks = new ArrayList<>();
		this.userStories = new ArrayList<>();
	}

	//all getters & setters
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public /*LocalDate*/ String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(/*LocalDate*/ String startDate)
	{
		this.startDate = startDate;
	}

	public /*LocalDate*/ String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(/*LocalDate*/ String endDate)
	{
		this.endDate = endDate;
	}

	//--------------------------------
	public Task getTask(int id)
	{
		for (Task task : allTasks)
		{
			if (task.getId() == id)
			{
				return task;
			}
		}
		return null;
	}

	public UserStory getUserStory(int number)
	{
		for(UserStory userStory : userStories)
		{
			if(userStory.getName().equals(number))
			{
				return userStory;
			}
		}
		return null;
	}

	public String printTasks()
	{
		String output = "";

		for (Task task : allTasks)
		{
			output = output + task.toString();
		}
		return output;
	}

	public String printPersonalTasks(Developer member)
	{
		String output = "";

		if (allTasks == null)
		{
			output = "You have no tasks assigned.";
		}
		else
		{
			for (Task task : allTasks)
			{
				if (task.isAssigned(member))
				{
					output = output + task.toString() + "\n";
				}
			}
		}
		return output;
	}

	public ArrayList<Task> getAllTasks()
	{
		return allTasks;
	}

	public ArrayList<UserStory> getAllUserStories()
	{
		return userStories;
	}

	//--------------------------------------------------

	//toString

	@Override
	public String toString()
	{
		return "Sprint Name: " + name +
				"\nStart Date: " + startDate +
				"\nEnd Date: " + endDate;
	}
}
