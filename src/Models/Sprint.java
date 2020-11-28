package Models;

import java.time.LocalDate;

public class Sprint {

    //attributes
    String name;
    LocalDate startDate;
    LocalDate endDate;

    //constructor
    public Sprint(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //all getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    //toString

    @Override
    public String toString() {
        return "Sprint Name: " + name +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate;
    }
}
