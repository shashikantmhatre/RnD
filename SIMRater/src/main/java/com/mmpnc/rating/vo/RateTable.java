package com.mmpnc.rating.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@XmlType(name = "RateTable", propOrder = { 
		"id",
		"Name",
		"Title",
		"overridden",
		"BureauRuleNumber",
		"product",
		"baseRateTable",
		"rateTableConfiguration",
		"rateTableEntry"
})

@Entity
@Table(name = "SIM_RateTable")
@XmlRootElement(name = "RateTable")
public class RateTable implements Serializable {
	
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
	
	@Column(name = "NAME")
	@XmlElement(name = "Name")
	private String Name;
	
	@Column(name = "TITLE")
	@XmlElement(name = "Title")
	private String Title;
	
	@Column(name = "OVERRIDDEN")
	@XmlElement(name = "Overridden")
	private boolean overridden;
	
	@Column(name = "BUREAURULENUMBER")
	@XmlElement(name = "BureauRuleNumber")
	private String BureauRuleNumber;
	
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCTID")
	@XmlIDREF
	@XmlElement(name = "Product")
	private Product product;
	
	@OneToOne(targetEntity = RateTable.class)
	@JoinColumn(name = "PARENTREFID")
	@XmlIDREF
	@XmlElement(name = "RateTable")
	private RateTable baseRateTable;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rateTable")
	@XmlElement(name = "RateTableConfiguration")
	@OrderBy(value="sequence")
	private List<RateTableConfiguration> rateTableConfiguration;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rateTable")
	@XmlElement(name = "RateTableEntry")
	private List<RateTableEntry> rateTableEntry;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getBureauRuleNumber() {
		return BureauRuleNumber;
	}
	public void setBureauRuleNumber(String bureauRuleNumber) {
		BureauRuleNumber = bureauRuleNumber;
	}
	public Product getProductId() {
		return product;
	}
	public void setProductId(Product productId) {
		this.product = productId;
	}
	
	public boolean isOverridden() {
		return overridden;
	}
	public void setOverridden(boolean overridden) {
		this.overridden = overridden;
	}
	public List<RateTableEntry> getRateTableEntry() {
		if(rateTableEntry == null){
			rateTableEntry = new ArrayList<RateTableEntry>();
		}
		return rateTableEntry;
	}
	public void setRateTableEntry(List<RateTableEntry> rateTableEntry) {
		this.rateTableEntry = rateTableEntry;
	}
	public List<RateTableConfiguration> getRateTableConfiguration() {
		if(rateTableConfiguration == null){
			rateTableConfiguration = new ArrayList<RateTableConfiguration>();
		}
		return rateTableConfiguration;
	}
	public void setRateTableConfiguration(List<RateTableConfiguration> rateTableConfiguration) {
		this.rateTableConfiguration = rateTableConfiguration;
	}

	@Override
	public boolean equals(Object ratetable) {
		if(ratetable instanceof RateTable){
			if (this.id.equals(((RateTable) ratetable).getId())){
				if(((RateTable) ratetable).getProductId()!= null && this.product != null){
					if(this.product.equals(((RateTable) ratetable).getProductId())){
						return true;
					} else {
						return false;
					}
				} else{
					return true;
				}
			}
		}
		
		return super.equals(ratetable);
	}
	public RateTable getBaseRateTable() {
		return baseRateTable;
	}
	public void setBaseRateTable(RateTable baseRateTable) {
		this.baseRateTable = baseRateTable;
	}	
}
