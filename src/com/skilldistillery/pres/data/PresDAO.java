package com.skilldistillery.pres.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class PresDAO {
	private String fileName="/WEB-INF/presData2.txt";
	private List<President> presList;
	private ServletContext context;
	
	public PresDAO(ServletContext context) {
		super();
		this.context = context;
		presList = new ArrayList<>();
		loadPresidents();
	}
	
	
	private void loadPresidents() {
		InputStream is = context.getResourceAsStream(fileName); //similar to fileReader
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				presList.add(new President(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]));
			}
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}


	public List<President> getPresList() {
		return presList;
	}
	
	
	
}
