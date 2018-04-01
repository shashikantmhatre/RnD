package com.mmpnc.rating.util;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public interface TableExecutor {
	TableExecutor execute(String tableName, Object[] variables);
	Object getValue();
	String getValueType();
}
