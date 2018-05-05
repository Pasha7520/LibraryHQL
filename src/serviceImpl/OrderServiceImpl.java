package serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import DAOImpl.DAOBookImpl;
import DAOImpl.DAOOrderImpl;


import cons.Constants;
import entity.Order;
import service.OrderService;
import util.DataBaseUtil2;
import util.MathUtil;

public class OrderServiceImpl implements OrderService {

	@Override
	public void checkDateUponReturn(Date endDate,Date actualDate,double pricebook) throws SQLException{
		
	
		Calendar cal0 = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal3 = Calendar.getInstance();
		cal0.setTime(endDate);
		cal1.setTime(endDate);
		cal2.setTime(endDate);
		cal3.setTime(endDate);
		
		
		
		cal0.add(cal0.DATE, Constants.TERM0);
		cal1.add(cal1.DATE, Constants.TERM1);
		cal2.add(cal2.DATE, Constants.TERM2);
		cal3.add(cal3.DATE, Constants.TERM3);
		Date date = new Date();
		Date date0 = cal0.getTime();
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();
		Date date3 = cal3.getTime();
		
		MathUtil math = new MathUtil();
		if(actualDate.before(endDate)){
			System.out.println("You brought the book on time!!Thank you!!");
		}
		else if(actualDate.after(endDate) && actualDate.before(date0)){
			System.out.println("You have delayed the return of the book!!Don't do so much more!!");
		}
		else if(actualDate.after(endDate) && actualDate.before(date1)){
			double price = math.findPersent(pricebook, Constants.FINE2);
			int dollar = (int)price;
			String cent = String.format("%.2f", price); 
			System.out.println("terminated!!");
			System.out.println("You must to pay a fine -"+dollar+" $! and "+cent.substring(cent.lastIndexOf(",")+1)+" cent!");
		}
		else if(actualDate.after(endDate) && actualDate.before(date2)){
			double price = math.findPersent(pricebook, Constants.FINE3);
			int dollar = (int)price;
			String cent = String.format("%.2f", price); 
			System.out.println("terminated!!");
			System.out.println("You must to pay a fine -"+dollar+" $! and "+cent.substring(cent.lastIndexOf(",")+1)+" cent!");
		}
		
		else if(actualDate.after(endDate) && actualDate.before(date3)){
			double price = math.findPersent(pricebook, Constants.FINE4);
			int dollar = (int)price;
			String cent = String.format("%.2f", price); 
			System.out.println("terminated!!");
			System.out.println("You must to pay a fine -"+dollar+" $! and "+cent.substring(cent.lastIndexOf(",")+1)+" cent!");
		}
		else{
			double price = math.findPersent(pricebook, Constants.FINE5);
			int dollar = (int)price;
			String cent = String.format("%.2f", price); 
			System.out.println("terminated!!");
			System.out.println("You must to pay a fine -"+dollar+" $! and "+cent.substring(cent.lastIndexOf(",")+1)+" cent!");
		}
		
		
		
		
	}

	
	@Override
	public void OrderReturn(int bookId) throws SQLException {
			Date date = new Date();
			DAOOrderImpl daoOrderImpl = new DAOOrderImpl();
			for(Order o : daoOrderImpl.getAll()){
				if(o.getActualDate() == null){
					if(o.getBookB().getId() == bookId){
						daoOrderImpl.writeActualDateInOrder(o.getId(),date);
						DAOBookImpl daoBookImpl = new DAOBookImpl();
						checkDateUponReturn(o.getEndDate(),date,daoBookImpl.getById(bookId).getPrice());
						daoBookImpl.changeAvailebleBook(bookId, true);
					}
				}
			}
			
			
		}
	@Override
	public Order createOrder(int term,int customerId ,int bookId) {
		Order order = new Order();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(cal.DATE, term);
		Date date = new Date();
		Date dep1 = cal.getTime();
		order.setStartDate(date);
		order.setEndDate(dep1);
		order.getCustomer().setId((customerId));
		order.getBookB().setId(bookId);
		
		return order;
	}


	
	

}
