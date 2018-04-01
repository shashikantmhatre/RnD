package com.mmpnc.rating.dao.impl.hibernate;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.dao.BaseDAO;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public abstract class ObjectDAO implements BaseDAO {

private static final Logger logger = LoggerFactory.getLogger(ObjectDAO.class);
	
	protected StringBuffer updateQuery(StringBuffer sb, boolean condition,
			Object obj, String attribute) {
		return updateQuery(sb, condition, obj, attribute, "=");
	}
	
	protected StringBuffer updateQuery(StringBuffer sb, boolean condition,
			Object obj, String attribute, String operator) {

		try {
			if (condition) {
				sb.append(" and ");
			}
			if (PropertyUtils.getPropertyType(obj, attribute).getSimpleName() == "String") {
				sb.append(attribute).append(" ").append(operator).append(" ")
						.append(BeanUtils.getProperty(obj, attribute));
			} else {
				sb.append(attribute).append(" ").append(operator).append(" ").append("'")
						.append(BeanUtils.getProperty(obj, attribute))
						.append("'");
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException: " + e.toString());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException: " + e.toString());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.error("NoSuchMethodException: " + e.toString());
			e.printStackTrace();
		}

		return sb;
	}
	
	protected StringBuffer updateQuery(StringBuffer sb, boolean condition,
			String attribute, String value) {
		return updateQuery(sb, condition, attribute, value, "=");
	}

	protected StringBuffer updateQuery(StringBuffer sb, boolean condition,
			String attribute, String value, String operator) {
		if (condition) {
			sb.append(" and ");
		}
		sb.append(attribute).append(" ").append(operator).append(" ").append(value);
		return sb;
	}

//	public abstract <T, S> T getUniqueRecord(S session);
//	public abstract <S> void save(S session);
}
