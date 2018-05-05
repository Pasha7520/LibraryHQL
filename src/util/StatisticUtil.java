package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;




import printImpl.PrintBookImpl;
import printImpl.PrintList;
import serviceImpl.CustomerServiceImpl;
import entity.Author;
import entity.Book;
import entity.Customer;
import DAOImpl.DAOAuthorImpl;
import DAOImpl.DAOBookImpl;
import DAOImpl.DAOCustomerImpl;

public class StatisticUtil {
	public boolean availebleBook1(String nameBook) throws SQLException{
		DAOBookImpl daoBookImlp = new DAOBookImpl();
		for(Book b :daoBookImlp.getAll()){
			if(b.getName().equals(nameBook)){
				if(b.getAvaileble()){
					System.out.println("This book is availeble!");
				}
				else{
					System.out.println("This book isn't availeble!");
				}
					return b.getAvaileble();
				
			}
		}
		System.out.println("This book doesn't exist!!");
		return false;
	}
	public void printAllBooksBelongAuthor2(String authorName) throws SQLException{
		DAOAuthorImpl daoAuthorImlp = new DAOAuthorImpl();
		boolean bol = true;
		for(Author a : daoAuthorImlp.getAll()){
				if(a.getAuthorName().equals(authorName)){
					System.out.println(a.getListBook());
					bol = false;
					}
				
			}
			if(bol){
			System.out.println("This author books doesn't exist!!");
			}
	}
/*	public void printAllBooksBelongAuthor2(String authorName) throws SQLException{
		DAOBookImpl daoBookImlp = new DAOBookImpl();
		boolean bol = true;
		for(Book b : daoBookImlp.getAll()){
			for(Author a : b.getAuthorList()){
				if(a.getAuthorName().equals(authorName)){
					PrintBookImpl printBookImpl = new PrintBookImpl();
					printBookImpl.print(b);
					bol = false;
					}
				}
			}
			if(bol){
			System.out.println("This author books doesn't exist!!");
			}
		}*/
	
	public void printCustomerAllBook()throws SQLException{
		Customer customer = new Customer();
		Scanner scn1 = new Scanner(System.in);
		System.out.println("Enter the username!!");
		customer.setName(scn1.nextLine());
		System.out.println("Enter the nickname!!");
		customer.setNickname(scn1.nextLine());
		System.out.println("Enter the surname of the user!!");
		customer.setSername(scn1.nextLine());
		System.out.println("Enter the age!!");
		customer.setAge(scn1.nextInt());
		StatisticDAOUtil daoCustomerImpl = new StatisticDAOUtil();
		System.out.println("All the books that the Customer use!!!");
		CustomerServiceImpl serviceCustomerImpl =new CustomerServiceImpl();
	
		for(Book b : daoCustomerImpl.getCustomerAllBook(serviceCustomerImpl.writeIdCustomer(customer))){
		System.out.println(b);	
			
		}
	}
	
	public void printCustomerInfo3(String name,String sername,String nickname)throws SQLException{
		StatisticDAOUtil daoCustomerImpl = new StatisticDAOUtil();
		Customer customer = daoCustomerImpl.getByInitials(name, sername, nickname);
		if(customer.getId()==0){
			System.out.println("This Customer doesn't exist!!");
			return;
		}
		getBookCustomerEverOrdered3(customer);
		printCustomerBooksOnHands3(customer);
		printTimeStartUseLibrary3(customer);
	}
	public void printCustomerBooksOnHands3(Customer customer)throws SQLException{
		StatisticDAOUtil daoCustomerImpl = new StatisticDAOUtil();
		System.out.println("The books that the Customer have on hands!!!");
		for(Book b : daoCustomerImpl.getCustomerBooksOnHands(customer)){
		System.out.println(b);	
			
		}
	}
	public void printTimeStartUseLibrary3(Customer customer) throws SQLException{
		StatisticDAOUtil daoCustomerImpl = new StatisticDAOUtil();
		Date startDate = daoCustomerImpl.getStartTimeUseLibrary(customer);
		int days = daoCustomerImpl.getDuringCustomerUseLibrary(customer);
		
		String S = "";
		String mounthS = "";
		String yearS = "";
		
		if(days > 1){
			S = "s";
		}
		
		System.out.println("Customer start use the librari "+startDate);
		System.out.println("Customer use the librari during "+days+" day"+S+"!!");
		
		
	}
	public void getAllBookEverOrdered4() throws SQLException {
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		boolean bol = true;
		System.out.println("All the books ever ordered!!");
		for(Book b : statistic.getAllBookEverOrdered()){
					System.out.println(b);
					bol = false;
		}
		if(bol){
			System.out.println("No one books is taken!!!");
		}
		
	}
	public void getBookCustomerEverOrdered3(Customer customer) throws SQLException {
		StatisticDAOUtil daoCustomerImpl = new StatisticDAOUtil();
		System.out.println("All the books Costomer ever ordered!!");
			for(Book b : daoCustomerImpl.getBookCustomerEverOrdered(customer)){
					System.out.println(b);
			}
		
		
	}
	public void printBookByIdHowMenyTimesTake5(String bookName)throws SQLException {
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		DAOBookImpl daoBookImpl = new DAOBookImpl();
		List<Book> listbook= daoBookImpl.getByName(bookName);
		if(listbook.isEmpty()){
			System.out.println("The name of book isn't correct!!");
		}
		for(Book b : listbook){
		System.out.println(b);
		System.out.println("This book was taken "+statistic.getBooksByIdHowMenyTimesTake(b)+" times");
		System.out.println("Average time reading the book :"+statistic.avarageTimeReading(b)+" days");
		}
	}
	public void printBestAndWorstDemandBook6() throws SQLException{
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		System.out.println("The most popular book :");
		statistic.printMostPopularBook();
		System.out.println("The least popular book :");
		if(!(statistic.printMostUnpopularBookNeverTaken())){
			statistic.printMostUnpopularBook();
		}
	}
	public void printDebtor7() throws SQLException{
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		System.out.println("Debtor :");
		statistic.printDebtor();
	}
	public void printAVGCustomersInfo8() throws SQLException{
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		statistic.printAVGCustomersInfo();
	}
	public void printAVGCustomersByBook9(int bookId) throws SQLException{
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		statistic.printAVGCustomersByBook(bookId);
	}
	public void printAVGCustomersByAuthor9(int authorId) throws SQLException{
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		statistic.printAVGCustomersByAuthor(authorId);
	}
	public void printBookInstance10(String bookName) throws SQLException{
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		DAOBookImpl daoBook = new DAOBookImpl();
		PrintList printList = new PrintList();
		printList.printListBook(daoBook.getByName(bookName));
		
	}
}
