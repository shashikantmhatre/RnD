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
		"name",
		"title",
		"type",
		"overridden",
		"bureauRuleNumber",
		"product",
		"baseDomainTable",
		"domainTableConfiguration",
		"domainTableEntry"
})

@Entity
@Table(name = "SIM_DomainTable")
@XmlRootElement(name = "DomainTable")
public class DomainTable implements Serializable {
	
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
	private String name;
	
	@Column(name = "TITLE")
	@XmlElement(name = "Title")
	private String title;
	
	@Column(name = "TYPE")
	@XmlElement(name = "Type")
	private String type;
	
	@Column(name = "OVERRIDDEN")
	@XmlElement(name = "Overridden")
	private boolean overridden;
	
	@Column(name = "BUREAURULENUMBER")
	@XmlElement(name = "BureauRuleNumber")
	private String bureauRuleNumber;
	
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCTID")
	@XmlIDREF
	@XmlElement(name = "Product")
	private Product product;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = DomainTable.class)
	@JoinColumn(name = "PARENTREFID")
	@XmlIDREF
	@XmlElement(name = "DomainTable")
	private DomainTable baseDomainTable;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domainTable")
	@XmlElement(name = "DomainTableConfiguration")
	private List<DomainTableConfiguration> domainTableConfiguration;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domainTable")
	@XmlElement(name = "DomainTableEntry")
	private List<DomainTableEntry> domainTableEntry;
	
	public DomainTable(){
		
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOverridden() {
		return overridden;
	}

	public void setOverridden(boolean overridden) {
		this.overridden = overridden;
	}

	public String getBureauRuleNumber() {
		return bureauRuleNumber;
	}

	public void setBureauRuleNumber(String bureauRuleNumber) {
		this.bureauRuleNumber = bureauRuleNumber;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<DomainTableConfiguration> getDomainTableConfiguration() {
		if(domainTableConfiguration == null){
			domainTableConfiguration = new ArrayList<DomainTableConfiguration>();
		}
		return domainTableConfiguration;
	}

	public void setDomainTableConfiguration(
			List<DomainTableConfiguration> domainTableConfiguration) {
		this.domainTableConfiguration = domainTableConfiguration;
	}

	public List<DomainTableEntry> getDomainTableEntry() {
		if(domainTableEntry == null){
			domainTableEntry = new ArrayList<DomainTableEntry>();
		}
		return domainTableEntry;
	}

	public void setDomainTableEntry(List<DomainTableEntry> domainTableEntry) {
		this.domainTableEntry = domainTableEntry;
	}
	
	@Override
	public boolean equals(Object domaintable) {
		if(domaintable instanceof DomainTable){
			if (this.id.equals(((DomainTable) domaintable).getId())){
				if(((DomainTable) domaintable).getProduct()!= null && this.product != null){
					if(this.product.equals(((DomainTable) domaintable).getProduct())){
						return true;
					} else {
						return false;
					}
				} else{
					return true;
				}
			}
		}
		
		return super.equals(domaintable);
	}

	public DomainTable getBaseDomainTable() {
		return baseDomainTable;
	}

	public void setBaseDomainTable(DomainTable baseDomainTable) {
		this.baseDomainTable = baseDomainTable;
	}	
	
}
