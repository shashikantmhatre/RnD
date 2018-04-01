package com.mmpnc.rating.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;

//import com.mmpnc.rating.algorithm.dao.impl.hibernate.RatingEntityDAO;
import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
//import com.mmpnc.rating.data.dao.impl.hibernate.DomainTableDAO;
//import com.mmpnc.rating.data.dao.impl.hibernate.RateTableDAO;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RatingEntity;
import com.mmpnc.rating.vo.UUIDGenerator;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ProductDAO extends ObjectDAO {

	private Product product;
	

	public ProductDAO(Product product){
		this.product = product;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getUniqueRecord(S session) {
		boolean condition = false;

		StringBuffer sb = new StringBuffer("from Product where ");

		if (this.product.getId() != null) {
			sb = updateQuery(sb, condition, this.product, "id");
			condition = true;
		}

		if (this.product.getTitle() != null) {
			sb = updateQuery(sb, condition, this.product, "title");
			condition = true;
		}

		this.product = (Product) ((Session)session).createQuery(sb.toString()).uniqueResult();
		return (T) this.product;
	}

	@Override
	public <S> void save(S session) {
		Session hSession = (Session) session;
		
		hSession.saveOrUpdate(product);
		
//		if(product.getParentRefId()!= null){
//			BaseDAO productSave = new ProductDAO(product.getParentRefId());
//			productSave.save(hSession);
//		}
		
		//do the codding for DomainTable and RateTable save option
		
		for(RateTable rateTable : product.getRateTable()){
			
			if(rateTable.getProductId() == null){
				rateTable.setProductId(product);
			}
			
			if(rateTable.getId() == null){
				String rateTableIDKey = product.getId() + "-" + rateTable.getName();
				rateTable.setId(UUIDGenerator.generate(rateTableIDKey).toUpperCase());
			}
			
//			BaseDAO rateTableSave = new RateTableDAO(rateTable);
			BaseDAO rateTableSave = RatingDAOFactory.getRatingDAOFactory().getRateTableDAO(rateTable);
	
			rateTableSave.save(hSession);
		}
		
		for(DomainTable domainTable : product.getDomainTable()){
			if(domainTable.getProduct() == null){
				domainTable.setProduct(product);
			}
			
			if(domainTable.getId() == null){
				String rateTableIDKey = product.getId() + "-" + domainTable.getName();
				domainTable.setId(UUIDGenerator.generate(rateTableIDKey).toUpperCase());
			}
			
//			BaseDAO domainTableSave = new DomainTableDAO(domainTable);
			BaseDAO domainTableSave = RatingDAOFactory.getRatingDAOFactory().getDomainTableDAO(domainTable);
	
			domainTableSave.save(hSession);
		}
		
		for(RatingEntity entity : product.getRatingEntity()){
			if(entity.getProduct() == null){
				entity.setProduct(product);
			}
			
			if(entity.getId() == null){
				String entityIDKey = product.getId() + "-" + entity.getName();
				entity.setId(UUIDGenerator.generate(entityIDKey).toUpperCase());
			}
			
			System.out.println("Saving " + entity.getName());
			
//			BaseDAO ratingEntitySave = new RatingEntityDAO(entity);
			BaseDAO ratingEntitySave = RatingDAOFactory.getRatingDAOFactory().getRatingEntityDAO(entity);

			ratingEntitySave.save(hSession);
		}
		
	}

	@Override
	public <T, S> List<T> getRecords(S session) {
		// TODO Auto-generated method stub
		return null;
	}

}
