package print;

import java.sql.SQLException;

public interface PrintBook {
	public void printBookAllBook() throws SQLException;
	public void printDepartmentBook(int DepNumber) throws SQLException;
	
}
