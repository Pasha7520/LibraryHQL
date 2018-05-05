package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	 private int id;
	
	@Column(name="person_name")
	 private String name;
	
	@Column(name="person_nickname")
	 private String nickname;
	
	@Column(name="person_sername")
	 private String sername;
	
	@Column(name="login")
	 private String login;
	
	@Column(name="password")
	 private String password;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="position_id",referencedColumnName="id")
	 private Position position = new Position();
	
	@Column(name="sallary")
	 private int sallary;
	
	@Column(name="person_age")
	 private int age;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id",referencedColumnName="id")
	 private Department Department = new Department();
	
		public Person(){
	 		
	 	}
	 public void setName(String name){
		 this.name = name;
	 }
	 public String getName(){
		 return this.name;
	 }
	 public void setPassword(String password){
		 this.password = password;
	 }
	 public String getPassword(){
		 return this.password;
	 }
	
	 public void setId(int id){
		 this.id = id;
	 }
	 
	 public int getId(){
		 return this.id;
	 }
	 
	 public int getSallary() {
			return sallary;
	 }
	 public void setSallary(int sallary) {
			this.sallary = sallary;
	 }
	 public int getAge() {
			return age;
	 }
	 public void setAge(int age) {
			this.age = age;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSername() {
		return sername;
	}
	public void setSername(String sername) {
		this.sername = sername;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Department getDepartmentNumber() {
		return Department;
	}
	public void setDepartmentNumber(Department departmentNumber) {
		Department = departmentNumber;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", nickname=" + nickname
				+ ", sername=" + sername + ", login=" + login + ", password="
				+ password + ", position=" + position + ", sallary=" + sallary
				+ ", age=" + age + ", DepartmentNumber=" + Department
				+ "]\n";
	}
	
}
