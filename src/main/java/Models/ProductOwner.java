package Models;

import java.io.Serializable;

import static View.DevTeamView.negativeIDPrint;
import static View.DevTeamView.noNamePrint;

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
        if (name.isEmpty()) {
            noNamePrint();
        } else {
            this.name = name;
        }

        if(id < 0) {
            negativeIDPrint();
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




