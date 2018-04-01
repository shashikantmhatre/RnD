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
		"sequence",
		"status",
		"rateTable",
		"product",
		"column1",
		"column2",
		"column3",
		"column4",
		"column5",
		"column6",
		"column7",
		"column8",
		"column9",
		"column10",
		"nColumn1",
		"nColumn2",
		"nColumn3",
		"nColumn4",
		"nColumn5",
		"nColumn6",
		"nColumn7",
		"nColumn8",
		"nColumn9",
		"nColumn10",
		"returnValue",
		"returnNValue"
})

@Entity
@Table(name = "SIM_RateTableEntry")
@XmlRootElement(name = "RateTableEntry")
public class RateTableEntry implements Serializable {

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
	
	@Column(name="SEQUENCE")
	@XmlElement(name = "Sequence")
	private int sequence;
	
	@Column(name="STATUS")
	@XmlElement(name = "Status")
	private String status;
	
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
	
	@Column(name="COLUMN1")
	@XmlElement(name = "Column1")
	private String column1;
	
	@Column(name="COLUMN2")
	@XmlElement(name = "Column2")
	private String column2;
	
	@Column(name="COLUMN3")
	@XmlElement(name = "Column3")
	private String column3;
	
	@Column(name="COLUMN4")
	@XmlElement(name = "Column4")
	private String column4;
	
	@Column(name="COLUMN5")
	@XmlElement(name = "Column5")
	private String column5;
	
	@Column(name="COLUMN6")
	@XmlElement(name = "Column6")
	private String column6;
	
	@Column(name="COLUMN7")
	@XmlElement(name = "Column7")
	private String column7;
	
	@Column(name="COLUMN8")
	@XmlElement(name = "Column8")
	private String column8;
	
	@Column(name="COLUMN9")
	@XmlElement(name = "Column9")
	private String column9;
	
	@Column(name="COLUMN10")
	@XmlElement(name = "Column10")
	private String column10;
	
	@Column(name="NCOLUMN1", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn1")
	private Double nColumn1;
	
	@Column(name="NCOLUMN2", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn2")
	private Double nColumn2;
	
	@Column(name="NCOLUMN3", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn3")
	private Double nColumn3;
	
	@Column(name="NCOLUMN4", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn4")
	private Double nColumn4;
	
	@Column(name="NCOLUMN5", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn5")
	private Double nColumn5;
	
	@Column(name="NCOLUMN6", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn6")
	private Double nColumn6;
	
	@Column(name="NCOLUMN7", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn7")
	private Double nColumn7;
	
	@Column(name="NCOLUMN8", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn8")
	private Double nColumn8;
	
	@Column(name="NCOLUMN9", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn9")
	private Double nColumn9;
	
	@Column(name="NCOLUMN10", columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "NColumn10")
	private Double nColumn10;
	
	@Column(name="RETURNVALUE")
	@XmlElement(name = "ReturnValue")
	private String returnValue;
	
	@Column(name="RETURNNVALUE" , columnDefinition="DOUBLE PRECISION")
	@XmlElement(name = "ReturnNValue")
	private Double returnNValue;
	
	public RateTableEntry(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product productId) {
		this.product = productId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RateTable getRateTable() {
		return rateTable;
	}

	public void setRateTable(RateTable rateTableID) {
		this.rateTable = rateTableID;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getColumn6() {
		return column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}

	public String getColumn7() {
		return column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

	public String getColumn8() {
		return column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}

	public String getColumn9() {
		return column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}

	public String getColumn10() {
		return column10;
	}

	public void setColumn10(String column10) {
		this.column10 = column10;
	}

	public Double getnColumn1() {
		return nColumn1;
	}

	public void setnColumn1(Double nColumn1) {
		this.nColumn1 = nColumn1;
	}

	public Double getnColumn2() {
		return nColumn2;
	}

	public void setnColumn2(Double nColumn2) {
		this.nColumn2 = nColumn2;
	}

	public Double getnColumn3() {
		return nColumn3;
	}

	public void setnColumn3(Double nColumn3) {
		this.nColumn3 = nColumn3;
	}

	public Double getnColumn4() {
		return nColumn4;
	}

	public void setnColumn4(Double nColumn4) {
		this.nColumn4 = nColumn4;
	}

	public Double getnColumn5() {
		return nColumn5;
	}

	public void setnColumn5(Double nColumn5) {
		this.nColumn5 = nColumn5;
	}

	public Double getnColumn6() {
		return nColumn6;
	}

	public void setnColumn6(Double nColumn6) {
		this.nColumn6 = nColumn6;
	}

	public Double getnColumn7() {
		return nColumn7;
	}

	public void setnColumn7(Double nColumn7) {
		this.nColumn7 = nColumn7;
	}

	public Double getnColumn8() {
		return nColumn8;
	}

	public void setnColumn8(Double nColumn8) {
		this.nColumn8 = nColumn8;
	}

	public Double getnColumn9() {
		return nColumn9;
	}

	public void setnColumn9(Double nColumn9) {
		this.nColumn9 = nColumn9;
	}

	public Double getnColumn10() {
		return nColumn10;
	}

	public void setnColumn10(Double nColumn10) {
		this.nColumn10 = nColumn10;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public Double getReturnNValue() {
		return returnNValue;
	}

	public void setReturnNValue(Double returnNValue) {
		this.returnNValue = returnNValue;
	}

	@Override
	public boolean equals(Object entry) {

		if(entry instanceof RateTableEntry){
			if(this.id.equals(((RateTableEntry) entry).getId())){
				return true;
			}
		}
		
		return super.equals(entry);
	}
	
}
