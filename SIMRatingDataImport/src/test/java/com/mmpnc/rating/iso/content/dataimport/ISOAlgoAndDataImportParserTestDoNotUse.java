package com.mmpnc.rating.iso.content.dataimport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
import com.mmpnc.rating.dao.impl.hibernate.ProductDAO;
import com.mmpnc.rating.icm.content.util.HibernateUtil;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableConfiguration;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportLexer;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportParser;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmimportParser.block_return;
import com.mmpnc.rating.iso.algorithm.dataimport.isoalgorithmwalker;
import com.mmpnc.rating.iso.content.NodeData;
import com.mmpnc.rating.iso.content.dataimport.isodataimportParser.data_return;
//import com.mmpnc.rating.vo.DomainTableEntry;
//import com.mmpnc.rating.vo.RateTableEntry;

public class ISOAlgoAndDataImportParserTestDoNotUse {

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
	public void test() throws IOException, JAXBException {

		Map<String,String[]> listing = new HashMap<String,String[]>();
				
		//add the content files for processing
		String[] cFiles = new String[10];
		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-NY-10012013-V01\\RC-CA-NY-10012013-V01\\RC-CA-NY-10012013-V01.xml";
		cFiles[1] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-NY-10012013-V01\\RC-CA-NY-10012013-V01\\RC-CA-CW2-04012013-V01.xml";
		cFiles[2] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-NY-10012013-V01\\RC-CA-NY-10012013-V01\\RC-CA-CW1-04012013-V01.xml";		
		listing.put("Content", cFiles);
		
		//add the algorithm files for processing
		String[] aFiles = new String[10];		
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-NY-10012013-V01\\ALG-CA-CW-04012013-V01_MR.xml";
		aFiles[1] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO Updates\\RC-CA-NY-10012013-V01\\ALG-CA-NY-10012013-V01_MR.xml";
		
		listing.put("Algorithm", aFiles);
	
		
		System.out.println("Start: " + Calendar.getInstance().getTimeInMillis());
		
		Context context = new ContextBase();
		context.putValue(ContextParam.PRODUCT, new ArrayList<Product>());
		context.putValue(ContextParam.RATETABLE, new ArrayList<RateTable>());
		context.putValue(ContextParam.RATETABLECONFIGURATION, new ArrayList<RateTableConfiguration>());
		context.putValue(ContextParam.DOMAINTABLE, new ArrayList<DomainTable>());
		context.putValue(ContextParam.DOMAINTABLECONFIGURATION, new ArrayList<DomainTableConfiguration>());

		
		//This will read the content e.g. domain table and rate tables
		for(String fileName : listing.get("Content")){
			
			if(fileName == null){
				continue;
			}
			
			System.out.println("Process the Content from file " + fileName);
				
				
			Document doc = getDocument(fileName);
			Node rootNode = doc.getRootElement();
	
//			System.out.println(rootNode.getPath());
			
			List<Node> nodes = (List<Node>)rootNode.selectNodes("Ratebook");
			
			for(Node node: nodes){
				NodeData rb = new NodeData(node);
				processNode(rb.getRatebookElement(),context);
			}
	
			nodes = (List<Node>)rootNode.selectNodes("RateTable");
			
			for(Node node: nodes){
				NodeData rt = new NodeData(node);
				processNode(rt.getRateTableElement(),context);
			}
	
			nodes = (List<Node>)rootNode.selectNodes("DomainTable");
			
			for(Node node: nodes){
				NodeData dt = new NodeData(node);
				processNode(dt.getDomainTableElement(),context);
			}
		
		}
		
		context.putValue(ContextParam.SPACECOUNT, new Integer(0));
		context.putValue(ContextParam.FORMAT, false);
		
		//This will read the algorithm files.
		for(String fileName : listing.get("Algorithm")){
				
				if(fileName == null){
					continue;
				}

				System.out.println("Process the Algorithm from file " + fileName);
				
				
				Document doc = getDocument(fileName);
				Node rootNode = doc.getRootElement();
				
				System.out.println(rootNode.getPath());				
				
				List<Node> nodes = (List<Node>)rootNode.selectNodes("reference[@type='Common Rating' or @type='Premium Calculation']");
				for(Node node: nodes){
					
					for(Node scopeNode : (List<Node>)node.selectNodes("scope"))
					{
						System.out.println(scopeNode.valueOf("@dbTables"));
						for(Node statementNode : (List<Node>)scopeNode.selectNodes("node()[name()!= 'PCH']")){
							if(statementNode instanceof Comment){
								continue;
							}
							if(statementNode.getName() != null){
								NodeData statement = new NodeData(statementNode);
								String statementString = statement.getStatementElement().toString();
//								System.out.println(statementString);
								processStatement(statementString, context);
							}
						}
						for(Node pchNode : (List<Node>)scopeNode.selectNodes("PCH")){
							
							if(pchNode instanceof Comment){
								continue;
							} else if(pchNode.valueOf("@name").equals("")){
								continue;
							}
							
							System.out.println("Processing : " + pchNode.valueOf("@name"));
							
							for(Node statementNode : (List<Node>)pchNode.selectNodes("node()")){
								
								//bypass the comment element during processing
								if(statementNode instanceof Comment){
									continue;
								}
								if(statementNode.getName() == null){
									continue;
								}
								
								NodeData pch = new NodeData(statementNode);
								String programString = pch.getStatementElement().toString();
																
//								System.out.println(programString);						
								processStatement(programString, context);

							
							}
						}
					}
				}
				
				
		}
		
		System.out.println("End: " + Calendar.getInstance().getTimeInMillis());

		HibernateUtil.beginTransaction();
		
		ObjectDAO saveProduct = new ProductDAO(((List<Product>)context.getValue(ContextParam.PRODUCT)).get(0));
		saveProduct.save(HibernateUtil.getSession());
		
		HibernateUtil.endTransaction();
		HibernateUtil.closeSession();
		
		Writer out = new FileWriter(new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\ISODataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\output.xml"));
		
		//XStream implementation
//		XStream xs = new XStream();
//		xs.toXML(context, out);
		
		//JAXB Implementation
		JAXBContext jb = JAXBContext.newInstance("com.mmpnc.rating.icm.content.vo");
		Marshaller marsh = jb.createMarshaller();
		marsh.marshal(((List<Product>)context.getValue(ContextParam.PRODUCT)).get(0), out);
		out.flush();
		out.close();
		
	}
	
	private void processStatement(String sb, Context context) {
		
		if(sb.length()<= 0 ){
			return;
		}
		
		try {
			
		CharStream charinput = new ANTLRStringStream(sb.toString());
		TokenSource tokensource = new isoalgorithmimportLexer(charinput );
		TokenStream tstream = new CommonTokenStream(tokensource );
		isoalgorithmimportParser parser = new isoalgorithmimportParser(tstream);
		
		block_return block = parser.block();
		
		
		CommonTreeNodeStream commontreenode = new CommonTreeNodeStream(block.getTree());
		
		isoalgorithmwalker walker = new isoalgorithmwalker(commontreenode);
		walker.setContext(context);
		walker.block();
		
		
//		System.out.println("tree -> " + ((CommonTree) block.getTree()).toStringTree());
		
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void processNode(StringBuffer sb, Context context){
		try {
			
//			System.out.println(sb);
			
			CharStream charinput = new ANTLRStringStream(sb.toString());
			TokenSource tokenSource = new isodataimportLexer(charinput);
			TokenStream tstream = new CommonTokenStream(tokenSource);
			isodataimportParser parser = new isodataimportParser(tstream);

			data_return data;
			CommonTreeNodeStream commontreenode;
			
			data = parser.data();
			commontreenode = new CommonTreeNodeStream(data.getTree());
			
			
			isodatawalker walker = new isodatawalker(commontreenode);
			walker.setContext(context);
			
//			System.out.println("tree -> " + ((CommonTree) data.getTree()).toStringTree());
			walker.data();
			
			
			
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
