package grandHotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Queries {

	private JFrame window = new JFrame("Warning");
	public String[][] data;
	private String username;
	private String password;
	
	public Queries(int n, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		data = new String[n][4];
		final int WINDOW_WIDTH = 350, WINDOW_HEIGHT = 200;
		window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		window.setLayout(null);
		username = user;
		password = pass;
	}
	
	public void query1(String position) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException
	{ 	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * "+
												"FROM employee "+ 
												"WHERE position = '"+position+"';"); 
		String[][] data = new String[500][3];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("empNo");
			System.out.println("\nempNo = "+data[count][0]);
			data[count][1] = rs.getString("fName");
			System.out.println("Employee fName = "+data[count][1]);
			data[count][2] = rs.getString("lName");
			System.out.println("Employee lName = "+data[count][2]+"\n");
			count++;
		}
		System.out.println("Number of tuples: "+count+"\n");
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		
	}

	
	public void query2(String stdrate) throws InstantiationException, 
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
		String[][] data = new String[500][3];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("g.guestID");
			System.out.println("\nGuestID = "+data[count][0]);
			data[count][1] = rs.getString("g.fName");
			System.out.println("Guest fName = "+data[count][1]);
			data[count][2] = rs.getString("g.lName");
			System.out.println("Guest lName = "+data[count][2]+"\n");
			count++;
		}
		System.out.println("Number of tuples: "+count+"\n");
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		
	}
	
	public void query3(String stdrate, String startdate, String enddate) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException
	{ 	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT DISTINCT r.roomNum, r.numBed, r.bedSize, r.stdRate"+ 
										" FROM room r, books b"+ 
										" WHERE r.roomNum = b.roomNum AND r.stdRate "+stdrate+" AND startDate < '"+startdate+"' OR startDate > '"+enddate+"'"+ 	
										" ORDER BY r.stdRate DESC;"); 
		String[][] data = new String[500][4];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("r.roomNum");
			System.out.println("\nroomNum = "+data[count][0]);
			data[count][1] = rs.getString("r.numBed");
			System.out.println("numBeds = "+data[count][1]);
			data[count][2] = rs.getString("r.bedSize");
			System.out.println("bedSize = "+data[count][2]);
			data[count][3] = rs.getString("r.stdRate");
			System.out.println("stdRate = "+data[count][3]+"\n");
			count++;
		}
		System.out.println("Number of tuples: "+count+"\n");
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		
	}
	
	public void query4(String position) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
		Statement statement = connection.createStatement(); 
		ResultSet rs = statement.executeQuery("SELECT COUNT(empNo)"+ 
												" FROM employee"+ 
												" WHERE position = '"+position+"';"); 
		String[][] data = new String[100][1];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString(1);
			System.out.println("\n"+position+"'s count = "+data[count][0]+"\n");
			count++;
		}
		System.out.println("Number of tuples: "+count+"\n");
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		
	}

	public void query5(String startdate, String enddate, String num) throws InstantiationException, 
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
		String[][] data = new String[100][2];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("r.roomNum");
			System.out.println("\nroomNum = "+data[count][0]);
			data[count][1] = rs.getString(2);
			System.out.println("Services' count = "+data[count][1]+"\n");
			count++;
		}
		System.out.println("Number of tuples: "+count+"\n");
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		
	}

	public void query6(String bed1, String bed2) throws InstantiationException, 
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
		String[][] data = new String[100][2];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("g1.fName");
			System.out.println("\nGuest fName = "+data[count][0]);
			data[count][1] = rs.getString("g1.lName");
			System.out.println("Guest lName = "+data[count][1]+"\n");
			count++;
		}
		System.out.println("Number of tuples: "+count+"\n");
		if(count == 0)
		{
			JLabel messageLabel1 = new JLabel("No Results found");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		connection.close();
		
	}
}
