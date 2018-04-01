package com.mmpnc.rating.iso.content.dataimport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import com.mmpnc.rating.icm.content.util.AlgorithmUtil;
import com.mmpnc.rating.iso.content.AlgorithmRealign;

public class TestDocumentNodeCopy {

	@Test
	public void test() throws IOException {
		
		String aFile = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-NY-10012013-V01\\ALG-CA-CW-04012013-V01_MR.xml";
		
		Document doc = AlgorithmUtil.getDocument(aFile);
		
		AlgorithmRealign realignAlgo = new AlgorithmRealign(doc);
		
		doc = realignAlgo.realign();
	
		Writer out = new FileWriter(new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\ISODataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\sample.xml"));
		
		XMLWriter writer = new XMLWriter(out);
		writer.write(doc);
		writer.close();
		
	}

}
