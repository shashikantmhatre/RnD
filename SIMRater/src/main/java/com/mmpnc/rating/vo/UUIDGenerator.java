package com.mmpnc.rating.vo;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class UUIDGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
		return UUID.randomUUID().toString();		
	}

	public static String generate(String uuidString)
			throws HibernateException {
		return UUID.nameUUIDFromBytes(uuidString.getBytes()).toString();		
	}

}
