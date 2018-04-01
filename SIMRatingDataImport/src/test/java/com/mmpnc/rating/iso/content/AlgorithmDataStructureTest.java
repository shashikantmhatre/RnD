package com.mmpnc.rating.iso.content;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.dom4j.Document;
import org.junit.Test;

import com.mmpnc.rating.icm.content.util.AlgorithmUtil;

public class AlgorithmDataStructureTest {

	@Test
	public void test() throws IOException {
		String aFile = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-NY-10012013-V01\\ALG-CA-CW-04012013-V01_MR.xml";
		
		Document doc = AlgorithmUtil.getDocument(aFile);
		
		Writer out = new FileWriter(new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\ISODataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\sample.xml"));
		
		AlgorithmDataStructure ds = new AlgorithmDataStructure();
		
		out.write(ds.simplifyDataStrucure(doc).toString());
		out.close();
		
	}

}
