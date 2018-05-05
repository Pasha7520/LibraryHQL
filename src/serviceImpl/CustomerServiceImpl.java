package serviceImpl;

import java.sql.SQLException;

import DAOImpl.DAOCustomerImpl;
import entity.Customer;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public boolean checkEntranceCustomer(String name, String sername, String nickname) throws SQLException {
		DAOCustomerImpl DAOcustomerImpl = new DAOCustomerImpl();
		for(Customer c : DAOcustomerImpl.getAll()){
			if((c.getName().equals(name))){
				if(c.getNickname().equals(nickname)){
					if(c.getSername().equals(sername)){
						return true;
					}
				}
				
			}
		}
		
		return false;
	}
	@Override
	public Customer writeIdCustomer(Customer customer) throws SQLException {
		DAOCustomerImpl DAOcustomerImpl = new DAOCustomerImpl();
		for(Customer c : DAOcustomerImpl.getAll()){
			if((c.getName().equals(customer.getName()))){
				if(c.getNickname().equals(customer.getNickname())){
					if(c.getSername().equals(customer.getSername())){
						customer.setId(c.getId());
						return customer;
					}
				}
				
			}
		}
		
		return customer;
	}
}
