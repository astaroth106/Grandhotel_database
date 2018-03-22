import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class User {
	private JFrame window = new JFrame("Warning");
	public Object[][] data;
	public User(int n, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		data = new Object[n][5];
		final int WINDOW_WIDTH = 350, WINDOW_HEIGHT = 200;
		window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		window.setLayout(null);
	}
	public boolean getUser(String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel","root", "1234qwer");
		Statement statement = connection.createStatement();
	
			String query = "select empNo from User where empNo= '"+user+"' and password = '"+pass+"';";
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next())
				return true;
			else
			{
				JLabel messageLabel1 = new JLabel("Incorrect username or password");
				messageLabel1.setSize(200, 30);
				messageLabel1.setLocation(50, 50);
				window.add(messageLabel1);
				window.setVisible(true);
				return false;
			}	
	}
	public Object[][] getTable0() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel","root", "1234qwer");
		Statement statement = connection.createStatement();
		String query = "select * from User";
		ResultSet rs = statement.executeQuery(query);

		
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("empNo");
			data[count][1] = rs.getString("password");
			data[count][2] = rs.getString("position");
			data[count][3] = rs.getString("fName");
			data[count][4] = rs.getString("lName");
			count++;
		}
		connection.close();
		return data;
	}
	
	public Object[][] getTable1(String empNo, String password, String position, String fName, String lName) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		String str = null, str2 = null; 
		
		if(!empNo.equals(""))
		{
			str = "empNo"; str2 = empNo;
		}
		if(!password.equals(""))
		{
			str = "password"; str2 = password;
		}
		if(!position.equals(""))
		{	
			str = "position";str2 = position;
		}
		if(!fName.equals(""))
		{
			str = "fName";str2 = fName;
		}
		if(!lName.equals(""))
		{
			str = "lName";str2 = lName;
		}
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel","root", "1234qwer");
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from User where "+str +"= '"+str2+"'"); 
		Object[][] data = new Object[100][5];
		int count=0;
		while(rs.next())
		{
			data[count][0] = rs.getString("empNo");
			data[count][1] = rs.getString("password");
			data[count][2] = rs.getString("position");
			data[count][3] = rs.getString("fName");
			data[count][4] = rs.getString("lName");
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
	
	public void Update(String empNo, String position, String fName, String lName) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		if(empNo.equals(""))
		{
			JLabel messageLabel1 = new JLabel("Missing empNo");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel","root", "1234qwer");
		Statement statement = connection.createStatement();
		String query = "Update User Set ";

			if(!position.equals(""))
				query = query + "position= '"+position+"', ";
			if(!fName.equals(""))
				query = query + ",fName= '"+fName+"'";
			if(!lName.equals(""))
				query = query + ",lName= '"+lName+"'";
		
		query = query +"where empNo = '"+empNo+"';";
		System.out.print(query);
		statement.executeUpdate(query);
		
		connection.close();
	}
	
	public void Add(String empNo, String position, String password, String fName, 
			String lName) throws InstantiationException, IllegalAccessException, 
																ClassNotFoundException, SQLException
	{
		if(empNo.equals(""))
		{
			JLabel messageLabel1 = new JLabel("Missing data");
			messageLabel1.setSize(100, 30);
			messageLabel1.setLocation(50, 50);
			window.add(messageLabel1);
			window.setVisible(true);
		}
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel","root", "1234qwer");
		Statement statement = connection.createStatement();
		String query = "INSERT INTO User VALUES('"+empNo+"', '"+password+"', '"+position+"', '"+fName+"', '"+lName+"');";
		statement.executeUpdate(query);
		
		connection.close();	
	}
	public void Delete(String empNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel","root", "1234qwer");
		Statement statement = connection.createStatement();
		String query = "delete from User where empNo= '"+empNo+"'";
		statement.executeUpdate(query);
		
		connection.close();
	}

}
