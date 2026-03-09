package models;

public class Mark {
    private final int studentId;
    private final int courseId;
    private float marks;

    public Mark(int studentId,int courseId,float marks){this.studentId=studentId;this.courseId=courseId;this.marks=marks;}
    // getters and setters
    public int getStudentId(){return studentId;}
    public int getCourseId(){return courseId;}
    public float getMarks(){return marks;}
    public void setMarks(float m){this.marks=m;}
}