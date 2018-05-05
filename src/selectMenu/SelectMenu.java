package selectMenu;

import java.sql.SQLException;

import menuImpl.DepartmentHeadMenuImpl;
import menuImpl.LibrarianHeadMenuImpl;
import menuImpl.LibrarianMenuImpl;
import entity.Person;

public class SelectMenu {
	public void selectMenu(Person user) throws SQLException{
		switch (user.getPosition().getName()){
		case "Librarian":
			LibrarianMenuImpl librarian = new LibrarianMenuImpl();
			librarian.menu(user);
			break;
		case "LibrarianHead" :
			LibrarianHeadMenuImpl librarianHeadMenuImpl = new LibrarianHeadMenuImpl();
			librarianHeadMenuImpl.menu(user);
			break;
		case "DepartmentHead" :
			DepartmentHeadMenuImpl departmentHead = new DepartmentHeadMenuImpl();
			departmentHead.menu(user);
			break;
		}
	}
}