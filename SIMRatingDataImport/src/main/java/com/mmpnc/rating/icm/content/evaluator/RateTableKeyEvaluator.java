package com.mmpnc.rating.icm.content.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class RateTableKeyEvaluator implements DataEvaluator {
	private List<DataEvaluator> keyvalueevaluators;
	private Context context;
	private Product product;
	private RateTable rateTable;
	
	public RateTableKeyEvaluator(String constant,
			List<DataEvaluator> keyvalueevaluators) {
		this.keyvalueevaluators = keyvalueevaluators;
	}

	@Override
	public RateTableConfiguration evaluate() {
		RateTableConfiguration configuration = new RateTableConfiguration();
		
		configuration.setConfigurationType("KEY");
		configuration.setProduct(this.product);
		configuration.setRateTable(this.rateTable);
		
		for(DataEvaluator eval : keyvalueevaluators){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			if (dv.getKey().equals("ID")) {
				configuration.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
			} else if (dv.getKey().equals("Sequence") && !dv.getValue().equals("")) {
				configuration.setSequence(Integer.parseInt(dv.getValue()));
			} else if (dv.getKey().equals("Name")) {
				configuration.setName(dv.getValue());
			} else if (dv.getKey().equals("LookupType")) {
				configuration.setLookupType(dv.getValue());
			} else if (dv.getKey().equals("Type")) {
				configuration.setType(dv.getValue());
			} else if (dv.getKey().equals("Size") && !dv.getValue().equals("")) {
				configuration.setSize(Integer.parseInt(dv.getValue()));
			} else if (dv.getKey().equals("Scale") && !dv.getValue().equals("")) {
				configuration.setScale(Integer.parseInt(dv.getValue()));
			}
		}
		
		return configuration;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public void setRateTable(RateTable rateTable) {
		this.rateTable = rateTable;
	}

	@Override
	public void setDomainTable(DomainTable domainTable) {
		// TODO Auto-generated method stub

	}

}
