package com.mmpnc.rating.util;

import java.util.HashMap;
import java.util.Map;

//import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
//import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
//import com.mmpnc.rating.data.dao.impl.hibernate.DomainTableDAO;
//import com.mmpnc.rating.data.dao.impl.hibernate.DomainTableEntryDAO;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableEntry;
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

public class DomainTableExecutor implements TableExecutor {
	private Product product;
	private DomainTable dt;
	private DomainTableEntry entry;
	private Context context;
	private Object session;
	private String value;
	private String valueType;

	private static final Logger logger = LoggerFactory
			.getLogger(DomainTableExecutor.class);

	public DomainTableExecutor(Context contex) {
		this.context = contex;
		this.session = this.context.getValue(ContextParam.SESSION);
	}

	@Override
	public DomainTableExecutor execute(String domainTableName, Object[] values) {
		this.product = (Product) context.getValue(ContextParam.RATEBOOK);

		if (logger.isInfoEnabled()) {
			logger.info("Ratebook ID -> " + product.getId());
		}
		this.dt = new DomainTable();
		this.dt.setName(domainTableName);
		this.dt.setProduct(product);

		if (logger.isInfoEnabled()) {
			logger.info("Search Domain Table -> " + dt.getName());
		}

//		ObjectDAO bd = new DomainTableDAO(this.dt);
		BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getDomainTableDAO(this.dt);
		
		this.dt = bd.getUniqueRecord(this.session);

		if (logger.isInfoEnabled()) {
			logger.info("DomainTable ID -> " + this.dt.getId());
		}

		Map<String, Object> valuePairs = new HashMap<String, Object>();

		String key = null;
		Object value = null;
		String returnColumn = "";
		for (int x = 0; x < values.length; x++) {
			if (x % 2 == 0) {
				key = (String) values[x];
				if (key == null) {
					break;
				}
			} else {
				value = values[x];
				if (value != null) {
					valuePairs.put(key, value);
				} else {
					returnColumn = key;
				}
			}
		}

//		bd = new DomainTableEntryDAO(this.dt, valuePairs);	
		bd = RatingDAOFactory.getRatingDAOFactory().getDomainTableEntryDAO(this.dt, valuePairs);
		
		this.entry = bd.getUniqueRecord(session);

		if (this.entry != null) {
			if (returnColumn.equals("DisplayValue")) {
				value = this.entry.getDescription();
			} else {
				value = this.entry.getCode();
			}
		} else {
			value = "";
			valueType = "String";
		}

		return this;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getValueType() {
		return this.valueType;
	}

}
