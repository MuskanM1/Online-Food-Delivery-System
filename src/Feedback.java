/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Feedback 
{
	
	public static void feedback() throws SQLException
	{
		int order_no,rating;
		String comment;
		Scanner input=new Scanner(System.in); 
                System.out.println("Enter customer id:");
		int customer_id=input.nextInt();
		System.out.println("Enter order number:");
		order_no=input.nextInt();
		System.out.println("GIve rating:");
		rating=input.nextInt();
		System.out.println("Give comments:");
		comment=input.next();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
		Statement stmt = null;
		stmt = con.createStatement();
		String query= "{call feedback1(?,?,?,?)}";
		  CallableStatement stmt1 = con.prepareCall(query);
                  stmt1.setInt(1, customer_id);
                stmt1.setInt(2, order_no);
                stmt1.setInt(3, rating);
                stmt1.setString(4,comment);
	      ResultSet rs = stmt1.executeQuery();
	      
	      
	      stmt1.execute();
	      con.close();
		
	}

}
