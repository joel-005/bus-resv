package BusResv;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner; //nested package/ hierarchical package

public class BusDemo {

	public static void main(String[] args) throws SQLException  {
		
		BusDAO busdao = new BusDAO();
		try {
		busdao.displayBusInfo();
      
		int userOpt = 1;
		Scanner scanner = new Scanner(System.in);
		
		while(userOpt == 1) {
		System.out.println("Enter 1 to book and 2 to Exit");
		userOpt = scanner.nextInt();
		if(userOpt == 1) {
			Booking booking = new Booking();
			if(booking.isAvailable()) {
				BookingDAO bookingdao = new BookingDAO();
				bookingdao.addBooking(booking);
				System.out.println("Your booking is confirmed");
			}
			else{
				System.out.println("Sorry. Bus is full. Try another bus or date");
			}
		}
	}
		scanner.close();   
	  }catch(Exception e) {
		  System.out.println(e);
	  }
   }
}