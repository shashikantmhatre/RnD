package com.mmpnc.rating.icm.algorithm.vo;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class Argument {

	private String type;
	private String value;
	private int sequence;
	
	public int getSequence() {
		return sequence;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setSequence(int i) {
		this.sequence = i;
	}

}
