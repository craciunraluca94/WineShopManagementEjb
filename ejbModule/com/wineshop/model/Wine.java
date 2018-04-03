package com.wineshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wineshop.dto.WineType;

@Entity
public class Wine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int year;

	private String name;

	private String description;

	private String variety;

	private WineType type;

	public Wine() {
		super();
	}
	
	public Wine(int year, String name, String description, String variety, WineType type) {
		super();
		this.year = year;
		this.name = name;
		this.description = description;
		this.variety = variety;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public WineType getType() {
		return type;
	}

	public void setType(WineType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Wine [id=" + id + ", year=" + year + ", name=" + name + ", description=" + description + ", variety="
				+ variety + ", type=" + type + "]";
	}
}
