package Models;

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

	public int getId()
	{
		return id;
	}

	public String toString()
	{
		return "ID: " + id + " Name: " + name;
	}

}
