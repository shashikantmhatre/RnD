package com.mmpnc.rating.icm.content.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.KeyConfiguration;
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

public class KeyEvaluator implements DataEvaluator {
	private List<DataEvaluator> keyValue;
	private Context context;
	
	public KeyEvaluator(List<DataEvaluator> keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public KeyConfiguration evaluate() {
		KeyConfiguration config = new KeyConfiguration();
		
		for(DataEvaluator eval : keyValue){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			
			if(dv.getKey().equals("Name")){
				config.setName(dv.getValue());
			} else if(dv.getKey().equals("value")){
				config.setValue(dv.getValue());
			}
		}

		return config;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
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
