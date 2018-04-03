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
public class Promo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	private StockSupplier stockSupplier;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	private String discount;

	public Promo() {
	}

	
	public Promo(StockSupplier stockSupplier, Date startDate, Date endDate, String discount) {
		super();
		this.stockSupplier = stockSupplier;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discount = discount;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StockSupplier getStockSupplier() {
		return stockSupplier;
	}

	public void setStockSupplier(StockSupplier stockSupplier) {
		this.stockSupplier = stockSupplier;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Promo [id=" + id + ", stockSupplier=" + stockSupplier + ", startDate=" + startDate + ", endDate="
				+ endDate + ", discount=" + discount + "]";
	}
}
