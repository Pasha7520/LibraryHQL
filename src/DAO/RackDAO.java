package DAO;

import java.sql.SQLException;
import java.util.List;

import entity.Rack;

public interface RackDAO {
	public int bookBelongRack(int bookId) throws SQLException;

	
		
	}
