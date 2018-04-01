package com.mmpnc.rating.iso.content.dataimport;


import java.io.File;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.dao.impl.hibernate.ProductDAO;
import com.mmpnc.rating.icm.content.util.HibernateUtil;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.UUIDGenerator;

public class StoreCustomerProduct {

	private Document getDocument(String fileName) {
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void test() throws JAXBException {
		
//		String productFile = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Customer\\Customer Product.xml";
		String productFile = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Customer\\PersonalAuto\\HM-PACWProductSetup.xml";
		
		Document doc = getDocument(productFile);
		
		Node rootNode = doc.getRootElement();

		JAXBContext jb = JAXBContext.newInstance(Product.class);
		
		for(Node node : (List<Node>)rootNode.selectNodes("Product")){
		
			StringReader reader = new StringReader(node.asXML());
			
			HibernateUtil.beginTransaction();
			
			Unmarshaller um = jb.createUnmarshaller();
			Product product = (Product) um.unmarshal( reader);
			
			String productIDKey = product.getLob() + "-" + product.getState();
			product.setId(UUIDGenerator.generate(productIDKey).toUpperCase());			
			
			Product parentProduct = product.getParentRefId();
			System.out.println(parentProduct.getId());
			
			ObjectDAO saveProduct = new ProductDAO(product);
			saveProduct.save(HibernateUtil.getSession());
			
			HibernateUtil.endTransaction();
			HibernateUtil.closeSession();
			
			System.out.println(product.toString());
			
			System.out.println(product.getParentRefId());
			
			
		}		
		
		
		
	}

}
