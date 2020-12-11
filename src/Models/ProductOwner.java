package Models;

import java.io.Serializable;

public class ProductOwner implements Serializable
{

    //attributes
    private String name;
    private int id;

    public ProductOwner(String name , int id)
    {
        this.name = name;
        this.id = id;
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




