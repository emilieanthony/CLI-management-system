package Models;

import java.io.Serializable;
import static View.DevTeamView.negativeIDPrint;


public class ProductOwner implements Serializable
{
    private String name;
    private int id;

    //Empty constructor for data exporting and importing.
    public ProductOwner(){}

    //Constructor
    public ProductOwner(String name , int id) throws Exception
    {
        this.name = name;
        if(id < 0)
        {
            negativeIDPrint();
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




