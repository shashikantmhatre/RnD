package com.mmpnc.rating.data.dao.impl.xml;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.connection.helper.Session;
import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
import com.mmpnc.rating.dao.impl.xml.XMLDAO;
import com.mmpnc.rating.dao.util.JAXBUtil;
import com.mmpnc.rating.vo.DomainTable;
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

public class DomainTableXMLDAO extends XMLDAO {

	private DomainTable domainTable;
	private Product product;
	private static final Logger logger = LoggerFactory.getLogger(DomainTableXMLDAO.class);

	public DomainTableXMLDAO(DomainTable domainTable) {
		this.domainTable = domainTable;
		this.product = this.domainTable.getProduct();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getUniqueRecord(S session) {
		boolean condition = false;

		StringBuffer sb = new StringBuffer(
				"for $x in /Product/DomainTable where ");

		if (this.domainTable.getId() != null) {
//			sb = updateQuery(sb, condition, domainTable, "id");
			sb.append("$x/Id").append("='").append(this.domainTable.getId()).append("' ");			
			condition = true;
		}

		if (this.domainTable.getName() != null) {
			if(condition){
				sb.append(" and ");
			}
//			sb = updateQuery(sb, condition, domainTable, "name");
			sb.append("$x/Name").append("='").append(this.domainTable.getName()).append("' ");
			condition = true;
		}

		if (this.domainTable.getProduct() != null && this.domainTable.getProduct().getId() != null) {
//			sb = updateQuery(sb, condition, "domainTable.product.id", "'"+ this.domainTable.getProduct().getId() + "'");
			sb.append("$x/Product").append("='").append(this.domainTable.getProduct().getId()).append("' ");
			condition = true;
		}
		
		sb.append("return <DomainTable>{$x/*[local-name()=('Id','Name','Title','DomainTableConfiguration')]}</DomainTable>");
		
		if(logger.isInfoEnabled()){
			logger.info("DomainTable Query -> " + sb);
		}
		
//		DomainTable domainTable = (DomainTable) ((Session)session).createQuery(sb.toString()).uniqueResult();

		String outputString = (String) ((Session)session).createQuery(sb.toString()).execute();
		DomainTable domainTable = null;
		if(outputString != null && !outputString.equals("")){
			
			try{
//				Reader stringReader = new StringReader(outputString);
//				JAXBContext jc = JAXBContext.newInstance(RateTable.class);
//				Unmarshaller um = jc.createUnmarshaller();
//				rateTable = (RateTable) um.unmarshal(stringReader);
				domainTable = JAXBUtil.getInstance().unmarshalObject(outputString);
			}catch(Exception _ex){
				_ex.printStackTrace();
			}
			
			//this was added to because XMLDB return xml string without product object in ratingentity
			if(domainTable.getProduct() == null){
				domainTable.setProduct(this.product);
			}
		}
		// if we do not find the Domain Table then find the Domain Table in the
		// parent ratebook of the current ratebook
		if (domainTable == null) {
			BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getProductDAO(this.domainTable.getProduct().getParentRefId());
			Product parentProduct = bd.getUniqueRecord(session);
			
			this.domainTable.setProduct(parentProduct);

//			BaseDAO bd = new DomainTableDAO(this.domainTable);
			bd = RatingDAOFactory.getRatingDAOFactory().getDomainTableDAO(this.domainTable);
			
			domainTable = bd.getUniqueRecord(session);
		}

		return (T) domainTable;
	}

	@Override
	public <S> void save(S session) {
		// TODO complete the domaintable save operation
		
	}

	@Override
	public <T, S> List<T> getRecords(S session) {
		// TODO Auto-generated method stub
		return null;
	}

}
