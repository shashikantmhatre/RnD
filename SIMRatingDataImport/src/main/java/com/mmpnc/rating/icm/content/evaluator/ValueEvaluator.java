package com.mmpnc.rating.icm.content.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.ValueConfiguration;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ValueEvaluator implements DataEvaluator {
	private List<DataEvaluator> keyValue;
	private Context context;
	
	public ValueEvaluator(List<DataEvaluator> keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public ValueConfiguration evaluate() {
		ValueConfiguration config = new ValueConfiguration();
		
		for(DataEvaluator eval : keyValue){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			
			if(dv.getKey().equals("Name")){
				config.setName(dv.getValue());
			} else if(dv.getKey().equals("value")){
				config.setValue(dv.getValue());
			} else if(dv.getKey().equals("DataValue")){
				config.setName(dv.getValue());
			} else if(dv.getKey().equals("DisplayValue")){
				config.setValue(dv.getValue());
			} else if(dv.getKey().equals("MinimumValue")){
				if(!dv.getValue().equals("")){
					config.setMinValue(Double.parseDouble(dv.getValue()));
				}
			} else if(dv.getKey().equals("MaximumValue")){
				if(!dv.getValue().equals("")){
					config.setMaxValue(Double.parseDouble(dv.getValue()));
				}
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
