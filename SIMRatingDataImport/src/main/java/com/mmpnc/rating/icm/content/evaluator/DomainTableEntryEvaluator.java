package com.mmpnc.rating.icm.content.evaluator;

import java.util.List;
//import java.util.UUID;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableConfiguration;
import com.mmpnc.rating.vo.DomainTableEntry;
import com.mmpnc.rating.vo.KeyConfiguration;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
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

public class DomainTableEntryEvaluator implements DataEvaluator {
	private List<DataEvaluator> keyvalueevaluators;
	private List<DataEvaluator> keys;
	private List<DataEvaluator> values;
	private DomainTable domainTable;
	private Context context;
	
	public DomainTableEntryEvaluator(String constant,
			List<DataEvaluator> keyvalueevaluators, List<DataEvaluator> keys, List<DataEvaluator> values) {
		this.keyvalueevaluators = keyvalueevaluators;
		this.keys = keys;
		this.values = values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DomainTableEntry evaluate() {
		DomainTableEntry domainTableEntry = new DomainTableEntry();
		
		for(DataEvaluator eval : keyvalueevaluators){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			
//			domainTableEntry.setId(UUID.randomUUID().toString().toUpperCase());
			
			if (dv.getKey().equals("Sequence")) {
				domainTableEntry.setSequence(Integer.parseInt(dv.getValue()));
			} else if (dv.getKey().equals("AddChangeDeleteFlag")) {
				domainTableEntry.setStatus(dv.getValue());
			} else if(dv.getKey().equals("RatebookId")
					&& (dv.getValue() != null && !dv.getValue().equals(""))){
				Product parentProduct = new Product();
				parentProduct.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
				
				parentProduct = Utility.getObject((List<Product>) this.context.getValue(ContextParam.PRODUCT), parentProduct);
				domainTableEntry.setProduct(parentProduct);
				
			}	
		}

		//set the key columns
				for(DataEvaluator eval : keys){
					eval.setContext(context);
					KeyConfiguration keyconfig = (KeyConfiguration) eval.evaluate();
					
					for(DomainTableConfiguration config : domainTable.getDomainTableConfiguration()){
						if(config.getConfigurationType().equals("KEY") && config.getName().equals(keyconfig.getName())){
							
//							System.out.println(keyconfig.getName() + " : " + config.getSequence() + " : " + config.getType());
							
							VarTypes varTypes = VarTypes.valueOf(config.getType().toUpperCase());

							if (varTypes == VarTypes.NVARCHAR) {
								switch (config.getSequence()) {
								case 1:
									domainTableEntry.setColumn1(keyconfig.getValue());
									break;
								case 2:
									domainTableEntry.setColumn2(keyconfig.getValue());
									break;
								case 3:
									domainTableEntry.setColumn3(keyconfig.getValue());
									break;
								case 4:
									domainTableEntry.setColumn4(keyconfig.getValue());
									break;
								case 5:
									domainTableEntry.setColumn5(keyconfig.getValue());
									break;
								case 6:
									domainTableEntry.setColumn6(keyconfig.getValue());
									break;
								case 7:
									domainTableEntry.setColumn7(keyconfig.getValue());
									break;
								case 8:
									domainTableEntry.setColumn8(keyconfig.getValue());
									break;
								case 9:
									domainTableEntry.setColumn9(keyconfig.getValue());
									break;
								case 10:
									domainTableEntry.setColumn10(keyconfig.getValue());
									break;
								}
							} else {
								Double dValue = new Double(keyconfig.getValue());
								switch (config.getSequence()) {
								case 1:
									domainTableEntry.setnColumn1(dValue);
									break;
								case 2:
									domainTableEntry.setnColumn2(dValue);
									break;
								case 3:
									domainTableEntry.setnColumn3(dValue);
									break;
								case 4:
									domainTableEntry.setnColumn4(dValue);
									break;
								case 5:
									domainTableEntry.setnColumn5(dValue);
									break;
								case 6:
									domainTableEntry.setnColumn6(dValue);
									break;
								case 7:
									domainTableEntry.setnColumn7(dValue);
									break;
								case 8:
									domainTableEntry.setnColumn8(dValue);
									break;
								case 9:
									domainTableEntry.setnColumn9(dValue);
									break;
								case 10:
									domainTableEntry.setnColumn10(dValue);
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
					
					domainTableEntry.setCode(valueConfig.getName());
					domainTableEntry.setDescription(valueConfig.getValue());
					domainTableEntry.setMinValue(valueConfig.getMinValue());
					domainTableEntry.setMaxValue(valueConfig.getMaxValue());
					
				}
		
		return domainTableEntry;
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
		this.domainTable = domainTable;
	}
	
	private enum VarTypes {
		DECIMAL, NVARCHAR, INTEGER, INT;
	}

}
