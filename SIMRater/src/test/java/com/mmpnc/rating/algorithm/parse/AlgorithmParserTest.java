package com.mmpnc.rating.algorithm.parse;

import java.io.File;
import java.util.Calendar;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.algorithm.execute.AlgorithmExecutor;

public class AlgorithmParserTest {
	
	private Document getDocument(String fileName){
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read( new File( fileName ) );
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return document;
	}
	
	@Test
	public void test() throws Exception {
		
		System.out.println( Calendar.getInstance().getTimeInMillis());
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("x = FLOOR(ROUND((ROUND(ROUND(ROUND(ROUND(ROUND(ROUND(ROUND(ROUND(ROUND(ROUND(ROUND( BasePremium * RadiusFactor ,5)* VehicleTypeFactor ,5)* RatingClassFactor ,5)* IncreasedLimitFactor ,5)* TimeInBusinessFactor ,5)* TerritoryFactor ,5)* MarketTierFactor ,5)* AverageDriverFactor ,5)* NrPowerUnitsFactor ,5)* CommodityFactor ,5)* ZoneFactor ,5)),5)+ LiabilityExpenseConstant )");
		Context context = new ContextBase();
		
		Document doc = getDocument("C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\PolicyData.xml");
		
		context.putValue(ContextParam.XMLDOCUMENT, doc);
		context.putValue(ContextParam.CURRENTNODE, doc.node(0));
		context.putValue(ContextParam.PREVIOUSNODE, null);
		
		AlgorithmExecutor parser = new AlgorithmExecutor(context, buffer);
		parser.execute();
		
		System.out.println(doc.node(0).valueOf("Premium"));
		
		System.out.println(doc.asXML());
		
		System.out.println( Calendar.getInstance().getTimeInMillis());
	}

//	@Test
	public void testXpath(){
		System.out.println( Calendar.getInstance().getTimeInMillis());
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(" LOOP THROUGH \"BOP\" Premium = Premium + XPATH:\"BOP/Premium\" END LOOP");
		
		Document doc = getDocument("C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRater\\src\\test\\resources\\com\\mmpnc\\SamplePolicyData.xml");
		
		Node currentNode = doc.selectSingleNode("/Policy/BOP/BOPTerrorismCoverage");
		
		Node refNode = currentNode.selectSingleNode("../BOPLocation/DedFactor");
		
		System.out.println(refNode.getParent().getPath());
		
		
	}
	
}
