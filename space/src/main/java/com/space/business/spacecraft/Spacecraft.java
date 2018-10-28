package com.space.business.spacecraft;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Spacecraft {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int debut;
	private int retirement;
	private int flights;
	
	public Spacecraft(int id, String name, int debut, int retirement, int flights) {
		super();
		this.id = id;
		this.name = name;
		this.debut = debut;
		this.retirement = retirement;
		this.flights = flights;
	}

	public Spacecraft(String name, int debut, int retirement, int flights) {
		super();
		this.name = name;
		this.debut = debut;
		this.retirement = retirement;
		this.flights = flights;
	}

	public Spacecraft() {
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

	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public int getRetirement() {
		return retirement;
	}

	public void setRetirement(int retirement) {
		this.retirement = retirement;
	}

	public int getFlights() {
		return flights;
	}

	public void setFlights(int flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Spacecraft [id=" + id + ", name=" + name + ", debut=" + debut + ", retirement=" + retirement
				+ ", flights=" + flights + "]";
	}
}
