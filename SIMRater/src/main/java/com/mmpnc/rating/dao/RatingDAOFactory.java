package com.mmpnc.rating.dao;

import java.util.Map;

import com.mmpnc.rating.algorithm.dao.impl.hibernate.ProcessDAO;
import com.mmpnc.rating.algorithm.dao.impl.hibernate.ProgramDAO;
import com.mmpnc.rating.algorithm.dao.impl.hibernate.RatingEntityDAO;
import com.mmpnc.rating.algorithm.dao.impl.hibernate.StepDAO;
import com.mmpnc.rating.algorithm.dao.impl.xml.ProcessXMLDAO;
import com.mmpnc.rating.algorithm.dao.impl.xml.ProgramXMLDAO;
import com.mmpnc.rating.algorithm.dao.impl.xml.RatingEntityXMLDAO;
import com.mmpnc.rating.algorithm.dao.impl.xml.StepXMLDAO;
import com.mmpnc.rating.dao.impl.hibernate.ProductDAO;
import com.mmpnc.rating.dao.impl.xml.ProductXMLDAO;
import com.mmpnc.rating.data.dao.impl.hibernate.DomainTableDAO;
import com.mmpnc.rating.data.dao.impl.hibernate.DomainTableEntryDAO;
import com.mmpnc.rating.data.dao.impl.hibernate.RateTableDAO;
import com.mmpnc.rating.data.dao.impl.hibernate.RateTableEntryDAO;
import com.mmpnc.rating.data.dao.impl.xml.DomainTableEntryXMLDAO;
import com.mmpnc.rating.data.dao.impl.xml.DomainTableXMLDAO;
import com.mmpnc.rating.data.dao.impl.xml.RateTableEntryXMLDAO;
import com.mmpnc.rating.data.dao.impl.xml.RateTableXMLDAO;
import com.mmpnc.rating.vo.DomainTable;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.Program;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.Process;
import com.mmpnc.rating.vo.RatingEntity;
import com.mmpnc.rating.vo.Step;


/**
 * Author : Shashikant Mhatre
 *
 * Created On : Dec 23, 2014
 *
 * Description :
 *
 * Note :
 */

public class RatingDAOFactory {
	private static RatingDAOFactory instance;
	private ContentOption option;
	
	private RatingDAOFactory(ContentOption option){
		this.option = option;
	}
	
	public static RatingDAOFactory getRatingDAOFactory(ContentOption option){
		if(instance == null){
			instance = new RatingDAOFactory(option);
		}				
		return instance;
	}
	
	public static RatingDAOFactory getRatingDAOFactory(){
		if(instance == null){
			instance = new RatingDAOFactory(ContentOption.SQL);
		}				
		return instance;
	}
	
	public BaseDAO getProductDAO(Product product){
		if(this.option == ContentOption.XML){
			return new ProductXMLDAO(product);
		} else {
			return new ProductDAO(product);
		}		
	}
	
	public BaseDAO getRateTableDAO(RateTable ratetable){
		if(this.option == ContentOption.XML){
			return new RateTableXMLDAO(ratetable);
		} else {
			return new RateTableDAO(ratetable);
		}		
	}
	
	public BaseDAO getRateTableEntryDAO(RateTable ratetable, Object[] values){
		if(this.option == ContentOption.XML){
			return new RateTableEntryXMLDAO(ratetable,values);
		} else {
			return new RateTableEntryDAO(ratetable,values);
		}		
	}
	
	public BaseDAO getDomainTableDAO(DomainTable domaintable){
		if(this.option == ContentOption.XML){
			return new DomainTableXMLDAO(domaintable);
		} else {
			return new DomainTableDAO(domaintable);
		}		
	}

	public BaseDAO getDomainTableEntryDAO(DomainTable domaintable, Map<String, Object> values){
		if(this.option == ContentOption.XML){
			return new DomainTableEntryXMLDAO(domaintable,values);
		} else {
			return new DomainTableEntryDAO(domaintable,values);
		}		
	}
	
	public BaseDAO getProcessDAO(Process process){
		if(this.option == ContentOption.XML){
			return new ProcessXMLDAO(process);
		} else {
			return new ProcessDAO(process);
		}		
	}
	
	public BaseDAO getProgramDAO(Program program){
		if(this.option == ContentOption.XML){
			return new ProgramXMLDAO(program);
		} else {
			return new ProgramDAO(program);
		}		
	}
	
	public BaseDAO getRatingEntityDAO(RatingEntity ratingEntity){
		if(this.option == ContentOption.XML){
			return new RatingEntityXMLDAO(ratingEntity);
		} else {
			return new RatingEntityDAO(ratingEntity);
		}		
	}
	
	public BaseDAO getStepDAO(Step step){
		if(this.option == ContentOption.XML){
			return new StepXMLDAO(step);
		} else {
			return new StepDAO(step);
		}		
	}
	
	public enum ContentOption {
		XML, SQL;
	}
}
