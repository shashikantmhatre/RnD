package com.mmpnc.rating.data.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableConfiguration;
import com.mmpnc.rating.vo.DomainTableEntry;
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

public class DomainTableDAO extends ObjectDAO {

	private DomainTable domainTable;
	private static final Logger logger = LoggerFactory.getLogger(DomainTableDAO.class);

	public DomainTableDAO(DomainTable domainTable) {
		this.domainTable = domainTable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, S> T getUniqueRecord(S session) {
		boolean condition = false;

		StringBuffer sb = new StringBuffer(
				"from DomainTable as domainTable where ");

		if (this.domainTable.getId() != null) {
			sb = updateQuery(sb, condition, domainTable, "id");
			condition = true;
		}

		if (this.domainTable.getName() != null) {
			sb = updateQuery(sb, condition, domainTable, "name");
			condition = true;
		}

		if (this.domainTable.getProduct() != null && this.domainTable.getProduct().getId() != null) {
			sb = updateQuery(sb, condition, "domainTable.product.id", "'"
					+ this.domainTable.getProduct().getId() + "'");
			condition = true;
		}
		
		
		if(logger.isInfoEnabled()){
			logger.info("DomainTable Query -> " + sb);
		}
		
		DomainTable domainTable = (DomainTable) ((Session)session).createQuery(
				sb.toString()).uniqueResult();

		// if we do not find the Domain Table then find the Domain Table in the
		// parent ratebook of the current ratebook
		if (domainTable == null) {
			this.domainTable.setProduct(this.domainTable.getProduct().getParentRefId());

//			BaseDAO bd = new DomainTableDAO(this.domainTable);
			BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getDomainTableDAO(this.domainTable);
			
			domainTable = bd.getUniqueRecord(session);
		}

		return (T) domainTable;
	}

	@Override
	public <S> void save(S session) {
		Session hSession = (Session) session;		
		hSession.saveOrUpdate(domainTable);
		
		for(DomainTableConfiguration config : domainTable.getDomainTableConfiguration()){
			
			if(config.getDomainTable() == null || config.getDomainTable().getId() == null){
				config.setDomainTable(domainTable);
			}
			
			if(config.getId() == null){
				String configIDKey = domainTable.getId() + "-" + config.getConfigurationType() + "-" + config.getName() ;
				config.setId(UUIDGenerator.generate(configIDKey).toUpperCase());
			}
			
//			hSession.saveOrUpdate(config);
			hSession.save(config);
		}
		
		for(DomainTableEntry entry : domainTable.getDomainTableEntry()){
			
			if(entry.getDomainTable() == null || entry.getDomainTable().getId() == null){
				entry.setDomainTable(domainTable);
			}
			
			if(entry.getProduct() == null || entry.getProduct().getId()==null){
				entry.setProduct(domainTable.getProduct());
			}
			
			if(entry.getId() == null){
				String entryIDKey = domainTable.getId() + "-" + entry.getSequence() + "-" + entry.getStatus();
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
