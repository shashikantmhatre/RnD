package com.mmpnc.rating.data.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;

import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;
import com.mmpnc.rating.vo.RateTableEntry;
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

public class RateTableDAO extends ObjectDAO {
	
	private RateTable rt;

	public RateTableDAO(RateTable rateTable) {
		this.rt = rateTable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getUniqueRecord(S session) {
		boolean condition = false;

		StringBuffer sb = new StringBuffer("from RateTable as ratetable where ");

		if (this.rt.getId() != null) {
			sb = updateQuery(sb, condition, rt, "id");
			condition = true;
		}

		if (this.rt.getName() != null) {
			sb = updateQuery(sb, condition, rt, "name");
			condition = true;
		}

		if (this.rt.getProductId() != null && this.rt.getProductId().getId() != null) {
			sb = updateQuery(sb, condition, "product.id", "'"
					+ this.rt.getProductId().getId() + "'");
			condition = true;
		}

		RateTable rateTable = (RateTable) ((Session)session).createQuery(sb.toString())
				.uniqueResult();

		// if we do not find the rate table then find the ratetable in the
		// parent ratebook of the current ratebook
		if (rateTable == null) {
			this.rt.setProductId(this.rt.getProductId().getParentRefId());
			
//			BaseDAO bd = new RateTableDAO(this.rt);
			BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getRateTableDAO(this.rt);
			
			rateTable = bd.getUniqueRecord(session);
		}
		return (T) rateTable;
	}

	@Override
	public <S> void save(S session) {
		Session hSession = (Session) session;		
		hSession.saveOrUpdate(rt);
		
//		if(rateTable.getBaseRateTable() != null){
//			BaseDAO rateTableSave = new RateTableDAO(rateTable.getBaseRateTable());
//			rateTableSave.save(hSession);
//		}		
		
		for(RateTableConfiguration config : rt.getRateTableConfiguration()){
			
			if(config.getRateTable() == null || config.getRateTable().getId() == null){
				config.setRateTable(rt);
			}
			
			if(config.getId() == null){
				String configIDKey = rt.getId() + "-" + config.getConfigurationType() + "-" + config.getName() ;
				config.setId(UUIDGenerator.generate(configIDKey).toUpperCase());
			}
			
//			hSession.saveOrUpdate(config);
			hSession.save(config);
		}
		
		for(RateTableEntry entry : rt.getRateTableEntry()){
			
			if(entry.getRateTable() == null || entry.getRateTable().getId() == null){
				entry.setRateTable(rt);
			}
			
			if(entry.getProduct() == null || entry.getProduct().getId() == null){
				entry.setProduct(rt.getProductId());
			}
			
			if(entry.getId() == null){
				String entryIDKey = rt.getId() + "-" + entry.getSequence() + "-" + entry.getStatus();
				entry.setId(UUIDGenerator.generate(entryIDKey).toUpperCase());
			}
//			hSession.saveOrUpdate(entry);
			hSession.save(entry);
		}
		
	}

	@Override
	public <T, S> List<T> getRecords(S session) {
		// TODO Auto-generated method stub
		return null;
	}

}
