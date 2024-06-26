package com.kh.semi.travelReview.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.AttachmentFile;
import com.kh.semi.travelReview.model.service.TravelReviewService;
import com.kh.semi.travelReview.model.vo.HashTag;
import com.kh.semi.travelReview.model.vo.TravelReview;

/**
 * Servlet implementation class TravelReviewDetailController
 */
@WebServlet("/detail.review")
public class TravelReviewDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelReviewDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get방식 인코딩 X
		
		// 조회페이지이기 때문에 조회수부터 올리기
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		
		//System.out.println(reviewNo);
		
		
		// 조회수 update할 게시물
		int result = new TravelReviewService().updateReviewCount(reviewNo);
		
		// 상세 조회 시 필요한 것들 1)해당 게시물 2)해당 게시물의 좋아요 수치 3)해당 게시물의 해시태그 4)해당 게시물의 첨부파일 5)다녀온 국가이름 
		
		// 1) + 2) 게시물 + 좋아요 수치 당겨옴
		TravelReview review = new TravelReviewService().selectDetailReview(reviewNo);
		request.setAttribute("review", review);
		
		// 3) 게시물의 해시태그
		List<HashTag> checkedHashTagList = new TravelReviewService().selectReviewHashTagList(reviewNo);
		request.setAttribute("checkedHashTagList", checkedHashTagList);
	
		request.getSession().getAttribute("hashTagList");
		
		// 4) 해당게시물의 첨부파일
		List<AttachmentFile> fileList = new TravelReviewService().selectAttachmentFileList(reviewNo);
		request.setAttribute("fileList", fileList);
		request.getRequestDispatcher("views/travelReview/travelReviewDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
