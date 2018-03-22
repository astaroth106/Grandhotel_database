package grandHotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;;

public class grandHotel {

	private static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, 
									ClassNotFoundException, SQLException, IOException {

		Queries qu = new Queries(200, "root", "1234qwer");
		String str, str1, str2;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the\n Grand Hotel Database");
		int i;
		
		i = menu();
		
		while(i != 0)
		{
			if(i == 1)
			{
				System.out.print("Enter a position (Manager, Front Desk, Maintenance, Housekeeping): ");
				str = br.readLine();
				qu.query1(str);
				
			}
			else if(i == 2)
			{
				System.out.print("Enter '<', '>' or '=' before a stdRate:");
				str = scan.next();
				qu.query2(str);
			}
			else if(i == 3)
			{
				System.out.print("Enter '<', '>' or '=' before a stdRate:");
				str = scan.next();
				System.out.print("Enter start date in the form (YYY-MM-DD):");
				str1 = scan.next();
				System.out.print("Enter end date in the form (YYY-MM-DD):");
				str2 = scan.next();
				qu.query3(str, str1, str2);
			}
			else if(i == 4)
			{
				System.out.print("Enter a position (Manager, Front Desk, Maintenance, Housekeeping):");
				str = br.readLine();
				qu.query4(str);
			}
			else if(i == 5)
			{
				System.out.print("Enter a month in the form (YYY-MM):");
				str = scan.next();
				System.out.print("Enter a month in the form (YYY-MM):");
				str1 = scan.next();
				System.out.print("Enter '<', '>' or '=' before a stdRate:");
				str2 = scan.next();
				qu.query5(str, str1, str2);
			}
			else if(i == 6)
			{
				System.out.print("Enter a bed size (King, Queen, Twin):");
				str = scan.next();
				System.out.print("Enter a diferent bed size (King, Queen, Twin):");
				str1 = scan.next();
				qu.query6(str, str1);
			}
			i = menu();
			
		}
		System.out.println("Good Bye");
		
	}

	private static int menu()
	{
		int choice = -1;
		
		System.out.println("\n---------------------\nGrand Hotel test API\n---------------------");
		System.out.println("1. List all the emmployees of one position");
		System.out.println("2. List the guests who booked a room with a rate");
		System.out.println("3. List the rooms free of books on a period of time");
		System.out.println("4. List the number of employees of one position");
		System.out.println("5. List the number services of a room based on two months");
		System.out.println("6. List guests with two books of two different bed sizes");
		System.out.println("0. To EXIT");
			while(true){
			System.out.print("\tEnter your choice:");
			choice = scan.nextInt();
			if(choice > -1 && choice < 7)
				break;
			else
				System.out.println("Incorrect input");
		}
		return choice;
	}
}
