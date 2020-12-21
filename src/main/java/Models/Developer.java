package Models;

import java.io.Serializable;

public class Developer implements Serializable
{

    private String name;
    private int id;


    //Empty constructor for data exporting and importing.
    public Developer(){

    }

    public Developer(String name, int id) throws Exception {

        this.name = name;
        if(id < 0) {
            System.out.println("ID cannot be negative, please try again.");
        } else {
            this.id = id;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString()
    {
        return "\nDeveloper: " + "\nName: " + name + "\nID: " + id;
    }

}
