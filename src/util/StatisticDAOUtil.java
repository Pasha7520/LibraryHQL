package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.mysql.jdbc.PreparedStatement;

import serviceImpl.OrderServiceImpl;
import DAOImpl.DAOAuthorImpl;
import DAOImpl.DAOBookImpl;
import DAOImpl.DAOCustomerImpl;


import entity.Author;
import entity.Book;
import entity.Customer;
import entity.Order;

public class StatisticDAOUtil {
	public List<Book> getCustomerAllBook(Customer customer) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Book AS b JOIN FETCH b.listOrder AS l WHERE l.customer.id = ? AND l.actualDate IS NOT NULL");
		query.setParameter(0, customer.getId());
		
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		return list;

	}
	
	public Customer getByInitials(String name, String sername, String nickname) throws SQLException {
		Customer customer = new Customer();
		DAOCustomerImpl daoCustomer = new DAOCustomerImpl();
		for(Customer c :daoCustomer.getAll()){
			if(c.getName().equals(name) && c.getNickname().equals(nickname) && c.getSername().equals(sername)){
				customer = c;
			}
		}
		
		return customer;
	}
	public List<Book> getCustomerBooksOnHands(Customer customer) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Book AS b JOIN FETCH b.listOrder AS l WHERE l.customer.id = ? AND l.actualDate IS NULL");
		query.setParameter(0, customer.getId());
		
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		return list;

	}

	public Date getStartTimeUseLibrary(Customer customer) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Date> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT MIN(startDate) FROM Order AS o WHERE o.customer.id =?");
		query.setParameter(0, customer.getId());
		
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		return list.get(0);

	}

	public int getDuringCustomerUseLibrary(Customer customer) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT DATEDIFF(CURDATE(),MIN(startDate)) FROM Order AS o WHERE customer.id =?");
		query.setParameter(0, customer.getId());
		
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		
		return (int)list.get(0);
	}

	public List<Book> getBookCustomerEverOrdered(Customer customer) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Book AS b JOIN FETCH b.listOrder AS l WHERE l.customer.id = ? AND l.actualDate IS NOT NULL");
		query.setParameter(0, customer.getId());
		
		 
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		
		
		return list;
	}
	
	public int avarageTimeReading(Book book) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Double> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT AVG(DATEDIFF(o.actualDate,o.startDate)) FROM Book AS b "
				+ "JOIN b.listOrder AS o WHERE b.id = ?");
		query.setParameter(0, book.getId());
		
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		
		return list.get(0).intValue();
	}

	public int getBooksByIdHowMenyTimesTake(Book book)throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Long> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT COUNT(b.id) FROM Book AS b JOIN b.listOrder WHERE b.id = ?");
		query.setParameter(0, book.getId());
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		
		
		
		return list.get(0).intValue();
	}
	
	public List<Book> getAllBookEverOrdered() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Book AS b INNER JOIN FETCH b.listOrder GROUP BY b.id ORDER BY b.id");
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		return list;

	}

	public void printMostPopularBook() throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> list = null;
		List<Integer> list1 = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT b.id FROM Book AS b JOIN b.listOrder AS o GROUP BY b.id "
				+ "HAVING count(*) >=  ALL (SELECT count(*) FROM Book AS b JOIN b.listOrder AS o GROUP BY b.id)");
		Query query1 = session.createQuery("SELECT count(*) AS HOW FROM Book AS b JOIN b.listOrder AS o"
				+ " GROUP BY b.id HAVING count(*) >=  ALL (SELECT count(*) FROM Book AS b JOIN b.listOrder AS o GROUP BY b.id)");
		
		list = query.list();
		list1 = query1.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		DAOBookImpl daoBookImpl = new DAOBookImpl();
		System.out.println(daoBookImpl.getById(list.get(0)));
		System.out.println("The book is taken is "+ list1.get(0)+" times!!");
	}
	
	public boolean printMostUnpopularBookNeverTaken() throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
	
		try{
		
		session.beginTransaction();

		Query query = session.createQuery("FROM Book AS b WHERE b.id NOT IN (SELECT b.id FROM Book AS b JOIN b.listOrder AS o GROUP BY b.id)");
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		
		if(list.isEmpty()){
			return false;
		}
		else{
			if(list.size() > 1){
				System.out.print("This books heve never taken!!! - ");
				for(Book b:list){
					System.out.println(b);
				}
				return true;
			}
			else{
				System.out.print("This book has never taken!!! - ");
				System.out.println(list.get(0));
				return true;
			}
		
		
		}
	}
	
	public boolean printMostUnpopularBook() throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
		List<Integer> list2 = null;
	
		try{
		
		session.beginTransaction();

		Query query = session.createQuery("SELECT b FROM Order AS o JOIN o.bookB AS b GROUP BY b.id "
		+ "HAVING count(*) <=  ALL (SELECT count(*) FROM Order AS o JOIN o.bookB AS b GROUP BY b.id )");
		
		Query query2 = session.createQuery("SELECT COUNT(b.id) AS coun FROM Order AS o JOIN o.bookB AS b GROUP BY b.id "
				+ "HAVING count(b.id) <= ALL (SELECT COUNT(b.id) AS coun FROM Order AS o JOIN o.bookB AS b GROUP BY b.id )");
		list = query.list();
		list2 = query2.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		boolean bool = false;
		int count = 0;
		for(Book b:list){
				System.out.println(b);
			bool = true;
			count++;
		}

		if(count>1)System.out.println("The books have been taken "+list2.get(0)+" times!!");
		else if(count==1) System.out.println("The book has been taken "+list2.get(0)+" time!!");

		return bool;
		
	}
	
	public void printDebtor() throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Order> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT o FROM Customer AS c JOIN "
				+ "c.listOrders AS o WHERE o.actualDate IS NULL ORDER BY c.id");
		
		list = query.list();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		Order order  = new Order();
		order.getCustomer().setId(0);
		for(Order c:list){
			if(order.getCustomer().getId() == c.getCustomer().getId()){
				System.out.println("Borow-------"+c.getBookB());
			}
			else{
				System.out.println(c.getCustomer());
				System.out.println("Borow-------"+c.getBookB());
			}
			order = c;
		}
		
		
	}
	
	public void printAVGCustomersInfo() throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Double> list = null;
		List<Double> list1 = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT AVG(c.age) FROM Order AS o JOIN o.customer AS c");
		Query query1 = session.createQuery("SELECT AVG(DATEDIFF(o.actualDate,o.startDate)) FROM "
				+ "Order AS o JOIN o.customer AS c WHERE o.actualDate IS NOT NULL");
		list = query.list();
		list1 = query1.list();
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		System.out.println("Middle age customers is :"
				+list.get(0).intValue()+", and average time of "
						+ "reading is :"+list1.get(0).intValue()+" days");
		
		
	}
	
	public void printAVGCustomersByBook(int bookId) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Double> list = null;

		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT AVG(c.age) FROM Order AS o JOIN o.customer AS c"
				+ " WHERE o.bookB.id = ?");
		query.setParameter(0, bookId);
		list = query.list();
	
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		DAOBookImpl daoBook = new DAOBookImpl();
		System.out.println("By the book :"+ daoBook.getById(bookId));
		System.out.println("Middle age customers is :" + list.get(0).intValue()+"!!");
		
	}

	public void printAVGCustomersByAuthor(int authorId) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Double> list = null;

		try{
		
		session.beginTransaction();
		Query query = session.createQuery("SELECT AVG(c.age) FROM Order AS o JOIN o.customer AS c JOIN o.bookB AS b JOIN b.listAuthor AS a WHERE a.id = ?");
		
		query.setParameter(0, authorId);
		list = query.list();
	
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		DAOAuthorImpl daoAuthor = new DAOAuthorImpl();
		System.out.println("By the author :"+ daoAuthor.getById(authorId));
		System.out.println("Middle age customers is :" + list.get(0).intValue()+"!!");
		
	}
	
}
