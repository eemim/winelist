package hh.sof3as3.WineList.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Wine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private double price;
	private String profile;
	private String country;
	@Column(name = "vintage")
	private int year;
	private int grade;
	private String comment;

	@ManyToOne
	@JoinColumn(name = "typeid")
	private Type type;
	
	@ManyToMany
	@JoinTable(
		name="recommendation",
		joinColumns = @JoinColumn(name = "wine_id"),
		inverseJoinColumns = @JoinColumn(name = "food_id"))
	private Set <Food> foods = new HashSet<>();

	public Wine() {
	}

	public Wine(String name, Type type, double price, String profile, String country, int year, int grade,
			String comment) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.profile = profile;
		this.country = country;
		this.year = year;
		this.grade = grade;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public String getProfile() {
		return profile;
	}

	public String getCountry() {
		return country;
	}

	public int getYear() {
		return year;
	}

	public int getGrade() {
		return grade;
	}

	public String getComment() {
		return comment;
	}
	

	public Set<Food> getFoods() {
		return foods;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}
	
	public void addFood(Food food) {
		this.foods.add(food);
		food.getWines().add(this);
	}

	@Override
	public String toString() {
		if (this.type != null) {
		return "Wine [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", profile=" + profile + ", country="
				+ country + ", year=" + year + ", grade=" + grade + ", comment=" + comment + ", recommendation="+"]";
		} else {
			return "Wine [id=" + id + ", name=" + name + ", price=" + price + ", profile=" + profile + ", country="
					+ country + ", year=" + year + ", grade=" + grade + ", comment=" + comment + "]";
		}
	}

}
