package com.mmpnc.simlanguage.read;

import com.mmpnc.simlanguage.Activity;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class ReaderFactory {

	public static Activity getLanguageReader (StringBuffer buffer){
		return new LanguageReader(buffer);
	}
	
}
