package com.mmpnc.rating.icm.content.util;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class AlgorithmUtil {

	public static Document getDocument(String fileName) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(fileName));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}
	
	public static Document generateDocument(StringBuffer buffer){
		SAXReader reader = new SAXReader();
		Reader stringReader = new StringReader(buffer.toString());
		Document document = null;
		try {
			document = reader.read(stringReader);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}
	
	public static void appendSpace(StringBuffer buffer, int count) {
		for (int x = 0; x < count; x++) {
			buffer.append("\t");
		}
	}
}
