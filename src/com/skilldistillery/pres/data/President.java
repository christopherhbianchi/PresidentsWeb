package com.skilldistillery.pres.data;

public class President {
	private int termNum, sYear, eYear;
	private String name, imgURL, fact, party;
	
	public int getTermNum() {
		return termNum;
	}
	public President(String termNum, String name, String sYear, String eYear, String imgURL, String fact, String party) {
		super();
		this.termNum = Integer.parseInt(termNum);
		this.sYear = Integer.parseInt(sYear);
		this.eYear = Integer.parseInt(eYear);
		this.name = name;
		this.imgURL = imgURL;
		this.fact = fact;
		this.party = party;
	}
	
	
	public int getsYear() {
		return sYear;
	}
	public int geteYear() {
		return eYear;
	}
	public String getName() {
		return name;
	}
	public String getImgURL() {
		return imgURL;
	}
	public String getFact() {
		return fact;
	}
	public String getParty() {
		return party;
	}

}
