import dao.*;
import models.*;
import java.util.*;
import java.sql.SQLException;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static StudentDAO studentDAO = new StudentDAO();
    static CourseDAO courseDAO = new CourseDAO();
    static MarkDAO markDAO = new MarkDAO();

    public static void main(String[] args){
        while(true){
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Add Course");
            System.out.println("6. View Courses");
            System.out.println("7. Update Course");
            System.out.println("8. Delete Course");
            System.out.println("9. Add/Update Mark");
            System.out.println("10. View Student Marks");
            System.out.println("11. Delete Student Mark");
            System.out.println("12. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt(); sc.nextLine();
            try{
                switch(choice){
                    case 1: addStudent(); break;
                    case 2: viewStudents(); break;
                    case 3: updateStudent(); break;
                    case 4: deleteStudent(); break;
                    case 5: addCourse(); break;
                    case 6: viewCourses(); break;
                    case 7: updateCourse(); break;
                    case 8: deleteCourse(); break;
                    case 9: addUpdateMark(); break;
                    case 10: viewStudentMarks(); break;
                    case 11: deleteStudentMark(); break;
                    case 12: System.exit(0);
                    default: System.out.println("Invalid choice");
                }
            } catch(Exception e){ System.out.println("Error: "+e.getMessage()); }
        }
    }

    static void addStudent(){
        System.out.print("First Name: "); String f=sc.nextLine();
        System.out.print("Last Name: "); String l=sc.nextLine();
        System.out.print("Email: "); String e=sc.nextLine();
        System.out.print("DOB YYYY-MM-DD: "); String d=sc.nextLine();
        try{ studentDAO.addStudent(new Student(f,l,e,d)); System.out.println("Student added"); }
        catch(SQLException ex){ System.out.println("Error: "+ex.getMessage()); }
    }

    static void viewStudents(){
        try{
            List<Student> list = studentDAO.getAllStudents();
            for(Student s : list)
                System.out.println(s.getId()+" "+s.getFirstName()+" "+s.getLastName()+" "+s.getEmail()+" "+s.getDateOfBirth());
        }catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }

    static void updateStudent(){
        System.out.print("Student ID: "); int id=sc.nextInt(); sc.nextLine();
        try{
            Student s = studentDAO.getStudentById(id);
            if(s == null){ System.out.println("Student not found"); return; }
            System.out.print("First Name ("+s.getFirstName()+"): "); String f=sc.nextLine(); if(!f.isEmpty()) s.setFirstName(f);
            System.out.print("Last Name ("+s.getLastName()+"): "); String l=sc.nextLine(); if(!l.isEmpty()) s.setLastName(l);
            System.out.print("Email ("+s.getEmail()+"): "); String e=sc.nextLine(); if(!e.isEmpty()) s.setEmail(e);
            System.out.print("DOB ("+s.getDateOfBirth()+"): "); String d=sc.nextLine(); if(!d.isEmpty()) s.setDateOfBirth(d);
            studentDAO.updateStudent(s); System.out.println("Student updated");
        }catch(SQLException ex){ System.out.println("Error: "+ex.getMessage()); }
    }

    static void deleteStudent(){
        System.out.print("Student ID: "); int id=sc.nextInt(); sc.nextLine();
        try{ studentDAO.deleteStudent(id); System.out.println("Student deleted"); }
        catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }

    static void addCourse(){
        System.out.print("Course Name: "); String n=sc.nextLine();
        System.out.print("Description: "); String d=sc.nextLine();
        try{ courseDAO.addCourse(new Course(n,d)); System.out.println("Course added"); }
        catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }

    static void viewCourses(){
        try{
            List<Course> list = courseDAO.getAllCourses();
            for(Course c : list)
                System.out.println(c.getId()+" "+c.getCourseName()+" "+c.getCourseDescription());
        }catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }

    static void updateCourse(){
        System.out.print("Course ID: "); int id=sc.nextInt(); sc.nextLine();
        try{
            Course c = courseDAO.getCourseById(id);
            if(c == null){ System.out.println("Course not found"); return; }
            System.out.print("Name ("+c.getCourseName()+"): "); String n=sc.nextLine(); if(!n.isEmpty()) c.setCourseName(n);
            System.out.print("Description ("+c.getCourseDescription()+"): "); String d=sc.nextLine(); if(!d.isEmpty()) c.setCourseDescription(d);
            courseDAO.updateCourse(c); System.out.println("Course updated");
        }catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }

    static void deleteCourse(){
        System.out.print("Course ID: "); int id=sc.nextInt(); sc.nextLine();
        try{ courseDAO.deleteCourse(id); System.out.println("Course deleted"); }
        catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }

    static void addUpdateMark(){
        System.out.print("Student ID: "); int sid=sc.nextInt();
        System.out.print("Course ID: "); int cid=sc.nextInt();
        System.out.print("Marks: "); float m=sc.nextFloat(); sc.nextLine();
        try{ markDAO.addOrUpdateMark(new Mark(sid,cid,m)); System.out.println("Mark added/updated"); }
        catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }

    static void viewStudentMarks(){
        System.out.print("Student ID: "); int sid=sc.nextInt(); sc.nextLine();
        try{
            List<Mark> list = markDAO.getMarksByStudent(sid);
            for(Mark m : list)
                System.out.println("Course ID: "+m.getCourseId()+" Marks: "+m.getMarks());
        }catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }

    static void deleteStudentMark(){
        System.out.print("Student ID: "); int sid=sc.nextInt();
        System.out.print("Course ID: "); int cid=sc.nextInt(); sc.nextLine();
        try{ markDAO.deleteMark(sid,cid); System.out.println("Mark deleted"); }
        catch(SQLException e){ System.out.println("Error: "+e.getMessage()); }
    }
}