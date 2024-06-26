package com.kh.semi.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InfoServelt
 */
@WebServlet("*.info")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String mapping = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		
		InfoController ic = new InfoController();
		String view = "";
		
		request.setCharacterEncoding("UTF-8");
		
		switch(mapping) {
		case "main" : view = ic.main(request, response); break;
		case "search" : view = ic.search(request, response); break;
		case "story" : view = ic.story(request, response); break;
		case "detailStory" : view = ic.detailStory(request, response); break;
		}
		
		request.getRequestDispatcher(view).forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
