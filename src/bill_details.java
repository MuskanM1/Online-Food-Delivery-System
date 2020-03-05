import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class bill_details 
{
	public static void display_bill(int cid)
	{
		 try {
			 //calling offers for prev orders
                         int bill_amount=0,bill_total=0,discount=0,delivery_f=0;
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn;
                            try {
                              conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/food_delivery","root","root");

                                    String query1= "{call offer_1(?)}";
                                    CallableStatement stmt1 = conn.prepareCall(query1);
                                    stmt1.setInt(1,cid);
                                    ResultSet rs1 = stmt1.executeQuery();

                                    //calling bill details procedure to insert into bill
                                    String query2= "{call bill_details4(?)}";
                                    CallableStatement stmt2 = conn.prepareCall(query2);
                                    stmt2.setInt(1,cid);
                                    ResultSet rs2 = stmt2.executeQuery();


                        //calling bill procedure to display            
                               String query= "{call bill_procedure(?)}";

                                   CallableStatement stmt = conn.prepareCall(query);
                                   stmt.setInt(1,cid);
                                   boolean rs3 = stmt.execute();
                                   ArrayList<Integer> ord_list = new ArrayList<>();

                           while (rs3) 
                           {     
                               ResultSet rset=stmt.getResultSet();

                               while(rset.next())
                               {
                                  
                                   int flag=0;
                                   

                                   int ord_id = rset.getInt("ord_id");


                                           if(!(ord_list.contains(ord_id)))
                                           {
                                                   ord_list.add(ord_id);
                                                   System.out.println("Order id: " + ord_id);
                                                   System.out.println("Item ID     Item Name    Quantity    Price    Final Price");
                                                   bill_amount = rset.getInt("bill_amount");
                                                   bill_total = rset.getInt("bill_total");
                                                   discount = rset.getInt("discount");
                                                   delivery_f=rset.getInt("delivery_fee");
                                           }

                                           int item_id = rset.getInt("d");
                                           String name = rset.getString("name");
                                           int qty = rset.getInt("qty");
                                           int price = rset.getInt("price");
                                           int final_price = rset.getInt("final_pr");
                                           System.out.println(item_id + "   " + name + "  " + qty + "   " + price + "    " + final_price);

                                   }
                               rs3=stmt.getMoreResults();
                             }

                                           stmt.close();
                                           stmt2.close();
                                           stmt1.close();

                                           System.out.println("Bill Amount " + bill_amount);
                                           System.out.println("Discount: " + discount);
                                           System.out.println("Delivery_fee " + delivery_f);
                                           System.out.println("Bill Total " + bill_total);


                            }
                    

                                catch (SQLException ex) {
                                   Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                               }

                 
                           } catch (ClassNotFoundException ex) {
                               Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                           }

                     }
		
}

