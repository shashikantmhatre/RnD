package com.majesco.acordtransformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.jaxen.SimpleNamespaceContext;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDocumentParsing {

	public Document parse(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}

	@Test
	public void testDocumentParsing() throws DocumentException, IOException, JAXBException, InvalidFormatException {

		SimpleNamespaceContext nc = new SimpleNamespaceContext();
		nc.addNamespace("xsd", "http://www.w3.org/2001/XMLSchema");

		System.out.println("Start Time " + Calendar.getInstance().getTime().toString());

		File file = new File("src\\test\\resources\\Component_Schemas_With_Codes\\combined.xsd");
		Document doc = parse(file.toURI().toURL());

		Element rootElement = doc.getRootElement();
		Node node = rootElement.selectSingleNode("xsd:complexType[@name='Policy_Type']");
//		 Node node =
//		 rootElement.selectSingleNode("xsd:complexType[@name='Location_Type']");
//		 Node node =
//		 rootElement.selectSingleNode("xsd:complexType[@name='PersAutoLineBusiness_Type']");
//		Node node = rootElement.selectSingleNode("xsd:complexType[@name='InstallmentInfo_Type']");
		// Node node =
		// rootElement.selectSingleNode("xsd:complexType[@name='BillingPolicyInfo_Type']");

		ParserContext parserContext = new ParserContext();

		traverse((Element) node, rootElement, parserContext, null);

		// Writer fileWriter = new FileWriter(new
		// File("src/test/resources/output.xml"));
		// JAXBContext jaxbContext = JAXBContext.newInstance(ParserContext.class);
		// Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		// jaxbMarshaller.marshal(parserContext, fileWriter);

		// ObjectMapper mapper = new ObjectMapper();
		// File resultFile = new File("src/test/resources/output.json");
		// mapper.writeValue(resultFile, parserContext);

		//
		Workbook workbook = new XSSFWorkbook();
		workbook.createSheet("OBJECT");
		workbook.createSheet("FIELD");
		workbook.createSheet("PRIMITIVE");

		StringBuilder records = createInsertRecords(parserContext, workbook);
		Writer fileWriter = new FileWriter(new File("src/test/resources/output.sql"));
		fileWriter.append(records);
		fileWriter.close();

		FileOutputStream excelFile = new FileOutputStream(new File("src/test/resources/output.xlsx"));
		workbook.write(excelFile);
		excelFile.close();

		System.out.println("End Time " + Calendar.getInstance().getTime().toString());
	}

	private StringBuilder createInsertRecords(ParserContext parserContext, Workbook workbook) {

		StringBuilder builder = new StringBuilder();
		for (Metadata metadata : parserContext.getMetadataList()) {
			if (!metadata.isProcessed()) {
				builder.append(createObjectMetadataInsert(metadata, workbook));
			}
		}
		return builder;
	}

	private StringBuilder createObjectMetadataInsert(Metadata metadata, Workbook workbook) {
		StringBuilder builder = new StringBuilder();

		// process the reference metadata before processing the current metadata
		Metadata referenceMetadata = metadata.getReference();
		if (referenceMetadata != null && !referenceMetadata.isProcessed()) {
			if ("PRIMITIVE".equals(referenceMetadata.getType())) {
				builder.append(createPrimitiveMetadataInsert(referenceMetadata, workbook));
			} else {
				builder.append(createObjectMetadataInsert(referenceMetadata, workbook));
			}
		}

		if (referenceMetadata != null) {
			if (!"PRIMITIVE".equals(referenceMetadata.getType())) {
				metadata.setType("MODEL_RELATION");
			}
		}

		// metadata.setId(UUID.randomUUID());

		System.out.println("Start Processing " + metadata.getName());

		metadata.setProcessed(true);
		if (metadata.getType() == null) {
			metadata.setType("OBJECT");
		}
		builder.append(
				"INSERT INTO PUBLIC.OBJECT_METADATA (ID, NAME, METADATA_TYPE, REFERENCE_METADATA, REFERENCE_METADATA_NAME) ");
		builder.append("VALUES('").append(metadata.getId().toString().replaceAll("-", "")).append("',");
		builder.append("'").append(metadata.getName()).append("',");
		builder.append("'").append(metadata.getType()).append("',");
		if (referenceMetadata != null) {
			builder.append("'").append(referenceMetadata.getId().toString().replaceAll("-", "")).append("',");
			builder.append("'").append(referenceMetadata.getName()).append("'");
		} else {
			builder.append("null,");
			builder.append("null");
		}
		builder.append(");");
		builder.append("\n");

		Sheet objectSheet = workbook.getSheet("OBJECT");

		if (objectSheet.getPhysicalNumberOfRows() <= 0) {
			Row row = objectSheet.createRow(0);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue("ID");
			Cell cell1 = row.createCell(1);
			cell1.setCellValue("NAME");
			Cell cell2 = row.createCell(2);
			cell2.setCellValue("METADATA_TYPE");
			Cell cell3 = row.createCell(3);
			cell3.setCellValue("REFERENCE_METADATA");
			Cell cell4 = row.createCell(4);
			cell4.setCellValue("REFERENCE_METADATA_NAME");
		}

		Row row = objectSheet.createRow(objectSheet.getPhysicalNumberOfRows());
		Cell cell0 = row.createCell(0);
		cell0.setCellValue(metadata.getId().toString().replaceAll("-", ""));
		Cell cell1 = row.createCell(1);
		cell1.setCellValue(metadata.getName());
		Cell cell2 = row.createCell(2);
		cell2.setCellValue(metadata.getType());
		Cell cell3 = row.createCell(3);
		Cell cell4 = row.createCell(4);
		if (referenceMetadata != null) {
			cell3.setCellValue(referenceMetadata.getId().toString().replaceAll("-", ""));
			cell4.setCellValue(referenceMetadata.getName());
		}

		if (!metadata.getChildren().isEmpty()) {
			for (Entry<String, Metadata> child : metadata.getChildren().entrySet()) {
				builder.append(
						createObjectFieldMetadataInsert(metadata, child.getKey(), child.getValue(), workbook));
			}
		}

		// System.out.println(builder.toString());
		return builder;
	}

	private StringBuilder createPrimitiveMetadataInsert(Metadata metadata, Workbook workbook) {
		StringBuilder builder = new StringBuilder();

		// metadata.setId(UUID.randomUUID());
		metadata.setProcessed(true);
		builder.append("INSERT INTO PUBLIC.PRIMITIVE_METADATA (ID, NAME, METADATA_TYPE) ");
		builder.append("VALUES('").append(metadata.getId().toString().replaceAll("-", "")).append("',");
		builder.append("'").append(metadata.getName()).append("',");
		builder.append("'").append(metadata.getType()).append("'");
		builder.append(");");
		builder.append("\n");

		Sheet primitiveSheet = workbook.getSheet("PRIMITIVE");

		if (primitiveSheet.getPhysicalNumberOfRows() <= 0) {
			Row row = primitiveSheet.createRow(0);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue("ID");
			Cell cell1 = row.createCell(1);
			cell1.setCellValue("NAME");
			Cell cell2 = row.createCell(2);
			cell2.setCellValue("METADATA_TYPE");
		}

		Row row = primitiveSheet.createRow(primitiveSheet.getPhysicalNumberOfRows());
		Cell cell0 = row.createCell(0);
		cell0.setCellValue(metadata.getId().toString().replaceAll("-", ""));
		Cell cell1 = row.createCell(1);
		cell1.setCellValue(metadata.getName());
		Cell cell2 = row.createCell(2);
		cell2.setCellValue(metadata.getType());

		// System.out.println(builder.toString());
		return builder;
	}

	private StringBuilder createObjectFieldMetadataInsert(Metadata parentMetadata, String key, Metadata metadata,
			Workbook workbook) {
		StringBuilder builder = new StringBuilder();

		// process the reference metadata before processing the current metadata
		Metadata referenceMetadata = metadata.getReference();
		if (referenceMetadata != null && !referenceMetadata.isProcessed()) {
			if ("PRIMITIVE".equals(referenceMetadata.getType())) {
				builder.append(createPrimitiveMetadataInsert(referenceMetadata, workbook));
			} else {
				builder.append(createObjectMetadataInsert(referenceMetadata, workbook));
			}
		}

		if (referenceMetadata != null) {
			if (!"PRIMITIVE".equals(referenceMetadata.getType())) {
				metadata.setType("MODEL_RELATION");
			}
		}

		// metadata.setId(UUID.randomUUID());
		metadata.setProcessed(true);
		builder.append(
				"INSERT INTO PUBLIC.OBJECT_FIELD_METADATA (ID, NAME, METADATA_TYPE, PARENT_METADATA,PARENT_METADATA_NAME, REFERENCE_METADATA, REFERENCE_METADATA_NAME, REFERENCE_METADATA_TYPE)");
		builder.append("VALUES('").append(metadata.getId().toString().replaceAll("-", "")).append("',");
		builder.append("'").append(metadata.getName()).append("',");
		builder.append("'").append(metadata.getType()).append("',");
		builder.append("'").append(parentMetadata.getId().toString().replaceAll("-", "")).append("',");
		builder.append("'").append(parentMetadata.getName().toString()).append("',");
		if (referenceMetadata != null) {
			builder.append("'").append(referenceMetadata.getId().toString().replaceAll("-", "")).append("',");
			builder.append("'").append(referenceMetadata.getName()).append("',");
			builder.append("'").append(referenceMetadata.getType()).append("'");
		} else {
			builder.append("null,");
			builder.append("null,");
			builder.append("null");
		}
		builder.append(");");
		builder.append("\n");

		Sheet fieldSheet = workbook.getSheet("FIELD");

		if (fieldSheet.getPhysicalNumberOfRows() <= 0) {
			Row row = fieldSheet.createRow(0);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue("ID");
			Cell cell1 = row.createCell(1);
			cell1.setCellValue("NAME");
			Cell cell2 = row.createCell(2);
			cell2.setCellValue("METADATA_TYPE");
			Cell cell3 = row.createCell(3);
			cell3.setCellValue("PARENT_METADATA");
			Cell cell4 = row.createCell(4);
			cell4.setCellValue("PARENT_METADATA_NAME");
			Cell cell5 = row.createCell(5);
			cell5.setCellValue("REFERENCE_METADATA");
			Cell cell6 = row.createCell(6);
			cell6.setCellValue("REFERENCE_METADATA_NAME");
			Cell cell7 = row.createCell(7);
			cell7.setCellValue("REFERENCE_METADATA_TYPE");
		}

		Row row = fieldSheet.createRow(fieldSheet.getPhysicalNumberOfRows());
		Cell cell0 = row.createCell(0);
		cell0.setCellValue(metadata.getId().toString().replaceAll("-", ""));
		Cell cell1 = row.createCell(1);
		cell1.setCellValue(metadata.getName());
		Cell cell2 = row.createCell(2);
		cell2.setCellValue(metadata.getType());
		Cell cell3 = row.createCell(3);
		cell3.setCellValue(parentMetadata.getId().toString().replaceAll("-", ""));
		Cell cell4 = row.createCell(4);
		cell4.setCellValue(parentMetadata.getName().toString());
		Cell cell5 = row.createCell(5);
		Cell cell6 = row.createCell(6);
		Cell cell7 = row.createCell(7);

		
		if (referenceMetadata != null) {
			cell5.setCellValue(referenceMetadata.getId().toString().replaceAll("-", ""));
			cell6.setCellValue(referenceMetadata.getName());
			cell7.setCellValue(referenceMetadata.getType());
		}

		// System.out.println(builder.toString());
		return builder;
	}

	private void traverse(Element element, Element rootElement, ParserContext parserContext, Metadata metadata) {
		// iterate through child elements of root
		if (element.getName().equals("sequence")) {
			readSequenceElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("complexType")) {
			readComplexTypeElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("complexContent")) {
			readComplexContentElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("element")) {
			readElementTypeElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("simpleContent")) {
			readSimpleContentElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("simpleType")) {
			readSimpleTypeElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("attribute")) {
			readAttributeElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("extension")) {
			readExtentionElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("choice")) {
			readChoiceElement(element, rootElement, parserContext, metadata);
		} else if (element.getName().equals("group")) {
			readGroupElement(element, rootElement, parserContext, metadata);
		} else {
			// System.out.println("parse " + element.getName());
		}

	}

	private void readGroupElement(Element groupElement, Element rootElement, ParserContext parserContext,
			Metadata metadata) {

		String refValue = groupElement.valueOf("@ref");

		List<Node> groupElements = rootElement.selectNodes("xsd:group[@name='" + refValue + "']/xsd:sequence/*");

		for (Node extensionNode : groupElements) {
			traverse((Element) extensionNode, rootElement, parserContext, metadata);
		}

		// iterate through child elements of root
		for (Iterator<Element> it = groupElement.elementIterator(); it.hasNext();) {
			Element childElement = it.next();
			traverse(childElement, rootElement, parserContext, metadata);
		}
	}

	private void readComplexContentElement(Element complexContentElement, Element rootElement,
			ParserContext parserContext, Metadata metadata) {
		// iterate through child elements of root
		for (Iterator<Element> it = complexContentElement.elementIterator(); it.hasNext();) {
			Element childElement = it.next();
			traverse(childElement, rootElement, parserContext, metadata);
		}
	}

	private void readChoiceElement(Element choiceElement, Element rootElement, ParserContext parserContext,
			Metadata metadata) {
		// iterate through child elements of root
		for (Iterator<Element> it = choiceElement.elementIterator(); it.hasNext();) {
			Element childElement = it.next();
			traverse(childElement, rootElement, parserContext, metadata);
		}
	}

	private void readExtentionElement(Element extensionElement, Element rootElement, ParserContext parserContext,
			Metadata metadata) {

		String baseType = extensionElement.valueOf("@base");

		if (baseType.equals("PCLINEBUSINESS")) {
			metadata.setType("MODEL");
		}

		List<Node> extentionElements = rootElement.selectNodes("xsd:*[@name='" + baseType + "']/xsd:sequence/*");

		for (Node extensionNode : extentionElements) {
			traverse((Element) extensionNode, rootElement, parserContext, metadata);
		}

		// iterate through child elements of root
		for (Iterator<Element> it = extensionElement.elementIterator(); it.hasNext();) {
			Element childElement = it.next();
			traverse(childElement, rootElement, parserContext, metadata);
		}

	}

	private void readAttributeElement(Element element, Element rootElement, ParserContext parserContext,
			Metadata metadata) {
		metadata.getAttribute().put(element.valueOf("@name"), element.valueOf("@type"));
	}

	private void readSimpleTypeElement(Element element, Element rootElement, ParserContext parserContext,
			Metadata metadata) {
		// TODO Auto-generated method stub

	}

	private void readSimpleContentElement(Element element, Element rootElement, ParserContext parserContext,
			Metadata metadata) {
		metadata.setType("PRIMITIVE");
	}

	private void readElementTypeElement(Element childElement, Element rootElement, ParserContext parserContext,
			Metadata metadata) {
		String elementReference = childElement.valueOf("@ref");
		String elementType = childElement.valueOf("@type");
		String elementname = childElement.valueOf("@name");

		if (elementReference != null && !"".equals(elementReference)) {
			// this is a element inside a complex type, as it references the another element
			// name
			Metadata childMetadata = new Metadata();
			childMetadata.setId(UUID.randomUUID());
			childMetadata.setName(elementReference);
			childMetadata.setType("FIELD");

			metadata.getChildren().put(elementReference, childMetadata);

			Node elementNode = rootElement.selectSingleNode("xsd:element[@name='" + elementReference + "']");
			if (elementNode != null) {
				String typeReference = elementNode.valueOf("@type");
				Node typeNode = rootElement.selectSingleNode("xsd:*[@name='" + typeReference + "']");

				if (typeNode != null) {

					// this is reference node for the child field
					Metadata reference;
					String metadataName = typeNode.valueOf("@name");

					if (!parserContext.getMetadataMap().containsKey(metadataName)) {
						reference = new Metadata();
						reference.setId(UUID.randomUUID());
						reference.setName(metadataName);
						parserContext.getMetadataMap().put(metadataName, reference);
						parserContext.getMetadataList().add(reference);
					} else {
						reference = parserContext.getMetadataMap().get(metadataName);
					}

					childMetadata.setReference(reference);
					traverse((Element) typeNode, rootElement, parserContext, reference);
				} else {
					if (elementReference.equals("RemarkText")) {
						Metadata reference = new Metadata();
						reference.setId(UUID.randomUUID());
						reference.setName("RemarkText");
						childMetadata.setReference(reference);
						typeNode = rootElement.selectSingleNode("xsd:*[@name='C-Infinite']");
						traverse((Element) typeNode, rootElement, parserContext, reference);
					} else {
						System.out.println("typeNode note found " + elementNode.asXML() + " while processing "
								+ elementNode.asXML());
						throw new UnsupportedOperationException("ReferenceNode not available for " + typeReference);
					}
				}
			}

		}

	}

	private void readComplexTypeElement(Element complexTypeElement, Element rootElement, ParserContext parserContext,
			Metadata metadata) {
		Metadata parentMetadata;
		String metadataName = complexTypeElement.valueOf("@name");
		if (metadata == null) {
			parentMetadata = new Metadata();
			parentMetadata.setId(UUID.randomUUID());
			parentMetadata.setName(metadataName);
			parserContext.getMetadataList().add(parentMetadata);
			parserContext.getMetadataMap().put(metadataName, parentMetadata);
		} else {
			if (!parserContext.getMetadataMap().containsKey(metadataName)) {
				parentMetadata = metadata;
				parentMetadata.setId(UUID.randomUUID());
				parentMetadata.setName(metadataName);
				parserContext.getMetadataMap().put(metadataName, parentMetadata);
				parserContext.getMetadataList().add(parentMetadata);
			} else {
				parentMetadata = parserContext.getMetadataMap().get(metadataName);
			}
		}

		// iterate through child elements of complexType
		for (Iterator<Element> it = complexTypeElement.elementIterator(); it.hasNext();) {
			Element childElement = it.next();
			traverse(childElement, rootElement, parserContext, parentMetadata);
		}

	}

	private void readSequenceElement(Element sequenceElement, Element rootElement, ParserContext parserContext,
			Metadata metadata) {
		// iterate through child elements of root
		for (Iterator<Element> it = sequenceElement.elementIterator(); it.hasNext();) {
			Element childElement = it.next();
			traverse(childElement, rootElement, parserContext, metadata);
		}
	}

}
