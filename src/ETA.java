/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author devshreepatel
 */
public class ETA {
    Scanner scan =new Scanner(System.in);
	public static void eta(int restaurant_id,int cust_id)
	{
		String query_td= "{call eta(?,?)}";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
				CallableStatement stmt_td=conn.prepareCall(query_td);
				
           	 	stmt_td.setInt(1,cust_id);
                        stmt_td.setInt(2,restaurant_id);
           	 	stmt_td.execute();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        
	}

}
