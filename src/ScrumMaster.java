public class ScrumMaster
{
	private int id;
	private String name;

	public ScrumMaster(int id,String name)
	{
		this.id = id;
		this.name = name;
	}

	public void assignTask(TeamMember member, Task task)
	{
		task.getAssignedTeamMembers().add(member);
	}
}
