package Models;


import java.time.LocalDate;
import java.util.ArrayList;

public class SprintBacklog
{

	//attributes
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private ArrayList<Task> allTasks;
	private ArrayList<UserStory> userStories;

	//constructor
	public SprintBacklog(String name, LocalDate startDate, LocalDate endDate)
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

	public LocalDate getStartDate()
	{
		return startDate;
	}

	public void setStartDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}

	public LocalDate getEndDate()
	{
		return endDate;
	}

	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}


	//--------------------------------------------------

	//toString

	@Override
	public String toString()
	{
		return "SprintBacklog Name: " + name +
				"\nStart Date: " + startDate +
				"\nEnd Date: " + endDate;
	}
}
