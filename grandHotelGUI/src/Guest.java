
	//Carlos A. Pena-Caballero, Hw6
	//April 25, 2015;

	import java.sql.*;

	import javax.swing.JFrame;
	import javax.swing.JLabel;

	public class Guest {
		public Object[][] data;
		private JFrame window = new JFrame("Warning");
		private String username;
		private String password;
		
		public Guest(int n, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			data = new Object[n][9];
			final int WINDOW_WIDTH = 200, WINDOW_HEIGHT = 200;
			window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
			window.setLayout(null);
			username = user;
			password = pass;
		}
		public Object[][] getTable0() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "select * from Guest";
			ResultSet rs = statement.executeQuery(query);

			
			int count=0;
			while(rs.next())
			{
				data[count][0] = rs.getInt("guestID");
				data[count][1] = rs.getString("fName");
				data[count][2] = rs.getString("lName");
				data[count][3] = rs.getString("phNum");
				data[count][4] = rs.getString("homeAdds");
				data[count][5] = rs.getString("DL_Num");
				data[count][6] = rs.getString("emergencyName");
				data[count][7] = rs.getString("emergencyNum");
				data[count][8] = rs.getString("email");
				count++;
			}
			connection.close();
			return data;
		}
		
		public Object[][] getTable1(String guestID, String fName, String lName, String phNum, String homeAdds, 
				String DL_Num, String emergencyName, String emergencyNum, String email) throws InstantiationException, 
						IllegalAccessException, ClassNotFoundException, SQLException
		{
			String str = null, str2 = null; 
			
			if(!guestID.equals(""))
			{
				str = "guestID"; str2 = guestID;
			}
			if(!lName.equals(""))
			{	
				str = "lName";str2 = lName;
			}
			if(!phNum.equals(""))
			{
				str = "phNum";str2 = phNum;
			}
			if(!fName.equals(""))
			{
				str = "fName";str2 = fName;
			}
			if(!emergencyName.equals(""))
			{	
				str = "emergencyName";str2 = emergencyName;
				
			}
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from Guest where "+str +"= '"+str2+"'"); 
			Object[][] data = new Object[200][9];
			int count=0;
			while(rs.next())
			{
				data[count][0] = rs.getInt("guestID");
				data[count][1] = rs.getString("fName");
				data[count][2] = rs.getString("lName");
				data[count][3] = rs.getString("phNum");
				data[count][4] = rs.getString("homeAdds");
				data[count][5] = rs.getString("DL_Num");
				data[count][6] = rs.getString("emergencyName");
				data[count][7] = rs.getString("emergencyNum");
				data[count][8] = rs.getString("email");
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
		
		public void Update(String guestID, String fName, String lName, String phNum, String homeAdds, String DL_Num, String emergencyName, String emergencyNum, String email) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			if(guestID.equals(""))
			{
				JLabel messageLabel1 = new JLabel("Missing guestID");
				messageLabel1.setSize(100, 30);
				messageLabel1.setLocation(50, 50);
				window.add(messageLabel1);
				window.setVisible(true);
			}
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "Update Guest Set ";
				
				if(!fName.equals(""))
					query = query + "fName= '"+fName+"', ";
				if(!lName.equals(""))
					query = query + "lName= '"+lName+"', ";
				if(!phNum.equals(""))
					query = query + "phNum= '"+phNum+"', ";
				if(!homeAdds.equals(""))
					query = query + "homeAdds= '"+homeAdds+"', ";
				if(!DL_Num.equals(""))
					query = query + "DL_Num= '"+DL_Num+"', ";
				if(!emergencyName.equals(""))
					query = query + "emergencyName= '"+emergencyName+"' ";
			
			query = query +"where guestID = '"+guestID+"';";
			System.out.print(query);
			statement.executeUpdate(query);
			
			connection.close();
		}
		
		public void Add(String guestID, String fName, String lName, String phNum, String homeAdds, 
				String DL_Num, String emergencyName, String emergencyNum, String email ) throws InstantiationException, IllegalAccessException, 
																	ClassNotFoundException, SQLException
		{
			if(guestID.equals(""))
			{
				JLabel messageLabel1 = new JLabel("Missing data");
				messageLabel1.setSize(100, 30);
				messageLabel1.setLocation(50, 50);
				window.add(messageLabel1);
				window.setVisible(true);
			}
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "INSERT INTO Guest VALUES('"+guestID+"', '"+fName+"', '"+lName+"', '"+phNum+"', '"+homeAdds+"', '"
														+DL_Num+"', '"+emergencyName+"', '"+emergencyNum+"', '"+email+"');";
			statement.executeUpdate(query);
			
			connection.close();	
		}
		public void Delete(String guestID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "delete from Guest where guestID= '"+guestID+"'";
			statement.executeUpdate(query);
			
			connection.close();
		}
	}


