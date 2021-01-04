package Models;

import Utility.DataManagement;
import View.ScrumMasterView;
import java.util.ArrayList;

public class SprintBacklog
{
	//attributes
	private String name;
	private String startDate;
	private String endDate;
	private ArrayList<Task> allTasks;
	private ArrayList<UserStory> userStories;
	private int totalStoryPoints;

	//Empty constructor for data exporting and importing.
	public SprintBacklog(){}

	//constructor
	public SprintBacklog(String name, String startDate, String endDate) throws Exception
	{
		this.name = name;
		if(DataManagement.stringToLocalDate(startDate).isAfter(DataManagement.stringToLocalDate(endDate)))
		{
			ScrumMasterView.wrongDatePrint();
		} else {
			this.startDate = startDate;
			this.endDate = endDate;
		}
		this.allTasks = new ArrayList<>();
		this.userStories = new ArrayList<>();
	}

	public int calcTotalStoryPoints()
	{
		this.totalStoryPoints = 0;

		for (UserStory userStory:userStories)
		{
			if (userStory.getStatus().equalsIgnoreCase("Done"))
			{
				totalStoryPoints = totalStoryPoints + userStory.getStoryPoints();
			}
		}
		return totalStoryPoints;
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

	public int getTotalStoryPoints() {
		return totalStoryPoints;
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

	public ArrayList<Task> getAllTasks()
	{
		return allTasks;
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
				"\nImplemented story points: " + totalStoryPoints +
				"\nUser Stories:" + userStories +
				"\nTasks:" + allTasks;
	}
}