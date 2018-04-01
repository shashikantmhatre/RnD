package com.mmpnc.rating.vo;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.mmpnc.policy.iso.vo
	 * 
	 */
	public ObjectFactory() {
	}

	public Product createRatebook() {
		return new Product();
	}

	public RateTable createRateTable(){
		return new RateTable();
	}
	
}
