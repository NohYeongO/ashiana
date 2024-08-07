package com.kh.semi.travelReview.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.common.AttachmentFile;
import com.kh.semi.info.model.vo.City;
import com.kh.semi.travelReview.model.dao.TravelReviewDao;
import com.kh.semi.travelReview.model.vo.HashTag;
import com.kh.semi.travelReview.model.vo.TravelReview;

public class TravelReviewService {

	public List<City> selectCityList(){
		
		Connection conn = getConnection();
		
		List<City> cityList = new TravelReviewDao().selectCityList(conn); 
		
		
		return cityList;
	}
	
	
	public List<TravelReview> selectReviewList(){
		
		
		// 커넥션 
		Connection conn = getConnection();
		
		List<TravelReview> list = new TravelReviewDao().selectReviewList(conn);
		

		close(conn);
		
		return list;
	}
	
	public List<TravelReview> selectLikeList(){
		
		Connection conn = getConnection();
		
		List<TravelReview> likeList = new TravelReviewDao().selectLikeList(conn);
		
		close(conn);
		
		return likeList;
	}
	
	
	public int updateReviewCount(int reviewNo) {
		
		Connection conn = getConnection();
		
		int result = new TravelReviewDao().updateReviewCount(conn, reviewNo);
		
		// 트랜잭션 처리
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		return result;
	}
	

	public TravelReview selectDetailReview(int reviewNo) {
		
		Connection conn = getConnection();
		
		TravelReview review = new TravelReviewDao().selectDetailReview(conn, reviewNo);
		
		close(conn);
		
		return review;
	}
	
	/***
	 * 내 여행기
	 * @param userNo
	 * @return
	 */
	public ArrayList<TravelReview> selectMyList(int userNo) {
		Connection conn = getConnection();

		ArrayList<TravelReview> list = new TravelReviewDao().selectMyList(conn, userNo);

		close(conn);
		
		return list;

	}
	/**
	 * 그들의 여행기
	 * @param userNo
	 * @return
	 */
	public ArrayList<TravelReview> selectOthersList(int userNo) {
		Connection conn = getConnection();

		ArrayList<TravelReview> list = new TravelReviewDao().selectOthersList(conn, userNo);

		close(conn);
		
		return list;

	}
	
	public List<HashTag> selectReviewHashTagList(int reviewNo){
		
		Connection conn = getConnection();
		
		List<HashTag> hashTagList = new TravelReviewDao().selectReviewHashTagList(conn, reviewNo);
		
		close(conn);
		
		return hashTagList;
	}
	
	public List<HashTag> selectHashTagList(){
		
		Connection conn = getConnection();
		
		List<HashTag> hashTagList = new TravelReviewDao().selectHashTagList(conn);
		
		close(conn);
		
		return hashTagList;
	}
	
	public List<HashTag> selectCheckedTagList(){
		
		Connection conn = getConnection();
		
		
		List<HashTag> list = new TravelReviewDao().selectCheckedTagList(conn);
		
		close(conn);
		
		return list;
	}
	
	public int intsertReview(TravelReview t, List<HashTag> tagList, List<AttachmentFile> fileList) {
		
		Connection conn = getConnection();
		
		// 여행기 insert 
		int insertReview = new TravelReviewDao().insertReview(conn, t);
		
		// 해시태그 insert
		int insertTagList = 1;
		
		if(!tagList.isEmpty()) {
			insertTagList = new TravelReviewDao().insertTagList(conn, tagList);
		}
		
		// 파일첨부 (최소 1개 존재)
		int insertFileList = new TravelReviewDao().insertFileList(conn, fileList);
		
		//좋아요 테이블 생성
		int insertLikePoint = new TravelReviewDao().insertLikePoint(conn, t);
		
		if(insertReview * insertTagList * insertFileList * insertLikePoint > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		close(conn);
		
		return (insertReview * insertTagList * insertFileList * insertLikePoint);
	}

	public List<City> selectDetailCity(int cityNo) {
		
		Connection conn = getConnection();
		
		List<City> cityInformation = new TravelReviewDao().selectDetailCity(conn, cityNo);
		
		close(conn);
		
		return cityInformation;
	}
	
	public List<AttachmentFile> selectAttachmentFileList(int boardNo){
		
		Connection conn = getConnection();
		
		List<AttachmentFile> fileList = new TravelReviewDao().selectAttachmentFileList(conn, boardNo);
		
		close(conn);
		
		return fileList;
	}
	
	public int selectCityNo(String cityName) {
		
		Connection conn = getConnection();
		
		int cityNo = new TravelReviewDao().selectCityNo(conn, cityName);
		
		close(conn);
		
		return cityNo;
	}
	
}
