package serviceImpl;

import java.sql.SQLException;
import java.util.Scanner;

import cons.Constants;
import entity.Person;
import DAOImpl.DAOUserImpl;
import service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean confirmAuthorization(String login,String password) throws SQLException {
			DAOUserImpl DAOuser = new DAOUserImpl();
			for(Person u :DAOuser.getAll()){
				if(!(u.getLogin() == null)){
				if(u.getLogin().equals(login)){
					if(u.getPassword().equals(password)){
						return true;
					}
				}
			}
	}
			return false;
		}

	@Override
	public boolean changePassword(Person user) throws SQLException {
		Scanner scn = new Scanner(System.in);
		Scanner scn1 = new Scanner(System.in);
		boolean entrance = true;
		int choice;
		while(entrance){
		System.out.println("Enter old password!");
		String oldPassword = scn.nextLine();
		System.out.println("Enter new password!");
		String newPassword = scn.nextLine();
		System.out.println("Enter new password egain!");
		String newPassword2 = scn.nextLine();
		if(!(user.getPassword().equals(oldPassword))){
			System.out.println("You have enteredan incorrect password!!");
			System.out.println("Do you want to continue? Yes:-\"1\"; No:-\"2\";");
			choice =scn1.nextInt();
			if(choice == 2){
			entrance = false;
			}
			continue;
		}
		if(newPassword.length()<4){
			System.out.println("You new password too short!!!");
			System.out.println("Do you want to continue? Yes:-\"1\"; No:-\"2\";");
			choice =scn1.nextInt();
			if(choice == 2){
			entrance = false;
			}
			continue;
		}

		if(newPassword.equals(newPassword2)){
			DAOUserImpl daoUserImpl = new DAOUserImpl();
			daoUserImpl.changePassword(user,newPassword);
			user.setPassword(newPassword);
			return true;
		}
		else{
			System.out.println("Repeat the new password the same way!!");
			System.out.println("Do you want to continue? Yes:-\"1\"; No:-\"2\";");
			choice =scn1.nextInt();
			if(choice == 2){
			entrance = false;
			}
			continue;
			}
		}
		return false;
	}

	@Override
	public boolean registerNewWorker() throws SQLException {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the Name!!");
		String name = scn.nextLine();
		System.out.println("Enter the Sername!!");
		String sername = scn.nextLine();
		System.out.println("Enter the Nickname!!");
		String nickname = scn.nextLine();
		System.out.println("Enter position!!");
		String position = scn.nextLine();
		System.out.println("Enter the Age of the worker!!");
		int age = scn.nextInt();
		System.out.println("Enter the Department of the worker!!");
		int depNum = scn.nextInt();
		Person user = new Person();
		user.setName(name);
		user.setSername(sername);
		user.setNickname(nickname);
		user.setAge(age);
		user.getDepartmentNumber().setId(depNum);
		user.getDepartmentNumber().setLibrary_id(1);
		
		user.getPosition().setName(position);
		user.setSallary(sallaryWorker(position));
		
		DAOUserImpl daoUserImpl = new DAOUserImpl();
		daoUserImpl.add(user);
		
		return true;
	}

	@Override
	public int sallaryWorker(String position) {
		switch (position){
		case "Cleaner":
			return Constants.SALLARY_CLEANER;
		case "Librarian":
			return Constants.SALLARY_LIBRARIAN;
		case "DepartmentHead":
			return Constants.SALLARY_DEPARTMENT_HEAD;
		case "LibrarianHead" :
			return Constants.SALLARY_LIBRARIAN_HEAD;
		}
		return 0;
	}
	
	
}
