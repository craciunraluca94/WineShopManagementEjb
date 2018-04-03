package com.wineshop.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(
        name="findUserByUsername",
        query="SELECT u FROM PlatformUser u WHERE u.username = :username"
)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "DTYPE")
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

	@Override
	public String toString() {
		return "PlatformUser [id=" + id + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", emmail=" + email + "]";
	}
}
