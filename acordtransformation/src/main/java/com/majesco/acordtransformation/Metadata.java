package com.majesco.acordtransformation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Metadata {
	private UUID id;
	private String name;
	private String type;
	private Map<String, Metadata> children;
	private Map<String, String> attribute;
	private Metadata reference;
	private boolean processed;

	@XmlElement(name = "reference")
	public Metadata getReference() {
		return reference;
	}

	public void setReference(Metadata reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "children")
	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String, Metadata> getChildren() {
		if (children == null) {
			children = new HashMap<>();
		}
		return children;
	}

	public Map<String, String> getAttribute() {
		if (attribute == null) {
			attribute = new HashMap<>();
		}
		return attribute;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

}
