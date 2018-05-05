package DAO;

import java.sql.SQLException;
import java.util.Date;

import entity.Order;

public interface OrderDAO {
	public void writeActualDateInOrder(int orderId, Date date) throws SQLException;
}
