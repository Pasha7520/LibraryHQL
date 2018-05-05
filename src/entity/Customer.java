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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="name")
	private String name;
	
	@Column(name="nickname")
	private String nickname;
	
	@Column(name="sername")
	private String sername;
	
	@Column(name="age")
	private int age;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List <Order> listOrders = new ArrayList<Order>();
	


		public Customer(){
			super();
		}
		
		Customer(String name,String nickname,String sername,int id){
			this.name = name;
			this.sername = sername;
			this.nickname = nickname;
			this.id = id;
		}
	
	public int getAge() {
			return age;
	}

	public void setAge(int age) {
			this.age = age;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public void setSername(String sername){
		this.sername = sername;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSername(){
		return this.sername;
	}
	
	public String getNickname(){
		return this.nickname;
	}
	
	public List<Order> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<Order> listOrders) {
		this.listOrders = listOrders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", nickname="
				+ nickname + ", sername=" + sername + ", age=" + age
				+ "]\n";
	}

	
	
	
	
}
