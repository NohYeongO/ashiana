package com.kh.semi.travelReview.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.kh.semi.common.JDBCTemplate.*;
import com.kh.semi.travelReview.model.vo.TravelReview;

public class TravelReviewDao {
	
	private Properties prop = new Properties();
	
	public TravelReviewDao() {
		
		String filePath = TravelReviewDao.class.getResource("/sql/travelReview/travelReview-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<TravelReview> selectReviewList(Connection conn){
		
		List<TravelReview> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				TravelReview review = new TravelReview();
				
				review.setReviewNo(rset.getInt("REVIEW_NO"));
				review.setReviewTitle(rset.getString("REVIEW_TITLE"));
				review.setReviewWriter(rset.getString("NICKNAME"));
				review.setCreateDate(String.valueOf(rset.getDate("CREATE_DATE")));
				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	public List<TravelReview> selectLikeList(Connection conn){
		
		List<TravelReview> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLikeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				TravelReview t = new TravelReview();
				
				t.setReviewNo(rset.getInt("REVIEW_NO"));
				t.setReviewContent(rset.getString("REIVEW_CONTENT"));
				t.setReviewWriter(rset.getString("NICKNAME"));
				t.setCreateDate(String.valueOf(rset.getDate("CREATE_DATE")));
				t.setLikes(rset.getInt("LIKES"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}
