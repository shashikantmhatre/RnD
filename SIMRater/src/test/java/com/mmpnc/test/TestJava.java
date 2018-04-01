package com.mmpnc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.rating.algorithm.util.XPathResolver;



public class TestJava {

private final static Logger logger = LoggerFactory.getLogger(TestJava.class);
	
//	@Test
	public void TestXPath(){
		String xpath = "../BOPLocation/BOPStructure/BOPClassification";
		String referenceXpath = "/Policy/BOP/BOPLocation[1]/BOPStructure[2]/BOPClassification[1]";
		
//		String updatableXPath = "../BOPLocation/BOPStructure/BOPClassification/Number";
		String updatableXPath = "../BOPLocation/Number";
		
		String[] baseXpath = xpath.split("/");
		String[] refXpath = referenceXpath.split("/");
		String[] upXpath = updatableXPath.split("/");

		StringBuffer updatedXPath = new StringBuffer();

		if(updatableXPath.equals(xpath)){			
			//xpath string matches with the updatable xpath string
			updatedXPath.append(referenceXpath);			
		} else if (updatableXPath.contains(xpath)) {  
			//portion of updatable xpath string matches with the xpath string
			updatedXPath.append(updatableXPath.replace(xpath, referenceXpath));
		}else {
			//we did not found the match lets replace one node at a time
			for (int x = 0; x < upXpath.length; x++) {
				if (!upXpath[x].equals("")) {
					if (upXpath[x].equals(baseXpath[x])) {
						updatedXPath.append("/").append(refXpath[x]);
					} else {
						updatedXPath.append("/").append(upXpath[x]);
					}
				}
			}
			
		}
		
		System.out.println(updatedXPath);	
	}
	
//	@Test
	public void TestXPathResolver(){
		XPathResolver xpath = new XPathResolver();
		xpath.setxPath("/Policy/BOP/BOPLocation/BOPStructure/BOPClassification");
		xpath.setReferenceXPath("/Policy/BOP/BOPLocation[1]/BOPStructure[2]/BOPClassification[1]");		
		System.out.println(xpath.getUpdatedXpath("/Policy/BOP/BOPLocation/BOPStructure/Number", null));
		
	}
	
//	@Test
	public void testString(){
		String xPath = "/Policy/BOP/BOPLocation/BOPStructure";
		String iteratorXPath = "BOPLocation/BOPStructure";
		String referenceXPath = "/Policy/BOP/BOPLocation[1]/BOPStructure[2]";
		String relativeXPath = "BOPLocation1/BOPStructure1/Premium";
		
		StringBuffer updatedXPath = new StringBuffer();
		
		
		if(relativeXPath.equals(iteratorXPath)){			
			//xpath string matches with the updatable xpath string
			if(logger.isInfoEnabled()){
				logger.info("Get the same XPath as Loop XPath");
				System.out.println("Get the same XPath as Loop XPath");
			}			
			updatedXPath.append(referenceXPath);			
		} else if (relativeXPath.contains(iteratorXPath+"/")) {  
			//portion of updatable xpath string matches with the xpath string
			if(logger.isInfoEnabled()){
				logger.info("1. [Complete Match] " + iteratorXPath + " is present in " + relativeXPath);
				System.out.println("1. [Complete Match] " + iteratorXPath + " is present in " + relativeXPath);
			}	
			updatedXPath.append(relativeXPath.replace(iteratorXPath, referenceXPath));
		} else {
			//Check if we found partial match, if yes then lets replace one node at a time
			
			String[] iteratorXPathArray = iteratorXPath.split("/");
			String[] updatableXPathArray = relativeXPath.split("/");
			
			StringBuffer partialMatchedStringBuffer = new StringBuffer();
			
			for(int x=0 ; x<updatableXPathArray.length; x++){
				if(updatableXPathArray[x].equals(iteratorXPathArray[x])){
					if(x!=0){
						System.out.println("/");
						partialMatchedStringBuffer.append("/");
					}
					System.out.println(updatableXPathArray[x]);
					partialMatchedStringBuffer.append(updatableXPathArray[x]);
				}else{
					break;
				}
			}
			
			if(partialMatchedStringBuffer.length()==0){
				System.out.println("not matching");
				
				updatedXPath.append(relativeXPath);
				
			} else {
				System.out.println("partial matching");
				
				if(logger.isInfoEnabled()){
					logger.info("2. [Partial Match]  " + relativeXPath + " is present in " + iteratorXPath);		
					System.out.println("2. [Partial Match]  " + relativeXPath + " is present in " + iteratorXPath);		
				}	
				
				String[] baseXpath = xPath.split("/");
				String[] refXpath = referenceXPath.split("/");
				
				String[] upXpath = relativeXPath.split("/");
				
				for (int x = 0; x < upXpath.length; x++) {
					if (!upXpath[x].equals("")) {
						if (upXpath[x].equals(baseXpath[x])) {
							updatedXPath.append("/").append(refXpath[x]);
						} else {
							updatedXPath.append("/").append(upXpath[x]);
						}
					}
				}
				
			}			
		} 
		/*
		else {
			//we did not find the match
			if(logger.isInfoEnabled()){
				logger.info("No Matching Loop Xpath found for XPath update ");
				System.out.println("No Matching Loop Xpath found for XPath update ");
			}	
			updatedXPath.append(updatableXPath);
		}
		*/
		System.out.println(updatedXPath);
	}
	
//	@Test
	public void testSelectionSort(){
		 int i, j, first, temp;  
		 int[] num = { 68, 64, 67, 87 };
	     
		 for ( i = 0; i < num.length; i++){
			 first = 0;
			 for(j = 0; j < num.length; j++)   //locate smallest element between positions 1 and i.
	          {
				   if( i > j ) {
					   continue;
				   }
	               if( num[ j ] < num[ first ] )         
	                 first = j;
	          }
	          temp = num[ first ];   //swap smallest found with element in position i.
	          num[ first ] = num[ i ];
	          num[ i ] = temp; 
		 }
		 
		 /*
		 for ( i = num.length - 1; i > 0; i--)  
	     {
	          first = 0;   //initialize to subscript of first element
	          for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
	          {
	               if( num[ j ] < num[ first ] )         
	                 first = j;
	          }
	          temp = num[ first ];   //swap smallest found with element in position i.
	          num[ first ] = num[ i ];
	          num[ i ] = temp; 
	      }
	      */           
	     for(int c : num){
	    	 System.out.println(c);
	     }
	}
	
//	@Test
	public void testYearDays(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar baseCal = Calendar.getInstance();
		baseCal.set(1900, 0, 1);
		
		try {
			String date1 = "2013-01-02";
			cal1.setTime(sdf.parse(date1));

			String date2 = "2012-01-01";
			cal2.setTime(sdf.parse(date2));
			
			System.out.println(" ----- Year : " + cal1.get(Calendar.DAY_OF_YEAR));
			System.out.println(" ----- Year : " + cal2.get(Calendar.DAY_OF_YEAR));
			
		} catch (ParseException e) {
			logger.error("Error in XpathYearFunction : get-year function", e);
		}
		
		
		int days1 = daysBetween(baseCal.getTime(), cal1.getTime());
		int days2 = daysBetween(baseCal.getTime(), cal2.getTime());
		
		System.out.println("Days1 = " + days1);
		System.out.println("Days2 = " + days2);
		
		System.out.println("days difference " + (days1 - days2));
		
	}
	
	
	 public int daysBetween( Date baseCal, Date cal ){
         return (int)( (cal.getTime() - baseCal.getTime()) / (1000 * 60 * 60 * 24));
	 }
	 
	 @Test
	 public void testStringBuffer(){
		 StringBuffer sb = new StringBuffer();
		 sb.append("This is a String");
		 
		 sb.insert(0, "Begining ");
		 
		 sb.append(" End");
		 
		 System.out.println(sb);
	 }
	
}
