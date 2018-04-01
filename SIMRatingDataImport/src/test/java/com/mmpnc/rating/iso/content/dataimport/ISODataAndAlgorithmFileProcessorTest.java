package com.mmpnc.rating.iso.content.dataimport;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.mmpnc.rating.iso.ISODataAndAlgorithmFileProcessor;


public class ISODataAndAlgorithmFileProcessorTest {

	@Test
	public void test() throws IOException, JAXBException {
		
		String[] cFiles = new String[10];
		//Businessowners
		//BOP CW 09012012
//		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\CW 09012012\\RC-BP-CW-09012012-V01.xml";		
		//BOP CW 07012013
//		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\CW 07012013\\RC-BP-CW-07012013-V01.xml";		
		//BOP PA 07012013
//		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\PA 07012013\\RC-BP-PA-07012013-V02.xml";		
		//BOP CO 07012013
//		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\CO 07012013\\RC-BP-CO-07012013-V01.xml";		
		//BOP MO 07012013
//		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\MO 07012013\\RC-BP-MO-07012013-V01.xml";		
		//BOP OR 07012013
//		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\OR 07012103\\RC-BP-OR-07012013-V01.xml";		
		
		//Workerscomp
		//WC TX 01012014
//		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\WC\\TX 01012014\\RC-WC-TX-01012014-V01.xml";		
		
		//Commercial Auto
		//CA CW 02012014
		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-CA-03012014-V01\\RC-CA-CA-03012014-V01\\RC-CA-CW1-02012014-V01.xml";	
		cFiles[1] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-CA-03012014-V01\\RC-CA-CA-03012014-V01\\RC-CA-CW2-02012014-V01.xml";
		
		//CA CA 02012014
		cFiles[2] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-CA-03012014-V01\\RC-CA-CA-03012014-V01\\RC-CA-CA-03012014-V01.xml";	
		
		
		String[] aFiles = new String[10];
		
		//Businessowners
		//BOP CW 09012012
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\CW 09012012\\ALG-BP-CW-09012012-V01_MR.xml";
		//BOP CW 07012013
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\CW 07012013\\ALG-BP-CW-07012013-V01_MR.xml";
		//vPA 07012013
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\PA 07012013\\ALG-BP-PA-07012013-V02_MR.xml";
		//BOP CO 07012013
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\CO 07012013\\ALG-BP-CO-07012013-V01_MR.xml";
		//BOP MO 07012013
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\MO 07012013\\ALG-BP-MO-07012013-V01_MR.xml";
		//BOP OR 07012013
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\BOP\\OR 07012103\\ALG-BP-OR-07012013-V01_MR.xml";
		
		//Workerscomp
		//WC TX 01012014
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\WC\\TX 01012014\\ALG-WC-TX-01012014-V01_MR.xml";

		//Commercial Auto
		//CA CW 02012014
		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-CA-03012014-V01\\ALG-CA-CW-02012014-V01_MR.xml";

		//CA CA 03012014
		aFiles[1] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-CA-03012014-V01\\ALG-CA-CA-03012014-V01_MR.xml";
		
		String outputDirectory = "C:\\Users\\shashikant460032\\Workspace\\eclipse\\SIMRatingDataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\";
		
		ISODataAndAlgorithmFileProcessor processor = new ISODataAndAlgorithmFileProcessor(cFiles, aFiles, outputDirectory);
		processor.process();
		
	}

}
