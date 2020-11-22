import java.util.ArrayList;

public class Project {

    private ArrayList<ProductOwner> allProductOwners;
    private ArrayList<DevelopmentMember> allDevelopmentMembers;

    public Project () {
        this.allProductOwners = new ArrayList<>();
        this.allDevelopmentMembers = new ArrayList<>();

    }

    public ArrayList<ProductOwner> getAllProductOwners(){
        return allProductOwners;
    }

    public ArrayList<DevelopmentMember> getAllDevelopmentMembers(){
        return allDevelopmentMembers;
    }

    public void viewAllProductOwners() {

        for (ProductOwner productOwner : allProductOwners){
            System.out.println(productOwner.toString());
        }
    }

    public void viewAllDevelopmentMembers() {
        for (DevelopmentMember developmentMember : allDevelopmentMembers){
            System.out.println(developmentMember.toString());
        }
    }
}
