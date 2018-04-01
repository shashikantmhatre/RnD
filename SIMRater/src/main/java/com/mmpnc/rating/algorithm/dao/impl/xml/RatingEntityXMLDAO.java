package com.mmpnc.rating.algorithm.dao.impl.xml;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.connection.helper.Session;
import com.mmpnc.rating.dao.impl.xml.XMLDAO;
import com.mmpnc.rating.dao.util.JAXBUtil;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RatingEntity;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class RatingEntityXMLDAO extends XMLDAO {

	private RatingEntity ratingEntity;
	private static final Logger logger = LoggerFactory.getLogger(RatingEntityXMLDAO.class);
	private Product product;
	
	public RatingEntityXMLDAO(RatingEntity ratingEntity){
		this.ratingEntity = ratingEntity;
		this.product = this.ratingEntity.getProduct();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getUniqueRecord(S session) {
		boolean condition = false;
		StringBuffer sb = new StringBuffer(
				"for $x in /Product/RatingEntity where ");

		if (this.ratingEntity.getName() != null) {
//			sb = updateQuery(sb, condition, ratingEntity, "name");
			sb.append("$x/Name").append("='").append(this.ratingEntity.getName()).append("' ");	
			condition = true;
		}

		if (this.ratingEntity.getId() != null && !this.ratingEntity.getId().equals("")) {
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, ratingEntity, "id");
			sb.append("$x/Id").append("='").append(this.ratingEntity.getId()).append("' ");			
			condition = true;
		}

		if (this.ratingEntity.getProduct() != null && ( this.ratingEntity.getProduct().getId() != null && !this.ratingEntity.getProduct().getId().equals(""))) {
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, ratingEntity, "product.id");
			sb.append("$x/Product").append("='").append(this.ratingEntity.getProduct().getId()).append("' ");	
			condition = true;
		}
		
		sb.append("return <RatingEntity>{$x/*[local-name()=('Id','Name','Product')]}</RatingEntity>");
		
		if (logger.isInfoEnabled()) {
			logger.info("RatingEntity Query -> " + sb);
		}
//		System.out.println("RatingEntity XQuery is " + sb);
		
		String outputString = (String) ((Session)session).createQuery(sb.toString()).execute();
		
		if(outputString != null && !outputString.equals("")){
			
			try{
//				Reader stringReader = new StringReader(outputString);
//				JAXBContext jc = JAXBContext.newInstance(RatingEntity.class);
//				Unmarshaller um = jc.createUnmarshaller();
//				this.ratingEntity = (RatingEntity) um.unmarshal(stringReader);
				
				this.ratingEntity = JAXBUtil.getInstance().unmarshalObject(outputString);
			
			}catch(Exception _ex){
				_ex.printStackTrace();
			}
			
			//this was added to because XMLDB return xml string without product object in ratingentity
			if(this.ratingEntity.getProduct() == null){
				this.ratingEntity.setProduct(this.product);
			}
			
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("We received empty outputString for RatingEntity -> " + outputString);
			}
			this.ratingEntity = null;
		}
		
		return (T) this.ratingEntity;
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
