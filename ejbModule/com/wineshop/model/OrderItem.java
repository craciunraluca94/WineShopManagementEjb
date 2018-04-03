package com.wineshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	private StockClientB2B stockClientB2B;

	@ManyToOne(fetch = FetchType.LAZY)
	private ClientOrder order;

	private double quantity;

	private double price;

	public OrderItem() {
		super();
	}	

	public OrderItem(StockClientB2B stockClientB2B, ClientOrder order, double quantity, double price) {
		super();
		this.stockClientB2B = stockClientB2B;
		this.order = order;
		this.quantity = quantity;
		this.price = price;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StockClientB2B getStockClientB2B() {
		return stockClientB2B;
	}

	public void setStockClientB2B(StockClientB2B stockClientB2B) {
		this.stockClientB2B = stockClientB2B;
	}

	public ClientOrder getOrder() {
		return order;
	}

	public void setOrder(ClientOrder order) {
		this.order = order;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", stockClientB2B=" + stockClientB2B + ", order=" + order + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
}