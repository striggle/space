package com.space.business.program;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Program {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int startYear;
	private int endYear;
	private int flights;
	
	public Program(int id, String name, int startYear, int endYear, int flights) {
		super();
		this.id = id;
		this.name = name;
		this.startYear = startYear;
		this.endYear = endYear;
		this.flights = flights;
	}

	public Program(String name, int startYear, int endYear, int flights) {
		super();
		this.name = name;
		this.startYear = startYear;
		this.endYear = endYear;
		this.flights = flights;
	}

	public Program() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public int getFlights() {
		return flights;
	}

	public void setFlights(int flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Program [id=" + id + ", name=" + name + ", startYear=" + startYear + ", endYear=" + endYear
				+ ", flights=" + flights + "]";
	}
}
