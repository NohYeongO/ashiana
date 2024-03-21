package com.kh.semi.community.model.vo;

import java.sql.Date;



public class Community {
	
	private  int comuNo;
	private int cityNo;
	private int memNo;
	private String comuContent;
	private Date comuDate;
	private char status;
	
	
	public Community() {
		super();
	}


	public Community(int comuNo, int cityNo, int memNo, String comuContent, Date comuDate, char status) {
		super();
		this.comuNo = comuNo;
		this.cityNo = cityNo;
		this.memNo = memNo;
		this.comuContent = comuContent;
		this.comuDate = comuDate;
		this.status = status;
	}


	public int getComuNo() {
		return comuNo;
	}


	public void setComuNo(int comuNo) {
		this.comuNo = comuNo;
	}


	public int getCityNo() {
		return cityNo;
	}


	public void setCityNo(int cityNo) {
		this.cityNo = cityNo;
	}


	public int getMemNo() {
		return memNo;
	}


	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}


	public String getComuContent() {
		return comuContent;
	}


	public void setComuContent(String comuContent) {
		this.comuContent = comuContent;
	}


	public Date getComuDate() {
		return comuDate;
	}


	public void setComuDate(Date comuDate) {
		this.comuDate = comuDate;
	}


	public char getStatus() {
		return status;
	}


	public void setStatus(char status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cityNo;
		result = prime * result + ((comuContent == null) ? 0 : comuContent.hashCode());
		result = prime * result + ((comuDate == null) ? 0 : comuDate.hashCode());
		result = prime * result + comuNo;
		result = prime * result + memNo;
		result = prime * result + status;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Community other = (Community) obj;
		if (cityNo != other.cityNo)
			return false;
		if (comuContent == null) {
			if (other.comuContent != null)
				return false;
		} else if (!comuContent.equals(other.comuContent))
			return false;
		if (comuDate == null) {
			if (other.comuDate != null)
				return false;
		} else if (!comuDate.equals(other.comuDate))
			return false;
		if (comuNo != other.comuNo)
			return false;
		if (memNo != other.memNo)
			return false;
		if (status != other.status)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Community [comuNo=" + comuNo + ", cityNo=" + cityNo + ", memNo=" + memNo + ", comuContent="
				+ comuContent + ", comuDate=" + comuDate + ", status=" + status + "]";
	}
	
	
	
	
	
		


}
