package DAOImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DataBaseUtil2;
import util.HibernateUtil;




import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mysql.jdbc.PreparedStatement;

import DAO.BaseDAO;
import DAO.CustomerDAO;
import entity.Author;
import entity.Book;
import entity.Customer;

public class DAOCustomerImpl implements BaseDAO<Customer>,CustomerDAO{

	@Override
	public Customer getById(int Id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Customer> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Customer AS c WHERE c.id = ?");
		query.setParameter(0, Id);
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
	public boolean add(Customer t) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();
			
			session.save(t);
			
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

	@Override
	public boolean delete(Customer t) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Customer AS c WHERE c.id =?");
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

	@Override
	public List<Customer> getAll() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Customer> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Customer");
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



}
