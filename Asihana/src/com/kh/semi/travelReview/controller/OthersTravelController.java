package com.kh.semi.travelReview.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.model.service.MemberService;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.travelReview.model.service.TravelReviewService;
import com.kh.semi.travelReview.model.vo.TravelReview;

/**
 * Servlet implementation class OthersTravelController
 */
@WebServlet("/othersTravel")
public class OthersTravelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OthersTravelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//그사람 userNo
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		ArrayList<TravelReview> othersTravelList = new TravelReviewService().selectOthersList(userNo);
		
		Member member =new MemberService().selectOtMember(userNo);
		
		
		request.setAttribute("othersTravelList", othersTravelList);
		request.setAttribute("member", member);
		
		
		String dispatcherPath = "";
		dispatcherPath = "views/member/OthersTravel.jsp";
		
		request.getRequestDispatcher(dispatcherPath).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
