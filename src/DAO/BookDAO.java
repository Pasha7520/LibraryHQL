package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DataBaseUtil2;

import entity.Author;
import entity.Book;
import entity.Customer;

public interface BookDAO {
	public void changeAvailebleBook(int id,boolean b) throws SQLException;
	public int bookBelongDepartment(int bookId) throws SQLException;
	public List<Book> getByName(String bookName) throws SQLException;
	public int getMaxBookId()throws SQLException;
	public boolean deleteBookAuthor(int id)throws SQLException;
}
