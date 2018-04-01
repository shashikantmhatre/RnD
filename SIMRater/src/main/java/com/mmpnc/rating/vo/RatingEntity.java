package com.mmpnc.rating.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
@XmlType(name = "RatingEntity", propOrder = { "id", 
		"name", 
		"product",
		"processes"
		})
@Entity
@Table(name = "SIM_RatingEntity")
@XmlRootElement(name = "RatingEntity")
public class RatingEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@XmlElement(name = "Id")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	private String id;

	@XmlElement(name = "Name")
	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy = "entity")
	@OrderBy(value = "executionorder")
	@XmlElement(name = "RatingProcess")
	private List<Process> processes;
	
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCTID")
	@XmlIDREF
	@XmlElement(name = "Product")
	private Product product;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Process> getProcesses() {
		if (this.processes == null) {
			this.processes = new ArrayList<Process>();
		}
		return processes;
	}

	public void setProcesse(Process process) {
		if (this.processes == null) {
			this.processes = new ArrayList<Process>();
		}
		this.processes.add(process);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof RatingEntity) {
			RatingEntity entity = (RatingEntity) obj;
			if (entity != null && (entity.getName() != null && entity.getName().equals(name))) {
				return true;
			} else {
				return super.equals(obj);
			}
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public String toString() {
		return name;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

}
