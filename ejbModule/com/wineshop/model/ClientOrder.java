package com.wineshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ClientOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int orderNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	private ClientB2C clientB2C;		
	
	public ClientOrder() {
		super();
	}

	public ClientOrder(Date date, int orderNumber, ClientB2C clientB2C) {
		super();
		this.date = date;
		this.orderNumber = orderNumber;
		this.clientB2C = clientB2C;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public ClientB2C getClientB2C() {
		return clientB2C;
	}

	public void setClientB2C(ClientB2C clientB2C) {
		this.clientB2C = clientB2C;
	}

	@Override
	public String toString() {
		return "ClientOrder [id=" + id + ", date=" + date + ", orderNumber=" + orderNumber + ", clientB2C=" + clientB2C
				+ "]";
	}
}
