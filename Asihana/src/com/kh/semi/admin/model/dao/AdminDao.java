package com.kh.semi.admin.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.customer.model.vo.Notice;
import com.kh.semi.info.model.dao.InfoDao;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.pageInfo.model.vo.PageInfo;
public class AdminDao {
	
	private Properties prop = new Properties();
	
	public AdminDao() {
		String filePath = InfoDao.class.getResource("/sql/admin/admin-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// notice 전체 고정 해제
	public int holdN(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("holdN");
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 선택한 noticeNo만 고정
	public int changeHolds(Connection conn, int hold) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("changeHolds");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hold);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, Integer.parseInt(notice.getNoticeWriter()));
			pstmt.setString(4, notice.getNoticeHold());
			pstmt.setInt(5, notice.getNoticeNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int storyDel(Connection conn, int storyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("storyDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int countMember(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("countMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) result = rset.getInt("COUNT");		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	public List<Member> memberList(Connection conn, PageInfo pi){
		List<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("memberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			int start = (pi.getCurrentPage() - 1) * pi.getBoardLimit() - 1;
			int end = start + pi.getBoardLimit() - 1;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setUserNo(rset.getInt("MEM_NO"));
				member.setUserId(rset.getString("MEM_ID"));
				member.setNickName(rset.getString("NICKNAME"));
				member.seteDate(rset.getString("ENROLL_DATE"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int memberDelete(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("memberDelete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Member> userDeleteList(Connection conn, PageInfo pi){
		List<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("userDeleteList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			int start = (pi.getCurrentPage() - 1) * pi.getBoardLimit() - 1;
			int end = start + pi.getBoardLimit() - 1;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setUserNo(rset.getInt("MEM_NO"));
				member.setUserId(rset.getString("MEM_ID"));
				member.setNickName(rset.getString("NICKNAME"));
				member.seteDate(rset.getString("ENROLL_DATE"));
				member.setModiDate(rset.getString("MODIFY_DATE"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int memberRollback(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("memberRollback");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
