package com.mmpnc.rating.iso.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.mmpnc.rating.icm.content.util.AlgorithmUtil;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class AlgorithmRealign {

	private Document document;

	public AlgorithmRealign(Document document) {
		this.setDocument(document);
	}

	@SuppressWarnings("unchecked")
	public Document realign() {

		Node rootNode = this.document.getRootElement();
		AlgorithmDataStructure ds = new AlgorithmDataStructure();

		Document dsDocument = AlgorithmUtil.generateDocument(ds
				.simplifyDataStrucure(document));

		List<Node> nodes = (List<Node>) rootNode
				.selectNodes("reference[@type='Common Rating' or @type='Premium Calculation']/scope");

		for (Node scopeNode : nodes) {
			for (Node pchNode : (List<Node>) scopeNode.selectNodes("PCH")) {
				// System.out.println("we are in " + pchNode.valueOf("@name"));
				String name = pchNode.valueOf("@name");

				if (name != null
						&& name.contains("/")) {
					String basePath = "";
					String nodePath = "";

					if (name.replaceAll("(\\[).[^\\]]*(\\])", "").contains("/")) {
						basePath = name.substring(0, name.lastIndexOf("/"));
						nodePath = name.substring(name.lastIndexOf("/") + 1);
					}

					Node newNode = (Node) pchNode.clone();

					Element newElement = (Element) newNode;
					Attribute att = newElement.attribute("name");
					att.setText(nodePath);

					for (Node eNode : (List<Node>) newElement
							.selectNodes("descendant::expression")) {
						eNode.setText(eNode.getText().replaceAll(
								"^" + basePath + "/", ""));
					}

					for (Element eNode : (List<Element>) newElement
							.selectNodes("descendant::assign")) {
						Attribute lValue = eNode.attribute("lValue");
						lValue.setText(lValue.getText().replaceAll(
								"^" + basePath + "/", ""));
					}

					for (Element eNode : (List<Element>) newElement
							.selectNodes("descendant::loop")) {
						Attribute lValue = eNode.attribute("through");
						lValue.setText(lValue.getText().replaceAll(
								"^" + basePath + "/", ""));
					}

					List<Element> scopeRefNodes = getScopeNode(
							(Element) rootNode, (Element) scopeNode, basePath,
							dsDocument);

					for (Node sNode : scopeRefNodes) {
						((Element) sNode).add((Element) newNode.clone());
					}

					// remove from the current node
					pchNode.detach();

				}

			}
		}

		return this.document;
	}

	@SuppressWarnings("unchecked")
	private List<Element> getScopeNode(Element rootNode, Element scopeElement,
			String refName, Document dsDocument) {

		List<Element> scopes = new ArrayList<Element>();

		Node dsRootNode = dsDocument.getRootElement();

		String xmlQuery = "";

		if (refName.equals(scopeElement.valueOf("@dbTables"))) {
			xmlQuery = "(//" + scopeElement.valueOf("@dbTables") + "/@xpath)";
		} else {
			xmlQuery = "(//" + scopeElement.valueOf("@dbTables") + "/"
					+ refName + "/@xpath)";
		}

		List<Node> dsNodes = dsRootNode.selectNodes(xmlQuery);
		Map<String, String> refNames = new HashMap<String, String>();

		if (dsNodes != null && dsNodes.size() > 0) {

//			if (dsNodes.size() > 1) {
//				System.out.println("check " + scopeElement.valueOf("@dbTables")
//						+ " for " + refName);
//			}

			for (Node dsNode : dsNodes) {
				refName = dsNode.getText().substring(
						dsNode.getText().lastIndexOf("/") + 1);
				refNames.put(dsNode.getText(), refName);
			}
		} else {
			refNames.put(refName, refName);
//			System.out.println(" in else for " + refName);
		}

		for (String dbTablesName : refNames.keySet()) {

			String value = refNames.get(dbTablesName);

			Element refElement = scopeElement.getParent();

			Element ref = (Element) rootNode
					.selectSingleNode("reference[@dbTables=\"" + value
							+ "\" and @type=\"" + refElement.valueOf("@type")
							+ "\"]");

			if (ref == null) {

				ref = rootNode.addElement("reference");
				for (Attribute att : (List<Attribute>) refElement.attributes()) {
					ref.add((Attribute) att.clone());
				}

				ref.attribute("name").setText(value + ".Premium");
				ref.attribute("dbTables").setText(value);

				ref.addAttribute("recreated", "Yes");

				Element scope = ref.addElement("scope");

				for (Attribute att : (List<Attribute>) scopeElement
						.attributes()) {
					scope.add((Attribute) att.clone());
				}

				scope.attribute("dbTables").setText(value);

			}

			Element scope = (Element) ref.selectSingleNode("scope[@dbTables=\""
					+ value + "\"]");

			scopes.add(scope);
		}

		return scopes;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}
