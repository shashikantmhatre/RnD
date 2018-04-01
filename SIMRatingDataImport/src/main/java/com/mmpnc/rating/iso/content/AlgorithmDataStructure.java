package com.mmpnc.rating.iso.content;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class AlgorithmDataStructure {
	
	public StringBuffer simplifyDataStrucure(Document doc) {
		StringBuffer content = new StringBuffer();
		content.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		content.append("<Policy xpath='Policy'>");
		content.append(createWrapperNodeContent());
		
		Node tableNode = doc.getRootElement().selectSingleNode("reference[@name='Table Objects']/TableName");
		
		readTable(content, tableNode, new StringBuffer("Policy"), new StringBuffer("Policy"));
		
		content.append("</Policy>");
		
		return content;
	}

	@SuppressWarnings("unchecked")
	private void readTable(StringBuffer content, Node table,
			StringBuffer priorXPath, StringBuffer priorQualPath) {

		StringBuffer xpath = getPath(table.valueOf("@name"), priorXPath, "/");
		String tableName = table.valueOf("@name");
		StringBuffer qualpath = new StringBuffer();

		content.append("<").append(tableName);
		content.append(" ").append("xpath='").append(xpath).append("'");
		// content.append(" ").append("qualPath='").append(qualpath).append("'");
		content.append(" ").append("multiple='").append(table.valueOf("@multipleAllowed")).append("'");
		content.append(">\n");

		List<Node> columnNodes = (List<Node>) table.selectNodes("ColumnNode");
		for(Node columnNode : columnNodes){
			readColumn(content, columnNode, xpath, qualpath);
		}
		
		List<Node> tableNodes = (List<Node>) table.selectNodes("TableName");
		
		for(Node tableNode : tableNodes){
			readTable(content, tableNode, xpath, qualpath);
		}
		
		content.append("</").append(tableName).append(">\n");
	}

	private void readColumn(StringBuffer content, Node column,
			StringBuffer xpath, StringBuffer qualpath) {
		content.append("<").append(column.getText());
		content.append(" ").append("xpath='").append(xpath).append("/")
				.append(column.getText()).append("'");
		// content.append(" ").append("qualPath='").append(qualpath).append(".").append(column.getContent()).append("'");
		content.append(" ").append("multiple='false'");
		content.append("/>\n");
	}

	private StringBuffer getPath(String nodeName,
			StringBuffer priorPath, String joiner) {
		// System.out.println("joiner");
		StringBuffer buffer = new StringBuffer();
		if (priorPath == null) {
			buffer.append(nodeName);
		} else {
			buffer.append(priorPath).append(joiner).append(nodeName);
		}
		return buffer;
	}

	private StringBuffer createWrapperNodeContent() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("	<EffDate xpath='Policy/EffDate'/>");
		buffer.append("	<ExpDate xpath='Policy/ExpDate'/>");
		buffer.append("	<AnnDate xpath='Policy/AnnDate'/>");
		buffer.append("	<Renewal xpath='Policy/Renewal'/>");
		buffer.append("	<Premium xpath='Policy/Premium'/>");
		buffer.append("	<State xpath='Policy/State'>");
		buffer.append("		<Code xpath='Polict/State/Code'/>");
		buffer.append("	</State>");
		return buffer;
	}

}
