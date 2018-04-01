package com.mmpnc.rating.icm.content.evaluator;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.content.DataEvaluator;
import com.mmpnc.rating.vo.DataValue;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
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

public class RatebookEvaluator implements DataEvaluator {
	private List<DataEvaluator> keyvalueevaluators;
	private DataEvaluator rb;
	private Context context;
	
	public RatebookEvaluator(String constant,
			List<DataEvaluator> keyvalueevaluators, DataEvaluator rb) {
		this.keyvalueevaluators = keyvalueevaluators;
		this.rb = rb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate() {
		Product product = new Product();
		Product parentProduct = null;
		
		for(DataEvaluator eval : keyvalueevaluators){
			eval.setContext(context);
			DataValue dv = (DataValue) eval.evaluate();
			
			if (dv.getKey().equals("ID")) {
				product.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
			} else if (dv.getKey().equals("Title")) {
				product.setTitle(dv.getValue());
			} else if (dv.getKey().equals("NewBusinessEffective")) {
				try {
					Calendar calNewBusinessEffective = Utility.getDate(dv
							.getValue());
					product.setNewbusinessEffectiveDate(calNewBusinessEffective);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (dv.getKey().equals("NewBusinessExpiration")
					&& (dv.getValue() != null && !dv.getValue().equals(""))) {
				try {
					Calendar calNewBusinessExpiration = Utility.getDate(dv
							.getValue());
					product.setNewbusinessExpirationDate(calNewBusinessExpiration);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (dv.getKey().equals("RenewalEffective")) {
				try {
					Calendar calNewBusinessEffective = Utility.getDate(dv
							.getValue());
					product.setRenewalEffectiveDate(calNewBusinessEffective);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (dv.getKey().equals("RenewalExpiration")
					&& (dv.getValue() != null && !dv.getValue().equals(""))) {
				try {
					Calendar calNewBusinessExpiration = Utility.getDate(dv
							.getValue());
					product.setRenewalExpirationDate(calNewBusinessExpiration);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if(dv.getKey().equals("ParentRatebookId")
					&& (dv.getValue() != null && !dv.getValue().equals(""))){
				parentProduct = new Product();
				parentProduct.setId(dv.getValue().replaceAll("^\\{|\\}$", ""));
			}
		
		}
		
		List<Product> products = (List<Product>) this.context.getValue(ContextParam.PRODUCT);

		//this will add the first product
//		if(products.size() == 0){
			product = Utility.updateList(products, product);
//		} 
		
		if(this.rb != null){
			//this will add all the child products
			this.rb.setContext(context);
			Product parent = (Product) this.rb.evaluate();
			parent = Utility.updateList(products, parent);		
		} 
		
		if(parentProduct != null){
			parentProduct = Utility.getObject(products, parentProduct);
			product.setParentRefId(parentProduct);
		}
		
		return product;
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
