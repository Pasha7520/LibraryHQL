package DAO;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import util.HibernateUtil;
import entity.Person;

public interface UserDAO {
	public boolean writeInAuthorization(Person user) throws SQLException;
	public boolean changePassword(Person user, String password) throws SQLException;
	public int getLastId() throws SQLException;
	public Person getByLogin(String login) throws SQLException;
}
