package menuImpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import printImpl.PrintBookImpl;
import serviceImpl.AuthorServiceImpl;
import serviceImpl.CustomerServiceImpl;
import serviceImpl.OrderServiceImpl;
import serviceImpl.UserServiceImpl;
import util.StatisticDAOUtil;
import util.StatisticMenu;
import DAOImpl.DAOBookImpl;
import DAOImpl.DAOCustomerImpl;
import DAOImpl.DAOOrderImpl;
import cons.Constants;
import entity.Book;
import entity.Customer;
import entity.Order;
import entity.Person;
import menu.LibrarianHeadMenu;

public class LibrarianHeadMenuImpl implements LibrarianHeadMenu{
	@Override
	public void menu(Person user) throws SQLException {
		Scanner scn = new Scanner(System.in);
		boolean work = true;
		while(work){
			int choice = 0;
			System.out.println("1-List of books; 2-Issue a book; 3- Hand over a book; 4- Register new user; 5- Add a new Book!!;6- Delete book!!;"
					+ " 7 - Change password!!; 8 - Register new Worker!! 9 - Statistic");
			choice = scn.nextInt();
			switch (choice){ 
				case 1:
					System.out.print("You want all books or booksin the department!!? All books press on -\"1\";Books Department - \"2\";");
					int choise = scn.nextInt();
					if(choise == 1){
						PrintBookImpl printBookImpl = new PrintBookImpl();
						printBookImpl.printBookAllBook();
					}
					else if(choise == 2){
						PrintBookImpl printBookImpl = new PrintBookImpl();
						printBookImpl.printDepartmentBook(user.getDepartmentNumber().getId());
					}
					
					break;
				case 2:	
						issueBook(user);
					break;
				case 3:
						handOverBook();
					break;
				case 4:
						registerUser();
					break;
				case 5:
						addNewBook();
					break;
				case 6:
						deleteBook();
					break;
				case 7 : 
						changePassword(user);
					break;
				case 8 :
						registerNewWorker();
					break;
				case 9:
					StatisticMenu Smenu = new StatisticMenu();
					Smenu.menu();
					break;
			}
		}
	}


	@Override
	public void issueBook(Person user) throws SQLException {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the user name");
		String name = scn.nextLine();
		System.out.println("Enter the user nickname");
		String nickname = scn.nextLine();
		System.out.println("Enter the sername");
		String sername = scn.nextLine();
		Customer customer = new Customer();
		
		CustomerServiceImpl check = new CustomerServiceImpl();
		StatisticDAOUtil statistic = new StatisticDAOUtil();
		
		if(check.checkEntranceCustomer(name,sername,nickname)){
		customer = statistic.getByInitials(name, sername, nickname);
		}
		else{
			System.out.println("You aren't registered!! Please complete the registration!!!");
			return;
		}
		boolean continuation = true;
		while(continuation){
		System.out.println("Enter the number of book!!!");
		int bookId = scn.nextInt();
		
		DAOBookImpl daoBookImpl = new DAOBookImpl();
		
		if(user.getDepartmentNumber().getId() == daoBookImpl.bookBelongDepartment(bookId)){
			
		}
		else {
			System.out.println("This book is located in "+daoBookImpl.bookBelongDepartment(bookId)+" department!!");
			return ;
		}
		
		
		Book book = daoBookImpl.getById(bookId);
		if(book.getAvaileble()){
			daoBookImpl.changeAvailebleBook(bookId, false); 
		}
		else return;
		
		
		int term = 0; 
		if(book.getPages() < 200){
			term = Constants.LESS200;
		}
		else if(book.getPages() < 500){
			term = Constants.LESS500;
		}
		else if(book.getPages() < 750){
			term = Constants.LESS750;
		}
		else if(book.getPages() < 1000){
			term = Constants.LESS1000;
		}
		DAOOrderImpl daoOrderImpl = new DAOOrderImpl();
		OrderServiceImpl orderService = new OrderServiceImpl();
		Order order = orderService.createOrder(term, customer.getId(), bookId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("Please!! Date of Return to "+dateFormat.format(order.getEndDate())+"!!");
		daoOrderImpl.add(order);
		DAOBookImpl bookDAO = new DAOBookImpl();
		bookDAO.changeAvailebleBook(bookId, false);
		
		
		System.out.println("Want more?? Yes-\"1\"!,No-\"2\"");
		int a = scn.nextInt();
		if(a==1){
			
		}
		else{
			continuation = false;
		}
		
		
		}
		System.out.println("Bay bay!!");
	}
	@Override
	public void handOverBook() throws SQLException {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the book id!");
		int bookId = scn.nextInt();
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		orderServiceImpl.OrderReturn(bookId);
		
	}


	@Override
	public void registerUser() throws SQLException {

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
		
		
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		
			if(customerServiceImpl.checkEntranceCustomer(customer.getName(), customer.getSername(), customer.getNickname())){
				System.out.println("You are already registered!!!");
			}
			else {
				DAOCustomerImpl daoCustomerImpl = new DAOCustomerImpl();
				daoCustomerImpl.add(customer);
				
			}
		
		
	}
	@Override
	public void addNewBook() throws SQLException{
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Enter a title of the book!");
		String name = scn.nextLine();
		System.out.println("Enter the author of the book Like (Name,Last Name)(If two and more then through the comma)!");
		String authorName = scn.nextLine();
		System.out.println("Enter the number of pages!");
		int pages = scn.nextInt();
		System.out.println("Enter the price of the book");
		double price = scn.nextDouble();
		AuthorServiceImpl authorServiceImpl = new AuthorServiceImpl();
		
		Book book = new Book();
		book.setName(name);
		book.setPages(pages);
		book.setPrice(price);
		book.setListAuthor((authorServiceImpl.createAuthor(authorName)));
		DAOBookImpl daoBookImpl = new DAOBookImpl();
		daoBookImpl.add(book);
		System.out.println("You added the book!");
	}

	@Override
	public void deleteBook() throws SQLException {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the book id!!");
		int id = scn.nextInt();
		Book book = new Book();
		book.setId(id);
		DAOBookImpl daoBookImpl = new DAOBookImpl();
		daoBookImpl.delete(book);
		
		AuthorServiceImpl authorServiceImpl = new AuthorServiceImpl();
		authorServiceImpl.checkingOrDeleteAuthorNonBook();
	}


	@Override
	public boolean changePassword(Person user) throws SQLException {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.changePassword(user);
		return true;
	}


	@Override
	public void registerNewWorker() throws SQLException {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.registerNewWorker();
	}
}
