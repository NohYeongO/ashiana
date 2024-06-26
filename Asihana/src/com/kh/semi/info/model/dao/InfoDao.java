package com.kh.semi.info.model.dao;

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

import com.kh.semi.info.model.vo.Currency;
import com.kh.semi.info.model.vo.Language;
import com.kh.semi.info.model.vo.Visa;
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
			close(rset);
			close(pstmt);
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
			close(rset);
			close(pstmt);
		}
		return list;
	};
	
	public List<Currency> searchCur(Connection conn, int nationNo){
		List<Currency> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchCur");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Currency cur = new Currency();
				cur.setCurrencyName(rset.getString("CURRENCY_NAME"));
				list.add(cur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	};

	public List<Visa> visaList(Connection conn){
		List<Visa> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("visaList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Visa visa = new Visa();
				visa.setVisaNo(rset.getInt("VISA_NO"));
				visa.setVisaName(rset.getString("VISA_NAME"));
				list.add(visa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int updateVisa(Connection conn, int nationNo, int visaNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateVisa");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, visaNo);
			pstmt.setInt(2, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}

	public List<Language> langList(Connection conn){
		List<Language> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("langList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Language e = new Language();
				e.setLanguageNo(rset.getInt("LANGUAGE_NO"));
				e.setLanguageName(rset.getString("LANGUAGE_NAME"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public List<Voltage> volList(Connection conn){
		List<Voltage> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("volList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Voltage e = new Voltage();
				e.setVoltageNo(rset.getInt("VOL_NO"));
				e.setVolName(rset.getString("VOL_NAME"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} 
		return list;
	}
	
	public List<Currency> curList(Connection conn){
		List<Currency> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("curList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Currency e = new Currency();
				e.setCurrencyNo(rset.getInt("CURRENCY_NO"));
				e.setCurrencyName(rset.getString("CURRENCY_NAME"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertLang(Connection conn, String name) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLang");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} 
		return result; 
	}
	
	public int insertCur(Connection conn, String name) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCur");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int nationVisa(Connection conn, int visaNo, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("nationVisa");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, visaNo);
			pstmt.setInt(2, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int nationVol(Connection conn, int voltageNo, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("nationVol");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, voltageNo);
			pstmt.setInt(2, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int nationCur(Connection conn, int currencyNo, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("nationCur");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currencyNo);
			pstmt.setInt(2, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int nationLang(Connection conn, int languageNo, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("nationLang");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, languageNo);
			pstmt.setInt(2, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int countCurrency(Connection conn, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("countCurrency");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
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
	
	public int deleteCurrency(Connection conn, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCurrency");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int countLanguage(Connection conn, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("countLanguage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
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
	
	public int deleteLanguage(Connection conn, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteLanguage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int countVoltage(Connection conn, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("countVoltage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
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
	
	public int deleteVoltage(Connection conn, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteVoltage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteVisa(Connection conn, int nationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteVisa");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

}
