package dao;
import db.Dbconnection;
import models.Mark;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarkDAO {
    public void addOrUpdateMark(Mark m) throws SQLException{
        String sql="INSERT INTO marks(student_id,course_id,marks) VALUES (?,?,?) " +
                "ON CONFLICT(student_id,course_id) DO UPDATE SET marks=EXCLUDED.marks";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1,m.getStudentId());
            stmt.setInt(2,m.getCourseId());
            stmt.setFloat(3,m.getMarks());
            stmt.executeUpdate();
        }
    }

    public List<Mark> getMarksByStudent(int studentId) throws SQLException{
        String sql="SELECT * FROM marks WHERE student_id=?";
        List<Mark> list=new ArrayList<>();
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1,studentId);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
                list.add(new Mark(rs.getInt("student_id"),rs.getInt("course_id"),rs.getFloat("marks")));
        }
        return list;
    }

    public void deleteMark(int studentId,int courseId) throws SQLException{
        String sql="DELETE FROM marks WHERE student_id=? AND course_id=?";
        try(Connection conn=Dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1,studentId);
            stmt.setInt(2,courseId);
            stmt.executeUpdate();
        }
    }
}