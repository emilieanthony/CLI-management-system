import java.util.Scanner;

public class ControllerProductOwner {


    private Project project;
    private Scanner scanner;

    public ControllerProductOwner() {
        project = new Project();
        scanner = new Scanner(System.in);

    }

    public void createProductOwner() {

        System.out.println("Please type the name of the new Product Owner below: ");
        String name = scanner.nextLine();

       // System.out.println("Please type the id of the new Product Owner below: ");
       // int id = scanner.nextInt();

        int id = createIdProductOwner();

        project.getAllProductOwners().add(new ProductOwner(name, id));

        System.out.println(" You have successfully created " + name + " as a new Product Owner with the id " + id);

        project.viewAllProductOwners();

    }

    public int createIdProductOwner() {

        int id = 200;

        if (project.getAllProductOwners().isEmpty()) {
            id = 200;
        } else {
            id = project.getAllProductOwners().get(project.getAllProductOwners().size() - 1).getId() + 1;
        }
        return id;
    }


}

