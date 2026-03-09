package models;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;

    public Student(int id, String firstName, String lastName, String email, String dateOfBirth){
        this.id=id; this.firstName=firstName; this.lastName=lastName;
        this.email=email; this.dateOfBirth=dateOfBirth;
    }
    public Student(String firstName, String lastName, String email, String dateOfBirth){
        this.firstName=firstName; this.lastName=lastName;
        this.email=email; this.dateOfBirth=dateOfBirth;
    }
    public int getId(){return id;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}
    public String getDateOfBirth(){return dateOfBirth;}
    public void setFirstName(String f){this.firstName=f;}
    public void setLastName(String l){this.lastName=l;}
    public void setEmail(String e){this.email=e;}
    public void setDateOfBirth(String d){this.dateOfBirth=d;}
}