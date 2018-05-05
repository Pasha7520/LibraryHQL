package service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import util.MathUtil;
import cons.Constants;
import entity.Order;

public interface OrderService {
	public void checkDateUponReturn(Date endDate,Date actualDate,double pricebook) throws SQLException;
	public void OrderReturn(int bookId) throws SQLException;
	public Order createOrder(int term,int customerId,int bookId);
}
