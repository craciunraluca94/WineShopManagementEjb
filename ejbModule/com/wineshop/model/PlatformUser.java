package com.wineshop.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = "findUserByUsername", query = "SELECT u FROM PlatformUser u WHERE u.username = :username")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "DTYPE")
@DiscriminatorValue("PlatformUser")
@Inheritance(strategy = InheritanceType.JOINED)
public class PlatformUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	private String address;

	private String email;

	public PlatformUser() {
	}

	public PlatformUser(String username, String password, String address, String emmail) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.email = emmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmmail(String email) {
		this.email = email;
	}

	@Transient
	public String getDiscriminatorValue() {
		System.out.println("User type is: "+this.getClass().getAnnotation(DiscriminatorValue.class).value());
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

	@Override
	public String toString() {
		return "PlatformUser [id=" + id + "type=" + getDiscriminatorValue() + ", username=" + username + ", password="
				+ password + ", address=" + address + ", emmail=" + email + "]";
	}
}
