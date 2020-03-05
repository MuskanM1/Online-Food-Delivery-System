import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class display_previous_orders {

    private static Connection conn;
    private static String query;
    private static Object stmt1;
    public static void display_Prev_Orders(int c_id)
    {
        try{
            String query = "{call display(?)}";
            Class.forName("com.mysql.jdbc.Driver");
           
            
        try {
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");
                CallableStatement stmt = conn.prepareCall(query);

                stmt.setInt(1,c_id);
                //ResultSet rs=stmt.executeQuery();
                boolean rs1 = stmt.execute();
                ArrayList<Integer> ord_list = new ArrayList<>();
                int order_no=0;
                /*ResultSet rset1=stmt.getResultSet();
                if(rset1.next())
                     order_no = rset1.getInt("order_no");
                
                rset1.previous();*/
                
                int count=0;
                
                int g=0;
                while (rs1) 
                {     
                    ResultSet rset=stmt.getResultSet();

                    while(rset.next())
                    {
                            ++count;
                            int temp;
                            /*if(g==0)
                            {
                                g=1;
                                temp = rset.getInt("order_no");

                            }*/
                            // if(rset.next())
                             order_no = rset.getInt("order_no");

                            //rset.previous();
                            ord_list.add(order_no);
                            
                            //System.out.println(rset.getInt("order_no"));
                            java.sql.Timestamp rts = rset.getTimestamp("order_time");

                            int id = rset.getInt("item_id");
                            String Name = rset.getString("name");
                            int quant = rset.getInt("qty");
                            int pri = rset.getInt("price");


                            System.out.println("Order NO " + order_no + "               " + " Order Date: " + rts);
                            System.out.println("Item ID"+"           "+ "Item Name " + "                "+ "Item Quantity"+ "             "+"Item Price");

                            int bill_amount = rset.getInt("bill_amount");
                            int discount = rset.getInt("discount");
                             int Final = rset.getInt("final_price");


                            System.out.println(id+"        " +Name + "           " + quant + "        " + pri);

                            order_no = rset.getInt("order_no");
                            //System.out.println(rset.getInt("order_no"));
                                if((ord_list.contains(order_no)))
                                {
                                    ord_list.add(order_no);
                                    System.out.println("Bill amount " + bill_amount + "               ");
                                    System.out.println(" Discount: " + discount + "              ");
                                    System.out.println(" Final Price" + Final);
                                    System.out.println("----------------------------------------------------------");

                                }

                      }
                    rs1=stmt.getMoreResults();
                    
                }
                stmt.close();
                
        } catch (SQLException ex) {
            Logger.getLogger(display_previous_orders.class.getName()).log(Level.SEVERE, null, ex);
        }
                
               
               
         } catch (ClassNotFoundException ex) {
            
        }
                            
                        
    }                 
                     
       
}     
    

