package com.kh.semi.friendShip.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.*;

import com.kh.semi.friendShip.model.dao.FriendShipDao;
import com.kh.semi.friendShip.model.vo.FriendShip;

public class FriendShipService {

	
	public ArrayList<FriendShip> selectList(int userNo) {
		Connection conn = getConnection();

		ArrayList<FriendShip> list = new FriendShipDao().selectList(conn, userNo);

		close(conn);
		
		return list;

	}
	
	
	public int insertFriendShip(FriendShip friendShip) {
		Connection conn = getConnection();
		
		int result = new FriendShipDao().insertFriendShip(conn, friendShip);
		
		if(result>0) commit(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
	public int deleteFriendShip(FriendShip friendShip) {
		Connection conn = getConnection();
		
		int result = new FriendShipDao().deleteFriendShip(conn, friendShip);
		
		if(result>0) commit(conn);
		
		close(conn);
		
		return result;
	}
}
