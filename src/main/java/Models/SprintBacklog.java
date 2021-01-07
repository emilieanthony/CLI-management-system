package Models;

import Exceptions.EmptyName;
import Exceptions.WrongDate;
import Utility.DataManagement;
import java.time.LocalDate;
import java.util.ArrayList;


public class SprintBacklog implements Comparable<SprintBacklog>
{
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
		if (name.isEmpty())
		{
			throw new EmptyName();
		} else {
			this.name = name;
		}

		if(DataManagement.stringToLocalDate(startDate).isAfter(DataManagement.stringToLocalDate(endDate)))
		{
			throw new WrongDate();
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
			if (userStory.getStatus().equalsIgnoreCase("Complete"))
			{
				totalStoryPoints = totalStoryPoints + userStory.getStoryPoints();
			}
		}
		return totalStoryPoints;
	}

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

	public UserStory getUserStory(int id)
	{
		for(UserStory userStory : userStories)
		{
			if(userStory.getId() == id)
			{
				return userStory;
			}
		}
		return null;
	}

	public String getEndDate() {
		return endDate;
	}

	public ArrayList<Task> getAllTasks()
	{
		return allTasks;
	}

	public ArrayList<UserStory> getUserStories()
	{
		return userStories;
	}

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

	@Override
	public int compareTo(SprintBacklog anotherSprint) {



		LocalDate endDate = DataManagement.stringToLocalDate(this.endDate);
		LocalDate anotherEndDate = DataManagement.stringToLocalDate(anotherSprint.getEndDate());



		if( endDate.isBefore(anotherEndDate) ) {
			return -1;

		}else if( endDate == anotherEndDate ) {
			return 0;

		} else {
			return 1;
		}

	}
}