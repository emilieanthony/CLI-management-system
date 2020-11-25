package View;

import Utility.Scan;
import java.util.Scanner;

public class DevTeamView {
    //TO DO
    //Projects projects = new Projects();
    Scanner input = new Scanner(System.in);

//methods
    /*-------------------------------------------Menu Development Team ----------------------------------------------*/

    public int menuTeamMember() {
        Scan.print("Welcome development team member!\n" +
                "Please enter an option below\n" +
                "1. View my own tasks\n" +
                "2. View assigned tasks\n" +
                "3. Add a new task to myself\n" +
                "4. Go back to main menu");
        int option = input.nextInt();
        return option;
        }

       //TO DO  - "project" doesn't connect
    /*public void viewTasks(){
        System.out.println("Write your ID: ");
        int id = input.nextInt();
        System.out.println("----YOUR ASSIGNED TASK(S)----");
        System.out.print(projects.printTasks(projects.getTeamMember(id)));
        System.out.println("-----------------------------");
    }

    public void viewAssignedTasks(){
        System.out.println("------ALL ASSIGNED TASKS-----");
        System.out.print(projects.printTasks());
        System.out.println("-----------------------------");
    }*/
}

