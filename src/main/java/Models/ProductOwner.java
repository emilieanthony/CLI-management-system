package Models;

import java.io.Serializable;

public class ProductOwner implements Serializable
{

    //attributes
    private String name;
    private int id;

    //Empty constructor for data exporting and importing.
    public ProductOwner(){

    }

    public ProductOwner(String name , int id) throws Exception
    {
        this.name = name;
        if (id < 0){
            throw new Exception("ID must be a positive integer.");
        } else {
            this.id = id;
        }
    }

    // getters & setters

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }


    public String toString()
    {
        return "\nProduct owner: " + "\nName: " + name + "\nID: " + id;
    }
}




