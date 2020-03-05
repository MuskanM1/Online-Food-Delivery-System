import java.sql.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin
{
    public static void admin_func()
    {
        try {
            String query= "{call top_10()}";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
                CallableStatement stmt = conn.prepareCall(query);
                boolean rs1 = stmt.execute();
                int rs_count=0;
                ArrayList<Integer> cid_list = new ArrayList<>();
                ArrayList<Integer> ord_list = new ArrayList<>();
                
                while (rs1) 
                {         
                	ResultSet rset=stmt.getResultSet();
                        while(rset.next())
                        {
                                    int c_id = rset.getInt("temp_cust_id");
                                String c_name = rset.getString("cust_name");
                                String contact = rset.getString("contact");
                                if(!(cid_list.contains(c_id)))
                                {
                                        cid_list.add(c_id);
                                        System.out.println("Customer Id: " + c_id + "               " + " Customer Name: " + c_name + "              " + "Contact: " + contact);

                                }

                                int ord_id = rset.getInt("temp_order_no");
                                java.sql.Time time = rset.getTime("order_time");

                                if(!(ord_list.contains(ord_id)))
                                {
                                        ord_list.add(ord_id);
                                        System.out.println("Order no: " + ord_id + "    " + "Order Time: " + time);
                                        System.out.println("Item ID    Item Name    Price   Quantity");
                                }

                                int item_id = rset.getInt("d");
                            String name = rset.getString("name");
                            int qty = rset.getInt("qty");
                            int price = rset.getInt("price");

                            System.out.println(item_id + "   " + name + "   " + qty + "   " + price);

                            if(!(ord_list.contains(ord_id)))
                                {
                                int bill_amt = rset.getInt("bill_amt");
                                int disc = rset.getInt("disc");
                                int final_price = rset.getInt("final_price");

                                System.out.println("Bill Amount  " + bill_amt + " discount : " + disc + "  final price: " + final_price);
                                }
                        }
                        rs1=stmt.getMoreResults();
                        
         
                }
                stmt.close();
                
                
            } 
             catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void admin_func1()
    {
        try {
            
            String query1= "{call restaurant_details3()}";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
            try{
                    CallableStatement stmt1 = conn.prepareCall(query1);
    
               
                boolean rs2 = stmt1.execute();

                ArrayList<Integer> rid_list = new ArrayList<>();
                
                        while(rs2)
                        {
                            ResultSet rset1=stmt1.getResultSet();
                        
                        while (rset1.next()) 
                            {         

                                    int r_id = rset1.getInt("temp_rest_id");
                                    String r_name = rset1.getString("c");
                                    int b_no = rset1.getInt("d");
                                    java.sql.Time o_time = rset1.getTime("e");
                                    java.sql.Time c_time = rset1.getTime("f");
                                    String contact;
                                            contact = rset1.getString("g");
                                    if(!(rid_list.contains(r_id)))
                                    {
                                            
                                            System.out.println("Restaurant Id: " + r_id + "               " + " Restaurant Name: " + r_name + "              " + "No of branches: " + b_no + "                "+ "Open Time"+ o_time+ "             "+"Close Time"+ c_time+ "              "+" Contact"+contact);

                                    }

                                    String branch_name = rset1.getString("temp_name");
                                    String branch_city = rset1.getString("temp_city");
                                    System.out.println("Branch Name"+ branch_name +"               "+"Branch City" + branch_city);
                                    int no_of_orders = rset1.getInt("temp_order_no");
                                    if(!(rid_list.contains(r_id)))
                                    {
                                            rid_list.add(r_id);
                                            System.out.println("No of orders:"+ no_of_orders);
                                    }


                            }
                           rs2=stmt1.getMoreResults();
                        } 
                        stmt1.close();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            } 
             catch (ClassNotFoundException ex) 
             {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
             }
             catch (SQLException ex) 
            {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
}