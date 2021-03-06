package com.mmpnc.rating.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@XmlType(name = "", propOrder = { 
		"id",
		"configurationType",
		"sequence",
		"name",
		"lookupType",
		"type",
		"description",
		"size",
		"scale",
		"product",
		"rateTable"
})

@Entity
@Table(name = "SIM_RateTableConfiguration")
@XmlRootElement(name = "RateTableConfiguration")
public class RateTableConfiguration implements Serializable {
	
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
	
	@Column(name="CONFIGURATIONTYPE")
	@XmlElement(name = "ConfigurationType")
	private String configurationType;
	
	@Column(name="SEQUENCE")
	@XmlElement(name = "Sequence")
	private int sequence;
	
	@Column(name="NAME")
	@XmlElement(name = "Name")
	private String name;
	
	@Column(name="LOOKUPTYPE")
	@XmlElement(name = "LookupType")
	private String lookupType;
	
	@Column(name="COLUMNTYPE")
	@XmlElement(name = "Type")
	private String type;
	
	@Column(name="DESCRIPTION")
	@XmlElement(name = "Description")
	private String description;
	
	@Column(name="COLUMNSIZE")
	@XmlElement(name = "Size")
	private int size;
	
	@Column(name="SCALE")
	@XmlElement(name = "Scale")
	private int scale;
	
	@ManyToOne(targetEntity = RateTable.class)
	@JoinColumn(name = "RATETABLEID")
	@XmlIDREF
	@XmlElement(name = "RateTable")
	private RateTable rateTable;
	
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCTID")
	@XmlIDREF
	@XmlElement(name = "Product")
	private Product product;
	
	public RateTableConfiguration(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(String configurationType) {
		this.configurationType = configurationType;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public RateTable getRateTable() {
		return rateTable;
	}

	public void setRateTable(RateTable rateTable) {
		this.rateTable = rateTable;
	}

	public String getLookupType() {
		return lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	@Override
	public boolean equals(Object config) {
		
		if(config instanceof RateTableConfiguration){
			
			if(this.id.equals(((RateTableConfiguration) config).getId())){
				
				if(this.product.equals(((RateTableConfiguration) config).getProduct()) && this.rateTable.equals(((RateTableConfiguration) config).getRateTable())){
					return true;
				} 
				
			}			
		}
		
		return super.equals(config);
	}
	
	
	
}
