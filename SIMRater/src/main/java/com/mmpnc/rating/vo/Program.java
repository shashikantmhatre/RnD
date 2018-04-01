package com.mmpnc.rating.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
@XmlType(name = "RatingProgram", propOrder = {
		"id",
		"name",
		"executionorder",
		"process",
		"steps"
})
@Entity
@Table(name = "SIM_RatingProgram")
@XmlRootElement(name = "RatingProgram")
public class Program implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@XmlID
	@XmlElement(name="Id")
	private String id;
	
	@XmlElement(name="Name")
	@Column(name = "NAME")
	private String name;

	@XmlElement(name="ExecutionOrder")
	@Column(name = "EXECUTIONORDER")
	private int executionorder;
	
	@OneToMany(mappedBy = "program")
	@OrderBy(value = "executionorder")
	@XmlElement(name = "ProgramStep")
	private List<Step> steps;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Process.class)
	@JoinColumn(name = "PROCESSID")
	@XmlIDREF
	@XmlElement(name = "RatingProcess")
	private Process process;

	@XmlTransient
	@Transient
	private boolean executed;

	@XmlTransient
	@Transient
	private boolean localProgram;

	public Program() {
		this.name = "";
		this.process = new Process();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExecutionorder(int executionorder) {
		this.executionorder = executionorder;
	}

	public int getExecutionorder() {
		return executionorder;
	}

	public void setStep(List<Step> steps) {
		this.steps = steps;
	}

	public void addStep(Step step) {
		if(this.steps == null){
			this.steps = new ArrayList<Step>();
		}
		this.steps.add(step);
	}

	public List<Step> getSteps() {
		if(this.steps == null){
			this.steps = new ArrayList<Step>();
		}
		return steps;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@Override
	public String toString() {
		return this.process.toString() + "." + this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Program) {
			Program prg = (Program) obj;
			if (prg.getName().equals(this.name)
					&& prg.getProcess().equals(this.process) 
					// && prg.getSteps().equals(this.steps)
					) {
				return true;
			} else {
				return super.equals(obj);
			}
		} else {
			return super.equals(obj);
		}
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public boolean isLocalProgram() {
		return localProgram;
	}
	
	public void setLocalProgram(boolean localProgram) {
		this.localProgram = localProgram;
	}

}
