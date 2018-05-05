package DAO;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
	public T getById(int Id) throws SQLException;
	public boolean add(T t)throws SQLException;
	public boolean delete(T t)throws SQLException;
	public List<T> getAll() throws SQLException;
	
}
