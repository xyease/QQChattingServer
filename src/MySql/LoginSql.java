package MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CommonClass.Datagram;

public class LoginSql {
     public static int Login_Sql(Datagram user) {
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
 		    if(!rs.next()) {connect.ConnectClose();return 0;}//�Ƿ��û���
 		    else {
 		    	String p=rs.getString(2);
 		    	if(p.equals(pass)) {connect.ConnectClose();return 1;}//������ȷ
 		    	else {connect.ConnectClose();return -1;}//�������
 		    }
 		    
 		}catch(SQLException ex) {
 			ex.printStackTrace();
 			System.out.println("ERROR: unable to Query");
 			//conn.close();
 			return -2;//�쳣
 		}
 		
     }
}
