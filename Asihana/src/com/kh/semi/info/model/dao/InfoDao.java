package com.kh.semi.info.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.common.JDBCTemplate;
import com.kh.semi.info.model.vo.City;
import com.kh.semi.info.model.vo.Language;
import com.kh.semi.info.model.vo.Nation;
import com.kh.semi.info.model.vo.Voltage;

public class InfoDao {
	
	private Properties prop = new Properties();
	
	public InfoDao() {
		String filePath = InfoDao.class.getResource("/sql/info/info-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<City> cityList(Connection conn){
		ArrayList<City> list = new ArrayList<City>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("cityList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				City c = new City();
				c.setCityNo(rset.getInt("CITY_NO"));
				c.setCityName(rset.getString("CITY_NAME"));
				c.setNationNo(rset.getInt("NATION_NO"));
				c.setNationName(rset.getString("NATION_NAME"));
				c.setCount(rset.getInt("COUNT"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}		
		return list;
	}
	
	public Nation searchNation(Connection conn, int nationNo) {
		Nation nation = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchNation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				nation = new Nation();
				nation.setNationName(rset.getString("NATION_NAME"));
				nation.setNationContent(rset.getString("NATION_CONTENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return nation;
	}
	
	public ArrayList<City> nationCity(Connection conn, int nationNo){
		ArrayList<City> cityList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("nationCity");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				City city = new City();
				city.setCityNo(rset.getInt("CITY_NO"));
				city.setCityName(rset.getString("CITY_NAME"));
				city.setCityContent(rset.getString("CITY_CONTENT"));
				
				cityList.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return cityList;
	}
	
	public int increaseCity(Connection conn, City c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCity");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getNationNo());
			pstmt.setString(2, c.getCityName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public City searchCity(Connection conn, City c) {
		City city = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchCity");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCityName());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				city = new City();
				city.setCityNo(rset.getInt("CITY_NO"));
				city.setCityName(rset.getString("CITY_NAME"));
				city.setCityContent(rset.getString("CITY_CONTENT"));
				city.setNationName(rset.getString("NATION_NAME"));
				city.setFlyingTime(rset.getString("FLYING_TIME"));
				city.setVisaName(rset.getString("VISA_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return city;
	}
	
	public List<Language> searchLang(Connection conn, int nationNo){
		List<Language> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchLang");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Language lang = new Language();
				lang.setLanguageName(rset.getString("LANGUAGE_NAME"));
				list.add(lang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	};
	
	public List<Voltage> searchVol(Connection conn, int nationNo){
		List<Voltage> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchVol");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Voltage vol = new Voltage();
				vol.setVolName(rset.getString("VOL_NAME"));
				list.add(vol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	};

}
