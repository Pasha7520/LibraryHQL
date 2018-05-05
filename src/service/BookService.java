package service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookService {
	public int bookBelongRack(int numberRack) throws SQLException;
	public int bookBelongDepartment(int bookId) throws SQLException;
}
