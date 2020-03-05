import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Complaint 
{
	
	public static void complaint()
	{
		int order_no;
		String comment,ct;
		Scanner input=new Scanner(System.in);
		System.out.println("Enter order number:");
		order_no=input.nextInt();
		System.out.println("GIve complaint_type:");
		ct=input.next();
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
			Statement stmt = null;
			stmt = con.createStatement();
			String query= "{call complaint(?,?)}";
			  CallableStatement stmt1 = con.prepareCall(query);
                          stmt1.setInt(1, order_no);
		      stmt1.setString(2, ct);
		      ResultSet rs = stmt1.executeQuery();
		      
		      //stmt1.setString(3, comment);
		      
		      stmt1.execute();
		      con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
