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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="library_id")
	private int library_id;
	
	@OneToMany(mappedBy = "department",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List <Rack> listRack = new ArrayList<Rack>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLibrary_id() {
		return library_id;
	}
	public void setLibrary_id(int library_id) {
		this.library_id = library_id;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", library_id=" + library_id
				+ ", listRack=" + printRackId(listRack) + "]\n";
	}
	
	public String printRackId(List<Rack> list){
		String s = "";
	
		for(Rack a:list){
		s=s+a.getId()+" ; ";
		}
		return s;
	}
	
}
