package com.kh.semi.info.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.AttachmentFile;
import com.kh.semi.info.model.service.CityService;
import com.kh.semi.info.model.service.InfoService;
import com.kh.semi.info.model.service.NationService;
import com.kh.semi.info.model.service.StoryService;
import com.kh.semi.info.model.vo.City;
import com.kh.semi.info.model.vo.CityFile;
import com.kh.semi.info.model.vo.Nation;
import com.kh.semi.info.model.vo.Story;
import com.kh.semi.info.model.vo.StoryFile;
import com.kh.semi.pageInfo.model.vo.PageInfo;

public class InfoController {

	
	public String main(HttpServletRequest request, HttpServletResponse response) {
		List<Nation> nationList = new NationService().allNationList();
		List<CityFile> files = new CityService().fileList();
		request.setAttribute("nationList", nationList);
		request.setAttribute("files", files);

		return "views/info/selectCity.jsp";
	};

	public String search(HttpServletRequest request, HttpServletResponse response) {
		int nationNo = Integer.parseInt(request.getParameter("nation"));
		int cityNo = 0;
		String cityName = request.getParameter("city");
		if(!cityName.equals("도시선택")) {
			cityNo = Integer.parseInt(cityName);
		}		

		String view = "";


		// 나라만 선택하는 경우
		if(cityName.equals("도시선택")) {
			AttachmentFile title = new NationService().selectTitlePhoto(nationNo);
			List<CityFile> cityList = new CityService().nationCity(nationNo);
			
			request.setAttribute("cityList", cityList);
			request.setAttribute("title", title);
			view = "views/info/nationInfo.jsp";
		// 도시까지 선택하는 경우
		} else {
			// City 조회수 1증가
			int result = new CityService().increaseCity(cityNo);
			
			if(result > 0) {
				// 도시정보 조회
				City city = new CityService().selectCity(cityNo);
				AttachmentFile file = new CityService().selectPhoto(cityNo);
				
				// 언어, 전압, 화폐
				request.setAttribute("language", new InfoService().nationLang(nationNo));
				request.setAttribute("voltage", new InfoService().nationVol(nationNo));
				request.setAttribute("currency", new InfoService().nationCur(nationNo));

				// 도시 내 즐길거리 조회
				//List<Attraction> attraction = new InfoService().searchAttraction(c);
				request.setAttribute("City", city);
				request.setAttribute("file", file);
				view = "views/info/cityInfo.jsp";
			} else {
				view = "views/common/errorPage.jsp";
			}
		}
		
		// select시 필요한 국가목록
		List<Nation> nationList = new NationService().allNationList();
		request.setAttribute("nationList", nationList);
		// 국가정보 조회 : 국가번호, 국가이름, 국가소개, 비자
		Nation nation = new NationService().searchNation(nationNo);
		request.setAttribute("nation", nation);
		
		return view;
	}
	
	/***
	 * Story list
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String story(HttpServletRequest request, HttpServletResponse response) {
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int listCount = new StoryService().countStory();
		int pageLimit = 10;
		int boardLimit = 5;
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = ((currentPage - 1) / pageLimit ) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(maxPage < endPage) endPage = maxPage;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		List<StoryFile> storyList = new StoryService().storyList(pi);
		
		String view = "";
		
		request.setAttribute("pageInfo", pi);
		request.setAttribute("list", storyList);

		
		return "views/info/storyMain.jsp";
	}
	
	public String detailStory(HttpServletRequest request, HttpServletResponse response) {
		int storyNo = Integer.parseInt(request.getParameter("storyNo"));
		StoryFile story = new StoryService().detailStory(storyNo);
		
		request.setAttribute("pageNo", Integer.parseInt(request.getParameter("pageNo")));
		request.setAttribute("story", story);
		
		return "views/info/storyDetail.jsp";
	}
	
}
