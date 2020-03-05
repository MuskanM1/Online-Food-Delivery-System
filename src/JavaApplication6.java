import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author devshreepatel
 */
public class JavaApplication6 {
public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException
	{
            Scanner input=new Scanner(System.in);
            int c_id=0,c_no=0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
                 
            System.out.println("Are you an admin or a customer?");
            System.out.println("Enter '1' for admin and '2' for customer");
            int choice=input.nextInt();
            if(choice==1)
            {
                Login.login(choice);
            }
            else if(choice==2)
            {
                int fg=0;
              while(fg==0)
              {
                System.out.println("Do you want to register or log in?");
                System.out.println("Enter '1' for log in and '2' for register");
                int choice1=input.nextInt();
               
                if(choice1==1)
                 {
                     fg=1;
                    c_id = Login.login(choice);
                    System.out.println(c_id);
                      Statement st=null,st1 = null;
                    String q1="Select max(order_no) as o_no from orders where orders.customer_id = ?";
                     
                        PreparedStatement s1 = conn.prepareStatement(q1);
                        s1.setInt(1,c_id);
                      ResultSet rs5 = s1.executeQuery();
                      
                         while(rs5.next())
                         {
                                c_no=rs5.getInt("o_no");
             
                         }
                         
                    System.out.println("1. Do you want to place an order:-");
                    System.out.println("2. Do you want to see your previous orders:-");
                    
                    int ch = input.nextInt();
                    switch(ch)
                    {
                       case 1: int rest_id=Order_Window.details(c_id,c_no);
                               bill_details.display_bill(c_id);
                               System.out.println("Do you want to give a feedback or launch a complaint?");
                               System.out.println("Enter 1 for feedback and 2 for complaint");
                               int ch1=input.nextInt();
                               if(ch1==1)
                               {
                                   Feedback.feedback();
                               }
                               if(ch1==2)
                               {
                                   Complaint.complaint();
                               }
                                   
                                break;
                       case 2:
                               display_previous_orders.display_Prev_Orders(c_id);
                               break;
                               
                        default: System.out.println("Wrong choice!");
                    }
                         
                         
                }
                else if(choice1==2)
                {
                    Registration.Registration();
                    fg=0;
                }
                else
                    System.out.println("Wrong choice!");
              }
                
            }
            
            
	}
    
}
