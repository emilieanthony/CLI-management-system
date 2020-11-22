import java.util.Scanner;

public class ControllerDevelopmentMember {



    private Project project;
    private Scanner scanner;

    public ControllerDevelopmentMember() {
        project = new Project();
        scanner = new Scanner(System.in);

    }

    public int createIdDevelopmentMember() {

        int id = 1;

        if (project.getAllDevelopmentMembers().isEmpty()) {
            id = 1;
        } else {
            id = project.getAllDevelopmentMembers().get(project.getAllDevelopmentMembers().size() - 1).getId() + 1;
        }
        return id;
    }



    public void createDevelopmentMember() {

        System.out.println("Please type the name of the new Development member below: ");
        String name = scanner.nextLine();

        int id = createIdDevelopmentMember();
        //System.out.println("Please type the id of the new Development member below: ");
        //int id = scanner.nextInt();

        project.getAllDevelopmentMembers().add(new DevelopmentMember(name, id));

        System.out.println(" You have successfully created " + name + " as a new Development Member with the id " + id);

        project.viewAllDevelopmentMembers();


    }

}

