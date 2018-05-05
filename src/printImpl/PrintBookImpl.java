package printImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import print.Print;
import print.PrintBook;
import util.DataBaseUtil2;


import util.HibernateUtil;
import entity.Author;
import entity.Book;

public class PrintBookImpl implements Print<Book>,PrintBook {
	public void printBookAllBook() throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
		ArrayList<String> ar = new ArrayList<String>();
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("FROM Book AS b order by b.id");

			
			list = query.list();
			
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		String s = "";
		for(Book b:list){
			System.out.println(b.getId()+"|"+b.getName()+"|"+printAuthors(b.getListAuthor())+"Department - "+b.getRack().getDepartment().getId());

		}
		
	}
	
	public void printDepartmentBook(int DepNumber) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("FROM Book AS b WHERE b.rack.department.id=? order by b.id");
			query.setParameter(0, DepNumber);
			
			list = query.list();
			
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		String s = "";
		for(Book b:list){
			System.out.println(b.getId()+"|"+b.getName()+"|"+printAuthors(b.getListAuthor())+"Department - "+b.getRack().getDepartment().getId());

		}
		
	}
	
	
	@Override
	public void print(Book book) {
		System.out.println(book);
		
	}
	@Override
	public void printList(List<Book> list) {
		for(Book book : list){
			System.out.println(book);
		}
		
	}
	public String printAuthors(List<Author>list){
		String s = "";
		for(Author author : list){
			s = s+author.getAuthorName()+"|";
		}
		return s;
	}
	
	
}
