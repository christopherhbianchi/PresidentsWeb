package com.skilldistillery.pres.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skilldistillery.pres.data.PresDAO;
import com.skilldistillery.pres.data.President;

/**
 * Servlet implementation class PresServlet
 */
public class PresServlet extends HttpServlet {
   
	private PresDAO dao;
	private List<President> presList;
	private int presIndex;
	
	public void init() throws ServletException{
		dao = new PresDAO(getServletContext());
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		presIndex = 0;
		String typeSearch = request.getParameter("presSubmit"); //looks for anything with name "presSubmit" and take the value of it
		
		switch(typeSearch) {
		
		case "allPresidents":
			presList = new ArrayList<>(dao.getPresList());
			break;
		case "filter":
			presList = new ArrayList<>(listFilter(request.getParameter("filterVal")));
			System.out.println(presList.get(0).getName());
			break;
		case "go to":
			presList = new ArrayList<>(dao.getPresList());
			presIndex = Integer.parseInt(request.getParameter("term"))-1;
			break;
			
		}
		
		request.setAttribute("pres", presList.get(presIndex));
		request.getRequestDispatcher("/WEB-INF/president.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextOrPrevious = request.getParameter("cycle");
		switch(nextOrPrevious) {
		case "Next President":
			if(presIndex== presList.size()-1) {
				presIndex = 0;
			}
			else {
				presIndex++;
			}
			break;
		
		case "Previous President":
			if(presIndex==0) {
				presIndex = presList.size() - 1;
			}
			else {
				presIndex--;
			}
			break;	
		}
		
		request.setAttribute("pres", presList.get(presIndex));
		request.getRequestDispatcher("/WEB-INF/president.jsp").forward(request, response);
	}
	
	private List<President> listFilter(String filter){
		List<President> filtered = new ArrayList<>();
		for(President president:dao.getPresList()) {
			if(president.getParty().equals(filter)) {
				filtered.add(president);
			}
		}
		return filtered;
	}
	
	
	
	
	

}
