package models;

public class Guest {
    private int id;
    private String name;
    private String email;
    private static int nextId = 1;

    // Constructor that accepts id, name, and email
    public Guest(String name, String email) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter methods for accessing guest details
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Override toString() method to provide a string representation of the guest
    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
