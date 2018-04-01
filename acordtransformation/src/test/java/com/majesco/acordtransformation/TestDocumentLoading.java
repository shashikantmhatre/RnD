package com.majesco.acordtransformation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.jaxen.SimpleNamespaceContext;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDocumentLoading {

	public Document parse(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}

	@Test
	public void testDocumentParsing() throws DocumentException, IOException {

		SimpleNamespaceContext nc = new SimpleNamespaceContext();
		nc.addNamespace("xsd", "http://www.w3.org/2001/XMLSchema");

		File file = new File("src\\test\\resources\\Component_Schemas_With_Codes\\combined.xsd");
		Document doc = parse(file.toURI().toURL());

		Element rootElement = doc.getRootElement();
//		Node node = rootElement.selectSingleNode("xsd:complexType[@name='Policy_Type']");
		Node node = rootElement.selectSingleNode("xsd:complexType[@name='InstallmentInfo_Type']");
//		Node node = rootElement.selectSingleNode("xsd:complexType[@name='BillingPolicyInfo_Type']");

		ParserContext parserContext = new ParserContext();

		traverse((Element) node, rootElement, parserContext, null);

		 ObjectMapper mapper = new ObjectMapper();
		 File resultFile = new File("src/test/resources/output.json");
		 mapper.writeValue(resultFile , parserContext);

		StringBuilder records = createInsertRecords(parserContext);
		Writer fileWriter = new FileWriter(new File("src/test/resources/output.sql"));
		fileWriter.append(records);
		fileWriter.close();
	}

	private StringBuilder createInsertRecords(ParserContext parserContext) {
		StringBuilder builder = new StringBuilder();
		for (Metadata metadata : parserContext.getMetadataList()) {
			if (metadata.getId() == null) {
				builder.append(createObjectMetadataInsert(metadata));
			}
		}
		return builder;
	}

	private StringBuilder createObjectMetadataInsert(Metadata metadata) {
		StringBuilder builder = new StringBuilder();

		// process the reference metadata before processing the current metadata
		Metadata referenceMetadata = metadata.getReference();
		if (referenceMetadata != null) {
			if ("PRIMITIVE".equals(referenceMetadata.getType())) {
				builder.append(createPrimitiveMetadataInsert(referenceMetadata));
			} else {
				if(!referenceMetadata.isProcessed()) {
					builder.append(createObjectMetadataInsert(referenceMetadata));
				}
			}
		}

//		metadata.setId(UUID.randomUUID());
		
		System.out.println("Start Processing " + metadata.getName());
		
		metadata.setProcessed(true);
		builder.append(
				"INSERT INTO PUBLIC.OBJECT_METADATA (ID, NAME, METADATA_TYPE, REFERENCE_METADATA, REFERENCE_METADATA_NAME) ");
		builder.append("VALUES('").append(metadata.getId().toString()).append("',");
		builder.append("'").append(metadata.getName()).append("',");
		builder.append("'").append(metadata.getType()).append("',");
		if (referenceMetadata != null) {
			builder.append("'").append(referenceMetadata.getId().toString()).append("',");
			builder.append("'").append(referenceMetadata.getName()).append("'");
		} else {
			builder.append("null,");
			builder.append("null");
		}
		builder.append(");");
		builder.append("\n");

		if (!metadata.getChildren().isEmpty()) {
			for (Entry<String, Metadata> child : metadata.getChildren().entrySet()) {
				builder.append(createObjectFieldMetadataInsert(metadata.getId(), child.getKey(), child.getValue()));
			}
		}

		// System.out.println(builder.toString());
		return builder;
	}

	private StringBuilder createPrimitiveMetadataInsert(Metadata metadata) {
		StringBuilder builder = new StringBuilder();

//		metadata.setId(UUID.randomUUID());
		builder.append("INSERT INTO PUBLIC.PRIMITIVE_METADATA (ID, NAME, METADATA_TYPE) ");
		builder.append("VALUES('").append(metadata.getId().toString()).append("',");
		builder.append("'").append(metadata.getName()).append("',");
		builder.append("'").append(metadata.getType()).append("'");
		builder.append(");");
		builder.append("\n");

		// System.out.println(builder.toString());
		return builder;
	}

	private StringBuilder createObjectFieldMetadataInsert(UUID parentMetadataId, String key, Metadata metadata) {
		StringBuilder builder = new StringBuilder();
			
		// process the reference metadata before processing the current metadata
		Metadata referenceMetadata = metadata.getReference();
		if (referenceMetadata != null) {
			if ("PRIMITIVE".equals(referenceMetadata.getType())) {
				builder.append(createPrimitiveMetadataInsert(referenceMetadata));
			} else {
				builder.append(createObjectMetadataInsert(referenceMetadata));
			}
		}

//		metadata.setId(UUID.randomUUID());
		builder.append(
				"INSERT INTO PUBLIC.OBJECT_FIELD_METADATA (ID, NAME, METADATA_TYPE, PARENT_METADATA, REFERENCE_METADATA, REFERENCE_METADATA_NAME, REFERENCE_METADATA_TYPE)");
		builder.append("VALUES('").append(metadata.getId().toString()).append("',");
		builder.append("'").append(metadata.getName()).append("',");
		builder.append("'").append(metadata.getType()).append("',");
		builder.append("'").append(parentMetadataId.toString()).append("',");
		if (referenceMetadata != null) {
			builder.append("'").append(referenceMetadata.getId().toString()).append("',");
			builder.append("'").append(referenceMetadata.getName()).append("',");
			builder.append("'").append(referenceMetadata.getType()).append("'");
		} else {
			builder.append("null,");
			builder.append("null,");
			builder.append("null");
		}
		builder.append(");");
		builder.append("\n");

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

		if (elementReference != null && !"".equals(elementReference)) {
			Metadata childMetadata = new Metadata();
			childMetadata.setId(UUID.randomUUID());
			
			if(!parserContext.getMetadataMap().containsKey(elementReference)) {
				childMetadata.setName(elementReference);
//				childMetadata.setType(elementType);
				parserContext.getMetadataMap().put(elementReference,childMetadata);
				parserContext.getMetadataList().add(childMetadata);
			} else {
				childMetadata = parserContext.getMetadataMap().get(elementReference);
			}
			
			metadata.getChildren().put(childMetadata.getName(), childMetadata);

			Node elementNode = rootElement.selectSingleNode("xsd:element[@name='" + elementReference + "']");

			// if no node is available, it means its accessing data from ext file
			if (elementNode != null) {

				String typeReference = elementNode.valueOf("@type");
				Node typeNode = rootElement.selectSingleNode("xsd:*[@name='" + typeReference + "']");

				if (typeNode != null) {
					Metadata reference = new Metadata();
					childMetadata.setId(UUID.randomUUID());
					childMetadata.setReference(reference);
					traverse((Element) typeNode, rootElement, parserContext, reference);
				}
			}
		} else if (elementType != null || !"".equals(elementType)) {
			Node typeNode = rootElement.selectSingleNode("xsd:*[@name='" + elementType + "']");

			if (typeNode != null) {
				traverse((Element) typeNode, rootElement, parserContext, metadata);
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
		} else {
			parentMetadata = metadata;
		}
		
		if(metadataName == null || "".equals(metadataName)||"null".equals(metadataName)) {
			throw new UnsupportedOperationException("Name is null in " + complexTypeElement.asXML());
		}

		if(!parserContext.getMetadataMap().containsKey(metadataName)) {
			parentMetadata.setName(metadataName);
			parserContext.getMetadataMap().put(metadataName,parentMetadata);
			parserContext.getMetadataList().add(parentMetadata);
		} else {
			parentMetadata.setName(metadataName);
			parentMetadata = parserContext.getMetadataMap().get(metadataName);
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
