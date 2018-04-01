package com.mmpnc.rating.icm.content.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;
import com.mmpnc.rating.vo.RateTableEntry;
import com.mmpnc.rating.vo.UUIDGenerator;
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

public class RateTableEvaluator implements DataEvaluator {
	private List<DataEvaluator> keyvalueevaluators;
	private List<DataEvaluator> rkevals;
	private List<DataEvaluator> rvevals;
	private List<DataEvaluator> reevals;
	private Context context;
	
	public RateTableEvaluator(String constant,
			List<DataEvaluator> keyvalueevaluators,
			List<DataEvaluator> rkevals, List<DataEvaluator> rvevals,
			List<DataEvaluator> reevals) {
		this.keyvalueevaluators = keyvalueevaluators;
		this.rkevals = rkevals;
		this.rvevals = rvevals;
		this.reevals = reevals;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate() {
		RateTable ratetable = new RateTable();
		RateTable clonedRateTable = null;
		Product parentProduct = new Product();
		
		ratetable.setOverridden(false);
		
		for(DataEvaluator eval : keyvalueevaluators){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			
			if (dv.getKey().equals("ID")) {
				ratetable.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
			} else if (dv.getKey().equals("Title")) {
				ratetable.setTitle(dv.getValue());
			} else if (dv.getKey().equals("Name")) {
				ratetable.setName(dv.getValue());
			} else if(dv.getKey().equals("RatebookId")
					&& (dv.getValue() != null && !dv.getValue().equals(""))){
				parentProduct.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
				
				parentProduct = Utility.getObject((List<Product>) this.context.getValue(ContextParam.PRODUCT), parentProduct);
				ratetable.setProductId(parentProduct);
				
				//add this ratetable to the product
				ratetable = Utility.updateList(parentProduct.getRateTable(), ratetable);
				
				//add thie rate table to RateTable list
				ratetable = Utility.updateList((List<RateTable>) this.context.getValue(ContextParam.RATETABLE),ratetable);
			}			
		}
		
		List<RateTable> ratetables = (List<RateTable>) this.context.getValue(ContextParam.RATETABLE);
		ratetable = Utility.updateList(ratetables, ratetable);

		for(DataEvaluator eval : rkevals){
			eval.setContext(context);
			eval.setRateTable(ratetable);
			eval.setProduct(parentProduct);
			RateTableConfiguration keyConfig =  (RateTableConfiguration) eval.evaluate();

			keyConfig =  Utility.updateList((List<RateTableConfiguration>) this.context.getValue(ContextParam.RATETABLECONFIGURATION), keyConfig);

			keyConfig =  Utility.updateList(ratetable.getRateTableConfiguration(), keyConfig);

		}

		for(DataEvaluator eval : rvevals){
			eval.setContext(context);
			eval.setRateTable(ratetable);
			eval.setProduct(parentProduct);
			RateTableConfiguration valueConfig =  (RateTableConfiguration) eval.evaluate();
			
			valueConfig =  Utility.updateList((List<RateTableConfiguration>) this.context.getValue(ContextParam.RATETABLECONFIGURATION), valueConfig);
			
			valueConfig =  Utility.updateList(ratetable.getRateTableConfiguration(), valueConfig);
		}

		int sequence = 1;
		
		for(DataEvaluator eval : reevals){
			StringBuffer rateTableEntryIdString = new StringBuffer();
			eval.setContext(context);
			eval.setRateTable(ratetable);
			RateTableEntry rateTableEntry = (RateTableEntry) eval.evaluate();
			
			rateTableEntry.setSequence(sequence++);
			
			//update RateTable entry with correct ratebook and rateTable details.
			if(!parentProduct.equals(rateTableEntry.getProduct())){
				if(clonedRateTable == null){
//					System.out.println("we have to create entry of rate table");
					clonedRateTable = cloneRateTable(ratetable, rateTableEntry.getProduct());
				}
				
				rateTableEntryIdString.append(clonedRateTable.getId()).append("-" ).append(rateTableEntry.getSequence()).append("-").append(rateTableEntry.getStatus());
				rateTableEntry.setId(UUIDGenerator.generate(rateTableEntryIdString.toString()).toUpperCase());

				rateTableEntry.setRateTable(clonedRateTable);
				
				//add ratetableentry to cloned ratetable
				rateTableEntry = Utility.updateList(clonedRateTable.getRateTableEntry(), rateTableEntry);
				
			}else{
				
				rateTableEntryIdString.append(ratetable.getId()).append("-" ).append(rateTableEntry.getSequence()).append("-").append(rateTableEntry.getStatus());
				rateTableEntry.setId(UUIDGenerator.generate(rateTableEntryIdString.toString()).toUpperCase());
				
				rateTableEntry.setRateTable(ratetable);
				
				//add ratetableentry to parent ratetable
				rateTableEntry = Utility.addToList(ratetable.getRateTableEntry(), rateTableEntry);
			}
			
//			rateTableEntry = Utility.addToList((List<RateTableEntry>)this.context.getValue("RateTableEntries"), rateTableEntry);
		}
		
		return null;
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
	
	
	@SuppressWarnings("unchecked")
	private RateTable cloneRateTable(RateTable rateTable, Product product){
		RateTable cloneRateTable = new RateTable();
		
		//create new id for ratetable
		String rateTableUUIDString = product.getId() + "-" + rateTable.getName();
		cloneRateTable.setId(UUIDGenerator.generate(rateTableUUIDString).toUpperCase());
		
		cloneRateTable.setName(rateTable.getName());
		cloneRateTable.setTitle(rateTable.getTitle());
		cloneRateTable.setBureauRuleNumber(rateTable.getBureauRuleNumber());
		cloneRateTable.setProductId(product);
		cloneRateTable.setOverridden(true);
		cloneRateTable.setBaseRateTable(rateTable);
		
		//add thie rate table to RateTable list
		cloneRateTable = Utility.updateList((List<RateTable>) this.context.getValue(ContextParam.RATETABLE),cloneRateTable);
		
		//add this ratetable to the product
		cloneRateTable = Utility.updateList(product.getRateTable(), cloneRateTable);

		for(RateTableConfiguration config : rateTable.getRateTableConfiguration()){
			RateTableConfiguration cloneConfig = new RateTableConfiguration();
			
			//create new id for ratetableconfiguration
			String rateTableConfigUUIDString = product.getId() + "-" + cloneRateTable.getId()  + "-" + config.getName();
			cloneConfig.setId(UUIDGenerator.generate(rateTableConfigUUIDString).toUpperCase());
			
			cloneConfig.setProduct(product);
			cloneConfig.setRateTable(cloneRateTable);
			
			
			cloneConfig.setConfigurationType(config.getConfigurationType());
			cloneConfig.setName(config.getName());
			cloneConfig.setType(config.getType());
			cloneConfig.setScale(config.getScale());
			cloneConfig.setSize(config.getSize());
			cloneConfig.setLookupType(config.getLookupType());
			cloneConfig.setSequence(config.getSequence());
			
			
			//add thie ratetableconfiguraton to RateTableConfiguration list
			cloneConfig = Utility.updateList((List<RateTableConfiguration>) this.context.getValue(ContextParam.RATETABLECONFIGURATION),cloneConfig);
			
			//add this configuration to the ratetable
			cloneRateTable.getRateTableConfiguration().add(cloneConfig);			
		}
		
		return cloneRateTable;
	}

}
