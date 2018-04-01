package com.mmpnc.rating.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
//import javax.xml.bind.annotation.XmlTransient;
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
@XmlType(name = "RatingStep", propOrder = {
		"id",
		"executionorder",
		"block",
		"process",
		"program"
})
@Entity
@Table(name = "SIM_RatingStep")
public class Step {
	@Id
	@Column(name = "ID")
	@XmlElement(name="Id")
	@XmlID
	private String id;
	
	@XmlElement(name="ExecutionOrder")
	@Column(name = "EXECUTIONORDER")
	private int executionorder;

	@Column(length = 10000, name="BLOCK")
	@Lob
	@XmlElement(name="Block")
	private String block;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Process.class)
	@JoinColumn(name = "PROCESSID")
	@XmlIDREF
	@XmlElement(name = "RatingProcess")
	private Process process;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Program.class)
	@JoinColumn(name = "PROGRAMID")
 	@XmlIDREF
	@XmlElement(name = "RatingProgram")
	private Program program;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public void setExecutionorder(int executionorder) {
		this.executionorder = executionorder;
	}

	public int getExecutionorder() {
		return executionorder;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Program getProgram() {
		return program;
	}

	@Override
	public String toString() {
		return process.toString() + " [ " + this.block + " ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Step) {
			Step step = (Step) obj;
			if (step.getBlock().equals(this.block)) {
				if(step.getProcess()!= null && step.getProcess().getName().equals(this.process.getName())){
					if(step.getProgram()!= null && step.getProgram().getName().equals(this.program.getName())){
						return true;
					} else if(step.getProgram() == null){
						return true;
					} else{
						return false;
					}
				}else if(step.getProgram()!= null && step.getProgram().getName().equals(this.program.getName())){
					return true;
				}else{
					return false;
				}
			} else {
				return super.equals(obj);
			}
		} else {
			return super.equals(obj);
		}
	}
}
