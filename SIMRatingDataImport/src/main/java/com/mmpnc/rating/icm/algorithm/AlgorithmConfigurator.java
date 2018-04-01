package com.mmpnc.rating.icm.algorithm;

import java.io.IOException;
import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mmpnc.rating.icm.algorithm.configuration.Configuration;


public class AlgorithmConfigurator {
	private IgnorePCHHolder ignorePCH;
	private ProgramPCHHolder programPCH;
	private Reader reader;
	private Configuration config;
	
	private static AlgorithmConfigurator configurator;
	
	
	public static AlgorithmConfigurator getInstance(Reader reader) throws IOException, JAXBException{
		if(configurator == null){
			configurator = new AlgorithmConfigurator(reader);
		}
		return configurator;
	}
	
	private AlgorithmConfigurator(Reader reader) throws IOException, JAXBException {
		this.reader = reader;
		this.config = new Configuration();
		loadConfiguration();
	}

	private void loadConfiguration() throws IOException, JAXBException {
		JAXBContext jbcontext = JAXBContext.newInstance(Configuration.class);
		Unmarshaller um = jbcontext.createUnmarshaller();		
		this.config = (Configuration) um.unmarshal(this.reader);
		this.ignorePCH = loadIgnorePCHList();
		this.programPCH = loadProgramPCHList();
	}

	public IgnorePCHHolder getIgnorePCH() {
		if (this.ignorePCH != null) {
			this.ignorePCH = loadIgnorePCHList();
		}
		return ignorePCH;
	}

//	public void setIgnorePCH(IgnorePCHHolder ignorePCH) {
//		this.ignorePCH = ignorePCH;
//	}

	public ProgramPCHHolder getProgramPCH() {
		if (this.programPCH != null) {
			this.programPCH = loadProgramPCHList();
		}
		return programPCH;
	}

//	public void setProgramPCH(ProgramPCHHolder programPCH) {
//		this.programPCH = programPCH;
//	}

	private IgnorePCHHolder loadIgnorePCHList() {
		IgnorePCHHolder pchHolder = new IgnorePCHHolder();

		for (String prg : this.config.getIgnorePCH().getProgram()) {
			pchHolder.addPCH(prg);
		}

		return pchHolder;
	}

	private ProgramPCHHolder loadProgramPCHList() {
		ProgramPCHHolder pchHolder = new ProgramPCHHolder();

		for (String prg : this.config.getProgramPCH().getProgram()) {
			pchHolder.addPCH(prg);
		}

		return pchHolder;
	}
}
