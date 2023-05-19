package BusResv;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BookingDAO {

	public int getBookedCount(int busNo, Date date) throws SQLException{
		String query ="select count(passenger_name) from booking where bus_no=? and travel_date=?";
		Connection con = DbConnection.getConnection();
		PreparedStatement st = con.prepareStatement(query);
		java.sql.Date sqldate = new java.sql.Date(date.getTime());
		st.setInt(1,busNo);
		st.setDate(2,sqldate);
		ResultSet rs = st.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	public void addBooking(Booking booking) throws SQLException {
		
		String query = "insert into booking values(?,?,?)";
		Connection con = DbConnection.getConnection();
		PreparedStatement st = con.prepareStatement(query);
		java.sql.Date sqldate = new java.sql.Date(booking.date.getTime());
		st.setString(1,booking.passengerName);
		st.setInt(2,booking.busNo);
		st.setDate(3, sqldate);
		st.executeUpdate();
	}

}
