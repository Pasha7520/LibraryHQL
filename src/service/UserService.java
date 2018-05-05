package service;

import java.sql.SQLException;

import entity.Person;

public interface UserService {
	public boolean confirmAuthorization(String login,String password) throws SQLException;
	public boolean changePassword(Person user) throws SQLException;
	public boolean registerNewWorker()throws SQLException;
	public int sallaryWorker(String position);
}
