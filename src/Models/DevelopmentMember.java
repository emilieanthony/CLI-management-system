package Models;

public class DevelopmentMember {
    /*As a scrum master I want to be able to create a new development team member that has a name and IDso that the
    development team member can get log in information to the system.
    Acceptance criteria5.1 The development team member
    that is created has to be stored and the scrum master should be able to access all development team members at anytime.
    5.2 To create a development team member, a unique ID will be added,and a name must be entered.
    The system shall generate a unique id to the development team member.
    5.3 When a development team member is created the system prints a confirmation text to the user.
     */



    String name;
    int id;

    public DevelopmentMember(String name, int id) {
        this.name = name;
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

    public String toString() {
        return name + "  " + id;
    }
}
