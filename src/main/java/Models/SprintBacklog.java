package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static View.DevTeamView.negativeIDPrint;
import static View.DevTeamView.noNamePrint;

public class SprintBacklog implements Serializable
{
	//attributes
	private String name;
	private String startDate;
	private String endDate;
	private ArrayList<Task> allTasks;
	private ArrayList<UserStory> userStories;

	//Empty constructor for data exporting and importing.
	public SprintBacklog(){

	}

	//constructor
	public SprintBacklog(String name, String startDate, String endDate) throws Exception
	{
		if (name.isEmpty()) {
			noNamePrint();
		} else {
			this.name = name;
		}

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

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate( String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
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
			if(userStory.getNumber() == number)
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

	public void setAllTasks(ArrayList<Task> allTasks) {
		this.allTasks = allTasks;
	}

	public void setUserStories(ArrayList<UserStory> userStories) {
		this.userStories = userStories;
	}

	public ArrayList<UserStory> getUserStories()
	{
		return userStories;
	}

	//--------------------------------------------------

	//toString

	@Override
	public String toString()
	{
		return "SprintBacklog Name: " + name +
				"\nStart Date: " + startDate +
				"\nEnd Date: " + endDate +
				"\nUser Stories:" + userStories +
				"\nTasks:" + allTasks;
	}
}
