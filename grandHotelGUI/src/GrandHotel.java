//Carlos A. Pena-Caballero
// 2015;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.SQLException;

import java.awt.*;

public class GrandHotel extends JFrame
{
	private static final long serialVersionUID = 6515560833958039985L;
	private JFrame login;       // Login JFrame to insert a username and a password in order to access their database
	private JFrame advanced = new JFrame("Advanced Search");
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;			//Different label for the grandHotel database's tables 
	private JLabel label4;
	private JLabel label5;            
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;
	private JLabel label11;
	private JLabel label12;
	private JLabel greet;			// An initial greet to the database user
	private JLabel greet1;			// WELCOME TO THE GRANDHOTEL DATABASE
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;		// Different textFields to insert information into the tables
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;
	private JTextField textField8;
	//private JButton box2;
	//private JCheckBox box1;
	private JPasswordField textField9;
	private JTextField textField10;
	private JTextField textField11;
	private JTextField textField12;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;			// Each button is assigned to one actionListener
	private JButton button6;			// depending on which table is active at the time
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button10;
	private JButton button11;
	private JButton button12;
	private JButton button13;
	////////////////////////////////////////////////////
	//  Column names to the default table mode
	private String[] columnNames = {"Room Number", "Smoking","Handicap","Number of Beds","Bed Size","Room Status","Rate"};
	private String[] empColumns = {"Employee Number", "First Name", "Last Name", "Position"};
	private String[] guestColumns = {"guestId", "fName", "lName", "phoneNum", "homeAddress", "dlNum", "emergencyName", "emergencyPhone", "email"};
	private String[] booksColumns = {"Employee", "GuestID","Room Number","Starting Date","End Date","Payment Type","Card Number"};
	private String[] serviceColumns = {"Employee", "Room Number","Request","Resquest Date","Completion Date","Priority","Notes"};
	////////////////////////////////////////////////////
	private JTable table; // a JTable for every table needed in the database
	private DefaultTableModel dm; // a default table model changing depending on the table active
	private JScrollPane scroll;		// a scroll system to navigate through the data in the table
	/////////////////////////////////////////////////////////
	//  Each table has its own class and set of SEARCH, UPDATE, ADD, and DELETE functions
	//  for the default mode of  the table
	private Room ci = new Room(1, null, null);	
	private Employee us = new Employee(1, null, null);
	private Guest gs = new Guest(1, null, null);
	private service se = new service(1, null, null);
	private books bk = new books(1, null, null);
	private advanced ad = new advanced(1, null, null);
	/////////////////////////////////////////////////////////
	//////////////////////////////////////////////////
	//  Strings that will store the user information
	//  to access their own database view
	private String user;
	private String pass;
	//////////////////////////////////////////////////
	public GrandHotel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException
	{
		super("GrandHotel");		// Name on top of the JTable
		setSize(1500,610);			// Size of the JTable window
		// Terminate the program when JTable is closed with the top left cross button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);	// Set the layout to null so it'll be empty and ready to be filled
		
		login = new JFrame("Login"); // Initialize the login procedure
		login.setSize(350, 240);	// Size of the login JFrame
		// Terminate program if the login window is closed without login in
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		login.setLayout(null); // Set the layout to null so it'll be empty and ready to be filled
		
		/////////////////////////////////////////////////////////
		// Login JFrame contents
		label8 = new JLabel("Username");
		label8.setSize(100, 30);
		label8.setLocation(120, 40);
		login.add(label8);		
		textField8 = new JTextField();
		textField8.setSize(100, 30);
		textField8.setLocation(120, 20);
		login.add(textField8);
		
		label9 = new JLabel("Password");
		label9.setSize(100, 30);
		label9.setLocation(120, 100);
		login.add(label9);		
		textField9 = new JPasswordField();
		textField9.setSize(100, 30);
		textField9.setLocation(120, 80);
		login.add(textField9);
		
		button5 = new JButton("Login");
		button5.setSize(100, 30);
		button5.setLocation(120, 140);
		button5.addActionListener(new Login());
		login.add(button5);
		login.setVisible(true);
		/////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////
		//  Initialize buttons on top of the table to switch among the different 
		//  tables available in the database
		button7 = new JButton("Employees");
		button7.setSize(150, 30);
		button7.setLocation(600, 10);
		button7.addActionListener(new EmployeeTable());
		add(button7);
		
		button8 = new JButton("Rooms");
		button8.setSize(150, 30);
		button8.setLocation(430, 10);
		button8.addActionListener(new RoomTable());
		add(button8);
		
		button9 = new JButton("Guests");
		button9.setSize(150, 30);
		button9.setLocation(260, 10);
		button9.addActionListener(new GuestTable());
		add(button9);
		
		button10 = new JButton("Service");
		button10.setSize(150, 30);
		button10.setLocation(770, 10);
		button10.addActionListener(new ServiceTable());
		add(button10);
		
		button11 = new JButton("Bookings");
		button11.setSize(150, 30);
		button11.setLocation(940, 10);
		button11.addActionListener(new BooksTable());
		add(button11);
		/////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////////////////////////////
		//  Initialize all button, labels, and text field to avoid nullPointerExceptions' error
		table = new JTable();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		label9 = new JLabel();
		label10 = new JLabel();
		label11 = new JLabel();
		label12 = new JLabel();
		greet = new JLabel();
		greet1 = new JLabel();
		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		textField4 = new JTextField();
		textField5 = new JTextField();
		textField6 = new JTextField();
		textField7 = new JTextField();
		textField10 = new JTextField();
		textField11 = new JTextField();
		textField12 = new JTextField();
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button6 = new JButton();
		button12 = new JButton();
		button13 = new JButton();
		scroll = new JScrollPane();
		//////////////////////////////////////////////////////////////////////////////////
	}
	// Contents of the room table that will be activated when the "Rooms" button is clicked
	private class RoomTable implements ActionListener

	{
		public void actionPerformed(ActionEvent arg0)
		{
			// Remove the initial greet from the JTable
			remove(greet);
			remove(greet1);
			// Set visible to false so the JTable can switch and successfully remove all the unnecessary 
			// elements of the JTable and initialize the SEARCH, UPDATE, ADD, and DELETE buttons to their 
			// proper table in their database
			setVisible(false);
		
			try {
				dm = new DefaultTableModel(ci.getTable0(), columnNames);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NullPointerException | SQLException e) {
				e.printStackTrace();
			}
			remove(scroll);
		    table.setModel(dm);
			scroll = new JScrollPane(table);
			scroll.setSize(1290, 270);
			scroll.setLocation(50, 50);
			add(scroll);
			
			remove(label1);
			remove(textField1);
			label1 = new JLabel("RoomNum");
			label1.setSize(100, 30);
			label1.setLocation(50, 400);
			add(label1);		
			textField1 = new JTextField();
			textField1.setSize(100, 30);
			textField1.setLocation(140, 400);
			add(textField1);
			
			remove(label2);
			remove(textField2);
			label2 = new JLabel("RoomStatus");
			label2.setSize(100, 30);
			label2.setLocation(50, 450);
			add(label2);		
			textField2 = new JTextField();
			textField2.setSize(100, 30);
			textField2.setLocation(140, 450);
			add(textField2);
			
			remove(label3);
			remove(textField3);
			label3 = new JLabel("Smoking");
			label3.setSize(100, 30);
			label3.setLocation(250, 450);
			add(label3);		
			textField3 = new JTextField();
			textField3.setSize(100, 30);
			textField3.setLocation(340, 450);
			add(textField3);		
			
			remove(label4);
			remove(textField4);
			label4 = new JLabel("Handicap");
			label4.setSize(100, 30);
			label4.setLocation(450, 450);
			add(label4);		
			textField4 = new JTextField();
			textField4.setSize(100, 30);
			textField4.setLocation(540, 450);
			add(textField4);		
			
			remove(label5);
			remove(textField5);
			label5 = new JLabel("numBeds");
			label5.setSize(100, 30);
			label5.setLocation(50, 500);
			add(label5);		
			textField5 = new JTextField();
			textField5.setSize(100, 30);
			textField5.setLocation(140, 500);
			add(textField5);
			
			remove(label6);
			remove(textField6);
			label6 = new JLabel("bedSize");
			label6.setSize(100, 30);
			label6.setLocation(250, 500);
			add(label6);		
			textField6 = new JTextField();
			textField6.setSize(100, 30);
			textField6.setLocation(340, 500);
			add(textField6);		
			
			remove(label7);
			remove(textField7);
			label7 = new JLabel("stdRate");
			label7.setSize(100, 30);
			label7.setLocation(450, 500);
			add(label7);		
			textField7 = new JTextField();
			textField7.setSize(100, 30);
			textField7.setLocation(540, 500);
			add(textField7);
			
			remove(button1);
			button1 = new JButton("Search");
			button1.setSize(100, 30);
			button1.setLocation(50, 350);
			button1.addActionListener(new SearchRoom());
			add(button1);
			
			remove(button2);
			button2 = new JButton("Delete");
			button2.setSize(100, 30);
			button2.setLocation(200, 350);
			button2.addActionListener(new DeleteRoom());
			add(button2);
			
			remove(button3);
			button3 = new JButton("Update");
			button3.setSize(100, 30);
			button3.setLocation(350, 350);
			button3.addActionListener(new UpdateRoom());
			add(button3);
			
			remove(button4);
			button4 = new JButton("Add");
			button4.setSize(100, 30);
			button4.setLocation(500, 350);
			button4.addActionListener(new AddRoom());
			add(button4);
			
			remove(button6);
			button6 = new JButton("Clear");
			button6.setSize(100, 30);
			button6.setLocation(650, 350);
			button6.addActionListener(new ClearRoom());
			add(button6);
			
			remove(button12);
			button12 = new JButton("Advanced Options");
			button12.setSize(220, 30);
			button12.setLocation(800, 350);
			button12.addActionListener(new Advanced());
			add(button12);
			
			setVisible(true);
		}
	}
	private class SearchRoom implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null;
			String str2 = null;
			try 
			{
				
				str = textField1.getText(); 				
				str2 = textField2.getText();			
				str3 = textField3.getText();				
				str4 = textField4.getText();
				str5 = textField5.getText();				
				str6 = textField6.getText();
				str7 = textField7.getText();
				dm.setDataVector(ci.getTable1(str, str2, str3, str4, str5, str6, str7), columnNames);
				dm.fireTableDataChanged();
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
		
	}
	private class DeleteRoom implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = "100";
			try 
			{
				str = textField1.getText();
				ci.Delete(str);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}	
	private class UpdateRoom implements ActionListener //
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str1 = null, str2 = null, str3 = null, str7 = null;
			String str4 = null, str5 = null, str6 = null; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText(); str5= textField5.getText(); str6= textField6.getText(); 
				str7= textField7.getText(); 
				ci.Update(str1, str2, str3, str4, str5, str6, str7);
				dm = new DefaultTableModel(ci.getTable0(), columnNames);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	}
	private class AddRoom implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
			String str1 = null, str3 = null, str7 = null;
			String str4 = null, str5 = null, str6 = null;
			String str2 = null; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText(); str5= textField5.getText(); str6= textField6.getText(); 
				str7= textField7.getText(); 
				ci.Add(str1, str2, str3, str4, str5, str6, str7);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}	
	private class ClearRoom implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				textField5.setText("");
				textField6.setText("");
				textField7.setText("");
				dm = new DefaultTableModel(ci.getTable0(), columnNames);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	private class EmployeeTable implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			remove(greet);
			remove(greet1);
			setVisible(false);
			
			remove(scroll);
			
			try {
				dm = new DefaultTableModel(us.getTable0(user, pass), empColumns);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NullPointerException | SQLException e) {
				e.printStackTrace();
			}
			table.setModel(dm);
			scroll = new JScrollPane(table);
			scroll.setSize(1290, 270);
			scroll.setLocation(50, 50);
			add(scroll);
			
			remove(label1);
			remove(textField1);
			label1 = new JLabel("empNo");
			label1.setSize(100, 30);
			label1.setLocation(50, 400);
			add(label1);		
			textField1 = new JTextField();
			textField1.setSize(100, 30);
			textField1.setLocation(120, 400);
			add(textField1);
			
			remove(label2);
			remove(textField2);
			label2 = new JLabel("fName");
			label2.setSize(100, 30);
			label2.setLocation(50, 450);
			add(label2);				
			textField2 = new JTextField();
			textField2.setSize(100, 30);
			textField2.setLocation(120, 450);
			add(textField2);
			
			remove(label3);
			remove(textField3);
			label3 = new JLabel("lName");
			label3.setSize(100, 30);
			label3.setLocation(250, 450);
			add(label3);		
			textField3 = new JTextField();
			textField3.setSize(100, 30);
			textField3.setLocation(320, 450);
			add(textField3);		
			
			remove(label4);
			remove(textField4);
			label4 = new JLabel("position");
			label4.setSize(100, 30);
			label4.setLocation(450, 450);
			add(label4);		
			textField4 = new JTextField();
			textField4.setSize(100, 30);
			textField4.setLocation(520, 450);
			add(textField4);		
			
			remove(label5);
			remove(textField5);
			
			remove(label6);
			remove(textField6);		
			
			remove(label7);
			remove(textField7);
			
			remove(button1);
			button1 = new JButton("Search");
			button1.setSize(100, 30);
			button1.setLocation(50, 350);
			button1.addActionListener(new SearchEmployee());
			add(button1);
			
			remove(button2);
			button2 = new JButton("Delete");
			button2.setSize(100, 30);
			button2.setLocation(200, 350);
			button2.addActionListener(new DeleteEmployee());
			add(button2);
			
			remove(button3);
			button3 = new JButton("Update");
			button3.setSize(100, 30);
			button3.setLocation(350, 350);
			button3.addActionListener(new UpdateEmployee());
			add(button3);
			
			remove(button4);
			button4 = new JButton("Add");
			button4.setSize(100, 30);
			button4.setLocation(500, 350);
			button4.addActionListener(new AddEmployee());
			add(button4);
			
			remove(button6);
			button6 = new JButton("Clear");
			button6.setSize(100, 30);
			button6.setLocation(650, 350);
			button6.addActionListener(new ClearEmployee());
			add(button6);
			
			remove(button12);
			button12 = new JButton("Advanced Options");
			button12.setSize(220, 30);
			button12.setLocation(800, 350);
			button12.addActionListener(new Advanced());
			add(button12);
			
			setVisible(true);
		}
	}
	private class SearchEmployee implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = null, str3 = null, str4 = null, str2 = null;
			try 
			{
				
				str = textField1.getText(); 				
				str2 = textField2.getText();			
				str3 = textField3.getText();				
				str4 = textField4.getText();			
				dm.setDataVector(us.getTable1(str, str2, str3, str4), empColumns);
				dm.fireTableDataChanged();
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
		
	}
	private class DeleteEmployee implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = "100";
			try 
			{
				str = textField1.getText();
				us.Delete(str);
				dm = new DefaultTableModel(us.getTable0(user, pass), empColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}
	private class UpdateEmployee implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str1 = null, str2 = null, str3 = null;
			String str4 = null;
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText();
				us.Update(str1, str2, str3, str4);
				dm = new DefaultTableModel(us.getTable0(user, pass), empColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	}
	private class AddEmployee implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
			String str1 = null, str3 = null;
			String str4 = null;
			String str2 = null; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText();
				System.out.println(str2);
				us.Add(str1, str2, str3, str4);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}	
	private class ClearEmployee implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				textField5.setText("");
				textField6.setText("");
				textField7.setText("");
				dm = new DefaultTableModel(us.getTable0(user, pass), empColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	private class GuestTable implements ActionListener

	{
		public void actionPerformed(ActionEvent arg0)
		{
			remove(greet);
			remove(greet1);
			setVisible(false);
			
			remove(scroll);
			try {
				dm = new DefaultTableModel(gs.getTable0(), guestColumns);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NullPointerException | SQLException e) {
				e.printStackTrace();
			}
		    table.setModel(dm);
			scroll = new JScrollPane(table);
			scroll.setSize(1290, 270);
			scroll.setLocation(50, 50);
			add(scroll);
			
			remove(label1);
			remove(textField1);
			label1 = new JLabel("guestId");
			label1.setSize(100, 30);
			label1.setLocation(50, 400);
			add(label1);		
			textField1 = new JTextField();
			textField1.setSize(100, 30);
			textField1.setLocation(140, 400);
			add(textField1);
			
			remove(label2);
			remove(textField2);
			label2 = new JLabel("fname");
			label2.setSize(100, 30);
			label2.setLocation(50, 450);
			add(label2);		
			textField2 = new JTextField();
			textField2.setSize(100, 30);
			textField2.setLocation(140, 450);
			add(textField2);
			
			remove(label3);
			remove(textField3);
			label3 = new JLabel("lName");
			label3.setSize(100, 30);
			label3.setLocation(250, 450);
			add(label3);		
			textField3 = new JTextField();
			textField3.setSize(100, 30);
			textField3.setLocation(340, 450);
			add(textField3);		
			
			remove(label4);
			remove(textField4);
			label4 = new JLabel("phoneNum");
			label4.setSize(100, 30);
			label4.setLocation(450, 450);
			add(label4);		
			textField4 = new JTextField();
			textField4.setSize(100, 30);
			textField4.setLocation(540, 450);
			add(textField4);		
			
			remove(label5);
			remove(textField5);
			label5 = new JLabel("homeAddress");
			label5.setSize(100, 30);
			label5.setLocation(50, 500);
			add(label5);		
			textField5 = new JTextField();
			textField5.setSize(100, 30);
			textField5.setLocation(140, 500);
			add(textField5);
			
			remove(label6);
			remove(textField6);
			label6 = new JLabel("dlNum");
			label6.setSize(100, 30);
			label6.setLocation(250, 500);
			add(label6);		
			textField6 = new JTextField();
			textField6.setSize(100, 30);
			textField6.setLocation(340, 500);
			add(textField6);		
			
			remove(label7);
			remove(textField7);
			label7 = new JLabel("emergencyName");
			label7.setSize(100, 30);
			label7.setLocation(450, 500);
			add(label7);		
			textField7 = new JTextField();
			textField7.setSize(100, 30);
			textField7.setLocation(540, 500);
			add(textField7);
			
			remove(button1);
			button1 = new JButton("Search");
			button1.setSize(100, 30);
			button1.setLocation(50, 350);
			button1.addActionListener(new SearchGuest());
			add(button1);
			
			remove(button2);
			button2 = new JButton("Delete");
			button2.setSize(100, 30);
			button2.setLocation(200, 350);
			button2.addActionListener(new DeleteGuest());
			add(button2);
			
			remove(button3);
			button3 = new JButton("Update");
			button3.setSize(100, 30);
			button3.setLocation(350, 350);
			button3.addActionListener(new UpdateGuest());
			add(button3);
			
			remove(button4);
			button4 = new JButton("Add");
			button4.setSize(100, 30);
			button4.setLocation(500, 350);
			button4.addActionListener(new AddGuest());
			add(button4);
			
			remove(button6);
			button6 = new JButton("Clear");
			button6.setSize(100, 30);
			button6.setLocation(650, 350);
			button6.addActionListener(new ClearGuest());
			add(button6);
			
			remove(button12);
			button12 = new JButton("Advanced Options");
			button12.setSize(220, 30);
			button12.setLocation(800, 350);
			button12.addActionListener(new Advanced());
			add(button12);
			
			setVisible(true);
		}
	}
	private class SearchGuest implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null;
			String str2 = null, str8 = null, str9 = null;
			try 
			{
				
				str = textField1.getText(); 				
				str2 = textField2.getText();			
				str3 = textField3.getText();				
				str4 = textField4.getText();
				str5 = textField5.getText();				
				str6 = textField6.getText();
				str7 = textField7.getText();
				str8 = textField1.getText();
				str9 = textField2.getText();
				dm.setDataVector(gs.getTable1(str, str2, str3, str4, str5, str6, str7, str8, str9), guestColumns);
				dm.fireTableDataChanged();
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
		
	}
	private class DeleteGuest implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = "100";
			try 
			{
				str = textField1.getText();
				gs.Delete(str);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}
	private class UpdateGuest implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str1 = null, str2 = null, str3 = null, str7 = null;
			String str4 = null, str5 = null, str6 = null; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText(); str5= textField5.getText(); str6= textField6.getText(); 
				str7= textField7.getText(); 
				gs.Update(str1, str2, str3, str4, str5, str6, str7, str1, str1);
				dm = new DefaultTableModel(us.getTable0(user, pass), guestColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	}
	private class AddGuest implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
			String str1 = null, str3 = null, str7 = null;
			String str4 = null, str5 = null, str6 = null;
			String str2 = null, str8 = "", str9 = ""; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText(); str5= textField5.getText(); str6= textField6.getText(); 
				str7= textField7.getText(); 
				System.out.println(str2);
				gs.Add(str1, str2, str3, str4, str5, str6, str7, str8, str9);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}	
	private class ClearGuest implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				textField5.setText("");
				textField6.setText("");
				textField7.setText("");
				dm = new DefaultTableModel(us.getTable0(user, pass), guestColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private class BooksTable implements ActionListener

	{
		public void actionPerformed(ActionEvent arg0)
		{
			remove(greet);
			remove(greet1);
			setVisible(false);
			
			remove(scroll);
			try {
				dm = new DefaultTableModel(bk.getTable0(), booksColumns);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NullPointerException | SQLException e) {
				e.printStackTrace();
			}
		    table.setModel(dm);
			scroll = new JScrollPane(table);
			scroll.setSize(1290, 270);
			scroll.setLocation(50, 50);
			add(scroll);
			
			remove(label1);
			remove(textField1);
			label1 = new JLabel("empNo");
			label1.setSize(100, 30);
			label1.setLocation(50, 400);
			add(label1);		
			textField1 = new JTextField();
			textField1.setSize(100, 30);
			textField1.setLocation(140, 400);
			add(textField1);
			
			remove(label2);
			remove(textField2);
			label2 = new JLabel("guestID");
			label2.setSize(100, 30);
			label2.setLocation(50, 450);
			add(label2);		
			textField2 = new JTextField();
			textField2.setSize(100, 30);
			textField2.setLocation(140, 450);
			add(textField2);
			
			remove(label3);
			remove(textField3);
			label3 = new JLabel("roomNum");
			label3.setSize(100, 30);
			label3.setLocation(250, 450);
			add(label3);		
			textField3 = new JTextField();
			textField3.setSize(100, 30);
			textField3.setLocation(340, 450);
			add(textField3);		
			
			remove(label4);
			remove(textField4);
			label4 = new JLabel("startDate");
			label4.setSize(100, 30);
			label4.setLocation(450, 450);
			add(label4);		
			textField4 = new JTextField();
			textField4.setSize(100, 30);
			textField4.setLocation(540, 450);
			add(textField4);		
			
			remove(label5);
			remove(textField5);
			label5 = new JLabel("endDate");
			label5.setSize(100, 30);
			label5.setLocation(50, 500);
			add(label5);		
			textField5 = new JTextField();
			textField5.setSize(100, 30);
			textField5.setLocation(140, 500);
			add(textField5);
			
			remove(label6);
			remove(textField6);
			label6 = new JLabel("paymentType");
			label6.setSize(100, 30);
			label6.setLocation(250, 500);
			add(label6);		
			textField6 = new JTextField();
			textField6.setSize(100, 30);
			textField6.setLocation(340, 500);
			add(textField6);		
			
			remove(label7);
			remove(textField7);
			label7 = new JLabel("cardNum");
			label7.setSize(100, 30);
			label7.setLocation(450, 500);
			add(label7);		
			textField7 = new JTextField();
			textField7.setSize(100, 30);
			textField7.setLocation(540, 500);
			add(textField7);
			
			remove(button1);
			button1 = new JButton("Search");
			button1.setSize(100, 30);
			button1.setLocation(50, 350);
			button1.addActionListener(new SearchBooking());
			add(button1);
			
			remove(button2);
			button2 = new JButton("Delete");
			button2.setSize(100, 30);
			button2.setLocation(200, 350);
			button2.addActionListener(new DeleteBooking());
			add(button2);
			
			remove(button3);
			button3 = new JButton("Update");
			button3.setSize(100, 30);
			button3.setLocation(350, 350);
			button3.addActionListener(new UpdateBooking());
			add(button3);
			
			remove(button4);
			button4 = new JButton("Add");
			button4.setSize(100, 30);
			button4.setLocation(500, 350);
			button4.addActionListener(new AddBooking());
			add(button4);
			
			remove(button6);
			button6 = new JButton("Clear");
			button6.setSize(100, 30);
			button6.setLocation(650, 350);
			button6.addActionListener(new ClearBooks());
			add(button6);
			
			remove(button12);
			button12 = new JButton("Advanced Options");
			button12.setSize(220, 30);
			button12.setLocation(800, 350);
			button12.addActionListener(new Advanced());
			add(button12);
			
			setVisible(true);
		}
	}
	private class SearchBooking implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null;
			String str2 = null;
			try 
			{
				
				str = textField1.getText(); 				
				str2 = textField2.getText();			
				str3 = textField3.getText();				
				str4 = textField4.getText();
				str5 = textField5.getText();				
				str6 = textField6.getText();
				str7 = textField7.getText();
				dm.setDataVector(bk.getTable1(str, str2, str3, str4, str5, str6, str7), booksColumns);
				dm.fireTableDataChanged();
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
		
	}
	private class DeleteBooking implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = "100";
			try 
			{
				str = textField1.getText();
				bk.Delete(str);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}	
	private class UpdateBooking implements ActionListener //
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str1 = null, str2 = null, str3 = null, str7 = null;
			String str4 = null, str5 = null, str6 = null; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText(); str5= textField5.getText(); str6= textField6.getText(); 
				str7= textField7.getText(); 
				bk.Update(str1, str2, str3, str4, str5, str6, str7);
				dm = new DefaultTableModel(bk.getTable0(), booksColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	}
	private class AddBooking implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
			String str1 = null, str3 = null, str7 = null;
			String str4 = null, str5 = null, str6 = null;
			String str2 = null; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText(); str5= textField5.getText(); str6= textField6.getText(); 
				str7= textField7.getText(); 
				bk.Add(str1, str2, str3, str4, str5, str6, str7);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}	
	private class ClearBooks implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				textField5.setText("");
				textField6.setText("");
				textField7.setText("");
				dm = new DefaultTableModel(bk.getTable0(), booksColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private class ServiceTable implements ActionListener

	{
		public void actionPerformed(ActionEvent arg0)
		{
			remove(greet);
			remove(greet1);
			setVisible(false);
			
			remove(scroll);
			try {
				dm = new DefaultTableModel(se.getTable0(), serviceColumns);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NullPointerException | SQLException e) {
				e.printStackTrace();
			}
		    table.setModel(dm);
			scroll = new JScrollPane(table);
			scroll.setSize(1290, 270);
			scroll.setLocation(50, 50);
			add(scroll);
			
			remove(label1);
			remove(textField1);
			label1 = new JLabel("empNo");
			label1.setSize(100, 30);
			label1.setLocation(50, 400);
			add(label1);		
			textField1 = new JTextField();
			textField1.setSize(100, 30);
			textField1.setLocation(140, 400);
			add(textField1);
			
			remove(label2);
			remove(textField2);
			label2 = new JLabel("roomNum");
			label2.setSize(100, 30);
			label2.setLocation(50, 450);
			add(label2);		
			textField2 = new JTextField();
			textField2.setSize(100, 30);
			textField2.setLocation(140, 450);
			add(textField2);
			
			remove(label3);
			remove(textField3);
			label3 = new JLabel("requestType");
			label3.setSize(100, 30);
			label3.setLocation(250, 450);
			add(label3);		
			textField3 = new JTextField();
			textField3.setSize(100, 30);
			textField3.setLocation(340, 450);
			add(textField3);		
			
			remove(label4);
			remove(textField4);
			label4 = new JLabel("dateRequested");
			label4.setSize(100, 30);
			label4.setLocation(450, 450);
			add(label4);		
			textField4 = new JTextField();
			textField4.setSize(100, 30);
			textField4.setLocation(540, 450);
			add(textField4);		
			
			remove(label5);
			remove(textField5);
			label5 = new JLabel("dateCompleted");
			label5.setSize(100, 30);
			label5.setLocation(50, 500);
			add(label5);		
			textField5 = new JTextField();
			textField5.setSize(100, 30);
			textField5.setLocation(140, 500);
			add(textField5);
			
			remove(label6);
			remove(textField6);
			label6 = new JLabel("priorityLevel");
			label6.setSize(100, 30);
			label6.setLocation(250, 500);
			add(label6);		
			textField6 = new JTextField();
			textField6.setSize(100, 30);
			textField6.setLocation(340, 500);
			add(textField6);		
			
			remove(label7);
			remove(textField7);
			label7 = new JLabel("notes");
			label7.setSize(100, 30);
			label7.setLocation(450, 500);
			add(label7);		
			textField7 = new JTextField();
			textField7.setSize(100, 30);
			textField7.setLocation(540, 500);
			add(textField7);
			
			remove(button1);
			button1 = new JButton("Search");
			button1.setSize(100, 30);
			button1.setLocation(50, 350);
			button1.addActionListener(new SearchService());
			add(button1);
			
			remove(button2);
			button2 = new JButton("Delete");
			button2.setSize(100, 30);
			button2.setLocation(200, 350);
			button2.addActionListener(new DeleteService());
			add(button2);
			
			remove(button3);
			button3 = new JButton("Update");
			button3.setSize(100, 30);
			button3.setLocation(350, 350);
			button3.addActionListener(new UpdateService());
			add(button3);
			
			remove(button4);
			button4 = new JButton("Add");
			button4.setSize(100, 30);
			button4.setLocation(500, 350);
			button4.addActionListener(new AddService());
			add(button4);
			
			remove(button6);
			button6 = new JButton("Clear");
			button6.setSize(100, 30);
			button6.setLocation(650, 350);
			button6.addActionListener(new ClearService());
			add(button6);
			
			remove(button12);
			button12 = new JButton("Advanced Options");
			button12.setSize(220, 30);
			button12.setLocation(800, 350);
			button12.addActionListener(new Advanced());
			add(button12);
			
			setVisible(true);
		}
	}
	private class SearchService implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null;
			String str2 = null;
			try 
			{
				
				str = textField1.getText(); 				
				str2 = textField2.getText();			
				str3 = textField3.getText();				
				str4 = textField4.getText();
				str5 = textField5.getText();				
				str6 = textField6.getText();
				str7 = textField7.getText();
				dm.setDataVector(se.getTable1(str, str2, str3, str4, str5, str6, str7), serviceColumns);
				dm.fireTableDataChanged();
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
		
	}
	private class DeleteService implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = "100";
			try 
			{
				str = textField1.getText();
				se.Delete(str);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}	
	private class UpdateService implements ActionListener //
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str1 = null, str2 = null, str3 = null, str7 = null;
			String str4 = null, str5 = null, str6 = null; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText(); str5= textField5.getText(); str6= textField6.getText(); 
				str7= textField7.getText(); 
				se.Update(str1, str2, str3, str4, str5, str6, str7);
				dm = new DefaultTableModel(se.getTable0(), serviceColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	}
	private class AddService implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
			String str1 = null, str3 = null, str7 = null;
			String str4 = null, str5 = null, str6 = null;
			String str2 = null; 
			try 
			{
				str1 = textField1.getText(); str2 = textField2.getText(); str3= textField3.getText(); 
				str4= textField4.getText(); str5= textField5.getText(); str6= textField6.getText(); 
				str7= textField7.getText(); 
				se.Add(str1, str2, str3, str4, str5, str6, str7);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			textField7.setText("");
		}
	
	}	
	private class ClearService implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				textField5.setText("");
				textField6.setText("");
				textField7.setText("");
				dm = new DefaultTableModel(se.getTable0(), serviceColumns);
			    table.setModel(dm);
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private class Advanced implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
				advanced.setLayout(null);
				advanced.setSize(650, 500);
				advanced.setLocation(50, 50);
				
				JButton button2 = new JButton("Guests' books for stdrate");
				button2.setSize(270, 30);
				button2.setLocation(50, 50);
				button2.addActionListener(new SearchAdv1());
				advanced.add(button2);
				
				JButton button3 = new JButton("Avaliable rooms for stdrate and date");
				button3.setSize(270, 30);
				button3.setLocation(50, 100);
				button3.addActionListener(new SearchAdv2());
				advanced.add(button3);
				
				JButton button4 = new JButton("Count of employees based on position");
				button4.setSize(270, 30);
				button4.setLocation(50, 150);
				button4.addActionListener(new SearchAdv3());
				advanced.add(button4);
				
				JButton button5 = new JButton("Rooms and count of services");
				button5.setSize(270, 30);
				button5.setLocation(50, 200);
				button5.addActionListener(new SearchAdv4());
				advanced.add(button5);
				
				JButton button6 = new JButton("Guests with two different bed sizes");
				button6.setSize(270, 30);
				button6.setLocation(50, 250);
				button6.addActionListener(new SearchAdv5());
				advanced.add(button6);
				
				advanced.setVisible(true);
		}
	
	}
	private class SearchAdv1 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{			
				advanced.setVisible(false);
				advanced.remove(label10);
				advanced.remove(textField10);
				advanced.remove(label11);
				advanced.remove(textField11);
				advanced.remove(label12);
				advanced.remove(textField12);
				label10 = new JLabel("stdRate (write '<','>' or '=' before the rate)");
				label10.setSize(270, 30);
				label10.setLocation(350, 20);
				advanced.add(label10);		
				textField10 = new JTextField();
				textField10.setSize(100, 30);
				textField10.setLocation(350, 50);
				advanced.add(textField10);
				
				advanced.remove(button13);
				button13 = new JButton("Execute");
				button13.setSize(100, 30);
				button13.setLocation(350, 90);
				button13.addActionListener(new Execute1());
				advanced.add(button13);
				
				advanced.setVisible(true);
				
			} 
		
	}
	private class Execute1 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String [] col = {"Guest ID","First Name","Last Name"};		
			String str = null;
			str = textField10.getText();
			try
			{
				dm.setDataVector(ad.query2(str), col);
				dm.fireTableDataChanged();
			}
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	private class SearchAdv2 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			advanced.setVisible(false);
			advanced.remove(label10);
			advanced.remove(textField10);
			advanced.remove(label11);
			advanced.remove(textField11);
			advanced.remove(label12);
			advanced.remove(textField12);
			label10 = new JLabel("stdRate (write '<','>' or '=' before the rate)");
			label10.setSize(270, 30);
			label10.setLocation(350, 20);
			advanced.add(label10);		
			textField10 = new JTextField();
			textField10.setSize(100, 30);
			textField10.setLocation(350, 50);
			advanced.add(textField10);
			label11 = new JLabel("Start Date (YYYY-MM-DD)");
			label11.setSize(270, 30);
			label11.setLocation(350, 100);
			advanced.add(label11);		
			textField11 = new JTextField();
			textField11.setSize(100, 30);
			textField11.setLocation(350, 130);
			advanced.add(textField11);
			label12 = new JLabel("End Date (YYYY-MM-DD)");
			label12.setSize(270, 30);
			label12.setLocation(350, 180);
			advanced.add(label12);		
			textField12 = new JTextField();
			textField12.setSize(100, 30);
			textField12.setLocation(350, 210);
			advanced.add(textField12);
			advanced.remove(button13);
			button13 = new JButton("Execute");
			button13.setSize(100, 30);
			button13.setLocation(350, 260);
			button13.addActionListener(new Execute2());
			advanced.add(button13);
			
			advanced.setVisible(true);
		}
		
	}
	private class Execute2 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String [] col = {"Room Number","Number of Beds","Bed Size", "Std Rate"};		
			String str = null, str2 = null, str3 = null;
			str = textField10.getText();
			str2 = textField11.getText();
			str3 = textField12.getText();
			try
			{
				dm.setDataVector(ad.query3(str, str2,str3), col);
				dm.fireTableDataChanged();
			}
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	private class SearchAdv3 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			advanced.setVisible(false);
			advanced.remove(label10);
			advanced.remove(textField10);
			advanced.remove(label11);
			advanced.remove(textField11);
			advanced.remove(label12);
			advanced.remove(textField12);
			label10 = new JLabel("Position (Manager, Front Desk, Maintenance, Housekeeping)");
			label10.setSize(270, 30);
			label10.setLocation(350, 20);
			advanced.add(label10);		
			textField10 = new JTextField();
			textField10.setSize(100, 30);
			textField10.setLocation(350, 50);
			advanced.add(textField10);
			advanced.remove(button13);
			button13 = new JButton("Execute");
			button13.setSize(100, 30);
			button13.setLocation(350, 90);
			button13.addActionListener(new Execute3());
			advanced.add(button13);
			
			advanced.setVisible(true);
		}
		
	}
	
	private class Execute3 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String str = null;
			str = textField10.getText();
			String [] col = {str+" Count"};
			try
			{
				dm.setDataVector(ad.query4(str), col);
				dm.fireTableDataChanged();
			}
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private class SearchAdv4 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			advanced.setVisible(false);
			advanced.remove(label10);
			advanced.remove(textField10);
			advanced.remove(label11);
			advanced.remove(textField11);
			advanced.remove(label12);
			advanced.remove(textField12);
			label10 = new JLabel("Start Date (YYYY-MM)");
			label10.setSize(270, 30);
			label10.setLocation(350, 20);
			advanced.add(label10);		
			textField10 = new JTextField();
			textField10.setSize(100, 30);
			textField10.setLocation(350, 50);
			advanced.add(textField10);
			label11 = new JLabel("End Date (YYYY-MM)");
			label11.setSize(270, 30);
			label11.setLocation(350, 100);
			advanced.add(label11);		
			textField11 = new JTextField();
			textField11.setSize(100, 30);
			textField11.setLocation(350, 130);
			advanced.add(textField11);
			label12 = new JLabel("Number of Services");
			label12.setSize(270, 30);
			label12.setLocation(350, 180);
			advanced.add(label12);		
			textField12 = new JTextField();
			textField12.setSize(100, 30);
			textField12.setLocation(350, 210);
			advanced.add(textField12);
			advanced.remove(button13);
			button13 = new JButton("Execute");
			button13.setSize(100, 30);
			button13.setLocation(350, 260);
			button13.addActionListener(new Execute4());
			advanced.add(button13);
			
			advanced.setVisible(true);
		}
		
	}
	
	private class Execute4 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String [] col = {"Room Number","Number of Services"};		
			String str = null, str2 = null, str3 = null;
			str = textField10.getText();
			str2 = textField11.getText();
			str3 = textField12.getText();
			try
			{
				dm.setDataVector(ad.query5(str, str2,str3), col);
				dm.fireTableDataChanged();
			}
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private class SearchAdv5 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			advanced.setVisible(false);
			advanced.remove(label10);
			advanced.remove(textField10);
			advanced.remove(label11);
			advanced.remove(textField11);
			advanced.remove(label12);
			advanced.remove(textField12);
			label10 = new JLabel("Enter a type of bed size");
			label10.setSize(270, 30);
			label10.setLocation(350, 20);
			advanced.add(label10);		
			textField10 = new JTextField();
			textField10.setSize(100, 30);
			textField10.setLocation(350, 50);
			advanced.add(textField10);
			label11 = new JLabel("Enter a different bed size");
			label11.setSize(270, 30);
			label11.setLocation(350, 100);
			advanced.add(label11);		
			textField11 = new JTextField();
			textField11.setSize(100, 30);
			textField11.setLocation(350, 130);
			advanced.add(textField11);
			
			advanced.remove(button13);
			button13 = new JButton("Execute");
			button13.setSize(100, 30);
			button13.setLocation(350, 260);
			button13.addActionListener(new Execute5());
			advanced.add(button13);
			
			advanced.setVisible(true);
		}
		
	}
	
	private class Execute5 implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String [] col = {"First Name", "Last Name"};		
			String str = null, str2 = null;
			str = textField10.getText();
			str2 = textField11.getText();
			try
			{
				dm.setDataVector(ad.query6(str, str2), col);
				dm.fireTableDataChanged();
			}
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private class Login implements ActionListener 
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			boolean log = false;
			try 
			{
				user = textField8.getText();
				pass = textField9.getText();
				log = us.getEmployee(user, pass);
				if(log == true)
				{
					login.setVisible(false);
					setVisible(true);
					ci = new Room(300, user, pass);
					us = new Employee(300, user, pass);
					gs = new Guest(300, user, pass);
					bk = new books(300, user, pass);
					se = new service(300, user, pass);
					ad = new advanced(200, user, pass);
					greet = new JLabel("Welcome to the");
					greet.setSize(550, 550);
					greet.setFont(new Font("Castellar", Font.BOLD, 40));
					greet.setLocation(450, 0);
					add(greet);	
					greet1 = new JLabel("GrandHotel database");
					greet1.setSize(550, 550);
					greet1.setFont(new Font("Castellar", Font.BOLD, 30));
					greet1.setLocation(450, 30);
					add(greet1);	
				}
			} 
			catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NullPointerException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}	
	
public static void main(String[] args)throws InstantiationException, 
								IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException
		{
			new GrandHotel();
		}
	
	

}
