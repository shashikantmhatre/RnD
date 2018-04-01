package com.mmpnc.rating.icm.content.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableConfiguration;
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

public class DomainTableValueEvaluator implements DataEvaluator {
	private List<DataEvaluator> keyvalueevaluators;
	private Context context;
	private Product product;
	private DomainTable domainTable;
	
	public DomainTableValueEvaluator(String constant,
			List<DataEvaluator> keyvalueevaluators) {
		this.keyvalueevaluators = keyvalueevaluators;
	}

	@Override
	public Object evaluate() {
		DomainTableConfiguration configuration = new DomainTableConfiguration();
		
		configuration.setConfigurationType("VALUE");
		configuration.setProduct(this.product);
		configuration.setDomainTable(this.domainTable);
		
		for(DataEvaluator eval : keyvalueevaluators){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			if (dv.getKey().equals("ColumnId")) {
				configuration.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
			} else if (dv.getKey().equals("Sequence") && !dv.getValue().equals("")) {
				configuration.setSequence(Integer.parseInt(dv.getValue()));
			} else if (dv.getKey().equals("ColumnName")) {
				configuration.setName(dv.getValue());
			} else if (dv.getKey().equals("ColumnType")) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setDomainTable(DomainTable domainTable) {
		this.domainTable = domainTable;
	}

}
