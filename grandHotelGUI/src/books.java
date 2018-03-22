	//Carlos A. Pena-Caballero, Hw6
	//April 25, 2015;

	import java.sql.*;

	import javax.swing.JFrame;
	import javax.swing.JLabel;

	public class books {
		public Object[][] data;
		private JFrame window = new JFrame("Warning");
		private String username;
		private String password;
		
		public books(int n, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
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
			String query = "select * from books";
			ResultSet rs = statement.executeQuery(query);
			
			int count=0;
			while(rs.next())
			{
				data[count][0] = rs.getString("empNo");
				data[count][1] = rs.getString("guestID");
				data[count][2] = rs.getString("roomNum");
				data[count][3] = rs.getString("startDate");
				data[count][4] = rs.getString("endDate");
				data[count][5] = rs.getString("paymentType");
				data[count][6] = rs.getString("cardNum");
				count++;
			}
			connection.close();
			return data;
		}
		
		public Object[][] getTable1(String empNo, String guestID, String roomNum, String startDate, String endDate, 
				String paymentType, String cardNum) throws InstantiationException, 
						IllegalAccessException, ClassNotFoundException, SQLException
		{
			String str = null, str2 = null; 
			
			if(!empNo.equals(""))
			{
				str = "empNo"; str2 = empNo;
			}
			if(!guestID.equals(""))
			{	
				str = "guestID";str2 = guestID;
			}
			if(!roomNum.equals(""))
			{
				str = "roomNum";str2 = roomNum;
			}
			if(!paymentType.equals(""))
			{
				str = "occStatus";str2 = paymentType;
			}
			if(!cardNum.equals(""))
			{	
				str = "cardNum";str2 = cardNum;
				
			}
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from books where "+str +"= '"+str2+"'"); 
			Object[][] data = new Object[200][7];
			int count=0;
			while(rs.next())
			{
				data[count][0] = rs.getString("empNo");
				data[count][1] = rs.getString("guestID");
				data[count][2] = rs.getString("roomNum");
				data[count][3] = rs.getString("startDate");
				data[count][4] = rs.getString("endDate");
				data[count][5] = rs.getString("paymentType");
				data[count][6] = rs.getString("cardNum");
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
		
		public void Update(String empNo, String guestID, String roomNum, String startDate, 
							String endDate, String paymentType, String cardNum) 
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
				
				if(!paymentType.equals(""))
					query = query + "paymentType= '"+paymentType+"', ";
				if(!guestID.equals(""))
					query = query + "guestID= '"+guestID+"', ";
				if(!roomNum.equals(""))
					query = query + "roomNum= '"+roomNum+"', ";
				if(!startDate.equals(""))
					query = query + "startDate= '"+startDate+"', ";
				if(!endDate.equals(""))
					query = query + "endDate= '"+endDate+"', ";
				if(!cardNum.equals(""))
					query = query + "cardNum= '"+cardNum+"' ";
			
			query = query +"where empNo = '"+empNo+"';";
			System.out.print(query);
			statement.executeUpdate(query);
			
			connection.close();
		}
		
		public void Add(String empNo, String guestID, String roomNum, String startDate, 
				String endDate, String paymentType, String cardNum) throws InstantiationException, IllegalAccessException, 
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
			String query = "INSERT INTO room VALUES('"+empNo+"', '"+guestID+"', '"+roomNum+"', '"+startDate+"', '"
														+endDate+"', '"+paymentType+"', '"+cardNum+"');";
			statement.executeUpdate(query);
			
			connection.close();	
		}
		public void Delete(String empNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrandHotel",username, password);
			Statement statement = connection.createStatement();
			String query = "delete from books where empNo= '"+empNo+"'";
			statement.executeUpdate(query);
			
			connection.close();
		}
	}


