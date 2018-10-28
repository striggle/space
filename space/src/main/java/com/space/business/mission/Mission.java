package com.space.business.mission;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.space.business.commander.Commander;
import com.space.business.program.Program;
import com.space.business.spacecraft.Spacecraft;

@Entity
public class Mission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private LocalDate launch;
	@ManyToOne
	@JoinColumn(name = "commanderId")
	private Commander commander;
	@ManyToOne
	@JoinColumn(name = "programId")
	private Program program;
	@ManyToOne
	@JoinColumn(name = "spacecraftId")
	private Spacecraft spacecraft;
	
	public Mission(int id, String name, LocalDate launch, Commander commander, Program program, Spacecraft spacecraft) {
		super();
		this.id = id;
		this.name = name;
		this.launch = launch;
		this.commander = commander;
		this.program = program;
		this.spacecraft = spacecraft;
	}

	public Mission(String name, LocalDate launch, Commander commander, Program program, Spacecraft spacecraft) {
		super();
		this.name = name;
		this.launch = launch;
		this.commander = commander;
		this.program = program;
		this.spacecraft = spacecraft;
	}

	public Mission() {
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

	public LocalDate getLaunch() {
		return launch;
	}

	public void setLaunch(LocalDate launch) {
		this.launch = launch;
	}

	public Commander getCommander() {
		return commander;
	}

	public void setCommander(Commander commander) {
		this.commander = commander;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Spacecraft getSpacecraft() {
		return spacecraft;
	}

	public void setSpacecraft(Spacecraft spacecraft) {
		this.spacecraft = spacecraft;
	}

	@Override
	public String toString() {
		return "Mission [id=" + id + ", name=" + name + ", launch=" + launch + ", commander=" + commander + ", program="
				+ program + ", spacecraft=" + spacecraft + "]";
	}
}
