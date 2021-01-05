package Models;

import Exceptions.EmptyName;
import Exceptions.NegativeId;

import java.io.Serializable;


public class ProductOwner implements Serializable
{
    private String name;
    private int id;

    //Empty constructor for data exporting and importing.
    public ProductOwner(){}

    //Constructor
    public ProductOwner(String name , int id) throws Exception
    {
        if (name.isEmpty())
        {
            throw new EmptyName();
        } else {
            this.name = name;
        }

        if(id < 0)
        {
            throw new NegativeId();
        } else {
            this.id = id;
        }
    }


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
        return "\nProduct owner:" +
                "\nName: " + name +
                "\nID: " + id;
    }
}




