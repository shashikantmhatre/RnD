package com.mmpnc.rating.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RatingProcess", propOrder = {
		"id",
		"name",
		"entity",
		"executionorder",
		"steps",
		"programs"
})
@Entity
@Table(name = "SIM_RatingProcess")
@XmlRootElement(name = "RatingProcess")
public class Process {

	@Id
	@Column(name = "ID")
	@XmlID
	@XmlElement(name="Id")
	private String id;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = RatingEntity.class)
	@JoinColumn(name = "ENTITYID")
	@XmlIDREF
	@XmlElement(name="RatingEntity")
	private RatingEntity entity;
	
	@Column(name = "NAME")
	@XmlElement(name="Name")
	private String name;

	@Column(name = "EXECUTIONORDER")
	@XmlElement(name="ExecutionOrder")
	private int executionorder;
	
	@OneToMany(mappedBy = "process")
	@OrderBy(value = "executionorder")
	@XmlElement(name="ProcessStep")
	private List<Step> steps;
	
	@OneToMany(mappedBy = "process")
	@XmlElement(name="RatingProgram")
	private List<Program> programs;
		
	public Process(){
		this.name = "";
		this.entity = new RatingEntity();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RatingEntity getEntity() {
		return entity;
	}
	
	public void setEntity(RatingEntity entity) {
		this.entity = entity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Step> getSteps() {
		if(this.steps == null){
			this.steps = new ArrayList<Step>();
		}
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public void addStep(Step step) {
		if(this.steps == null){
			this.steps = new ArrayList<Step>();
		}
		this.steps.add(step);
	}

	public List<Program> getPrograms() {
		if(this.programs == null){
			this.programs = new ArrayList<Program>();
		}
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public void addProgram(Program program) {
		if(this.programs == null){
			this.programs = new ArrayList<Program>();
		}
		this.programs.add(program);
	}

	@Override
	public String toString() {
		return this.entity.toString() + "." + this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Process) {
			Process prc = (Process) obj;
			if (prc.getName().equals(this.name)
					&& prc.getEntity().equals(this.entity)) {
				return true;
			} else {
				return super.equals(obj);
			}
		} else {
			return super.equals(obj);
		}
	}

	public void setExecutionorder(int executionorder) {
		this.executionorder = executionorder;
	}

	public int getExecutionorder() {
		return executionorder;
	}
}
