<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
   Class.forName("com.mysql.cj.jdbc.Driver");
   String url = "jdbc:mysql://localhost:3306/besant";
   String username ="root";
   String password = "@1234567";
   String sql = "select * from employee";
   Connection con = DriverManager.getConnection(url,username,password);
   Statement st = con.createStatement();
   ResultSet rs = st.executeQuery(sql);
   while(rs.next()){;
   %>
   Id :<%= rs.getInt(1) %><br>
   EName :<%= rs.getString(2) %><br>
   Job_Desc :<%= rs.getString(3) %><br>
   Salary :<%= rs.getInt(4) %><br>
  <br>
  <%
}
rs.close();
st.close();
con.close();
%>
 
</body>
</html>