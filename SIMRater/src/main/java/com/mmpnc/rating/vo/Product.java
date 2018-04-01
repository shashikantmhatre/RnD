package com.mmpnc.rating.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
//import javax.xml.bind.annotation.XmlIDREF;
//import javax.xml.bind.annotation.XmlIDREF;
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
		"id","lob","program","state", 
		"title", 
		"newbusinessEffectiveDate",
		"newbusinessExpirationDate", 
		"renewalEffectiveDate", 
		"renewalExpirationDate",
		"parentRefId",
		"rateTable",
		"domainTable",
		"ratingEntity"
})

@Entity
@Table(name = "SIM_Product")
@XmlRootElement(name = "Product")
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(generator="UUIDGenerator")
//	@GenericGenerator(name="UUIDGenerator", strategy="com.mmpnc.rating.icm.content.vo.UUIDGenerator")
	@Column(name = "ID")
	@XmlElement(name = "Id")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	private String id;
	
	@Column(name = "LOB")
	@XmlElement(name = "LOB")
	private String lob;
	
	@Column(name = "PROGRAM")
	@XmlElement(name = "Program")
	private String program;
	
	@Column(name = "STATE")
	@XmlElement(name = "State")
	private String state;
	
	@Column(name = "TITLE")
	@XmlElement(name = "Title")
	private String title;
	
	@Column(name = "NEWBUSINESSEFFECTIVEDATE")
	@XmlElement(name = "NewBusinessEffectiveDate")
	private Calendar newbusinessEffectiveDate;
	
	@Column(name = "NEWBUSINESSEXPIRATIONDATE")
	@XmlElement(name = "NewBusinessExpirationDate")
	private Calendar newbusinessExpirationDate;
	
	@Column(name = "RENEWALEFFECTIVEDATE")
	@XmlElement(name = "RenewalEffectiveDate")
	private Calendar renewalEffectiveDate;
	
	@Column(name = "RENEWALEXPIRATIONDATE")
	@XmlElement(name = "RenewalExpirationDate")
	private Calendar renewalExpirationDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@XmlElement(name = "RateTable")
	private List<RateTable> rateTable;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@XmlElement(name = "DomainTable")
	private List<DomainTable> domainTable;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@XmlElement(name = "RatingEntity")
	private List<RatingEntity> ratingEntity;
	
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
	@JoinColumn(name = "PARENTREFID")
	@XmlElement(name = "Product")
//	@XmlIDREF
	private Product parentRefId;
	
	public Calendar getNewbusinessEffectiveDate() {
		return newbusinessEffectiveDate;
	}

	public void setNewbusinessEffectiveDate(Calendar newbusinessEffectiveDate) {
		this.newbusinessEffectiveDate = newbusinessEffectiveDate;
	}

	public Calendar getNewbusinessExpirationDate() {
		return newbusinessExpirationDate;
	}

	public void setNewbusinessExpirationDate(Calendar newbusinessExpirationDate) {
		this.newbusinessExpirationDate = newbusinessExpirationDate;
	}

	public Calendar getRenewalEffectiveDate() {
		return renewalEffectiveDate;
	}

	public void setRenewalEffectiveDate(Calendar renewalEffectiveDate) {
		this.renewalEffectiveDate = renewalEffectiveDate;
	}

	public Calendar getRenewalExpirationDate() {
		return renewalExpirationDate;
	}

	public void setRenewalExpirationDate(Calendar renewalExpirationDate) {
		this.renewalExpirationDate = renewalExpirationDate;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Product(){
		
	}

	public String getId() {
		if(id == null){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
			
			String productIDKey = lob + "-" + program + "-" + state + "-" + sf.format(newbusinessEffectiveDate.getTime()) + "-" + sf.format(renewalEffectiveDate.getTime());
			id = UUIDGenerator.generate(productIDKey).toUpperCase();
		}
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Product getParentRefId() {
		return parentRefId;
	}

	public void setParentRefId(Product parentRefId) {
		this.parentRefId = parentRefId;
	}
	
	@Override
	public boolean equals(Object prod) {
		if(prod instanceof Product){
			if(this.id.equals(((Product) prod).getId())){
				return true;
			}
		} 
		return super.equals(prod);
	}

	public List<RateTable> getRateTable() {
		if (rateTable == null){
			rateTable = new ArrayList<RateTable>();
		}
		return rateTable;
	}

	public void setRateTable(List<RateTable> rateTable) {
		this.rateTable = rateTable;
	}

	public List<DomainTable> getDomainTable() {
		if (domainTable == null){
			domainTable = new ArrayList<DomainTable>();
		}
		return domainTable;
	}

	public void setDomainTable(List<DomainTable> domainTable) {
		this.domainTable = domainTable;
	}

	public List<RatingEntity> getRatingEntity() {
		if(ratingEntity == null){
			this.ratingEntity = new ArrayList<RatingEntity>();
		}
		return ratingEntity;
	}

	public void setRatingEntity(List<RatingEntity> ratingEntity) {
		this.ratingEntity = ratingEntity;
	}

}
