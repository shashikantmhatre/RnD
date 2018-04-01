package com.mmpnc.rating.iso.content.dataimport;


import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.dao.impl.hibernate.ProductDAO;
import com.mmpnc.rating.icm.content.util.HibernateUtil;
import com.mmpnc.rating.vo.Product;

public class StoreISOProductToDB {

	@Test
	public void test() {

		//Commercial Auto
		final File folder = new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected\\Commercial Auto");
		
		//WorkersComp
//		final File folder = new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Corrected");

		
		//PersonalAuto
//		final File folder = new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Customer\\PersonalAuto");

		//PA
//		final File folder = new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\Customer\\PA");
				
		
		List<Product> products = new ArrayList<Product>();
		
		for(File file : listFilesForFolder(folder)){
			System.out.println(file.getAbsolutePath());
			
			try {
				JAXBContext jb = JAXBContext.newInstance(Product.class);
				
				Unmarshaller unmarsh = jb.createUnmarshaller();
				Product product = (Product) unmarsh.unmarshal(file);				
				
				products.add(product);				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		System.out.println("Started : " + System.currentTimeMillis());
		HibernateUtil.beginTransaction();
		
		
		for(Product product : products){
			
			System.out.println("start saving " + product.getId() + " - " + product.getTitle());
			
			ObjectDAO saveProduct = new ProductDAO(product);
			saveProduct.save(HibernateUtil.getSession());
		}
		
		HibernateUtil.endTransaction();
		HibernateUtil.closeSession();
		
		System.out.println("Ended : " + System.currentTimeMillis());
		
	}

	public List<File> listFilesForFolder(final File folder) {
	    List<File> files = new ArrayList<File>();
	    FileFilter filter = new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				if(file.getName().endsWith(".xml")){	
					
//					if(file.getName().equals("HM-PACWProductSetup.xml")	
//							){
						return true;
//					} else {
//						return false;
//					}
					
				} else {
					return false;
				}
			}
		};
	    
		for (final File fileEntry : folder.listFiles(filter)) {
	        if (fileEntry.isDirectory()) {
//	            listFilesForFolder(fileEntry);
	        } else {
	            files.add(fileEntry);
	        }
	    }
		
		return files;
	}

	

}
