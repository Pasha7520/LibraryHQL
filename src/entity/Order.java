package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="start_date")
	private Date startDate;

	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="actual_date")
	private Date actualDate;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer = new Customer();
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book bookB = new Book();
	
		public Book getBookB() {
		return bookB;
	}

	public void setBookB(Book bookB) {
		this.bookB = bookB;
	}

		public Order(){
			
		}
	
	public void setId(int id){
		this.id = id;
	}
	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	public void setActualDate(Date actualDate){
		this.actualDate = actualDate;
	}
	
	
	public int getId(){
		return this.id;
	}
	public Date getStartDate(){
		return this.startDate;
	}
	public Date getEndDate(){
		return this.endDate;
	}
	public Date getActualDate(){
		return this.actualDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", startDate=" + startDate + ", endDate="
				+ endDate + ", actualDate=" + actualDate + ", customer="
				+ customer + ", bookB=" + bookB + "]";
	}

	
}
