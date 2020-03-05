import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import static javaapplication6.Admin.admin_func;
import static javaapplication6.Admin.admin_func1;

public class Registration 
{
    public static void Registration()
    {
            try
             {
                 Scanner scan =new Scanner(System.in);
                 int r=0;
                         while(r==0)
                         {
                             System.out.println("Enter registration details:-");
                             System.out.println("Enter your role: Admin or customer");
                             String role=scan.next();
                             if(role.equals("C"))
                             {
                                 System.out.println("1.Enter Name of the customer:");
                             String cust_name=scan.next();
                             System.out.println("2.Enter contact of the customer:");
                             String contact=scan.next();
                             System.out.println("3. Do you want to become a premium member?");
                             String prem_mem = scan.next();
                             //call premium member procedure
                             
                             
                            // Premium_member.premium_member();
                             
                             
                             
                             
                             
                             
                             
                             
                             
                             System.out.println("Enter username:");
                             String uname=scan.next();
                             System.out.println("Enter password:");
                             String pass=scan.next();
                             String query= "{call chk_user(?,?)}";
                             String query1="{call password_check(?,?)}";
                             Class.forName("com.mysql.jdbc.Driver");
                             Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
                             CallableStatement stmt=conn.prepareCall(query);
                             CallableStatement stmt1=conn.prepareCall(query1);
                             stmt.setString(1,uname);
                             stmt.registerOutParameter("flag",java.sql.Types.INTEGER);
                             stmt.execute();

                             stmt1.setString(1,pass);
                             stmt1.registerOutParameter("flag",java.sql.Types.INTEGER);
                             stmt1.execute();
                             int p=0;
                             if(stmt.getInt("flag")==stmt1.getInt("flag") && stmt.getInt("flag")==1)
                             {
                                 r = 1;
                                 while(p!=1)
                                 {
                                     System.out.println("Confirm your password:-");
                                     String cpass=scan.next();
                                     if(cpass.equals(pass))
                                     {
                                         String query2="{call registration1(?,?,?,?,?,?)}";
                                          CallableStatement stmt2=conn.prepareCall(query2);
                                           stmt2.setString(1,uname);
                                           stmt2.setString(2,cust_name);
                                           stmt2.setString(3,pass);
                                           stmt2.setString(4,contact);
                                           stmt2.setString(5,prem_mem);
                                           stmt2.setString(6,role);
                                           stmt2.execute();

                                         System.out.println("Registration done successfully!");
                                         p=1;
                                         break;
                                     }
                                     else
                                     {
                                         System.out.println("Retry!");
                                     } 
                                 }

                             }
                             else
                                 System.out.println("retry!");

                             conn.close(); 
                             }

                             
                              
                         }
             }
            catch(Exception e)
            {
                System.out.println("EXCEPTION!");
            }
                        
             }
    }