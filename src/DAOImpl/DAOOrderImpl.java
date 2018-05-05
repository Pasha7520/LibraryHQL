package DAOImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mysql.jdbc.PreparedStatement;

import util.DataBaseUtil2;
import util.HibernateUtil;
import entity.Author;
import entity.Order;
import entity.Rack;
import DAO.BaseDAO;
import DAO.OrderDAO;

public class DAOOrderImpl implements BaseDAO<Order>,OrderDAO {

	@Override
	public Order getById(int id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Order> list = null;
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("FROM Order AS o WHERE o.id =?");
			query.setParameter(0 , id);
			list = query.list();
			
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();

		}
		return list.get(0);
		
	
	}

	@Override
	public boolean add(Order t) throws SQLException {
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
	public boolean delete(Order t) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Order AS o WHERE o.id = ?");
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
	public List<Order> getAll() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Order> list = null;
	
		try{
		
		session.beginTransaction();
		Query query = session.createQuery("FROM Order");

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
	public void writeActualDateInOrder(int orderId,Date date) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
	
		try{
		
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery("UPDATE orders SET actual_date =? WHERE id =?");
		query.setParameter(0,date);
		query.setParameter(1,orderId);
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

	
	
}
