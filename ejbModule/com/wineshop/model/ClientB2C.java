package com.wineshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ClientB2C extends PlatformUser {

	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date registerDate;

	private String CNP;

	public ClientB2C() {
	}	
	
	public ClientB2C(String firstName, String lastName, Date registerDate, String cNP,String username, String password, String address, String email) {
		super(username, password, address, email);		
		this.firstName = firstName;
		this.lastName = lastName;
		this.registerDate = registerDate;
		CNP = cNP;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

	@Override
	public String toString() {
		return "ClientB2C [firstName=" + firstName + ", lastName=" + lastName + ", registerDate=" + registerDate
				+ ", CNP=" + CNP + "]";
	}
}
