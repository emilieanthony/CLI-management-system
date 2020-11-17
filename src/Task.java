public class Task
{
	private int priorityNumber;
	private String status;
	private String name;
	private String description;

	public Task(int priorityNumber, String name, String description)
	{
		this.priorityNumber = priorityNumber;
		this.status = "Open";
		this.name = name;
		this.description = description;
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
}
