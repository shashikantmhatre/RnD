package com.mmpnc.rating.dao.impl.xml;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.connection.helper.Session;
import com.mmpnc.rating.dao.util.JAXBUtil;
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

public class ProductXMLDAO extends XMLDAO {

	private Product product;
	private static final Logger logger = LoggerFactory.getLogger(ProductXMLDAO.class);

	public ProductXMLDAO(Product product){
		this.product = product;
	}
	
	/*
	 * (non-Javadoc)
	 * We will need to build below query to get the product
	 * 
	 * for $x in Product
	 * where $x/Id = '93BBD210-679F-4516-BA9F-3629CFE79583' and $x/Title = 'ISO Commercial Auto CA'
	 * return <Product>{$x/*[local-name()=('Id','Title','NewBusinessEffectiveDate','RenewalEffectiveDate','Product')]}</Product>
	 * 
	 */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getUniqueRecord(S session) {
		boolean condition = false;

		StringBuffer sb = new StringBuffer("for $x in /Product where ");

		if (this.product.getId() != null) {
//			sb.append("$x/").append(updateQuery(this.product, "id"));
			sb.append("$x/Id").append("='").append(this.product.getId()).append("' ");
			condition = true;
		}
		
		if (this.product.getTitle() != null) {
			if(condition){
				sb.append(" and ");
			}
			sb.append("$x/Title").append("='").append(this.product.getTitle()).append("' ");
		}
		
		sb.append("return <Product>{$x/*[local-name()=('Id','Title','State','NewBusinessEffectiveDate','RenewalEffectiveDate','Product')]}</Product>");
		
//		System.out.println("Product XQuery is " + sb);
		
		if (logger.isInfoEnabled()) {
			logger.info("Product Query -> " + sb);
		}
		
		String outputString = (String) ((Session)session).createQuery(sb.toString()).execute();
		
		if(outputString != null && !outputString.equals("")){
			
			try{
//				Reader stringReader = new StringReader(outputString);
//				JAXBContext jc = JAXBContext.newInstance(Product.class);
//				Unmarshaller um = jc.createUnmarshaller();
//				this.product = (Product) um.unmarshal(stringReader);
				this.product = JAXBUtil.getInstance().unmarshalObject(outputString);
			}catch(Exception _ex){
				_ex.printStackTrace();
			}
		} else{
			this.product = null;
		}
		
		return (T) this.product;
	}

	@Override
	public <S> void save(S session) {
// TODO - Implement the XML Data saving function
	}

	@Override
	public <T, S> List<T> getRecords(S session) {
		// TODO Auto-generated method stub
		return null;
	}

}
