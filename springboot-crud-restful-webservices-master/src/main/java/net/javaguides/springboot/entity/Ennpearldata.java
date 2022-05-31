package net.javaguides.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "muthudata")
public class Ennpearldata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "email")
	private String email;
	
	public Ennpearldata() {
		
	}
	
	public Ennpearldata(String name, String company, String email) {
		super();
		this.name = name;
		this.company = company;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return name;
	}
	public void setFirstName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return company;
	}
	public void setLastName(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
