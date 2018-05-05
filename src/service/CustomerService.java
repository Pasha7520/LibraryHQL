package service;

import java.sql.SQLException;

import entity.Customer;

public interface CustomerService {
	public boolean checkEntranceCustomer(String name, String sername, String nickname) throws SQLException;
	public Customer writeIdCustomer(Customer customer) throws SQLException ;
}
