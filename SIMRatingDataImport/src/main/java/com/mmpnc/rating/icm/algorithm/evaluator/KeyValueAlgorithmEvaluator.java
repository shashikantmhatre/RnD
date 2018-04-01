package com.mmpnc.rating.icm.algorithm.evaluator;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.algorithm.AlgorithmEvaluator;
import com.mmpnc.rating.icm.algorithm.vo.DataValue;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Product;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class KeyValueAlgorithmEvaluator implements AlgorithmEvaluator {

	private String key;
	private String value;

	
	public KeyValueAlgorithmEvaluator(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Object evaluate() {
//		System.out.println(this.key + " : " + this.value.replaceAll("^\"|\"$", ""));
		return new DataValue(this.key, this.value.replaceAll("^\"|\"$", ""));
	}

	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProcess(Process process) {
		// TODO Auto-generated method stub
		
	}

}
