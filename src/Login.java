import java.sql.*;
import java.util.*;
import static javaapplication6.Admin.admin_func;
import static javaapplication6.Admin.admin_func1;

public class Login 
{
	public static int login(int a_u)
	{
            int c_id=0,c_no=0;
	try
	  {     Scanner scan =new Scanner(System.in);
             int r=0;
             
             while(r==0)
             {
            	 //System.out.println("Press 1 if you are a customer, 2 if you are an admin");
            	 //int a_u= scan.nextInt();
                 System.out.println("Enter login details:-");
                 System.out.println("Enter username:");
                 String uname=scan.next();
                 System.out.println("2.Enter password:");
                 String pass=scan.next();
                 
                 String query_u= "{call user_login(?,?,?)}";
                 String query_a="{call admin_login(?,?,?)}";
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
                 
                 if(a_u==2)
                 {
                     
                     Statement st=null,st1 = null;
                     r=1;
                     CallableStatement stmt_u=conn.prepareCall(query_u);
                     stmt_u.setString(1,uname);
                     stmt_u.setString(2,pass);
                     stmt_u.registerOutParameter("flag",java.sql.Types.INTEGER);
                     stmt_u.execute();
                     if(stmt_u.getInt("flag")==1)
                     {
                    	 System.out.println("successful login");
                         String q = "SELECT customer_id FROM customer where customer.email = ?";
                        
                        PreparedStatement s1 = conn.prepareStatement(q);
                        s1.setString(1,uname);
                    
                           ResultSet rs = s1.executeQuery();
                          
                          
                         while(rs.next())
                         {
                                c_id=rs.getInt("customer_id");
                                
     
                         }
                         
                        
                     }
                     else
                     {
                    	 System.out.println("unsuccessful login");
                     }
                     
                     
                 }
                 else if(a_u==1)
                 {
                	 r = 1;
                         CallableStatement stmt_a=conn.prepareCall(query_a);
                	 stmt_a.setString(1,uname);
                	 stmt_a.setString(2,pass);
                         stmt_a.registerOutParameter("flag",java.sql.Types.INTEGER);
                         stmt_a.execute();
                            if(stmt_a.getInt("flag")==1)
                            {
                                System.out.println("successful login");
                                 int ans;
                                 System.out.println("1.Do you want to view the top 10 customers?");
                                 System.out.println("2.View Restaurant details");
                                 ans = scan.nextInt();
                                 if(ans == 1)
                                 {
                                    admin_func();
                                 }
                                 if(ans==2)
                                 {
                                     admin_func1();
                                 }
                            }
                            else
                            {
                                System.out.println("unsuccessful login");
                            }
                           
                 }

                 conn.close(); 
             }
        }
         catch(ClassNotFoundException | SQLException e)
         {
           System.out.println(e.getMessage());
         }
            
        
        return c_id;
	}
}

