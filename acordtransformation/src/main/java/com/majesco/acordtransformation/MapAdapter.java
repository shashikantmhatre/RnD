package com.majesco.acordtransformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<String, Metadata>> {

	public static class AdaptedMap {
		List<Metadata> entry = new ArrayList<>();

		public List<Metadata> getEntry() {
			return entry;
		}

		public void setEntry(List<Metadata> entry) {
			this.entry = entry;
		}

	}

	public static class Entry {
//		String key;
		Metadata value;

//		public String getKey() {
//			return key;
//		}
//
//		public void setKey(String key) {
//			this.key = key;
//		}

		public Metadata getValue() {
			return value;
		}

		public void setValue(Metadata value) {
			this.value = value;
		}

	}

	@Override
	public AdaptedMap marshal(Map<String, Metadata> map) throws Exception {
		AdaptedMap adaptedMap = new AdaptedMap();
		for (Map.Entry<String, Metadata> mapEntry : map.entrySet()) {
//			Entry entry = new Entry();
//			entry.key = mapEntry.getKey();
//			entry.value = mapEntry.getValue();
//			adaptedMap.entry.add(entry);
			adaptedMap.entry.add(mapEntry.getValue());
		}
		return adaptedMap;
	}

	@Override
	public Map<String, Metadata> unmarshal(AdaptedMap map) throws Exception {
		return null;
	}

}
