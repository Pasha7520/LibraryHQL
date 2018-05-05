package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class AuthorBookIntr implements Serializable{

	protected Integer book_id;


	protected Integer author_id;
	
		public AuthorBookIntr(){
			
		}
		public AuthorBookIntr(int book_id,int author_id){
			this.book_id=book_id;
			this.author_id=author_id;
		}

		public int getBook_id() {
			return book_id;
		}

		public void setBook_id(int book_id) {
			this.book_id = book_id;
		}

		public int getAuthor_id() {
			return author_id;
		}

		public void setAuthor_id(int author_id) {
			this.author_id = author_id;
		}
		
	
}
