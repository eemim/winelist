package hh.sof3as3.WineList.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typeid;
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
	@JsonIgnoreProperties("type")
	private List<Wine> wines;

	public Type() {

	}

	public Type(String name) {
		super();
		this.name = name;
	}

	public Type(Long typeid, String name) {
		super();
		this.typeid = typeid;
		this.name = name;
	}

	public Long getTypeid() {
		return typeid;
	}

	public String getName() {
		return name;
	}

	public List<Wine> getWines() {
		return wines;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWines(List<Wine> wines) {
		this.wines = wines;
	}

	@Override
	public String toString() {
		return "Type [typeid=" + typeid + ", name=" + name + "]";
	}

}