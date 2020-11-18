import java.util.Scanner;

public class MenuProductOwner {
    Scanner input = new Scanner(System.in);

    public void menuProductOwner() {

        System.out.println("Welcome product owner!\n" +
                "Please enter an option below\n" +
                "1. Create a new product backlog\n" +
                "2. View product backlog\n" +
                "3. Go back to main menu\n");
        int option = input.nextInt();

        switch (option) { //put switch in controller and call methods?
            case 1:
                createBacklog();

            case 2:
                //viewBacklog(Backlog);

            case 3:
                // go back to main menu
        }

    }

    public Backlog createBacklog() {
        System.out.println("Please enter product backlog name:");
        String backlogName = input.nextLine();
        System.out.println("Please enter start date:");
        String startDate = input.nextLine();
        System.out.println("Please enter end date:");
        String endDate = input.nextLine();

        return new Backlog(backlogName + startDate + endDate);
    }

    //public void viewBacklog(){
    //loop and print back log
    //}


}
