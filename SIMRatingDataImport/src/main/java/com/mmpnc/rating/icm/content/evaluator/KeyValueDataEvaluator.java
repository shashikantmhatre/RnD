package com.mmpnc.rating.icm.content.evaluator;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class KeyValueDataEvaluator implements DataEvaluator {
	private String key;
	private String value;
	
	public KeyValueDataEvaluator(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	
	@Override
	public Object evaluate() {
		return new DataValue(this.key, this.value.replaceAll("\"", "").replace("^", "\""));
	}

	@Override
	public void setContext(Context context) {
//		System.out.println("keyvalue called");
	}

	@Override
	public void setProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRateTable(RateTable rateTable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDomainTable(DomainTable domainTable) {
		// TODO Auto-generated method stub

	}

}
