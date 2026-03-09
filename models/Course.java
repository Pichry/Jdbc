package models;

public class Course {
    private int id;
    private String courseName;
    private String courseDescription;

    public Course(int id,String name,String desc){this.id=id; this.courseName=name; this.courseDescription=desc;}
    public Course(String name,String desc){this.courseName=name; this.courseDescription=desc;}

    // getters and setters
    public int getId(){return id;}
    public String getCourseName(){return courseName;}
    public String getCourseDescription(){return courseDescription;}
    public void setCourseName(String n){this.courseName=n;}
    public void setCourseDescription(String d){this.courseDescription=d;}
}