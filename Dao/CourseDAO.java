package dao;
import db.Dbconnection;
import models.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public void addCourse(Course c) throws SQLException{
        String sql="INSERT INTO courses(course_name,course_description) VALUES (?,?)";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1,c.getCourseName());
            stmt.setString(2,c.getCourseDescription());
            stmt.executeUpdate();
        }
    }

    public Course getCourseById(int id) throws SQLException{
        String sql="SELECT * FROM courses WHERE id=?";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next())
                return new Course(rs.getInt("id"),rs.getString("course_name"),rs.getString("course_description"));
        }
        return null;
    }

    public List<Course> getAllCourses() throws SQLException{
        String sql="SELECT * FROM courses";
        List<Course> list=new ArrayList<>();
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
                list.add(new Course(rs.getInt("id"),rs.getString("course_name"),rs.getString("course_description")));
        }
        return list;
    }

    public void updateCourse(Course c) throws SQLException{
        String sql="UPDATE courses SET course_name=?, course_description=? WHERE id=?";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1,c.getCourseName());
            stmt.setString(2,c.getCourseDescription());
            stmt.setInt(3,c.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCourse(int id) throws SQLException{
        String sql="DELETE FROM courses WHERE id=?";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }
}