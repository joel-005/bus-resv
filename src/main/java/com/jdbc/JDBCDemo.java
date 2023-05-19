package com.jdbc;
import java.sql.*;

public class JDBCDemo {

	public static void main(String[] args) throws Exception {

		batchdemo();
	}
	public static void readRecords() throws SQLException {
		String url ="jdbc:mysql://localhost:3306/besant";
		String userName ="root";
		String passWord ="@1234567";
		String sql ="select * from employee";
		Connection con = DriverManager.getConnection(url,userName,passWord);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println("Id Is "+rs.getInt(1));
			System.out.println("Name Is "+rs.getString(2));
			System.out.println("JobDesc Is "+rs.getString(3));
			System.out.println("Salary Is "+rs.getInt(4));
		}
		con.close();
	}

	public static void insertRecords() throws Exception {
		String url ="jdbc:mysql://localhost:3306/besant";
		String userName ="root";
		String passWord ="@1234567";
		String sql ="insert into employee values(4,'joel',50000)";
		Connection con = DriverManager.getConnection(url,userName,passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(sql);
		
		System.out.println("Number Of Rows Affected "+rows);
		con.close();
	}
	//insert with variables
	
	public static void insertVar() throws Exception {
		String url ="jdbc:mysql://localhost:3306/besant";
		String userName ="root";
		String passWord ="@1234567";
		int id = 5;
		String name ="jona";
		int salary =300000;
		String sql ="insert into employee values(?,?,?)";
		Connection con = DriverManager.getConnection(url,userName,passWord);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.setString(2, name);
		st.setInt(3,salary);
		
		int rows = st.executeUpdate();
		System.out.println("Number Of Rows Affected "+rows);
		con.close();
	}
	public static void delete() throws Exception {
		String url ="jdbc:mysql://localhost:3306/besant";
		String userName ="root";
		String passWord ="@1234567";
		int id = 5;
		String sql ="delete from employee where emp_id="+id;
		Connection con = DriverManager.getConnection(url,userName,passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(sql);
		
		System.out.println("Number Of Rows Affected "+rows);
		con.close();
	}
	//calling simple stored procedure
	public static void sp() throws Exception {
		String url ="jdbc:mysql://localhost:3306/besant";
		String username ="root";
		String password ="@1234567";
		Connection con = DriverManager.getConnection(url,username,password);
		CallableStatement st = con.prepareCall("{call GetEmp()}");
		ResultSet rs=  st.executeQuery();
		while(rs.next()) {
			System.out.println("Id Is "+rs.getInt(1));
			System.out.println("Name Is "+rs.getString(2));
			System.out.println("Salary Is "+rs.getInt(3));
		}
		con.close();
	}
	//in parameter
	public static void sp2() throws Exception {
		String url ="jdbc:mysql://localhost:3306/besant";
		String username ="root";
		String password ="@1234567";
		int id = 4;
		Connection con = DriverManager.getConnection(url,username,password);
		CallableStatement st = con.prepareCall("{call GetEmpId(?)}");
		st.setInt(1, id);
		ResultSet rs=  st.executeQuery();
		while(rs.next()) {
			System.out.println("Id Is "+rs.getInt(1));
			System.out.println("Name Is "+rs.getString(2));
			System.out.println("Salary Is "+rs.getInt(3));
		}
		con.close();
	}
	//in and out parameter
	public static void sp3() throws Exception {
		String url ="jdbc:mysql://localhost:3306/besant";
		String username ="root";
		String password ="@1234567";
		int id = 4;
		Connection con = DriverManager.getConnection(url,username,password);
		CallableStatement st = con.prepareCall("{call GetNameById(?,?)}");
		st.setInt(1, id);
		st.registerOutParameter(2, Types.VARCHAR);
		st.executeUpdate();
		System.out.println(st.getString(2));
		con.close();
}
	//commit demo
	public static void commitdemo() throws Exception {
		String url ="jdbc:mysql://localhost:3306/besant";
		String username ="root";
		String password ="@1234567";
		
		String query1 = "update employee set salary=450000 where emp_id=1";
		String query2 = "update employee set salary=400000 where emp_id=2";

		Connection con = DriverManager.getConnection(url,username,password);
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		int row1 = st.executeUpdate(query1);
		System.out.println("Rows affected :"+row1);
		
		int row2 = st.executeUpdate(query2);
		System.out.println("Rows affected :"+row2);
		
		if(row1 >0 && row2 >0) {
			con.commit();
			con.close();
		}
	}	
	// batch
	public static void batchdemo() throws Exception {
		String url ="jdbc:mysql://localhost:3306/besant";
		String username ="root";
		String password ="@1234567";
		
		String query1 = "update employee set salary=300000 where emp_id=1";
		String query2 = "update employee set salary=300000 where emp_id=2";
		String query3 = "update employee set salary=300000 where emp_id=3";
		String query4 = "update employee set salary=300000 where emp_id=4";


		Connection con = DriverManager.getConnection(url,username,password);
		con.setAutoCommit(false);
		Statement st = con.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        st.addBatch(query4);
        
        int[] result = st.executeBatch();
        for(int i:result) {
        	if(i>0) {
        		continue;
        	}else {
        		con.rollback();
        	}	
        }
        con.commit();
    	con.close();
	}
}
