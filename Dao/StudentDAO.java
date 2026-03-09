package dao;
import db.Dbconnection;
import models.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void addStudent(Student s) throws SQLException {
        String sql="INSERT INTO students(first_name,last_name,email,date_of_birth) VALUES (?,?,?,?)";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1,s.getFirstName());
            stmt.setString(2,s.getLastName());
            stmt.setString(3,s.getEmail());
            stmt.setString(4,s.getDateOfBirth());
            stmt.executeUpdate();
        }
    }

    public Student getStudentById(int id) throws SQLException{
        String sql="SELECT * FROM students WHERE id=?";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next())
                return new Student(rs.getInt("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"),
                        rs.getString("date_of_birth"));
        }
        return null;
    }

    public List<Student> getAllStudents() throws SQLException{
        String sql="SELECT * FROM students";
        List<Student> list=new ArrayList<>();
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                list.add(new Student(rs.getInt("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"),
                        rs.getString("date_of_birth")));
            }
        }
        return list;
    }

    public void updateStudent(Student s) throws SQLException{
        String sql="UPDATE students SET first_name=?, last_name=?, email=?, date_of_birth=? WHERE id=?";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1,s.getFirstName());
            stmt.setString(2,s.getLastName());
            stmt.setString(3,s.getEmail());
            stmt.setString(4,s.getDateOfBirth());
            stmt.setInt(5,s.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException{
        String sql="DELETE FROM students WHERE id=?";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }
}