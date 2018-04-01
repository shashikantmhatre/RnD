package com.mmpnc.simlanguage.execute;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class EvaluatorFactoryBuilder {

	private static EvaluatorFactoryBuilder instance;
	private EvaluatorFactory factoryInstance;
	private Map<String, String> factoryMap;
	
	private static final Logger logger = LoggerFactory.getLogger(EvaluatorFactoryBuilder.class);
	
	private EvaluatorFactoryBuilder() {
		factoryMap = new HashMap<String, String>();
		factoryMap.put("default", "com.mmpnc.simlanguage.execution.DefaultExecutorFactory");
	}
	
	static {
		instance = new EvaluatorFactoryBuilder();
	}
	
	public EvaluatorFactory getEvaluatorFactory(){
		
		try {
			factoryInstance = (EvaluatorFactory) Class.forName(factoryMap.get("default")).newInstance();
		} catch (InstantiationException e) {
			logger.error("InstantiationException : " ,e);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException : " ,e);
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException : " ,e);
		}
		
		return factoryInstance;
	}
	
	public static EvaluatorFactoryBuilder getInstance()
	{
		return instance;
	}
	
	public void registerFactory(String factoryClass){
		factoryMap.put("default", factoryClass);
	}
	
}
