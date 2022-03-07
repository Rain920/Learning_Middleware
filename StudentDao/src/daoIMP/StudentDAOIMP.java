package daoIMP;
import bean.Student;
import dao.StudentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO{
	// 添加操作
	public void insert(Student s){
		String sql = "INSERT INTO student (name) values (?)";
		PreparedStatement pstmt = null;
		DataBaseConnection conn = null;
		//针对数据库的具体操作
		try{
			conn = new DataBaseConnection();

			pstmt = conn.getConnection().prepareStatement(sql);
//			pstmt.setLong(1,s.getID());
			//pstmt.setString(1,s.getID());
			pstmt.setString(1,s.getName());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}
		catch(Exception e){  }
	}

	public void update(Student s) {
//		作业
		/*
		* 根据学生id修改其姓名
		* */
		String sql = "UPDATE student SET name = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		DataBaseConnection conn = null;
		//针对数据库的具体操作
		try{
			conn = new DataBaseConnection();

			pstmt = conn.getConnection().prepareStatement(sql);
			//pstmt.setString(1,s.getID());
			pstmt.setString(1,s.getName());
			pstmt.setLong(2,s.getID());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}
		catch(Exception e){  }
	}

	public void delete(long iD){
//		作业
		/*
		* 删除对应id的学生
		* */
		String sql = "DELETE FROM student WHERE id = ?";
		PreparedStatement pstmt = null;
		DataBaseConnection conn = null;
		//针对数据库的具体操作
		try{
			conn = new DataBaseConnection();

			pstmt = conn.getConnection().prepareStatement(sql);
			//pstmt.setString(1,s.getID());
			pstmt.setLong(1,iD);

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}
		catch(Exception e){  }
	}

	public List<Student> findAll(){
//		作业
		/*
		* select * from
		* */
		String sql = "SELECT * FROM student";
		PreparedStatement pstmt = null;
		DataBaseConnection conn = null;
		List<Student> stuList = new ArrayList<>();
		ResultSet result = null;
		//针对数据库的具体操作
		try{
			conn = new DataBaseConnection();

			pstmt = conn.getConnection().prepareStatement(sql);

			result = pstmt.executeQuery();

			while(result.next()){
				Student s = new Student();
				s.setID(result.getLong("id"));
				s.setName(result.getString("name"));
				stuList.add(s);
			}
			pstmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return stuList;
	}

	public Student findByID(long iD){
//		作业
		/*
		* 根据学生id找到该学生
		* */
		String sql = "SELECT * FROM student WHERE id = ?";
		PreparedStatement pstmt = null;
		DataBaseConnection conn = null;
		Student s = new Student();
		ResultSet result = null;
		s.setID(iD);
		//针对数据库的具体操作
		try{
			conn = new DataBaseConnection();

			pstmt = conn.getConnection().prepareStatement(sql);
			pstmt.setLong(1, iD);

			result = pstmt.executeQuery();
			result.next();
			s.setName(result.getString("name"));

			pstmt.close();
			conn.close();

		}
		catch(Exception e){  }

		return s;
	}
}
