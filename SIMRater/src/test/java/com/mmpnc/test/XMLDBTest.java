package com.mmpnc.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mmpnc.connection.helper.DBFactoryBuilder;
import com.mmpnc.connection.helper.Database;
import com.mmpnc.connection.helper.Session;
import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
import com.mmpnc.rating.dao.RatingDAOFactory.ContentOption;
import com.mmpnc.rating.vo.Product;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Dec 24, 2014
 *
 * Description :
 *
 * Note :
 */

public class XMLDBTest {

	@Test
	public void test() {
		Database db;
		Session session;
			
		Map<String, String> xmlMap = new HashMap<String, String>();
//		xmlMap.put("","C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto");

		xmlMap.put("","C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto\\F1244B7F-10B4-43DB-9460-C59E72E21E9D_ISO Commercial Auto CW 1_20140201.xml");
		xmlMap.put("","C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto\\0E17C7FD-40B3-44B0-86AC-6858EBD58F49_ISO Commercial Auto CW 2_20140201.xml");
		xmlMap.put("","C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto\\93BBD210-679F-4516-BA9F-3629CFE79583_ISO Commercial Auto CA_20140301.xml");
		
		db = DBFactoryBuilder.getXMLDatabase("RuntimeXMLDB", xmlMap);
		session = db.openSession();
		
		Product product = new Product();
		product.setId("93BBD210-679F-4516-BA9F-3629CFE79583");
		BaseDAO productDAO = RatingDAOFactory.getRatingDAOFactory(ContentOption.XML).getProductDAO(product);
		product = productDAO.getUniqueRecord(session);
		System.out.println(product.getState());
		
	}

}
