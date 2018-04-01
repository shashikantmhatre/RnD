package com.mmpnc.rating.iso.content.dataimport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import com.mmpnc.rating.icm.content.util.HibernateUtil;
import com.mmpnc.rating.vo.Product;

public class GetProductTest {

	@Test
	public void test() throws JAXBException, IOException {
		HibernateUtil.beginTransaction();
		
		Product product = new Product();
		product.setId("FAFDF2CF-B931-382F-AC8F-4AD0FBB2387E");
//		product.setId("AECDA1F0-5F07-4220-954D-C711DD06D8DB");
		
		product = (Product) HibernateUtil.getSession().get(Product.class, product.getId());
		
		System.out.println(product.getTitle());
		
		JAXBContext jb = JAXBContext.newInstance(Product.class);
		Marshaller marsh = jb.createMarshaller();
		marsh.setProperty("jaxb.encoding", "US-ASCII");
		marsh.setProperty("jaxb.formatted.output", true);
		
		FileWriter writer = new FileWriter(new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\ISODataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Customer\\productdata.xml"));
		
		marsh.marshal(product, writer);
		
		writer.flush();
		writer.close();
		
		HibernateUtil.endTransaction();
		HibernateUtil.closeSession();
	}

}
