package com.mmpnc.rating.data.dao.impl.xml;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.connection.helper.Session;

import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.dao.util.JAXBUtil;
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

public class RateTableXMLDAO extends ObjectDAO {
	
	private RateTable rt;
	private Product product;
	private static final Logger logger = LoggerFactory.getLogger(RateTableXMLDAO.class);
	
	public RateTableXMLDAO(RateTable rateTable) {
		this.rt = rateTable;
		this.product = this.rt.getProductId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getUniqueRecord(S session) {
		boolean condition = false;

		StringBuffer sb = new StringBuffer("for $x in ");
//		sb.append("/Product[Id = '").append(this.rt.getProductId().getId()).append("']").append("/RateTable where ");
		sb.append("/Product/RateTable where ");

		if (this.rt.getId() != null) {
//			sb = updateQuery(sb, condition, rt, "id");
			sb.append("$x/Id").append("='").append(this.rt.getId()).append("' ");
			condition = true;
		}

		if (this.rt.getName() != null) {
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, rt, "name");
			sb.append("$x/Name").append("='").append(this.rt.getName()).append("' ");	
			condition = true;
		}		

		if (this.rt.getProductId() != null && this.rt.getProductId().getId() != null) {
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, "product.id", "'" + this.rt.getProductId().getId() + "'");
			sb.append("$x/Product").append("='").append(this.rt.getProductId().getId()).append("' ");
			condition = true;
		}
		
		sb.append("return <RateTable>{$x/*[local-name()=('Id','Name','Title','RateTableConfiguration')]}</RateTable>");
		
		if (logger.isInfoEnabled()) {
			logger.info("RateTable Query -> " + sb);
		}
		
		String outputString = (String) ((Session)session).createQuery(sb.toString()).execute();
		
		RateTable rateTable = null;
		
		if(outputString != null && !outputString.equals("")){
		
			try{
//				Reader stringReader = new StringReader(outputString);
//				JAXBContext jc = JAXBContext.newInstance(RateTable.class);
//				Unmarshaller um = jc.createUnmarshaller();
//				rateTable = (RateTable) um.unmarshal(stringReader);
				rateTable = JAXBUtil.getInstance().unmarshalObject(outputString);
			}catch(Exception _ex){
				_ex.printStackTrace();
			}
			
			//this was added to because XMLDB return xml string without product object in ratingentity
			if(rateTable.getProductId() == null){
				rateTable.setProductId(this.product);
			}
		}
		// if we do not find the rate table then find the ratetable in the
		// parent ratebook of the current ratebook
		if (rateTable == null) {
			BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getProductDAO(this.rt.getProductId().getParentRefId());
			Product parentProduct = bd.getUniqueRecord(session);
			
			this.rt.setProductId(parentProduct);
			
//			BaseDAO bd = new RateTableDAO(this.rt);
			bd = RatingDAOFactory.getRatingDAOFactory().getRateTableDAO(this.rt);
			
			rateTable = bd.getUniqueRecord(session);
		}
		return (T) rateTable;
	}

	@Override
	public <S> void save(S session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T, S> List<T> getRecords(S session) {
		// TODO Auto-generated method stub
		return null;
	}

}
