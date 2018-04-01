package com.mmpnc.rating.icm.content.evaluator;

import java.util.List;
//import java.util.UUID;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.KeyConfiguration;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;
import com.mmpnc.rating.vo.RateTableEntry;
import com.mmpnc.rating.vo.ValueConfiguration;
import com.mmpnc.util.Utility;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class RateTableEntryEvaluator implements DataEvaluator {
	private List<DataEvaluator> keyvalueevaluators;
	private List<DataEvaluator> keys;
	private List<DataEvaluator> values;
	private RateTable rateTable;
	private Context context;
	
	public RateTableEntryEvaluator(String constant,
			List<DataEvaluator> keyvalueevaluators, List<DataEvaluator> keys, List<DataEvaluator> values) {
		this.keyvalueevaluators = keyvalueevaluators;
		this.keys = keys;
		this.values = values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RateTableEntry evaluate() {
		RateTableEntry rateTableEntry = new RateTableEntry();
		
		for(DataEvaluator eval : keyvalueevaluators){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			
//			rateTableEntry.setId(UUID.randomUUID().toString().toUpperCase());
			
			if (dv.getKey().equals("Sequence")) {
				rateTableEntry.setSequence(Integer.parseInt(dv.getValue()));
			} else if (dv.getKey().equals("AddChangeDeleteFlag")) {
				rateTableEntry.setStatus(dv.getValue());
			} else if(dv.getKey().equals("RatebookId")
					&& (dv.getValue() != null && !dv.getValue().equals(""))){
				Product parentProduct = new Product();
				parentProduct.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
				
				parentProduct = Utility.getObject((List<Product>) this.context.getValue(ContextParam.PRODUCT), parentProduct);
				rateTableEntry.setProduct(parentProduct);
				
			}	
		}

		//set the key columns
		for(DataEvaluator eval : keys){
			eval.setContext(context);
			KeyConfiguration keyconfig = (KeyConfiguration) eval.evaluate();
			
			for(RateTableConfiguration config : rateTable.getRateTableConfiguration()){
				if(config.getConfigurationType().equals("KEY") && config.getName().equals(keyconfig.getName())){
					
//					System.out.println(keyconfig.getName() + " : " + config.getSequence() + " : " + config.getType());
					
					VarTypes varTypes = VarTypes.valueOf(config.getType().toUpperCase());

					if (varTypes == VarTypes.NVARCHAR) {
						switch (config.getSequence()) {
						case 1:
							rateTableEntry.setColumn1(keyconfig.getValue());
							break;
						case 2:
							rateTableEntry.setColumn2(keyconfig.getValue());
							break;
						case 3:
							rateTableEntry.setColumn3(keyconfig.getValue());
							break;
						case 4:
							rateTableEntry.setColumn4(keyconfig.getValue());
							break;
						case 5:
							rateTableEntry.setColumn5(keyconfig.getValue());
							break;
						case 6:
							rateTableEntry.setColumn6(keyconfig.getValue());
							break;
						case 7:
							rateTableEntry.setColumn7(keyconfig.getValue());
							break;
						case 8:
							rateTableEntry.setColumn8(keyconfig.getValue());
							break;
						case 9:
							rateTableEntry.setColumn9(keyconfig.getValue());
							break;
						case 10:
							rateTableEntry.setColumn10(keyconfig.getValue());
							break;
						}
					} else {
						if(keyconfig.getValue().equals("")){
							continue;
						}
						Double dValue = new Double(keyconfig.getValue());
						switch (config.getSequence()) {
						case 1:
							rateTableEntry.setnColumn1(dValue);
							break;
						case 2:
							rateTableEntry.setnColumn2(dValue);
							break;
						case 3:
							rateTableEntry.setnColumn3(dValue);
							break;
						case 4:
							rateTableEntry.setnColumn4(dValue);
							break;
						case 5:
							rateTableEntry.setnColumn5(dValue);
							break;
						case 6:
							rateTableEntry.setnColumn6(dValue);
							break;
						case 7:
							rateTableEntry.setnColumn7(dValue);
							break;
						case 8:
							rateTableEntry.setnColumn8(dValue);
							break;
						case 9:
							rateTableEntry.setnColumn9(dValue);
							break;
						case 10:
							rateTableEntry.setnColumn10(dValue);
							break;
						}
					}
					
					break;
				}
			}
			
		}

		//set the value column
		for(DataEvaluator eval : values){
			eval.setContext(context);
			ValueConfiguration valueConfig = (ValueConfiguration) eval.evaluate();
			for(RateTableConfiguration config : rateTable.getRateTableConfiguration()){
				if(config.getConfigurationType().equals("VALUE") && config.getName().equals(config.getName())){
					VarTypes varTypes = VarTypes.valueOf(config.getType().toUpperCase());

					if (varTypes == VarTypes.NVARCHAR) {
						rateTableEntry.setReturnValue(valueConfig.getValue());
					} else{
						Double dValue = new Double(valueConfig.getValue());
						rateTableEntry.setReturnNValue(dValue) ;
					} 
					break;
				}
			}
		}

		return rateTableEntry;
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
		this.rateTable = rateTable;
	}

	@Override
	public void setDomainTable(DomainTable domainTable) {
		// TODO Auto-generated method stub

	}

	private enum VarTypes {
		DECIMAL, NVARCHAR, INTEGER, INT;
	}
	
}
