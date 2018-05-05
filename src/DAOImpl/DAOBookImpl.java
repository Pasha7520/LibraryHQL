package DAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mysql.jdbc.PreparedStatement;

import entity.Author;
import entity.AuthorBook;
import entity.Book;
import entity.Customer;
import service.OrderService;
import serviceImpl.AuthorServiceImpl;
import serviceImpl.OrderServiceImpl;
import serviceImpl.RackServiceImpl;
import util.DataBaseUtil2;
import util.HibernateUtil;
import util.MathUtil;
import DAO.BaseDAO;
import DAO.BookDAO;

public class DAOBookImpl implements BaseDAO<Book>,BookDAO {

	@Override
	public Book getById(int Id) throws SQLException {
	Session session = HibernateUtil.getSessionFactory().openSession();
	List<Book> list = null;
	
	try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Book AS b WHERE b.id =?");

		
		query.setParameter(0 , Id);
		
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

	@Override
	public boolean add(Book b) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		

		try{
			AuthorServiceImpl authorServiceImpl = new AuthorServiceImpl();
			authorServiceImpl.checkingOrWritingNewAuthor(b.getListAuthor());
			AuthorServiceImpl authorService = new AuthorServiceImpl();
			b.setListAuthor(authorServiceImpl.findAuthorsId(b.getListAuthor()));
			session.beginTransaction();
			b.setId(1);
			RackServiceImpl rackService = new RackServiceImpl();
			b.getRack().setId(rackService.findfreeRac());
			b.setAvaileble(true);
			
			session.save(b);
			
			session.getTransaction().commit();
			}catch(Exception e){
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
			}
		
			//bookAuthorWrite(b.getListAuthor());


			return true;
			
	
	}

	@Override
	public boolean delete(Book t) throws SQLException {
		
		if(deleteBookAuthor(t.getId())){
			Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Book AS b WHERE b.id =?");
			query.setParameter(0,t.getId());
			
			query.executeUpdate();
			
			session.getTransaction().commit();
			}catch(Exception e){
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Book> getAll() throws SQLException {	
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Book AS b ORDER BY b.id");
		
		
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


	//@Override
	public void bookAuthorWrite(List<Author> listAuthor) throws SQLException {
		int BookId = getMaxBookId();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		
		try{
			
			session.beginTransaction();

				for(Author a:listAuthor){
				AuthorBook ab = new AuthorBook();
				ab.createAuthorBookIntr(BookId,a.getId());
				session.save(ab);
				}

			session.getTransaction().commit();
			
			}catch(Exception e){
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
			}

	}
	public int getMaxBookId(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> list = null;
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("SELECT MAX(id) FROM Book");
			
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
	
	@Override
	public void changeAvailebleBook(int id,boolean b) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("UPDATE Book AS b SET b.availeble = ? WHERE b.id = ?");
				
			query.setParameter(0,b);
			query.setParameter(1,id);
			
			query.executeUpdate();
			
			session.getTransaction().commit();
			}catch(Exception e){
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
				
			}
			
	}

	@Override
	public int bookBelongDepartment(int bookId) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> list = null;
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("SELECT b.rack.department.id FROM Book AS b WHERE b.id =?");
			
			query.setParameter(0,bookId);
			
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


	@Override
	public List<Book> getByName(String name)throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("FROM Book AS b where b.name =?");

			query.setParameter(0 , name);
		
			
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

	@Override
	public boolean deleteBookAuthor(int id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("DELETE FROM AuthorBook AS ab WHERE ab.a.book_id =?");
			query.setParameter(0,id);
			
			query.executeUpdate();
			
			session.getTransaction().commit();
			}catch(Exception e){
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
			}
			return true;
		
	}

	

	

	
}
