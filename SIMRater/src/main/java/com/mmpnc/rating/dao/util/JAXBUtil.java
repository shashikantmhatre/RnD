package com.mmpnc.rating.dao.util;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mmpnc.rating.vo.Product;

/**
 * Author : Shashikant Mhatre
 * 
 * Created On : Dec 31, 2014
 * 
 * Description :
 * 
 * Note :
 */

public class JAXBUtil {

	private static JAXBUtil instance;
	private static JAXBContext jc;
	private static Unmarshaller um;
	
	private JAXBUtil() {
		try {
			jc = JAXBContext.newInstance(Product.class);
			um = jc.createUnmarshaller();	
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static JAXBUtil getInstance() {
		if (instance == null) {
			instance = new JAXBUtil();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T> T unmarshalObject(String xmlString) throws JAXBException{
		Reader stringReader = new StringReader(xmlString);
		return (T) um.unmarshal(stringReader);
	}
	
}
