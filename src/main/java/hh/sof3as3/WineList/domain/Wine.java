package hh.sof3as3.WineList.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

	public Wine() {
	}

	public Wine(String name, double price, String profile, String country, int year, int grade, String comment) {
		super();
		this.name = name;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Wine [id=" + id + ", name=" + name + ", price=" + price + ", profile=" + profile + ", country="
				+ country + ", year=" + year + ", grade=" + grade + ", comment=" + comment + "]";
	}

}
