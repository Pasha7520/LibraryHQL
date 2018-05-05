package util;

import entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import menuImpl.DepartmentHeadMenuImpl;
import menuImpl.LibrarianMenuImpl;
import DAOImpl.DAOUserImpl;
import serviceImpl.UserServiceImpl;

public class AuthorizationUtil {
	public Person user = new Person();
	
	public	AuthorizationUtil(){
			
		}
	public Person getUser() {
		return this.user;
	}


	public void setUser(Person user) {
		this.user = user;
	}

	
	public boolean authorization() throws SQLException{
		Scanner scn = new Scanner(System.in);
		boolean enter = true;
		while(enter){
			System.out.println("Enter the login");
			String login = scn.nextLine();
			System.out.println("Enter the password");
			String password = scn.nextLine();
			UserServiceImpl userService = new UserServiceImpl();
			if(userService.confirmAuthorization(login,password)){
				
				DAOUserImpl rr = new DAOUserImpl();				
				user = rr.getByLogin(login);
				return true;
				
			}
	
			System.out.println("Incorrect login or password");
			System.out.println("Want to try agein ? Yes - press \"1\",Not - press\"2\"");
			String again = scn.nextLine();
			if(again.equals("1")){
				enter = true;
			}
			else return false;
		}
		return false;
	}
}
