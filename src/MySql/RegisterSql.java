package MySql;
//import java.util.*;
//import oracle.jdbc.driver.OracleDriver;
import java.sql.*;
import CommonClass.Usr;
//import java.math.*;
public class RegisterSql {

	
	public static int RegisterVerify(Usr user) {
		//-1 注册失败 1 注册成功 0 用户名存在
		String name=user.GetName();
		String pass=user.GetPass();
		System.out.println(name+pass);
		String sql="SELECT * FROM REGISTER WHERE USR_NAME = ?";
		ConnectToSQL connect=new ConnectToSQL();
		Connection conn=connect.GetConn();
		try {
		    PreparedStatement pre=conn.prepareStatement(sql);
		    pre.setString(1, name);
		    ResultSet rs=pre.executeQuery();		    
		    if(!rs.next()) {
		    	String sql1="INSERT INTO REGISTER "+"VALUES(?,?)";
		    	PreparedStatement pre1=conn.prepareStatement(sql1);
		    	pre1.setString(1, name);
		    	pre1.setString(2, pass);
		    	pre1.executeUpdate();
		    	connect.ConnectClose();
		    	return 1;
		   }
		    else {
		    	System.out.println(7);
		    	connect.ConnectClose();
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
