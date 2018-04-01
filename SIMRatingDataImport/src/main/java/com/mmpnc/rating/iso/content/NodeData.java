package com.mmpnc.rating.iso.content;

import java.util.List;

import org.dom4j.Comment;
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

public class NodeData {
	private Node node;
	
	
	public NodeData(Node node){
		this.node = node;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getRatebookElement(){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Ratebook { ");
		buffer.append(" ID:\"").append(this.node.valueOf("Id")).append("\",");
		buffer.append(" WorkbookId:\"").append(this.node.valueOf("WorkbookId")).append("\",");
		buffer.append(" ParentRatebookId:\"").append(this.node.valueOf("ParentRatebookId")).append("\",");
		buffer.append(" Title:\"").append(this.node.valueOf("Title")).append("\",");
		buffer.append(" NewBusinessEffective:\"").append(this.node.valueOf("NewBusinessEffective")).append("\",");
		buffer.append(" NewBusinessExpiration:\"").append(this.node.valueOf("NewBusinessExpiration")).append("\",");
		buffer.append(" RenewalEffective:\"").append(this.node.valueOf("RenewalEffective")).append("\",");
		buffer.append(" RenewalExpiration:\"").append(this.node.valueOf("RenewalExpiration")).append("\"");
		
		List<Node> nodes = (List<Node>)this.node.selectNodes("Ratebook");
		
		for(Node node: nodes){		
			NodeData rb = new NodeData(node);
			buffer.append(",").append(rb.getRatebookElement());
		}
		
		buffer.append("}");
		
		
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getDomainTableElement() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DomainTable { ");
		buffer.append(" ID:\"").append(this.node.valueOf("Id")).append("\", ");
		buffer.append(" RatebookId:\"").append(this.node.valueOf("RatebookId")).append("\", ");
		buffer.append(" TableId:\"").append(this.node.valueOf("TableId")).append("\", ");
		buffer.append(" Name:\"").append(this.node.valueOf("Name")).append("\", ");
		buffer.append(" Title:\"").append(this.node.valueOf("Title").replaceAll("\"", "'")).append("\", ");
		buffer.append(" Type:\"").append(this.node.valueOf("Type")).append("\", ");
		buffer.append(" BureauRuleNumber:\"").append(this.node.valueOf("BureauRuleNumber")).append("\" ");

		List<Node> nodes = (List<Node>)this.node.selectNodes("DomainTableKey");
		
		for(Node node: nodes){		
			NodeData rtk = new NodeData(node);
			buffer.append(",").append(rtk.getDomainTableKeyElement());
		}

		nodes = (List<Node>)this.node.selectNodes("DomainTableValue");
		
		for(Node node: nodes){		
			NodeData rtk = new NodeData(node);
			buffer.append(",").append(rtk.getDomainTableValueElement());
		}

		nodes = (List<Node>)this.node.selectNodes("DomainTableEntry");
		
		for(Node node: nodes){		
			NodeData rtk = new NodeData(node);
			buffer.append(",").append(rtk.getDomainTableEntryElement());
		}
		
		buffer.append("}");
		return buffer;
	}
	

	private StringBuffer getDomainTableKeyElement() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DomainTableKey { ");
		buffer.append(" ID:\"").append(this.node.valueOf("Id")).append("\",");
		buffer.append(" DomainTableId:\"").append(this.node.valueOf("RateTableId")).append("\",");
		buffer.append(" Sequence:\"").append(this.node.valueOf("Sequence")).append("\",");
		buffer.append(" Name:\"").append(this.node.valueOf("Name")).append("\",");
		buffer.append(" Type:\"").append(this.node.valueOf("Type")).append("\",");
		buffer.append(" Size:\"").append(this.node.valueOf("Size")).append("\"");		
		buffer.append("} ");
		
		return buffer;
	}

	private StringBuffer getDomainTableValueElement() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DomainTableValue { ");
		buffer.append(" ColumnId:\"").append(this.node.valueOf("ColumnId")).append("\",");
		buffer.append(" ColumnName:\"").append(this.node.valueOf("ColumnName")).append("\",");
		buffer.append(" ColumnType:\"").append(this.node.valueOf("ColumnType")).append("\",");
		buffer.append(" ColumnDescription:\"").append(this.node.valueOf("ColumnDescription")).append("\",");
		buffer.append(" Type:\"").append(this.node.valueOf("Type")).append("\",");
		buffer.append(" Size:\"").append(this.node.valueOf("Size")).append("\",");		
		buffer.append(" Scale:\"").append(this.node.valueOf("Scale")).append("\"");		
		buffer.append("} ");
		
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	private StringBuffer getDomainTableEntryElement() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("DomainTableEntry { ");
		
		buffer.append(" RatebookId:\"").append(this.node.valueOf("RatebookId")).append("\",");
		buffer.append(" AddChangeDeleteFlag:\"").append(this.node.valueOf("AddChangeDeleteFlag")).append("\",");
		buffer.append(" Sequence:\"").append(this.node.valueOf("Sequence")).append("\" ");

		List<Node> nodes = (List<Node>)this.node.selectNodes("node()[@isKey='1']");
		
		for(Node node: nodes){		
			buffer.append(",");
			buffer.append(" Key { ");
			buffer.append(" Name:\"").append(node.getName()).append("\",");
			buffer.append(" value:\"").append(node.getText().replace("\"", "^")).append("\"");			
			buffer.append(" } ");		
		}
		buffer.append(" , Value {");		
		buffer.append(" DataValue:\"").append(this.node.valueOf("DataValue").replace("\"", "^")).append("\", ");
		buffer.append(" DisplayValue:\"").append(this.node.valueOf("DisplayValue").replace("\"", "^")).append("\", ");
		buffer.append(" MinimumValue:\"").append(this.node.valueOf("MinimumValue")).append("\", ");
		buffer.append(" MaximumValue:\"").append(this.node.valueOf("MaximumValue")).append("\" ");
		buffer.append(" } ");		
		
		buffer.append("} ");
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getRateTableElement(){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("RateTable { ");
		buffer.append(" ID:\"").append(this.node.valueOf("Id")).append("\",");
		buffer.append(" RatebookId:\"").append(this.node.valueOf("RatebookId")).append("\",");
		buffer.append(" TableId:\"").append(this.node.valueOf("TableId")).append("\",");
		buffer.append(" Name:\"").append(this.node.valueOf("Name")).append("\",");
		buffer.append(" Title:\"").append(this.node.valueOf("Title").replaceAll("\"", "'")).append("\",");
		buffer.append(" BureauRuleNumber:\"").append(this.node.valueOf("BureauRuleNumber")).append("\"");
		
		List<Node> nodes = (List<Node>)this.node.selectNodes("RateTableKey");
		
		for(Node node: nodes){		
			NodeData rtk = new NodeData(node);
			buffer.append(",").append(rtk.getRateTableKeyElement());
		}

		nodes = (List<Node>)this.node.selectNodes("RateTableValue");
		
		for(Node node: nodes){		
			NodeData rtv = new NodeData(node);
			buffer.append(",").append(rtv.getRateTableValueElement());
		}

		nodes = (List<Node>)this.node.selectNodes("RateTableEntry");
		
		for(Node node: nodes){		
			NodeData rte = new NodeData(node);
			buffer.append(",").append(rte.getRateTableEntryElement());
		}
		
		
		buffer.append("} ");
		
		return buffer;
	}
	
	public StringBuffer getRateTableKeyElement() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("RateTableKey { ");
		buffer.append(" ID:\"").append(this.node.valueOf("Id")).append("\",");
		buffer.append(" RateTableId:\"").append(this.node.valueOf("RateTableId")).append("\",");
		buffer.append(" Sequence:\"").append(this.node.valueOf("Sequence")).append("\",");
		buffer.append(" LookupType:\"").append(this.node.valueOf("LookupType")).append("\",");
		buffer.append(" Name:\"").append(this.node.valueOf("Name")).append("\",");
		buffer.append(" Type:\"").append(this.node.valueOf("Type")).append("\",");
		buffer.append(" Size:\"").append(this.node.valueOf("Size")).append("\"");		
		buffer.append("} ");
		
		return buffer;
	
	}
	
	public StringBuffer getRateTableValueElement() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("RateTableValue { ");
		buffer.append(" ColumnId:\"").append(this.node.valueOf("ColumnId")).append("\",");
		buffer.append(" ColumnName:\"").append(this.node.valueOf("ColumnName")).append("\",");
		buffer.append(" ColumnType:\"").append(this.node.valueOf("ColumnType")).append("\",");
		buffer.append(" ColumnDescription:\"").append(this.node.valueOf("ColumnDescription")).append("\",");
		buffer.append(" Type:\"").append(this.node.valueOf("Type")).append("\",");
		buffer.append(" Size:\"").append(this.node.valueOf("Size")).append("\",");		
		buffer.append(" Scale:\"").append(this.node.valueOf("Scale")).append("\"");		
		buffer.append("} ");
		
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getRateTableEntryElement() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("RateTableEntry { ");
		
		buffer.append(" RatebookId:\"").append(this.node.valueOf("RatebookId")).append("\",");
		buffer.append(" AddChangeDeleteFlag:\"").append(this.node.valueOf("AddChangeDeleteFlag")).append("\",");
		buffer.append(" Sequence:\"").append(this.node.valueOf("Sequence")).append("\" ");

		List<Node> nodes = (List<Node>)this.node.selectNodes("node()[@isKey='1']");
		
		for(Node node: nodes){		
			buffer.append(",");
			buffer.append(" Key { ");
			buffer.append(" Name:\"").append(node.getName()).append("\",");
			buffer.append(" value:\"").append(node.getText().replace("\"", "^")).append("\"");			
			buffer.append(" } ");		
		}

		nodes = (List<Node>)this.node.selectNodes("node()[@isValue='1']");
		
		for(Node node: nodes){		
			buffer.append(",");
			buffer.append(" Value { ");
			buffer.append(" Name:\"").append(node.getName()).append("\",");
			buffer.append(" value:\"").append(node.getText().replace("\"", "^")).append("\"");			
			buffer.append(" } ");		
		}
		
		buffer.append("} ");
		return buffer;
	}
	
	
	
	/*
	@SuppressWarnings("unchecked")
	public StringBuffer getPCHElement(){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("program { ");
		buffer.append("name:\"").append(this.node.valueOf("@name")).append("\", ");
		buffer.append("overridden:\"").append(this.node.valueOf("@overridden")).append("\", ");
		buffer.append("excelSheet:\"").append(this.node.valueOf("@excelSheet")).append("\" ");
		
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			
			NodeData pch = new NodeData(statementNode);
			buffer.append(pch.getStatementElement());
		}
		
		
		buffer.append(" }");
		
		return buffer;
	}
	*/
	
	public StringBuffer getStatementElement(){
		StringBuffer buffer = new StringBuffer();

		//bypass the comment element during processing
		if(this.node instanceof Comment){
			return buffer;
		}
		if(this.node.getName() == null){
			return buffer;
		}
		
		if(this.node.getName().equals("if")){
			NodeData pch = new NodeData(this.node);
			buffer.append(pch.getIfElement());
		} else if(this.node.getName().equals("assign")){
			NodeData pch = new NodeData(this.node);
			buffer.append(pch.getAssignElement());
		} else if(this.node.getName().equals("loop")){
			NodeData pch = new NodeData(this.node);
			buffer.append(pch.getLoopElement());
		} 
		
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getIfElement(){
		StringBuffer buffer = new StringBuffer();
		boolean validFlag = false;
		buffer.append("IF { ");
		
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			
			if(statementNode.getName().equals("condition")){
				NodeData pch = new NodeData(statementNode);
				buffer.append("CONDITION { ");
				buffer.append(pch.getConditionElement());
				buffer.append("} ");
			} else if(statementNode.getName().equals("then")){
				NodeData pch = new NodeData(statementNode);
				
				StringBuffer thenBuffer = (StringBuffer) pch.getThenElement();
				
				if(thenBuffer.length() > 0) {
					buffer.append("THEN { ");
					buffer.append(thenBuffer);
					buffer.append("} ");
					validFlag = true;
				} 
			} else if(statementNode.getName().equals("else")){
				NodeData pch = new NodeData(statementNode);
				
				StringBuffer elseBuffer = (StringBuffer) pch.getElseElement();
				
				if(elseBuffer.length() > 0){
					if(validFlag == true){
						buffer.append("ELSE { ");
					} else{
						buffer.append("THEN { ");
					}
					buffer.append(elseBuffer);
					buffer.append("} ");
				}
			} 
		}
		
		
		buffer.append(" }");
		
		if(buffer.length() > 7 ){
			return buffer;
		} else {
			return new StringBuffer();
		}
	}
	
	@SuppressWarnings("unchecked")
	private Object getConditionElement() {
		StringBuffer buffer = new StringBuffer();
//		buffer.append("CONDITION { ");
		
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			
			if(statementNode.getName().equals("expression")){
				NodeData pch = new NodeData(statementNode);
				buffer.append(pch.getExpressionElement());
			}
		}
		
//		buffer.append(" } ");
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	private Object getThenElement() {
		StringBuffer buffer = new StringBuffer();
//		buffer.append("THEN { ");
		
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			
			NodeData pch = new NodeData(statementNode);
			buffer.append(pch.getStatementElement());
		}
		
//		buffer.append(" } ");
		
		if(buffer.length() > 0 ){
			return buffer;
		} else {
			return new StringBuffer();
		}
		
	}

	@SuppressWarnings("unchecked")
	private Object getElseElement() {
		StringBuffer buffer = new StringBuffer();
//		buffer.append("ELSE { ");
		
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			
			NodeData pch = new NodeData(statementNode);
			buffer.append(pch.getStatementElement());
		}	
		
//		buffer.append(" } ");

		if(buffer.length() > 0 ){
			return buffer;
		} else {
			return new StringBuffer();
		}

	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getLoopElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("LOOP { ");
		
		buffer.append("through:\"").append(this.node.valueOf("@through")).append("\" ");
		
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			
			NodeData pch = new NodeData(statementNode);
			buffer.append(pch.getStatementElement());
		}
		
		buffer.append(" } ");
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getAssignElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ASSIGN { ");
		buffer.append("variable:\"").append(this.node.valueOf("@lValue")).append("\", ");
		buffer.append("variableType:\"").append(this.node.valueOf("@variableType")).append("\" ");
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			if(statementNode.getName().equals("expression")){
				NodeData pch = new NodeData(statementNode);
				buffer.append(pch.getExpressionElement());
			}
		}
		
		buffer.append(" } ");
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getExpressionElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("EXPRESSION { ");
		
		buffer.append("op:\"").append(this.node.valueOf("@op").replaceAll("\"", "'")).append("\", ");
		buffer.append("side:\"").append(this.node.valueOf("@side")).append("\", ");
		buffer.append("variableType:\"").append(this.node.valueOf("@variableType")).append("\", ");
		buffer.append("ruleReference:\"").append(this.node.valueOf("@ruleReference")).append("\", ");
		buffer.append("value:\"").append(this.node.getText().trim().replaceAll("\"", "'")).append("\" ");
		
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			if(statementNode.getName().equals("expression")){
				NodeData pch = new NodeData(statementNode);
				buffer.append(pch.getExpressionElement());
			}
			if(statementNode.getName().equals("bracket")){
				NodeData pch = new NodeData(statementNode);
				buffer.append(pch.getBracketElement());
			}
			if(statementNode.getName().equals("ratetable")){
				NodeData pch = new NodeData(statementNode);
				buffer.append(pch.getRTElement());
			}
			if(statementNode.getName().equals("function")){
				NodeData pch = new NodeData(statementNode);
				buffer.append(pch.getFunctionElement());
			}
		}
		
		buffer.append(" } ");
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	private Object getFunctionElement() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("FUNCTION { ");
		
		buffer.append("name:\"").append(this.node.valueOf("@type")).append("\" ");
		
		for(Node args : (List<Node>)this.node.selectNodes("arg")){
			buffer.append("ARG { ");
			buffer.append("number:\"").append(this.node.valueOf("@number")).append("\", ");
//			buffer.append("variableType:\"").append(this.node.valueOf("@variableType")).append("\", ");
			buffer.append("value:\"").append(args.getText().trim().replaceAll("\"", "'")).append("\" ");
			buffer.append(" } ");
		}
		
		buffer.append(" } ");
		return buffer;
	}

	@SuppressWarnings("unchecked")
	private Object getRTElement() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("RATETABLE { ");
		
		buffer.append("name:\"").append(this.node.valueOf("@code")).append("\" ");
		
		for(Node arg : (List<Node>)this.node.selectNodes("arg")){
			buffer.append("ARG { ");
			buffer.append("number:\"").append(arg.valueOf("@number")).append("\", ");
			buffer.append("variableType:\"").append(arg.valueOf("@variableType")).append("\", ");
			buffer.append("value:\"").append(arg.getText().trim().replaceAll("\"", "'")).append("\" ");
			buffer.append(" } ");
		}
		
		buffer.append(" } ");
		return buffer;
	}

	@SuppressWarnings("unchecked")
	public StringBuffer getBracketElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("EXPRESSION { ");
		
		buffer.append("op:\"bracket\", ");
		buffer.append("side:\"").append("\", ");
		buffer.append("variableType:\"").append("\", ");
		buffer.append("ruleReference:\"").append("\", ");
		buffer.append("value:\"").append("\" ");
		
		for(Node statementNode : (List<Node>)this.node.selectNodes("node()")){
			//bypass the comment element during processing
			if(statementNode instanceof Comment){
				continue;
			}
			if(statementNode.getName() == null){
				continue;
			}
			if(statementNode.getName().equals("expression")){
				NodeData pch = new NodeData(statementNode);
				buffer.append(pch.getExpressionElement());
			}
		}
		
		buffer.append(" } ");
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuffer getClassElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("CLASS { ");
		buffer.append("name:\"").append(this.node.valueOf("@name")).append("\", ");
		buffer.append("type:\"").append(this.node.valueOf("@type")).append("\", ");
		buffer.append("pass:\"").append(this.node.valueOf("@pass")).append("\" ");
		
		for(Node childNode : (List<Node>)this.node.selectNodes("node()")){
			//bypass the comment element during processing
			if(childNode instanceof Comment){
				continue;
			}
			if(childNode.getName() == null){
				continue;
			}
			
			if(childNode.getName().equals("class")){
				NodeData childNodeData = new NodeData(childNode);
				buffer.append(childNodeData.getClassElement());
			}

			if(childNode.getName().equals("rating")){
				NodeData childNodeData = new NodeData(childNode);
				buffer.append(childNodeData.getRatingElement());
			}

			if(childNode.getName().equals("err")){
				NodeData childNodeData = new NodeData(childNode);
				buffer.append(childNodeData.getErroElement());
			}

			if(childNode.getName().equals("formAttachment")){
				NodeData childNodeData = new NodeData(childNode);
				buffer.append(childNodeData.getFormAttachmentElement());
			}

			if(childNode.getName().equals("stat")){
				NodeData childNodeData = new NodeData(childNode);
				buffer.append(childNodeData.getStatElement());
			}
		}
		
		buffer.append(" }");
		return buffer;
	}
	
	public StringBuffer getRatingElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("RATING { ");
		buffer.append("className:\"").append(this.node.valueOf("@className")).append("\", ");
		buffer.append("type:\"").append(this.node.valueOf("@type")).append("\", ");
		buffer.append("variables:\"").append(this.node.valueOf("@variables")).append("\" ");
		buffer.append(" }");
		return buffer;
	}

	public StringBuffer getErroElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ERROR { ");
		buffer.append("className:\"").append(this.node.valueOf("@className")).append("\", ");
		buffer.append("type:\"").append(this.node.valueOf("@type")).append("\", ");
		buffer.append("variables:\"").append(this.node.valueOf("@variables")).append("\" ");
		buffer.append(" }");
		return buffer;
	}

	public StringBuffer getFormAttachmentElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("FORMATTACHMENT { ");
		buffer.append("className:\"").append(this.node.valueOf("@className")).append("\", ");
		buffer.append("type:\"").append(this.node.valueOf("@type")).append("\", ");
		buffer.append("variables:\"").append(this.node.valueOf("@variables")).append("\" ");
		buffer.append(" }");
		return buffer;
	}

	public StringBuffer getStatElement(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("STAT { ");
		buffer.append("className:\"").append(this.node.valueOf("@className")).append("\", ");
		buffer.append("type:\"").append(this.node.valueOf("@type")).append("\", ");
		buffer.append("variables:\"").append(this.node.valueOf("@variables")).append("\" ");
		buffer.append(" }");
		return buffer;
	}
	
}
