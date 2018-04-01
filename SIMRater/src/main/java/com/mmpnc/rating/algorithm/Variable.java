package com.mmpnc.rating.algorithm;

import java.math.BigDecimal;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class Variable {

	private String name;
	private String type;
	private BigDecimal numValue;
	private String charValue;
	
	public Variable(String name, String type){
		this.name = name;
		this.type = type;
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
	public BigDecimal getNumValue() {
		return numValue;
	}
	public void setNumValue(BigDecimal numValue) {
		this.numValue = numValue;
	}
	public String getCharValue() {
		return charValue;
	}
	public void setCharValue(String charValue) {
		this.charValue = charValue;
	}
	
	
}
