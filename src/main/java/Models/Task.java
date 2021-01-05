package Models;

import Utility.DataManagement;
import View.ScrumMasterView;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import static View.DevTeamView.*;


public class Task implements Comparable<Task> {

	private int id;
	private int priorityNumber;
	private int estimatedHours;
	private int actualHours;
	private String status;
	private String name;
	private String description;
	private ArrayList<Developer> assignedDevelopers;
	private String deadline;
	private String completedBy;

	public Task(int id, int priorityNumber, int estimatedTime, String name, String description) throws Exception {

		if (id < 0) {
			negativeIDPrint();
		} else {
			this.id = id;
		}
		if (priorityNumber < 0 || priorityNumber > 5) {
			ScrumMasterView.invalidNumberPrint();
		} else {
			this.priorityNumber = priorityNumber;
		}
		if (estimatedHours < 0) {
			negativeNumberPrint();
		} else {
			this.estimatedHours = estimatedTime;
		}

		this.name = name;
		this.description = description;
		this.actualHours = 0;
		this.status = "Open";
		this.assignedDevelopers = new ArrayList<>();
	}


	//Empty constructor for data exporting and importing.
	public Task() {
	}

 	//-----------------------------------Getters & Setters--------------------------------------------------------------

	public void setId(int id) {
		this.id = id;
	}

	public void setActualHours(int actualHours) {
		this.actualHours = actualHours;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPriorityNumber(int priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public void setOpen() {
		status = "Open";
	}

	public void setInProgress() {
		status = "In progress";
	}

	public void setComplete() {
		status = "Complete";
	}

	public void setAssigned() {
		status = "Assigned";
	}

	public void setAssignedTeamMembers(ArrayList<Developer> assignedTeamMembers) {
		this.assignedDevelopers = assignedTeamMembers;
	}


	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public int getActualHours() {
		return actualHours;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public ArrayList<Developer> getAssignedDevelopers() {
		return assignedDevelopers;
	}

	public boolean isAssigned(Developer member) {
		return assignedDevelopers.contains(member);
	}

	public String getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	//---------------------------------Methods--------------------------------------------------------------------------

	public int compareTo(Task task) {
		if (this.priorityNumber < task.priorityNumber) {
			return 1;
		} else if (this.priorityNumber > task.priorityNumber) {
			return -1;
		} else {
			return 0;
		}
	}

	public int compareByDeadline(Task anotherTask){
		return DataManagement.compareDeadlines(this.deadline, anotherTask.getDeadline());
	}


	//--------------------------------------ToString--------------------------------------------------------------------

	public String toString() {
		String output;

		output =
				" \nTask: " +
						"\nTask ID: " + id +
						"\nName: " + name +
						"\nPriority number: " + priorityNumber +
						"\nStatus: " + status +
						"\nDescription: " + description +
						"\nEstimated hours: " + estimatedHours +
						"\nActual hours: " + actualHours +
						"\nAssigned Team Members:";

		if (assignedDevelopers.isEmpty()) {
			output = output + " None\n";
		} else {
			for (Developer member : assignedDevelopers) {
				output = output + "\n" + member.toString() + "\n";
			}
		}

		if (!(completedBy==null)){
			output = output + "Set as complete by: " + completedBy + "\n";
		}

		if(!(deadline==null)){
			output = output + "Deadline: " + deadline + "\n";
		}

		return output;
	}

}

