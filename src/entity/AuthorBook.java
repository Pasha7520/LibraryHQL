package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_author")
public class AuthorBook implements Serializable{
	@EmbeddedId
	private AuthorBookIntr a;
	
		public AuthorBook(){
			
		}
	
	public void createAuthorBookIntr(int book_id ,int author_id){
		a = new AuthorBookIntr(book_id,author_id);
	}

		
	
}
