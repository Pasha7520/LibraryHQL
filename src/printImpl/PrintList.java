package printImpl;

import java.util.ArrayList;
import java.util.List;

import entity.Book;

public class PrintList {
	public static void printList(ArrayList<String>ar){
		for(String s:ar){
			System.out.println(s);
		}
	}
	public static void printListBook(List<Book>ar){
		for(Book b : ar){
			System.out.println(b);
		}
	}
}
