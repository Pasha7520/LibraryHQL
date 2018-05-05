package DAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.mysql.jdbc.PreparedStatement;

import serviceImpl.PositionServiceImpl;
import util.DataBaseUtil2;


import util.HibernateUtil;
import entity.Author;
import entity.Customer;
import entity.Person;
import DAO.BaseDAO;
import DAO.UserDAO;

public class DAOUserImpl implements BaseDAO<Person>,UserDAO{
	@Override
	public Person getById(int id) throws SQLException {
		

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Person> list = null;
		
		try{
			session.beginTransaction();
			//Query query = session.createQuery("FROM Person AS p LEFT JOIN FETCH p.Position AS po"
			//	+ " LEFT JOIN p.Department AS d WHERE p.id =?");
			Query query = session.createQuery("FROM Person AS p WHERE p.id =?");
			query.setParameter(0,id);
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
	public boolean add(Person user) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();
			
			DAOPositionImpl position = new DAOPositionImpl();
			user.getPosition().setId(position.getNubmerOfPosition(user.getPosition().getName()));
			PositionServiceImpl positionService = new PositionServiceImpl();
			
			if(!(positionService.checkNumberOfPosition(user.getPosition().getId()))){
				System.out.println("Sach a position doesn't exist!!");
				return false;
			}

			session.save(user);
			
			session.getTransaction().commit();
			
			if(user.getPosition().getId()>1){
				writeInAuthorization(user);
			}
			
			
			}catch(Exception e){
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
			}
			System.out.println("Emploee registered!!");
			return true;
		
	}

	@Override
	public boolean delete(Person user) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Person AS p WHERE p.id =?");
			query.setParameter(0,user.getId());
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
	public List<Person> getAll() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Person> list = null;
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("FROM Person AS p ORDER BY p.id");
			list = query.list();
			
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
		
	}


	@Override
	public boolean writeInAuthorization(Person user) throws SQLException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			String login = user.getName()+"_"+user.getSername();
			int id = getLastId();
		try{
			
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("UPDATE Person AS p SET p.login =?,p.password=? WHERE p.id = ?;");
			query.setParameter(0,login );
			query.setParameter(1,"7520");
			query.setParameter(2,id);
			
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
	public boolean changePassword(Person user,String password) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
	try{
		
		session.beginTransaction();
		Query query = session.createQuery("UPDATE Person AS p SET p.password = ? WHERE p.id = ?");
		query.setParameter(0,password);
		query.setParameter(1,user.getId());
		
		query.executeUpdate();
		
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		System.out.println("Changed!!");
		return true;
		
	}



	@Override
	public int getLastId() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> list = null;
		
		try{
			
			session.beginTransaction();
			Query query = session.createQuery("SELECT MAX(id) FROM Person");
			
			list = query.list();
			
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return (int)list.get(0);
		
	}



	@Override
	public Person getByLogin(String login) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Person> list = null;
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("FROM Person AS p WHERE p.login =?");
			query.setParameter(0 , login);
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






	
	

	

}
