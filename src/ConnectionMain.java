import java.sql.*;

import javax.swing.JOptionPane;
public class ConnectionMain {

	Connection conn = null;
	public static Connection dbConnector()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?characterEncoding=latin1", "root", "password");
			
		 return conn;
			
		}catch(Exception e)
		{
			//System.out.print("Exception:"+e);
			e.printStackTrace();
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	public static void main(String arg []) {
		ConnectionMain ojbConnectionMain = new ConnectionMain();
		ojbConnectionMain.dbConnector();
	}
}
