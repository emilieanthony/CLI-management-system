package Models;

import Exceptions.EmptyName;


public class Developer
{
    private String name;
    private int id;

    //Empty constructor for data exporting and importing.
    public Developer(){}

    //Constructor
    public Developer(String name, int id) throws Exception
    {

        if (name.isEmpty())
        {
            throw new EmptyName();
        } else {
            this.name = name;
        }
        this.id = id;
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
        return "\nDevelopment team member:" +
                "\nName: " + name +
                "\nID: " + id;
    }

}
