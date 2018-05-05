package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author ÿ
 *
 */
@Entity
@Table(name="book")
public class Book{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id ;
	
	@Column(name="book_name")
	private String name;
	
	@Column(name="book_page")
	private int pages;
	
	@Column(name="availeble")
	private boolean availeble;
	
	@ManyToOne
	@JoinColumn(name="rack_id")
	private Rack rack = new Rack();
	
	@Column(name="price")
	private double price;
	
	@OneToMany(mappedBy="bookB",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List <Order> listOrder = new ArrayList<Order>();

	public List<Order> getListOrder() {
		return listOrder;
	}


	public void setListOrder(List<Order> listOrder) {
		this.listOrder = listOrder;
	}


	public List<Author> getListAuthor() {
		return listAuthor;
	}


	public void setListAuthor(List<Author> listAuthor) {
		this.listAuthor = listAuthor;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "book_author",
		joinColumns={@JoinColumn(name="book_id")},
		inverseJoinColumns={@JoinColumn(name="author_id")})
	private List <Author> listAuthor = new ArrayList<Author>();

			public Book(){
				
			}
			
		public void setName(String name){
			this.name = name;
		}
		public void setPages(int pages){
			this.pages = pages;
		}
		public void setAvaileble(boolean availeble){
			this.availeble = availeble;
		}
		public String getName(){
			return this.name;
		}
		public int getPages(){
			return this.pages;
		}
		public boolean getAvaileble(){
			return this.availeble;
		}
		
		public Rack getRack() {
			return rack;
		}


		public void setRack(Rack rack) {
			this.rack = rack;
		}


		public void setId(int id){
			this.id = id;
		}
		public int getId(){
			return this.id;
		}
		public void setPrice(double price){
			this.price = price;
		}
		public double getPrice(){
			return this.price;
		}
		public void addAuthor(Author a){
			listAuthor.add(a);
		}
		
		@Override
		public String toString() {
			return "Book [id=" + id + ", name=" + name + ", pages=" + pages
					+ ", availeble=" + availeble + ", rack=" + rack.getId()
					+ ", price=" + price + ", Author=" + printAuthorName(listAuthor) + "]"+"\n";
		}


		public String printAuthorName(List<Author> list){
			String s = "";
		
			for(Author a:list){
			s=s+a.getAuthorName()+" ; ";
			}
			return s;
		}
}
