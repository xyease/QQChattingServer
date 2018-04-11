package MySql;
//import java.util.*;
//import oracle.jdbc.driver.OracleDriver;
import java.sql.*;
import CommonClass.Usr;
//import java.math.*;
public class RegisterSql {
	private static Connection conn=null;
	//private Statement stmt;
	private static String URL;
	private static String USR;
	private static String PASS;
	static void LoadDriver() {//加载驱动程序
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException ex) {
			System.out.println("ERROR: unable to load driver class");
			System.exit(1);
		}		
	}
	static void CreateLinker() {
		URL="jdbc:oracle:thin:@localhost:1521:XE";
		USR="QQCHAT";
		PASS="965489000";
		try {
		    conn=DriverManager.getConnection(URL,USR,PASS);
		    /*
            stmt=conn.createStatement();
            String sql1="INSERT INTO REGISTER "+"VALUES('xy','111')";
            stmt.executeUpdate(sql1);
            
            String sql2="SELECT * FROM REGISTER";
            ResultSet rs=stmt.executeQuery(sql2);
            while(rs.next()) {
            	String usrname=rs.getString("USR_NAME");
            	String password=rs.getString("PASSWORD");
            	System.out.println("USRNAME"+usrname);
            	System.out.println("PASSWORD"+password);
            	
            }
            rs.close();
            */
            
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("ERROR: unable to create linker");
			System.exit(1);
		}		
	}
	public static int RegisterVerify(Usr user) {
		//-1 注册失败 1 注册成功 0 用户名存在
		String name=user.GetName();
		String pass=user.GetPass();
		System.out.println(name+pass);
		String sql="SELECT * FROM REGISTER WHERE USR_NAME = ?";
		LoadDriver();
		CreateLinker();
		
		try {
		    PreparedStatement pre=conn.prepareStatement(sql);
		    System.out.println(1);
		    pre.setString(1, name);
		    System.out.println(2);
		    ResultSet rs=pre.executeQuery();
		    System.out.println(3);
		    
		    if(!rs.next()) {
		    	System.out.println(6);
		    	String sql1="INSERT INTO REGISTER "+"VALUES(?,?)";
		    	PreparedStatement pre1=conn.prepareStatement(sql1);
		    	pre1.setString(1, name);
		    	pre1.setString(2, pass);
		    	System.out.println(4);
		    	pre1.executeUpdate();
		    	System.out.println(5);
		    	conn.close();
		    	return 1;
		   }
		    else {
		    	System.out.println(7);
		    	conn.close();
		    	return 0;
		    }
		    
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("ERROR: unable to query");
			//conn.close();
			return -1;
		}
		
		
	}
/*	
	public static void main(String[] args) {
		Register test=new Register();
		test.LoadDriver();
		test.CreateLinker();
		try{
			test.
		}catch(SQLException ex){
			System.out.println("ERROR: unable to close linker");
			System.exit(1);			
		}
	}
*/
}
