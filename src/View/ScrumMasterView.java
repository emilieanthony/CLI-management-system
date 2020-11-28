package View;

import Models.Task;
import Models.UserStory;
import Utility.Scan;


public class ScrumMasterView {

    public int menuScrumMaster() {

        int option = Scan.readInt("\n\nWelcome Scrum Master!\n" +
                "Please enter an option below\n" +
                "1. Create a new project\n" +
                "2. Create a new sprint\n" +
                "3. Create a new Development Team Member\n" +
                "4. Create a new Product owner\n" +
                "5. Assign a task to Development Team Member\n" +
                "6. View product backlog\n" +
                "7. View all Development Team Members\n" +
                "8. Go back to main menu\n");

        return option;
    }

  /*  public Task getTaskInfo()
    {
        Scan.print("Creating a new task");
        String name = Scan.readLine("Name: ");
        int id = taskIdGenerator.();
        int prioNumber = Scan.readInt("Priority number: ");
        String description = Scan.readLine("Task description: ");

        Scan.print("You have now created a new task!");
        return new Task(id, prioNumber, name, description);
    }*/

    public String getNewUSStatus()
    {
        String newUSStatus = Scan.readLine("Enter new Status for the user " +
                "story.");
        return newUSStatus;
    }
}



