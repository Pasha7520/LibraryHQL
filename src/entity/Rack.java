package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Rack")
public class Rack {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="capacity")
	private int capacity;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department = new Department();
	
	@OneToMany(mappedBy="rack",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List <Book> listbook = new ArrayList<Book>();
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Rack() {
		
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Rack [id=" + id + ", capacity=" + capacity + ", department="
				+ department.getId() + ", listbook=" + printBookName(listbook) + "]\n";
	}
	public String printBookName(List<Book> list){
		String s = "";
	
		for(Book a:list){
		s=s+"\""+a.getName()+"\""+" ; ";
		}
		return s;
	}

	
}
