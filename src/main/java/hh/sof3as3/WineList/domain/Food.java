package hh.sof3as3.WineList.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long foodid;
	@Size(min=2, max=20)
	private String name;

	@ManyToMany(mappedBy = "foods")
	@JsonIgnoreProperties("foods")
	private Set<Wine> wines = new HashSet<>();

	public Food() {

	}

	public Food(String name) {
		super();
		this.name = name;
	}

	public Long getFoodid() {
		return foodid;
	}

	public String getName() {
		return name;
	}

	public Set<Wine> getWines() {
		return wines;
	}

	public void setFoodid(Long foodid) {
		this.foodid = foodid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWines(Set<Wine> wines) {
		this.wines = wines;
	}

	@Override
	public String toString() {
		return "Food [foodid=" + foodid + ", name=" + name + "]";
	}

}
