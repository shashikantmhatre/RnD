package com.mmpnc.rating.dao;

import java.util.List;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Dec 23, 2014
 *
 * Description :
 *
 * Note :
 */

public interface BaseDAO {
	public <T, S> T getUniqueRecord(S session);
	public <T, S> List<T> getRecords(S session);
	public <S> void save(S session);
}
