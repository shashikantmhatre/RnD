package com.majesco.acordtransformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class ParserContext {
	private List<Metadata> metadataList;
	
	private Map<String, Metadata> metadataMap;

	public List<Metadata> getMetadataList() {
		if (metadataList == null) {
			metadataList = new ArrayList<>();
		}
		return metadataList;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String, Metadata> getMetadataMap() {
		if(metadataMap==null) {
			metadataMap = new HashMap<>();
		}
		return metadataMap;
	}
}
