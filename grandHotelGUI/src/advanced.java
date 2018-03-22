import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class advanced {

	private JFrame window = new JFrame("Warning");
	public Object[][] data;
	private String username;
	private String password;
	
	public advanced(int n, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		data = new Object[n][4];
		final int WINDOW_WIDTH = 350, WINDOW_HEIGHT = 200;
		window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		window.setLayout(null);
		username = user;
		password = pass;
	}
	
	public Object[][] query2(String stdrate) throws InstantiationException, 
					IllegalAccessException, ClassNotFoundException, SQLException
	{ 	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT DISTINCT g.guestID, g.fName, g.lName"+ 
												" FROM guest g, room r, books b"+ 
												" WHERE g.guestID = b.guestID"+ 
												" AND b.roomNum = r.roomNum"+ 		
												" AND r.stdRate"+stdrate+";"); 
		Object[][] data = new Object[500][3];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("g.guestID");
			data[count][1] = rs.getString("g.fName");
			data[count][2] = rs.getString("g.lName");
			count++;
		}
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		return data;
	}
	
	public Object[][] query3(String stdrate, String startdate, String enddate) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException
	{ 	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT DISTINCT r.roomNum, r.numBed, r.bedSize, r.stdRate"+ 
										" FROM room r, books b"+ 
										" WHERE r.roomNum = b.roomNum AND r.stdRate <"+stdrate+" AND startDate < '"+startdate+"' OR startDate > '"+enddate+"'"+ 	
										" ORDER BY r.stdRate DESC;"); 
		Object[][] data = new Object[500][4];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("r.roomNum");
			data[count][1] = rs.getString("r.numBed");
			data[count][2] = rs.getString("r.bedSize");
			data[count][3] = rs.getString("r.stdRate");
			count++;
		}
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		return data;
	}
	
	public Object[][] query4(String position) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
		Statement statement = connection.createStatement(); 
		ResultSet rs = statement.executeQuery("SELECT COUNT(empNo)"+ 
												" FROM employee"+ 
												" WHERE position = '"+position+"';"); 
		Object[][] data = new Object[100][1];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getInt(1);
			count++;
		}
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		return data;
	}

	public Object[][] query5(String startdate, String enddate, String num) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
		Statement statement = connection.createStatement(); 
		ResultSet rs = statement.executeQuery("SELECT r.roomNum, COUNT(*) as Services "+
												"FROM room r, service s "+
												"WHERE r.roomNum = s.roomNum "+ 
													"AND (dateRequested LIKE '"+startdate+"%' OR dateRequested LIKE '"+enddate+"%') "+ 
												"GROUP BY s.roomNum "+ 
												"HAVING COUNT(*) "+num+";"); 
		Object[][] data = new Object[100][2];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getInt("r.roomNum");
			data[count][1] = rs.getInt(2);
			count++;
		}
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		return data;
	}

	public Object[][] query6(String bed1, String bed2) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
		Statement statement = connection.createStatement(); 
		ResultSet rs = statement.executeQuery("SELECT DISTINCT g1.fName, g1.lName "+
												"FROM guest g1, books b1, room r1 "+
												"WHERE g1.guestID = b1.guestID "+
												"AND b1.roomNum = r1.roomNum "+
												"AND r1.bedSize = '"+bed1+"' "+
												"AND EXISTS "+
												"(SELECT * "+
												"FROM guest g2, books b2, room r2 "+
												"WHERE g2.guestID = b2.guestID "+
												"AND b2.roomNum = r2.roomNum "+
												"AND r2.bedSize = '"+bed2+"' "+
												"AND g1.fName = g2.fName "+
												"AND g1.lName = g2.lName);"); 
		Object[][] data = new Object[100][2];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("g1.fName");
			data[count][1] = rs.getString("g1.lName");
			count++;
		}
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		return data;
	}
}
