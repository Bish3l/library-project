package main.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person {
    private int person_id;

    @Size(min = 1, max = 249, message = "Name length must be between 1 and 250 characters")
    private String name;

    @Min(value = 1901, message = "Birth year must be greater than 1900")
    private int birthYear;

    public Person() {}

    public Person(int id, String fullName, int birthYear) {
        this.person_id = id;
        this.name = fullName;
        this.birthYear = birthYear;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
