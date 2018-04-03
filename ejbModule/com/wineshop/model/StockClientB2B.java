package com.wineshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StockClientB2B implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	private ClientB2B clientB2B;

	private double price;

	private double quantity;

	public StockClientB2B() {
		super();
	}
		
	public StockClientB2B(ClientB2B clientB2B, double price, double quantity) {
		super();
		this.clientB2B = clientB2B;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientB2B getClientB2B() {
		return clientB2B;
	}

	public void setClientB2B(ClientB2B clientB2B) {
		this.clientB2B = clientB2B;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "StockClientB2B [id=" + id + ", clientB2B=" + clientB2B + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
}