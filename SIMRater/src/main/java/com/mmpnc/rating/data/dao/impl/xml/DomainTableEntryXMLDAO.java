package com.mmpnc.rating.data.dao.impl.xml;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableConfiguration;
import com.mmpnc.rating.vo.DomainTableEntry;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class DomainTableEntryXMLDAO extends ObjectDAO {

	private DomainTable dt;
	private Map<String, Object> values;

	private static final Logger logger = LoggerFactory
			.getLogger(DomainTableEntryXMLDAO.class);

	public DomainTableEntryXMLDAO(DomainTable dt,
			Map<String, Object> values) {
		super();
		this.dt = dt;
		this.values = values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T,S> T getUniqueRecord(S session) {
		boolean condition = false;
		DomainTableEntry entry = null;

		StringBuffer sb = new StringBuffer(
				"from DomainTableEntry as domainTableEntry where ");

		if (this.dt != null && dt.getId() != null) {
			sb = updateQuery(sb, condition, "domainTableEntry.domainTable.id",
					"'" + this.dt.getId() + "'");
			condition = true;
		}

		sb = updateQuery(sb, condition, "domainTableEntry.status", "'A'");

		if (logger.isInfoEnabled()) {
			logger.info("Query -> " + sb.toString());
		}

		StringBuffer countString = new StringBuffer();
		countString.append("Select count(id) ");
		countString.append(sb);

		long count = (Long) ((Session)session).createQuery(countString.toString())
				.uniqueResult();

		logger.info("MAP -> " + values.toString());

		if (count > 0) {
			if (logger.isInfoEnabled()) {
				logger.info("Records Found");
			}

			if (values.containsKey("DataValue")) {
				sb = updateQuery(sb, condition, "domainTableEntry.code",
						"'" + values.get("DataValue") + "'");
				condition = true;
			}

			if (values.containsKey("DisplayValue")) {
				sb = updateQuery(sb, condition, "domainTableEntry.description",
						"'" + values.get("DisplayValue") + "'");
				condition = true;
			}

			for (DomainTableConfiguration dtConfig : dt.getDomainTableConfiguration()) {

				if(dtConfig.getConfigurationType().equals("VALUE")){
					continue;
				}	
				
				StringBuffer left = new StringBuffer();
				left.append("domainTableEntry.column").append(
						dtConfig.getSequence());

				StringBuffer right = new StringBuffer();
				right.append("'").append(values.get(dtConfig.getName()))
						.append("'");

				sb = updateQuery(sb, condition, left.toString(),
						right.toString());
				condition = true;

			}

			if (logger.isInfoEnabled()) {
				logger.info("Query -> " + sb.toString());
			}
			entry = (DomainTableEntry) ((Session)session).createQuery(sb.toString())
					.uniqueResult();

		} else {
			if (logger.isInfoEnabled()) {
				logger.info("no record");
			}
		}

		return (T) entry;
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
