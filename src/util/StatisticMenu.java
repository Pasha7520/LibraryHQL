package util;

import java.sql.SQLException;
import java.util.Scanner;

public class StatisticMenu {
	public void menu() throws SQLException{
	StatisticUtil statistic = new StatisticUtil();
	Scanner scn1 = new Scanner(System.in);
	System.out.println(" 1 -The Book availeble!;\n 2 - All the book belong author;\n 3 - Customer info(book ever taken,witch is in the hands"
			+ ",how long does the library use);\n 4 - All book ever ordered;\n"
			+ " 5 - How meny times the book taken!;\n 6 - Most popular and unpopular book!;\n 7 - List Readers debtors;\n 8 - "
			+ "Customer info(Middle age,average time use library);\n 9 - Average age on the book;\n 10 - Average age on the author;\n"
			+ " 11 - Find out the instances of the book(availeble info)");
		int a = scn1.nextInt();
		scn1.reset();
		switch (a){
		
			case 1:
				System.out.println("Enter the book name!");
				String bookName = scn1.nextLine();
				String bookName1 = scn1.nextLine();
				statistic.availebleBook1(bookName1);
					
				
				break;
			case 2:
				System.out.println("Enter the author name!");
				String authorName = scn1.nextLine();
				String authorName1 = scn1.nextLine();
				statistic.printAllBooksBelongAuthor2(authorName1);
				break;
			case 3:
				System.out.println("Enter the customer name!");
				String nam
				= scn1.nextLine();
				String name = scn1.nextLine();
				System.out.println("Enter the customer sername!");
				String sername = scn1.nextLine();
				System.out.println("Enter the customer nickname!");
				String nickname = scn1.nextLine();
				statistic.printCustomerInfo3(name, sername, nickname);
				break;
			case 4:
				statistic.getAllBookEverOrdered4();
				break;
			case 5:
				System.out.println("Enter the book name!");
				String l = scn1.nextLine();
				statistic.printBookByIdHowMenyTimesTake5(scn1.nextLine());
				break;
			case 6:
				statistic.printBestAndWorstDemandBook6();
				break;
			case 7:
				statistic.printDebtor7();
				break;
			case 8:
				statistic.printAVGCustomersInfo8();
				break;
			case 9:
				System.out.println("Enter the book Id!");
				statistic.printAVGCustomersByBook9(scn1.nextInt());
				break;
			case 10:
				System.out.println("Enter the author id!");
				statistic.printAVGCustomersByAuthor9(scn1.nextInt());
				break;
			case 11:
				System.out.println("Enter the book name!");
				String s = scn1.nextLine();
				statistic.printBookInstance10(scn1.nextLine());
				break;
		}
	}
}
