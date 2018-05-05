package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="author_name")
	private String authorName;
	
	@ManyToMany(mappedBy="listAuthor",fetch = FetchType.LAZY)
	private List <Book> listbook = new ArrayList<Book>();
	
		public Author(){
			
		}
	
		public List<Book> getListBook() {
			return listbook;
		}

		public void setListBook(List<Book> listbook) {
			this.listbook = listbook;
		}

		public void setId(int id){
			this.id = id;
		}
		public void setAuthorName(String authorName){
			this.authorName = authorName;
		}
		public int getId(){
			return this.id;
		}
		public String getAuthorName(){
			return this.authorName;
		}

		@Override
		public String toString() {
			return "Author [id=" + id + ", authorName=" + authorName
					+ ", listbook=" + printBookInfo(getListBook())+ "]\n";
		}
		public String printBookInfo(List<Book> list){
			String s = "";
		
			for(Book a:list){
			s=s+"name: "+a.getName()+" pages: "+a.getPages()+ " price: " +a.getPrice()+" ; ";
			}
			return s;
		}
		
		
}
