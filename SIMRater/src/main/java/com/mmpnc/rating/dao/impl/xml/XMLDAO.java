package com.mmpnc.rating.dao.impl.xml;

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

public abstract class XMLDAO implements BaseDAO {

private static final Logger logger = LoggerFactory.getLogger(XMLDAO.class);
	
	protected StringBuffer updateQuery(Object obj, String attribute) {
		return updateQuery(obj, attribute, "=");
	}
	
	protected StringBuffer updateQuery(Object obj, String attribute, String operator) {
		StringBuffer sb = new StringBuffer();
		try {
			
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
	
	protected StringBuffer updateQuery(String attribute, String value) {
		return updateQuery(attribute, value, "=");
	}

	protected StringBuffer updateQuery(String attribute, String value, String operator) {
		StringBuffer sb = new StringBuffer();
		sb.append(attribute).append(" ").append(operator).append(" ").append(value);
		return sb;
	}

}
