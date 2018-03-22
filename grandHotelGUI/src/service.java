	//Carlos A. Pena-Caballero, Hw6
	//April 25, 2015;

	import java.sql.*;

	import javax.swing.JFrame;
	import javax.swing.JLabel;

	public class service {
		public Object[][] data;
		private JFrame window = new JFrame("Warning");
		private String username;
		private String password;
		
		public service(int n, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
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
			String query = "select * from service";
			ResultSet rs = statement.executeQuery(query);
			
			int count=0;
			while(rs.next())
			{
				data[count][0] = rs.getString("empNo");
				data[count][1] = rs.getString("roomNum");
				data[count][2] = rs.getString("requestType");
				data[count][3] = rs.getString("dateRequested");
				data[count][4] = rs.getString("dateCompleted");
				data[count][5] = rs.getString("priorityLevel");
				data[count][6] = rs.getString("notes");
				count++;
			}
			connection.close();
			return data;
		}
		
		public Object[][] getTable1(String empNo, String roomNum, String requestType, String dateRequested, String dateCompleted, 
				String priorityLevel, String notes) throws InstantiationException, 
						IllegalAccessException, ClassNotFoundException, SQLException
		{
			String str = null, str2 = null; 
			
			if(!empNo.equals(""))
			{
				str = "empNo"; str2 = empNo;
			}
			if(!roomNum.equals(""))
			{	
				str = "roomNum";str2 = roomNum;
			}
			if(!requestType.equals(""))
			{
				str = "requestType";str2 = requestType;
			}
			if(!priorityLevel.equals(""))
			{
				str = "occStatus";str2 = priorityLevel;
			}
			if(!notes.equals(""))
			{	
				str = "notes";str2 = notes;
				
			}
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from service where "+str +"= '"+str2+"'"); 
			Object[][] data = new Object[200][7];
			int count=0;
			while(rs.next())
			{
				data[count][0] = rs.getString("empNo");
				data[count][1] = rs.getString("roomNum");
				data[count][2] = rs.getString("requestType");
				data[count][3] = rs.getString("dateRequested");
				data[count][4] = rs.getString("dateCompleted");
				data[count][5] = rs.getString("priorityLevel");
				data[count][6] = rs.getString("notes");
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
		
		public void Update(String empNo, String roomNum, String requestType, String dateRequested, 
							String dateCompleted, String priorityLevel, String notes) 
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
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "Update Room Set ";
				
				if(!priorityLevel.equals(""))
					query = query + "priorityLevel= '"+priorityLevel+"', ";
				if(!roomNum.equals(""))
					query = query + "roomNum= '"+roomNum+"', ";
				if(!requestType.equals(""))
					query = query + "requestType= '"+requestType+"', ";
				if(!dateRequested.equals(""))
					query = query + "dateRequested= '"+dateRequested+"', ";
				if(!dateCompleted.equals(""))
					query = query + "dateCompleted= '"+dateCompleted+"', ";
				if(!notes.equals(""))
					query = query + "notes= '"+notes+"' ";
			
			query = query +"where empNo = '"+empNo+"';";
			System.out.print(query);
			statement.executeUpdate(query);
			
			connection.close();
		}
		
		public void Add(String empNo, String roomNum, String requestType, String dateRequested, 
				String dateCompleted, String priorityLevel, String notes) throws InstantiationException, IllegalAccessException, 
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
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "INSERT INTO room VALUES('"+empNo+"', '"+roomNum+"', '"+requestType+"', '"+dateRequested+"', '"
														+dateCompleted+"', '"+priorityLevel+"', '"+notes+"');";
			statement.executeUpdate(query);
			
			connection.close();	
		}
		public void Delete(String empNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "delete from service where empNo= '"+empNo+"'";
			statement.executeUpdate(query);
			
			connection.close();
		}
	}


