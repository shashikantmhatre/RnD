package com.mmpnc.rating.icm.content.evaluator;

import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableConfiguration;
import com.mmpnc.rating.vo.DomainTableEntry;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
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

public class DomainTableEvaluator implements DataEvaluator {

	private List<DataEvaluator> keyvalueevaluators;
	private List<DataEvaluator> dkevals;
	private List<DataEvaluator> dvevals;
	private List<DataEvaluator> deevals;
	private Context context;
	
	public DomainTableEvaluator(String constant,
			List<DataEvaluator> keyvalueevaluators,
			List<DataEvaluator> dkevals, List<DataEvaluator> dvevals,
			List<DataEvaluator> deevals) {
		this.keyvalueevaluators = keyvalueevaluators;
		this.dkevals = dkevals;
		this.dvevals = dvevals;
		this.deevals = deevals;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate() {
		DomainTable domainTable = new DomainTable();
		DomainTable clonedDomainTable = null;
		Product parentProduct = new Product();
		
		domainTable.setOverridden(false);
		
		for(DataEvaluator eval : keyvalueevaluators){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			
			if (dv.getKey().equals("ID")) {
				domainTable.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
			} else if (dv.getKey().equals("Title")) {
				domainTable.setTitle(dv.getValue());
			} else if (dv.getKey().equals("Name")) {
				domainTable.setName(dv.getValue());
			} else if(dv.getKey().equals("RatebookId")
					&& (dv.getValue() != null && !dv.getValue().equals(""))){
				parentProduct.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
				
				parentProduct = Utility.getObject((List<Product>) this.context.getValue(ContextParam.PRODUCT), parentProduct);
				domainTable.setProduct(parentProduct);
				
				//add this DomainTable to the product
				domainTable = Utility.updateList(parentProduct.getDomainTable(), domainTable);
				
				//add thie rate table to DomainTable list
				domainTable = Utility.updateList((List<DomainTable>) this.context.getValue(ContextParam.DOMAINTABLE),domainTable);
			}			
		}
		
		List<DomainTable> DomainTables = (List<DomainTable>) this.context.getValue(ContextParam.DOMAINTABLE);
		domainTable = Utility.updateList(DomainTables, domainTable);

		for(DataEvaluator eval : dkevals){
			eval.setContext(context);
			eval.setDomainTable(domainTable);
			eval.setProduct(parentProduct);
			DomainTableConfiguration keyConfig =  (DomainTableConfiguration) eval.evaluate();

			keyConfig =  Utility.updateList((List<DomainTableConfiguration>) this.context.getValue(ContextParam.DOMAINTABLECONFIGURATION), keyConfig);

			keyConfig =  Utility.updateList(domainTable.getDomainTableConfiguration(), keyConfig);

		}

		for(DataEvaluator eval : dvevals){
			eval.setContext(context);
			eval.setDomainTable(domainTable);
			eval.setProduct(parentProduct);
			DomainTableConfiguration valueConfig =  (DomainTableConfiguration) eval.evaluate();
			
			valueConfig =  Utility.updateList((List<DomainTableConfiguration>) this.context.getValue(ContextParam.DOMAINTABLECONFIGURATION), valueConfig);
			
			valueConfig =  Utility.updateList(domainTable.getDomainTableConfiguration(), valueConfig);
		}

		
		
		int sequence = 1;
		
		for(DataEvaluator eval : deevals){
			StringBuffer domainTableEntryIdString = new StringBuffer();
			eval.setContext(context);
			eval.setDomainTable(domainTable);
			DomainTableEntry domainTableEntry = (DomainTableEntry) eval.evaluate();
			
			domainTableEntry.setSequence(sequence++);
			
			//update DomainTable entry with correct ratebook and DomainTable details.
			if(!parentProduct.equals(domainTableEntry.getProduct())){
				if(clonedDomainTable == null){
//					System.out.println("we have to create entry of rate table");
					clonedDomainTable = cloneDomainTable(domainTable, domainTableEntry.getProduct());
					
				} 

				domainTableEntryIdString.append(clonedDomainTable.getId()).append("-" ).append(domainTableEntry.getSequence()).append("-").append(domainTableEntry.getStatus());
				domainTableEntry.setId(UUIDGenerator.generate(domainTableEntryIdString.toString()).toUpperCase());
				
				domainTableEntry.setDomainTable(clonedDomainTable);
				
				//add DomainTableentry to cloned DomainTable
				domainTableEntry = Utility.updateList(clonedDomainTable.getDomainTableEntry(), domainTableEntry);
				
			}else{
				
				domainTableEntryIdString.append(domainTable.getId()).append("-" ).append(domainTableEntry.getSequence()).append("-").append(domainTableEntry.getStatus());
				domainTableEntry.setId(UUIDGenerator.generate(domainTableEntryIdString.toString()).toUpperCase());
				domainTableEntry.setDomainTable(domainTable);
				
				//add DomainTableentry to parent DomainTable
				domainTableEntry = Utility.addToList(domainTable.getDomainTableEntry(), domainTableEntry);
			}
		
//			domainTableEntry = Utility.addToList((List<DomainTableEntry>)this.context.getValue("DomainTableEntries"), domainTableEntry);
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
	private DomainTable cloneDomainTable(DomainTable domainTable, Product product){
		DomainTable cloneDomainTable = new DomainTable();
		
		String domainTableUUIDString = product.getId() + "-" + domainTable.getName();
		cloneDomainTable.setId(UUIDGenerator.generate(domainTableUUIDString).toUpperCase());
		
		
		cloneDomainTable.setName(domainTable.getName());
		cloneDomainTable.setTitle(domainTable.getTitle());
		cloneDomainTable.setBureauRuleNumber(domainTable.getBureauRuleNumber());
		cloneDomainTable.setProduct(product);
		cloneDomainTable.setOverridden(true);
		cloneDomainTable.setBaseDomainTable(domainTable);
		
		//add thie rate table to DomainTable list
		cloneDomainTable = Utility.updateList((List<DomainTable>) this.context.getValue(ContextParam.DOMAINTABLE),cloneDomainTable);
		
		//add this DomainTable to the product
		cloneDomainTable = Utility.updateList(product.getDomainTable(), cloneDomainTable);

		for(DomainTableConfiguration config : domainTable.getDomainTableConfiguration()){
			DomainTableConfiguration cloneConfig = new DomainTableConfiguration();
			cloneConfig.setProduct(product);
			cloneConfig.setDomainTable(cloneDomainTable);
			
			String domainTableConfigUUIDString = product.getId() + "-" + cloneDomainTable.getId()  + "-" + config.getId() + "-" + config.getName();
			cloneConfig.setId(UUIDGenerator.generate(domainTableConfigUUIDString).toUpperCase());
			
			cloneConfig.setConfigurationType(config.getConfigurationType());
			cloneConfig.setName(config.getName());
			cloneConfig.setType(config.getType());
			cloneConfig.setScale(config.getScale());
			cloneConfig.setSize(config.getSize());
			cloneConfig.setLookupType(config.getLookupType());
			cloneConfig.setSequence(config.getSequence());
			
			
			//add thie DomainTableconfiguraton to DomainTableConfiguration list
			cloneConfig = Utility.updateList((List<DomainTableConfiguration>) this.context.getValue(ContextParam.DOMAINTABLECONFIGURATION),cloneConfig);
			
			//add this configuration to the DomainTable
			cloneDomainTable.getDomainTableConfiguration().add(cloneConfig);			
		}
		
		return cloneDomainTable;
	}

}
