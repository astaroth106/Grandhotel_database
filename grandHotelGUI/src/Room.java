	//Carlos A. Pena-Caballero, Hw6
	//April 25, 2015;

	import java.sql.*;

	import javax.swing.JFrame;
	import javax.swing.JLabel;

	public class Room {
		public Object[][] data;
		private JFrame window = new JFrame("Warning");
		private String username;
		private String password;
		
		public Room(int n, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			data = new Object[n][7];
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
			String query = "select * from room";
			ResultSet rs = statement.executeQuery(query);
			
			int count=0;
			while(rs.next())
			{
				data[count][0] = rs.getString("roomNum");
				data[count][1] = rs.getString("smoking");
				data[count][2] = rs.getString("handicap");
				data[count][3] = rs.getString("numBed");
				data[count][4] = rs.getString("bedSize");
				data[count][5] = rs.getString("roomStatus");
				data[count][6] = rs.getString("stdRate");
				count++;
			}
			connection.close();
			return data;
		}
		
		public Object[][] getTable1(String roomNum, String smoking, String handicap, String numBed, String bedSize, 
				String roomStatus, String stdRate) throws InstantiationException, 
						IllegalAccessException, ClassNotFoundException, SQLException
		{
			String str = null, str2 = null; 
			
			if(!roomNum.equals(""))
			{
				str = "roomNum"; str2 = roomNum;
			}
			if(!smoking.equals(""))
			{	
				str = "smoking";str2 = smoking;
			}
			if(!handicap.equals(""))
			{
				str = "handicap";str2 = handicap;
			}
			if(!roomStatus.equals(""))
			{
				str = "roomStatus";str2 = roomStatus;
			}
			if(!stdRate.equals(""))
			{	
				str = "stdRate";str2 = stdRate;
				
			}
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from room where "+str +"= '"+str2+"'"); 
			Object[][] data = new Object[200][7];
			int count=0;
			while(rs.next())
			{
				data[count][0] = rs.getString("roomNum");
				data[count][1] = rs.getString("smoking");
				data[count][2] = rs.getString("handicap");
				data[count][3] = rs.getString("numBed");
				data[count][4] = rs.getString("bedSize");
				data[count][5] = rs.getString("roomStatus");
				data[count][6] = rs.getString("stdRate");
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
		
		public void Update(String roomNum, String smoking, String handicap, String numBed, 
							String bedSize, String roomStatus, String stdRate) 
						throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			if(roomNum.equals(""))
			{
				JLabel messageLabel1 = new JLabel("Missing roomNum");
				messageLabel1.setSize(100, 30);
				messageLabel1.setLocation(50, 50);
				window.add(messageLabel1);
				window.setVisible(true);
			}
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "Update Room Set ";
				
				if(!roomStatus.equals(""))
					query = query + "roomStatus= '"+roomStatus+"', ";
				if(!smoking.equals(""))
					query = query + "smoking= '"+smoking+"', ";
				if(!handicap.equals(""))
					query = query + "handicap= '"+handicap+"', ";
				if(!numBed.equals(""))
					query = query + "numBed= '"+numBed+"', ";
				if(!bedSize.equals(""))
					query = query + "bedSize= '"+bedSize+"', ";
				if(!stdRate.equals(""))
					query = query + "stdRate= '"+stdRate+"' ";
			
			query = query +"where roomNum = '"+roomNum+"';";
			System.out.print(query);
			statement.executeUpdate(query);
			
			connection.close();
		}
		
		public void Add(String roomNum, String smoking, String handicap, String numBed, 
				String bedSize, String roomStatus, String stdRate) throws InstantiationException, IllegalAccessException, 
																	ClassNotFoundException, SQLException
		{
			if(roomNum.equals(""))
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
			String query = "INSERT INTO room VALUES('"+roomNum+"', '"+smoking+"', '"+handicap+"', '"+numBed+"', '"
														+bedSize+"', '"+roomStatus+"', '"+stdRate+"');";
			statement.executeUpdate(query);
			
			connection.close();	
		}
		public void Delete(String roomNum) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "delete from room where roomNum= '"+roomNum+"'";
			statement.executeUpdate(query);
			
			connection.close();
		}
	}


