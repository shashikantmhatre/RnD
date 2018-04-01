package com.mmpnc.rating.algorithm.util;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class DateFuntion {
	
	public static int year(XMLGregorianCalendar d){
		return d.getYear();
	}
	
	public static int day(XMLGregorianCalendar d){
		return d.getDay();
	}

	public static int month(XMLGregorianCalendar d){
		return d.getMonth();
	}
}
