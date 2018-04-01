package com.mmpnc.rating.icm.algorithm;

import java.util.ArrayList;
import java.util.List;

public class IgnorePCHHolder {
	private List <String> pchList = new ArrayList<String>();
	
	public void addPCH(String pchName){
		pchList.add(pchName);
	}
	
	public boolean isAvailable(String pchName){
		return pchList.contains(pchName.trim());
	}
	
	
}
