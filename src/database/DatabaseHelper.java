package database;




import java.sql.*;
import phmgtsys.Notification;



public class DatabaseHelper{
    
    
    private String userName, password;
    private Connection con;
    
    
    
    public DatabaseHelper(){
        
    }
    
    
    
    public DatabaseHelper( String userName, String password ){        
        this.userName = userName;
        this.password = password;        
    }
    
    
    
    
    public boolean createConnection(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");            
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharmacy", "root", "");            
            //Statement st = con.createStatement();            
            return true;
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            return false;
        }
        
    }
    
    
    
    
    public boolean authUser( String userName, char[] password ){
        
        try{
            String query = "select * from user_accounts where username='" + userName + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            
            
            if( rs == null ){
                return false;
            }
            else if( rs.next() ){                
                //return (rs.getString("username").compareTo(userName) == 0) & (rs.getString("password").compareTo(password) == 0);
                //String str = rs.getString("password");
                String str = "";
                for( int i = 0; i < password.length; i++ ){
                    str += password[i];
                }
                
                return (rs.getString("username").compareTo(userName) == 0) && ((rs.getString("password").compareTo(str) == 0) );
            }
            else{
                return false;
            }
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            return false;
        }
        
    }
    
    
    
    
    
    
    public String addNewDrug( String name, String code, String batch, String exp, String pur, String manufacturer, int quantity, float price ){
        
        try{
            String query = "insert into drugs values( "+"'" + code + "',"
                                                        +"'" + name + "',"
                    +"'" + batch + "',"
                    +"'" + exp + "',"
                    +"'" + pur + "',"
                    +"" + price + ","
                    +"" + quantity + ","
                    +"'" + manufacturer + "')";

            Statement st = con.createStatement();
            st.executeUpdate( query );
            
            return "Success";
        }
        catch( Exception e ){
             System.out.println( e.getMessage() );
            return e.getMessage();
        }
        
    }

    
    
    
    
    
    public ResultSet searchDrugs( String searchString ){
        
        try{
            String query = "select * from drugs where item_name like '%" + searchString + "%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            //System.out.println( e.getStackTrace() );
            System.out.println( e.toString() );
            return null;
        }
    }
    
    
    
    //det details of drugs table
    public ResultSet getAll(){
        try{
            String query = "select * from drugs";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);            
            return rs;
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            System.out.println( e.getStackTrace() );
            System.out.println( e.toString() );
            return null;
        }
    }
    
    
    
    //delete drug records
    public boolean deleteRecord( String itemCode ){
        try{
            String query = "delete from drugs where item_code='" + itemCode + "'";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            return true;
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            return false;
        }
        
        
    }
    
    
    
    //update drug details
    public int updateDrug( String code, String name, String batch, String exp, String pur, float price, int quantity, String manufacturer ){
        try{
            //String query1 = "delete from drugs where item_code='" + code + "'";
            String query = "update drugs set " +
                    "item_code='" + code + "', " +
                    "item_name='" + name + "', " +
                    "batch_number='" + batch + "', " +
                    "exp_date='" + exp + "', " +
                    "purchase_date='" + pur + "'," +
                    "unit_price=" + price + "," +
                    "quantity=" + quantity + "," +
                    "manufacturer='" + manufacturer + "' " +
                    "where item_code='" + code + "'";
            
            Statement st = con.createStatement();
            int status = st.executeUpdate( query );
            
            return status;
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            return 0;
        }
    }
    
    
    
    
    
    //getting all the data about drugs
    public ResultSet getDrugData( String name ){
        try{
            String query = "select * from drugs where item_name='" + name + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;       
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            return null;
        }
    }
    
    
    
    
    //decrease quantity of a item
    public boolean reduceQuantity( String name, int quantity ){
        
        try{
            String query = "update drugs set quantity='" + quantity + "' where item_name='" + name + "'";
            Statement  st = con.createStatement();
            st.executeUpdate(query);
            return true;
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            return false;
        }
    }
    
    
    //increase quantity of a item
   public boolean increaseQuantity( String name, int quantity ){
       try{
            Statement st = con.createStatement();
            String getValue = "select * from drugs where item_name='" + name + "'";
            ResultSet rs = st.executeQuery( getValue );
            
            rs.next();
            int available = rs.getInt("quantity");
            quantity += available;
            String query = "update drugs set quantity='" + quantity + "' where item_name='" + name + "'";
            
            st.executeUpdate(query);
            return true;
        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            return false;
        }
   }
    
    
    
   
   
   //ading a user
   public boolean addUser( String user, String pass ){
       try{
            Statement st = con.createStatement();
            String query = "insert into user_accounts values('" + user + "', '" + pass + "')";
            st.executeUpdate(query);
            
            return true;
       }
       catch( Exception e ){
           System.out.println( e.getMessage() );
           return false;
       }
   }
    
   
   //remove a user
   public boolean removeUser( String user ){
       try{
            Statement st = con.createStatement();
            String query = "delete from user_accounts where username='" + user + "'";
            st.executeUpdate( query );
            return true;
       }
       catch( Exception e ){
           System.out.println( e.getMessage() );
           return true;
       }
   }
   
   
   
   //check there is a user named given string
   public boolean isUserThere( String user ){
       try{
        Statement st = con.createStatement();
        String quary = "select username from user_accounts";
        ResultSet rs = st.executeQuery(quary );
        while( rs.next() ){
            if( rs.getString("username").equals(user)  ){
                System.out.println( rs.getString("username").compareTo(user) == 0 );
                return true;
            }
        }
        return false;
       }
       catch( Exception e ){
           System.out.println( e.getMessage() );
           return false;
       }
   }
    
}










