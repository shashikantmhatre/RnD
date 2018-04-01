package com.mmpnc.simlanguage.execute;


import org.junit.Test;

//import com.thoughtworks.xstream.XStream;

public class TestLanguageExecutor {

	@Test
	public void testLanguageExecution() {
		StringBuffer buffer = new StringBuffer();
//		buffer.append("IF ( LossSettlementType != \"Standard\" And ( PolicyForm != 2.0 Or PolicyForm != 3.0 Or PolicyForm != 5.0 Or PolicyForm != 8.0 ) ) THEN "); 
//		buffer.append("AdditionalAmountsOfCoverage = \"\" "); 
//		buffer.append("END IF");
		
		buffer.append("cal = FLOOR( BasePremium * GarageKeeperRatingBasisFactor * DeductibleFactor * GarageKeeperLimitFactor )\r\n" + 
				"");
		MyLanguageExecutorImplementation lang = new MyLanguageExecutorImplementation(buffer);
		try {
			lang.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void testLanguageSerialize(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Premium =  RoundDollar (   ( LV_DOUBLE:calcPremium * XPATH_NUMERIC:\"../VacancyPermitProRateFactor\" )  )  \r\n"
				);

		
		try {
			/*
			Activity reader = ReaderFactory.getLanguageReader(buffer);
			CommonTreeNodeStream tree = (CommonTreeNodeStream) reader.execute();
			
			XStream xstream = new XStream();
			String xmlString = xstream.toXML(tree);
			
			
			System.out.println("with stored data " + System.currentTimeMillis());
			CommonTreeNodeStream intree = (CommonTreeNodeStream) xstream.fromXML(xmlString);
			
			MyLanguageExecutorImplementation lang = new MyLanguageExecutorImplementation(intree);
			try {
				lang.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("with stored data " + System.currentTimeMillis());		
			*/
			
			System.out.println("with antlr " + System.currentTimeMillis());		
			MyLanguageExecutorImplementation mlang = new MyLanguageExecutorImplementation(buffer);
			try {
				mlang.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("with antlr " + System.currentTimeMillis());		
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("WE got error");
			e.printStackTrace();
		}
		
	}

}
