package com.kh.semi.customer.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.common.MyFileRenamePolicy;
import com.kh.semi.customer.model.service.CustomerService;
import com.kh.semi.customer.model.vo.Answer;
import com.kh.semi.customer.model.vo.Notice;
import com.kh.semi.customer.model.vo.NoticeFile;
import com.kh.semi.customer.model.vo.QNA;
import com.kh.semi.pageInfo.model.vo.PageInfo;
import com.oreilly.servlet.MultipartRequest;

public class CustomerController {
	
	
	public String notice(HttpServletRequest request, HttpServletResponse response) {
		
		Notice[] notice = new Notice[3];
		notice = new CustomerService().notice();
		request.setAttribute("notice", notice);
		String view = "/views/customer/customer.jsp";
		
		return view;
	}
	
	public String noticeList(HttpServletRequest request, HttpServletResponse response) {
		
		int listCount; // 현재 일반게시판의 게시글 총 개수
		int currentPage; // 현재 페이지(사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수
		
		int maxPage; // 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
		
		String select = request.getParameter("select");
		String searchContent = request.getParameter("searchContent");

		listCount = new CustomerService().selectCount(select, searchContent);

		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		pageLimit = 10;
		boardLimit = 15;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage -1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		
		List<Notice> noticeList = new ArrayList<Notice>();
		
		if(select != null) {
			noticeList = new CustomerService().noticeSearch(select, searchContent, pi);
		} else {
			noticeList = new CustomerService().noticeList(pi);
		}
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pi", pi);
		request.setAttribute("searchContent", searchContent);
		request.setAttribute("select", select);
		
		
		String view = "/views/customer/notice.jsp";
		
		return view;
		
	}
	
	public String noticeDetail(HttpServletRequest request, HttpServletResponse response) {
	
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String currentPage = request.getParameter("currentPage");
		
		int boardType = 4; 
		
		Notice noticeDetail = new CustomerService().noticeDetail(noticeNo);
		NoticeFile noticeFile = new CustomerService().selectFile(noticeNo, boardType);
		
		
		request.setAttribute("noticeDetail", noticeDetail);
		request.setAttribute("noticeFile", noticeFile);
		request.setAttribute("currentPage", currentPage);
		String view = "";
		
		if(noticeDetail != null) {
			view = "/views/customer/noticeDetail.jsp";
		} else {
			view = "/views/customer/nonePage.jsp";
		}
		
		return view;
	}
	
	public String noticeInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String view = "";

		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024* 1024* 10;
			String savePath = request.getServletContext().getRealPath("/resources/notice");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			String noticeTitle = multiRequest.getParameter("title");
			String noticeContent = multiRequest.getParameter("content");
			String noticeWriter = multiRequest.getParameter("userNo");
			String noticeHold = "";
			if (multiRequest.getParameter("hold") == null) noticeHold = "N"; 
			else if(multiRequest.getParameter("hold").equals("Y")) noticeHold = "Y";
			
			Notice notice = new Notice();
			notice.setNoticeTitle(noticeTitle);
			notice.setNoticeContent(noticeContent);
			notice.setNoticeWriter(noticeWriter);
			notice.setNoticeHold(noticeHold);
			
			String key = "noticeFile";
			NoticeFile noticeFile = null;
			
			if(multiRequest.getOriginalFileName(key)!=null) {
				noticeFile = new NoticeFile();
				noticeFile.setOriginName(multiRequest.getOriginalFileName(key));
				noticeFile.setChangeName(multiRequest.getFilesystemName(key));
				noticeFile.setFilePath("resources/notice");
			}
				
			if(new CustomerService().noticeInsert(notice, noticeFile) > 0) {
				view = "/notice.admin?currentPage=1";
			} else {
				view = "";
			}
		}
		return view;
	}
	
	public String qaList(HttpServletRequest request, HttpServletResponse response) {
		
		int listCount; // 현재 일반게시판의 게시글 총 개수 
		int currentPage; // 현재 페이지(사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 최대 개수 
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수
		
		int maxPage; // 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
		
		String select = request.getParameter("select");
		String searchContent = request.getParameter("searchContent");
		
		listCount = new CustomerService().selectQnaCount(select, searchContent);

		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		pageLimit = 10;
		boardLimit = 10;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage -1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		// endPage를 maxPage값으로 변경
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		List<QNA> qnaList = new ArrayList<QNA>();
		
		if(select != null) {
			qnaList = new CustomerService().qnaSearch(select, searchContent, pi);
		} else {
			qnaList = new CustomerService().qnaList(pi);
		}
		
		request.setAttribute("pi", pi);
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("select", select);
		request.setAttribute("searchContent", searchContent);
		
		String view = "/views/customer/qa.jsp";
		
		return view;
		
	}
	
	public String insertQa(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String view = "";
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			
			ServletContext application = request.getSession().getServletContext();
			String savePath = application.getRealPath("/resources/qa_files/");// 파일 경로
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String userNo = multiRequest.getParameter("userNo");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			content = content.replaceAll("\\n", "<br>");
			
			QNA qna = new QNA();
			qna.setQnaWriter(userNo);
			qna.setQnaTitle(title);
			qna.setQnaContent(content);
			
			
			NoticeFile file = null;
			
			if(multiRequest.getOriginalFileName("qnaFile") != null) {
				
				file = new NoticeFile();
				file.setOriginName(multiRequest.getOriginalFileName("qnaFile"));
				file.setChangeName(multiRequest.getFilesystemName("qnaFile"));
				file.setFilePath("resources/qa_files");
				
			}
			
			int result = new CustomerService().insertQa(qna, file);
			
			if(result > 0) {
				
				request.getSession().setAttribute("alertMsg", "저장 성공");;
				view = "/qa.customer?currentPage=1";
				
			}else {
				if(file != null) {
					new File(savePath + file.getChangeName()).delete();
				}
				request.setAttribute("errorMsg", "게시글 작성 실패");
				view = "views/common/errorPage.jsp";
			}
			
		}
		
		return view;
	}
	
	
	public String qnaDetail(HttpServletRequest request, HttpServletResponse response) {
		
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		int boardType = 8;
		String currentPage = request.getParameter("currentPage");
		
		NoticeFile qnaFile = new CustomerService().selectFile(qnaNo, boardType);
		
		List<Answer> answer = new CustomerService().selectAnswer(qnaNo);
		
		QNA qna = new CustomerService().selectQna(qnaNo); 
		
		
		request.setAttribute("qnaFile", qnaFile);
		request.setAttribute("answer", answer);
		request.setAttribute("qna", qna);
		request.setAttribute("currentPage", currentPage);
		
		String view = "";
		if(qna != null) {
			view = "views/customer/qnaDetail.jsp";
		} else {
			view = "views/customer/nonePage.jsp";
		}
		
		return view;
		
	}
	
	
	public String qnaDelete(HttpServletRequest request, HttpServletResponse response) {
		
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		int qnaFileNo = Integer.parseInt(request.getParameter("qnaFileNo"));
		int boardType = 8;
		
		NoticeFile file = new CustomerService().selectFile(qnaNo, boardType);
		
		int result = new CustomerService().qnaDelete(qnaNo, qnaFileNo);
		
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/resources/qa_files/");
		
		String view = "";
		
		if(result > 0) {
			if(file != null) {
				new File(savePath + file.getChangeName()).delete();
				request.getSession().setAttribute("alertMsg", "삭제 성공");
			}
			view = "qa.customer?currentPage=1";
			
		} else {
			
			request.setAttribute("errorMsg", "삭제 실패");
			view = "views/common/errorPage.jsp";
			
		}
		return view;
	}
	
	public String updateQaView(HttpServletRequest request, HttpServletResponse response) {
		
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		int boardType = 8;
		String currentPage = request.getParameter("currentPage");
		
		NoticeFile file = new CustomerService().selectFile(qnaNo, boardType);
		
		QNA qna = new CustomerService().selectQna(qnaNo);
		
		qna.setQnaContent(qna.getQnaContent().replaceAll("<br>", ""));
		
		request.setAttribute("file", file);
		request.setAttribute("qna", qna);
		request.setAttribute("currentPage", currentPage);
		
		String view = "views/customer/updateQa.jsp";
		
		return view;
		
	}
	
	public String updateQa(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String view = "";
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			
			ServletContext application = request.getSession().getServletContext();
			String savePath = application.getRealPath("/resources/qa_files/");// 파일 경로
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			int qnaNo = Integer.parseInt(multiRequest.getParameter("qnaNo"));
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String currentPage = multiRequest.getParameter("currentPage");
			
			content = content.replaceAll(" ", "&nbsp;");
			content = content.replaceAll("\\n", "<br>");
			
			QNA qna = new QNA();
			qna.setQnaNo(qnaNo);
			qna.setQnaTitle(title);
			qna.setQnaContent(content);
			
			HashMap<String, NoticeFile> files = new HashMap<String, NoticeFile>();
			
			if(multiRequest.getOriginalFileName("qnaFile") != null) {
				
				NoticeFile originFile = new CustomerService().selectFile(qnaNo, 8);
				
				NoticeFile file = new NoticeFile();
				file.setOriginName(multiRequest.getOriginalFileName("qnaFile"));
				file.setChangeName(multiRequest.getFilesystemName("qnaFile"));
				file.setBoardNo(qnaNo);
				file.setFilePath("resources/qa_files");
				
				files.put("file", file);
				files.put("originFile", originFile);
			}
			
			int updateResult = new CustomerService().updateQna(qna, files.get("file"), files.get("originFile"));
			
			if(updateResult > 0) {
				if(files.get("originFile") != null) {
					new File(savePath + files.get("originFile").getChangeName()).delete();
				}
				request.getSession().setAttribute("alertMsg", "수정 성공");;
				view = "/qnaDetail.customer?qnaNo="+ qnaNo +"&currentPage=" + currentPage;
			} else {
				request.setAttribute("errorMsg", "수정 실패");
				view = "views/common/errorPage.jsp";
			}
		}
		return view;
	}
	
	public String allSerach(HttpServletRequest request, HttpServletResponse response) {
		
		String searchText = request.getParameter("query");
		
		request.setAttribute("query", searchText);
		
		String view = "views/common/searchPage.jsp";
		
		return view;
	}
	
	
	
}
