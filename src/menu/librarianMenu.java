package menu;

import java.sql.SQLException;

import entity.Person;

public interface librarianMenu {
	
	public void menu(Person user) throws SQLException;
	public void issueBook(Person user) throws SQLException;
	public void registerUser() throws SQLException;
	public void handOverBook() throws SQLException;
	public void addNewBook() throws SQLException;
	public void deleteBook() throws SQLException;
	public boolean changePassword(Person user) throws SQLException;
	
}
