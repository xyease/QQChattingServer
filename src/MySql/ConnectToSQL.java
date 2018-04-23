package MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToSQL {
	private static Connection conn=null;
	//private Statement stmt;
	private static String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static String USR="QQCHAT";
	private static String PASS="965489000";
	private void LoadDriver() {//¼ÓÔØÇý¶¯³ÌÐò
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException ex) {
			System.out.println("ERROR: unable to load driver class");
			System.exit(1);
		}		
	}
	private void CreateLinker() {
		try {
		    conn=DriverManager.getConnection(URL,USR,PASS);
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("ERROR: unable to create linker");
			System.exit(1);
		}		
	}
	public ConnectToSQL() {
		LoadDriver();
		CreateLinker();
	}
	public Connection GetConn() {return conn;}
	public void ConnectClose ()throws SQLException{
		conn.close();
	}
	
}
