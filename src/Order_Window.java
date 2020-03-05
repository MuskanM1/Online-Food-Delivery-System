/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class Order_Window 
{
	public static int details(int cust_id, int order_id) throws SQLException, ClassNotFoundException
	{
		 Scanner scan = new Scanner(System.in);
		 String item_string="",qty_string="";
		 Class.forName("com.mysql.jdbc.Driver");
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
		 Statement stmt = conn.createStatement();
                 ResultSet rs;
 
                rs = stmt.executeQuery("SELECT restaurant_name,restaurant_id,open_time,close_time from restaurant");
                
	       int i=0;
	       System.out.println("Restaurant ID          Restaurant Name      Open Time       Close Time");
	        while (rs.next()) 
	        {
	            i = i+1;
	            String restName = rs.getString("restaurant_name");
	            int rest_id = rs.getInt("restaurant_id");
	            java.sql.Time open_t = rs.getTime("open_time");
	            java.sql.Time close_t = rs.getTime("close_time");
	            
	            System.out.println(rest_id + "       " + restName + "         " + open_t + "           " + close_t);
	            System.out.println(restName);
	            
	        }
	        
	        System.out.println("Select Restaurant ID from which you want to order:");
	        int x = scan.nextInt();
	        
	        //String query1 = "SELECT item_name,item_id,category,price from food_item where food_item.item_id IN (SELECT menu_food.item_id from menu_food where x=menu_food.menu_id)";
	        
	        	//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
                            Statement stmt1 = conn.createStatement();
                            String q1=("SELECT item_name,item_id,category,price,availability from food_item where food_item.item_id IN (SELECT menu_food.item_id from menu_food where menu_food.menu_id=?)");
                            
                        
                            PreparedStatement s1 = conn.prepareStatement(q1);
                            s1.setInt(1,x);

                           ResultSet rs1 = s1.executeQuery();
                          
	        	i=0;
		       System.out.println("Item ID          Item Name      Category       Price        Availability");
		        while (rs1.next()) 
		        {
		        	i = i+1;
		            String itemName = rs1.getString("item_name");
		            int item_id = rs1.getInt("item_id");
		            String category = rs1.getString("category");
		            int price= rs1.getInt("price");
                            int av= rs1.getInt("availability");
		            char av1 = (av>0? 'y':'n');
		            System.out.println(item_id + "       " + itemName + "         " + category + "           " + price+"           " + av1);
		            
		        }
                        
                        String new_order= "{call orderInsert(?,?)}";
                        conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
                        CallableStatement stmt_no=conn.prepareCall(new_order);
                        stmt_no.setInt(1,cust_id);
           	 	stmt_no.setInt(2,x);
                        stmt_no.execute();
                        
		        System.out.println("Select the item_ids of the items you want to order:");
		        int cont=1; 
		        int j=0;
		        int temp,q=0;
		        ArrayList<Integer> items= new ArrayList<>(); 
		        do
		        {
		        	temp=scan.nextInt();
		        	items.add(temp);
		        	j++;
		        	item_string=item_string+temp;
		        	System.out.println("Enter quantity:");
		        	q=scan.nextInt();
                                
                                String new_orderdetails= "{call orderDetailsInsert(?,?,?)}";
                                CallableStatement stmt_no1=conn.prepareCall(new_orderdetails);
                                stmt_no1.setInt(1,cust_id);
                                stmt_no1.setInt(2,q);
                                stmt_no1.setInt(3,temp);
                                stmt_no1.execute();
                                
		        	qty_string=(qty_string+q);
		        	System.out.println("Press 1 to add more items, 0 to stop");
		        	cont=scan.nextInt();
		        	if(cont==1)
		        	{
		        		item_string+=",";
		        		qty_string+=",";
		        	}
		        }while(cont==1);
		        
		        System.out.println("Enter delivery address:");
		        System.out.println("Enter house number:");
		        String houseno=scan.next();
		        System.out.println("Enter street number:");
		        String streetno=scan.next();
		        System.out.println("Enter area:");
		        String area=scan.next();
		        System.out.println("Enter city:");
		        String city=scan.next();
		        System.out.println("Enter pincode:");
		        String pincode=scan.next();
		        String new_deladd="{call deliveryAddress_insert(?,?,?,?,?,?)}";
		        CallableStatement stmt_no2=conn.prepareCall(new_deladd);
                        
		        try {
                            Class.forName("com.mysql.jdbc.Driver");
			} 
                        
                        catch (ClassNotFoundException e) 
                        {
                            e.printStackTrace();
			}
                        
                        //System.out.println(qty_string);
                        stmt_no2.setString(1,houseno);
                        stmt_no2.setString(2,streetno);
                        stmt_no2.setString(3,area);
                        stmt_no2.setString(4,city);
                        stmt_no2.setString(5,pincode);
                        stmt_no2.setInt(6,cust_id);
                        stmt_no2.execute();
                        
                        //ETA
 		        ETA.eta(x,cust_id);
                        
                        //delivery fee
                        //delivery_fee.delivery(cust_id);
                                
                        return(x);
}
	
	
}