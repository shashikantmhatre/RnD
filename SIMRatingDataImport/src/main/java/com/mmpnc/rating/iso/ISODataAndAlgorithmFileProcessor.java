package com.mmpnc.rating.iso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextBase;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.icm.algorithm.AlgorithmConfigurator;
import com.mmpnc.rating.icm.content.util.AlgorithmUtil;
import com.mmpnc.rating.iso.content.AlgorithmContentParser;
import com.mmpnc.rating.iso.content.AlgorithmDataStructure;
import com.mmpnc.rating.iso.content.AlgorithmRealign;
import com.mmpnc.rating.iso.content.NodeData;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.DomainTableConfiguration;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.Program;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;
import com.mmpnc.rating.vo.RatingEntity;
import com.mmpnc.rating.vo.Step;
import com.mmpnc.rating.vo.UUIDGenerator;
import com.mmpnc.util.Utility;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Jan 4, 2014
 *
 * Description :
 *
 * Note :
 */

public class ISODataAndAlgorithmFileProcessor {

private static final Logger logger = LoggerFactory.getLogger(ISODataAndAlgorithmFileProcessor.class);
	
	//add the content files for processing
	private String[] cFiles;
	
	//add the algorithm files for processing
	private String[] aFiles;		

	private String outputDirectory;
	
	private AlgorithmConfigurator configurator;
	
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
	
	public ISODataAndAlgorithmFileProcessor(String[] contentFiles, String[] algorithmFiles, String outputDirectory){
		this.cFiles = contentFiles;
		this.aFiles = algorithmFiles;
		this.outputDirectory = outputDirectory;
	}
	
	@SuppressWarnings("unchecked")
	public void process() throws IOException, JAXBException{
		this.configurator = AlgorithmConfigurator.getInstance(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("\\com\\mmpnc\\rating\\algorithm\\configuration\\Configuration.xml")));
		
		System.out.println(configurator.getProgramPCH().toString());
		
		Map<String,String[]> listing = new HashMap<String,String[]>();
		
		
		//CW 09012012
//		cFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\CW 09012012\\RC-BP-CW-09012012-V01.xml";		
		//CW 07012013
//		cFiles[1] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\CW 07012013\\RC-BP-CW-07012013-V01.xml";		
		
		listing.put("Content", cFiles);
		
		
		//CW 09012012
//		aFiles[0] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\CW 09012012\\ALG-BP-CW-09012012-V01_MR.xml";
		//CW 07012013
//		aFiles[1] = "C:\\Users\\shashikant460032\\Mastek\\ISO\\ISO ERC Files\\CW 07012013\\ALG-BP-CW-07012013-V01_MR.xml";
		
		listing.put("Algorithm", aFiles);
	
		
		System.out.println("Start: " + Calendar.getInstance().getTimeInMillis());
		
		Context context = new ContextBase();
		
		/*
		 * Below entries are for content loading 
		 * 
		 * 
		 */
		
		context.putValue(ContextParam.PRODUCT, new ArrayList<Product>());
		context.putValue(ContextParam.RATETABLE, new ArrayList<RateTable>());
		context.putValue(ContextParam.RATETABLECONFIGURATION, new ArrayList<RateTableConfiguration>());
		context.putValue(ContextParam.DOMAINTABLE, new ArrayList<DomainTable>());
		context.putValue(ContextParam.DOMAINTABLECONFIGURATION, new ArrayList<DomainTableConfiguration>());
		context.putValue(ContextParam.CONFIGURTOR, this.configurator);
		
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
				AlgorithmContentParser.processNode(rb.getRatebookElement(),context);
			}
	
			nodes = (List<Node>)rootNode.selectNodes("RateTable");
			
			for(Node node: nodes){
				NodeData rt = new NodeData(node);
				AlgorithmContentParser.processNode(rt.getRateTableElement(),context);
			}
	
			nodes = (List<Node>)rootNode.selectNodes("DomainTable");
			
			for(Node node: nodes){
				NodeData dt = new NodeData(node);
				AlgorithmContentParser.processNode(dt.getDomainTableElement(),context);
			}
		}
		
		/*
		 * 
		 * Below entries are for the algorithm
		 * 
		 */
		
		context.putValue(ContextParam.SPACECOUNT, new Integer(0));
		context.putValue(ContextParam.FORMAT, true);
		
		for(String fileName : listing.get("Algorithm")){
				
				if(fileName == null){
					continue;
				}

				System.out.println("Process the Algorithm from file " + fileName);
				
				
				Document doc = getDocument(fileName);
				
				//Realign the Algorithm
				AlgorithmRealign algoRealign = new AlgorithmRealign(doc);
				doc = algoRealign.realign(); 

				Node rootNode = doc.getRootElement();
				
				//this will provide the data structure database.
				AlgorithmDataStructure ds = new AlgorithmDataStructure();
				Document dsDatabase = AlgorithmUtil.generateDocument(ds.simplifyDataStrucure(doc));
				context.putValue(ContextParam.OBJECTDATABASE, dsDatabase);
				
				
				System.out.println(rootNode.getPath());				
				
				Product algoProduct = new Product();
				
				algoProduct.setId(rootNode.valueOf("@RatebookId")); 

				
				/*
				 * set the flag based on CW or State file indicator.
				 * if file is for Countrywide definition, set it to true or else false;
				 * 
				*/
				boolean rootConent = true;				
				if(! rootNode.valueOf("@state").trim().toUpperCase().equals("CW")){
					rootConent = false;
				}
				
				//get the product from product list
				algoProduct = Utility.getObject((List<Product>)context.getValue(ContextParam.PRODUCT),algoProduct);  
				
				if(algoProduct == null){
					algoProduct = new Product();
					algoProduct.setId("Dummy");
					algoProduct = Utility.updateList((List<Product>)context.getValue(ContextParam.PRODUCT),algoProduct);  
				}
				
				
				List<Node> nodes = (List<Node>)rootNode.selectNodes("reference[@type='Common Rating' or @type='Premium Calculation']");
				for(Node refNode: nodes){
					
//					for(Node scopeNode : (List<Node>)refNode.selectNodes("scope[@dbTables='CommercialAutoVehicleOtherThanCollisionCoverage']"))
//					for(Node scopeNode : (List<Node>)refNode.selectNodes("scope[@dbTables='CommercialAutoVehicleCollisionCoverage']"))
//					for(Node scopeNode : (List<Node>)refNode.selectNodes("scope[@dbTables='CommercialAutoRentalReimbursementCov']"))
//					for(Node scopeNode : (List<Node>)refNode.selectNodes("scope[@dbTables='BOPStructure']"))
//					for(Node scopeNode : (List<Node>)refNode.selectNodes("scope[@dbTables='BOPVacancyPermitBusnPrsnlPropCoverage']"))
//					for(Node scopeNode : (List<Node>)refNode.selectNodes("scope[@dbTables='WorkersCompAdmiraltyIncrLimitsCoverage']"))
					for(Node scopeNode : (List<Node>)refNode.selectNodes("scope"))
					{

						//This map holds the processed block list within scope
						Map<String, String> processedBlocks = new HashMap<String, String>();
//						context.putValue(ContextParam.REFERENCE, refNode.valueOf("@dbTables"));
//						context.putValue(ContextParam.REFERENCETYPE, refNode.valueOf("@type"));
						context.putValue(ContextParam.SCOPE, scopeNode.valueOf("@dbTables"));
						context.putValue(ContextParam.PROCESSEDBLOCK, processedBlocks);
						
						RatingEntity ratingEntity = new RatingEntity();
						ratingEntity.setName(scopeNode.valueOf("@dbTables"));
						ratingEntity.setProduct(algoProduct);
						
						String ratingEntityKey = algoProduct.getId()+"-"+ratingEntity.getName();
						ratingEntity.setId(UUIDGenerator.generate(ratingEntityKey).toUpperCase());
						
						ratingEntity = Utility.updateList(algoProduct.getRatingEntity(),ratingEntity);
						
						
						
						Process ratingProcess = new Process();
						ratingProcess.setName("Premium Calculation");
						ratingProcess.setEntity(ratingEntity);						
						
						String ratingProcessKey = ratingEntity.getId()+"-"+ratingProcess.getName();
						ratingProcess.setId(UUIDGenerator.generate(ratingProcessKey).toUpperCase());
						
						ratingProcess = Utility.updateList(ratingEntity.getProcesses(),ratingProcess);
												
						logger.info("scope " + scopeNode.valueOf("@dbTables"));
//						System.out.println("Scope : " + scopeNode.valueOf("@dbTables"));
						
						//this loop gets the process statements
						for(Node statementNode : (List<Node>)scopeNode.selectNodes("node()[name()!= 'PCH']")){
							
							//Below statement commented as we want to store the process statement under the program block
//							processStep(statementNode, context, ratingProcess);
							
							processStepToProgramStep(statementNode, context, ratingProcess);
						
						}
						
						//this loop gets the programs in a scope element
						for(Node pchNode : (List<Node>)scopeNode.selectNodes("PCH")){
//						for(Node pchNode : (List<Node>)scopeNode.selectNodes("PCH[@name='AdditionalInterestCount']")){
							
							if(pchNode instanceof Comment){
								continue;
							} else if(pchNode.valueOf("@name").equals("")){
								continue;
							} else if(pchNode.getName() == null){
								continue;
							}

//							if( !rootConent && (pchNode.valueOf("@state").contains("CW") && pchNode.valueOf("@overridden").toLowerCase().equals("false"))){
							
							if( !rootConent && (pchNode.valueOf("@state").contains("CW") && new Boolean(pchNode.valueOf("@state = node()/@state")))){
								System.out.println("Skip Program " + pchNode.valueOf("@name"));
								System.out.println("Its overriddent value will be " + pchNode.valueOf("@state = node()/@state").toString());
								((Element) pchNode).addAttribute("executed","true");
								continue;
							}
							
							if(configurator.getIgnorePCH().isAvailable(pchNode.valueOf("@name").trim()))
							{
								logger.info("PCH ignored - " + pchNode.valueOf("@name") + " as this is marked in ignored PCH list ");
								((Element) pchNode).addAttribute("executed","true");
								continue;
							}
							
//							System.out.println("#########started processing " + pchNode.valueOf("@name"));
							
//							if(pchNode.valueOf("@name").trim().equals("Total Premium")){
//								for (Node statementNode : (List<Node>) pchNode.selectNodes("node()")) {
//									processStep(statementNode, context, ratingProcess);
//								}
//							} else {
								
								// I commented above statement so that total premium block is store as premium block under rating entity
							
								processProgram(pchNode, context, ratingProcess);
//							}
							
//							System.out.println("#########end processing " + pchNode.valueOf("@name"));
							
						}
					}
				}
				
				
				Node classNode = rootNode.selectSingleNode("reference[@type='Execution Flow']/class");
				
				if(classNode != null && !classNode.equals("")){
					
					context.putValue(ContextParam.PARENTXPATH, "");
					context.putValue(ContextParam.SPACECOUNT, 0);
					context.putValue(ContextParam.OBJECTDATABASE, dsDatabase);
					
					RatingEntity ratingEntity = new RatingEntity();
					ratingEntity.setName("Policy");
					ratingEntity.setProduct(algoProduct);
					
					String ratingEntityKey = algoProduct.getId()+"-"+ratingEntity.getName();
					ratingEntity.setId(UUIDGenerator.generate(ratingEntityKey).toUpperCase());
					
					ratingEntity = Utility.updateList(algoProduct.getRatingEntity(),ratingEntity);
										
					Process ratingProcess = new Process();
					ratingProcess.setName("Premium Calculation");
					ratingProcess.setEntity(ratingEntity);						
					
					String ratingProcessKey = ratingEntity.getId()+"-"+ratingProcess.getName();
					ratingProcess.setId(UUIDGenerator.generate(ratingProcessKey).toUpperCase());
					
					ratingProcess = Utility.updateList(ratingEntity.getProcesses(),ratingProcess);
					
					Program program = new Program();
					program.setName("Execute");
					program.setProcess(ratingProcess);
					
					String programKey = ratingProcess.getId()+"-"+ program.getName();
					program.setId(UUIDGenerator.generate(programKey).toUpperCase());
					
					program = Utility.updateList(ratingProcess.getPrograms(),program);
					
					StringBuffer buffer = new StringBuffer();
					Step step = new Step();
					step.setProcess(ratingProcess);
					step.setProgram(program);
					
					AlgorithmContentParser.processExecutionFlow(classNode, buffer, context);
					
					step.setBlock(buffer.toString());
					step.setExecutionorder(program.getSteps().size());
					
					String programStepKey = program.getId()+"-"+ step.getExecutionorder();
					step.setId(UUIDGenerator.generate(programStepKey).toUpperCase());
					
					program.addStep(step);
				}
				
				
				
				//write the modified ISO algorithm file
				Writer out;
				try {
					out = new FileWriter(new File(outputDirectory + "realign\\RealignedRCFile.xml"));
				
					XMLWriter xmlwriter = new XMLWriter(out);
					xmlwriter.write(doc);
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		
		System.out.println("End: " + Calendar.getInstance().getTimeInMillis());
		
		for(Product outputProduct : (List<Product>)context.getValue(ContextParam.PRODUCT)){
		
			Writer out;
			try {
					
//				out = new FileWriter(new File("C:\\Users\\shashikant460032\\Workspace\\eclipse\\ISODataImport\\src\\test\\resources\\com\\mmpnc\\rating\\iso\\content\\dataimport\\"+outputProduct.getId()+".xml"));
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String rateDate = format.format(outputProduct.getNewbusinessEffectiveDate().getTime());
				out = new FileWriter(new File(outputDirectory + outputProduct.getId() + "_" + outputProduct.getTitle() + "_" + rateDate + ".xml"));
			
			
				//JAXB Implementation
				JAXBContext jb = JAXBContext.newInstance("com.mmpnc.rating.vo");
				Marshaller marsh = jb.createMarshaller();
				marsh.setProperty("jaxb.encoding", "US-ASCII");
				marsh.setProperty("jaxb.formatted.output", true);
		//		marsh.marshal(((List<Product>)context.getValue(ContextParam.PRODUCT)).get(0), out);
				marsh.marshal(outputProduct, out);
				out.flush();
				out.close();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void processStepToProgramStep(Node statementNode, Context context, Process ratingProcess){
		if(statementNode instanceof Comment){
			return;
		} else if(statementNode.getName() == null){
			return;
		}
		
		context.putValue(ContextParam.PARENTPROCESS, statementNode);
		context.putValue(ContextParam.PCHSTEPS, new ArrayList<Step>());
		
		Program program = new Program();
		program.setName("Premium");
		program.setProcess(ratingProcess);
		
		String programKey = ratingProcess.getId()+"-"+ program.getName();
		program.setId(UUIDGenerator.generate(programKey).toUpperCase());
		
		program = Utility.updateList(ratingProcess.getPrograms(),program);
		
		Step programStep = AlgorithmContentParser.createStep(statementNode, context);
		
		// add the local PCH Steps before current PCH statement execution
		@SuppressWarnings("unchecked")
		List<Step> localPchAlgoSteps = (List<Step>) context.getValue(ContextParam.PCHSTEPS);
		for(Step localStep : localPchAlgoSteps){
			localStep.setExecutionorder(program.getSteps().size());
			localStep.setProcess(ratingProcess);
			localStep.setProgram(program);
			
			String localStepKey = program.getId()+"-"+ localStep.getExecutionorder();
			localStep.setId(UUIDGenerator.generate(localStepKey).toUpperCase());
			program.addStep(localStep);
		}		
		//end of local steps add
		
		programStep.setExecutionorder(program.getSteps().size());
		programStep.setProcess(ratingProcess);
		programStep.setProgram(program);
		
		String programStepKey = program.getId()+"-"+ programStep.getExecutionorder();
		programStep.setId(UUIDGenerator.generate(programStepKey).toUpperCase());
		program.addStep(programStep);
	}
	
	
	private void processProgram(Node pchNode, Context context, Process ratingProcess){
		context.putValue(ContextParam.PARENTPROCESS, pchNode);
		context.putValue(ContextParam.PCHSTEPS, new ArrayList<Step>());
		
		if (!(pchNode.valueOf("@executed").equals("true"))) {
			Program program = new Program();
			
			if (pchNode.valueOf("@name").trim().equals("Total Premium")) {
				program.setName("Premium");
			} else {
				program.setName(pchNode.valueOf("@name"));
			}
			program.setProcess(ratingProcess);
			
			String programKey = ratingProcess.getId()+"-"+ program.getName();
			program.setId(UUIDGenerator.generate(programKey).toUpperCase());
			
			program = Utility.updateList(ratingProcess.getPrograms(),program);
			
			Program tempProgram = AlgorithmContentParser.createProgram(pchNode, context);
			
			for(Step step : tempProgram.getSteps()){
				
//				System.out.println("block " + step.getBlock());
				
				step.setProcess(ratingProcess);
				step.setProgram(program);
				step.setExecutionorder(program.getSteps().size());
				
				String programStepKey = program.getId()+"-"+ step.getExecutionorder();
				step.setId(UUIDGenerator.generate(programStepKey).toUpperCase());
				
				program.addStep(step);
			}
		}
	}
	
	/*
	@SuppressWarnings("unused")
	private void processStep(Node statementNode, Context context, Process ratingProcess){
		if(statementNode instanceof Comment){
			return;
		} else if(statementNode.getName() == null){
			return;
		}
		
		context.putValue(ContextParam.PARENTPROCESS, statementNode);
		context.putValue(ContextParam.PCHSTEPS, new ArrayList<Step>());
		
		Step processStep = AlgorithmContentParser.createStep(statementNode, context);
		
		processStep.setExecutionorder(ratingProcess.getSteps().size());
		processStep.setProcess(ratingProcess);
		
		String processStepKey = ratingProcess.getId()+"-"+processStep.getExecutionorder();
		processStep.setId(UUIDGenerator.generate(processStepKey).toUpperCase());
		
		processStep = Utility.addToList(ratingProcess.getSteps(),processStep);
	}
	*/
}
