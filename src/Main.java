import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import menuImpl.LibrarianMenuImpl;
import cons.Constants;
import entity.Author;
import entity.Book;
import entity.Department;
import entity.Rack;
import entity.Person;
import DAO.BaseDAO;
import DAOImpl.DAOAuthorImpl;
import DAOImpl.DAOBookImpl;
import DAOImpl.DAOCustomerImpl;
import DAOImpl.DAODepartmentImpl;
import DAOImpl.DAOOrderImpl;
import DAOImpl.DAOPositionImpl;
import DAOImpl.DAORackImpl;
import DAOImpl.DAOUserImpl;
import selectMenu.SelectMenu;
import service.OrderService;
import serviceImpl.AuthorServiceImpl;
import serviceImpl.OrderServiceImpl;
import serviceImpl.RackServiceImpl;
import util.AuthorizationUtil;
import util.StatisticUtil;
import entity.Customer;
import util.DataBaseUtil2;

public class Main {

	public static void main(String[] args) throws SQLException {
		

		
		AuthorizationUtil authorization = new AuthorizationUtil();
		if(authorization.authorization()){ 
			SelectMenu selectMenu = new SelectMenu();
			selectMenu.selectMenu(authorization.getUser());
		}

	}

}
