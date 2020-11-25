package Models;

public class ProductOwner {

    //As a scrum master I want to be able to create a new product owner that has a name and ID so that the
    // new product owner can get log in information to the system.
    //Acceptance criteria
    //4.1 The product owner that is created must be stored and the scrum master should be able to access all
    // product owners at any time after the product owner has been created.
    //4.2 To create a product owner, a unique ID will be added and a name must be entered.
    // The system shall generate a unique id to the product owner.
    //4.3 When a product owner is created the system prints a confirmation to the user.

    //attributes
    private String name;
    private String typeId;
    private int id;

    public ProductOwner (String name, String typeId, int id){
        this.name = name;
        this.typeId = typeId;
        this.id = id;
    }

    // getters & setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return name + "  " + typeId + id;
    }
}




