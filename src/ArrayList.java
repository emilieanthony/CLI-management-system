import Models.DevelopmentMember;
import Models.ProductOwner;

public class ArrayList {

    private java.util.ArrayList<ProductOwner> allProductOwners;
    private java.util.ArrayList<DevelopmentMember> allDevelopmentMembers;

    public ArrayList() {
        this.allProductOwners = new java.util.ArrayList<>();
        this.allDevelopmentMembers = new java.util.ArrayList<>();
    }

    public java.util.ArrayList<ProductOwner> getAllProductOwners(){
        return allProductOwners;
    }

    public java.util.ArrayList<DevelopmentMember> getAllDevelopmentMembers(){
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
