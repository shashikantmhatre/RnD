package com.mmpnc.rating.icm.algorithm;

import java.util.ArrayList;
import java.util.List;

public class ProgramPCHHolder {
	private List<String> pchList = new ArrayList<String>();

	public void addPCH(String pchName) {
		pchList.add(pchName);
	}

	public boolean isAvailable(String pchName) {
		return pchList.contains(pchName.trim());
	}
	
	public boolean matchProgramName(String pchName){
		for (String str : pchList){
			if(pchName.matches(str)){
				return true;
			}
		}
		return false;
	}
}
