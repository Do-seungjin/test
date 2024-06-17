package com.gn.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gn.model.vo.Buy;

public class BuyDao {
	// 관리자 조회용 
	public List<Buy> checkBuyList(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Buy> resultList = new ArrayList<Buy>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pwd = "tiger";
			conn = DriverManager.getConnection(url,id,pwd);
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM sm_buy";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Buy b =new Buy(rs.getInt("buy_no")
						,rs.getInt("buy_userNo")
						,rs.getInt("buy_prodNo")
						,rs.getInt("buy_amount")
						);
				resultList.add(b);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
		
		
	}
	
	
	
	// 사용자 구매 시 구매 정보 추가
	public int addList(int no,int prodNum,int buyAmount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 1;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pwd = "tiger";
			conn = DriverManager.getConnection(url,id,pwd);
			
			conn.setAutoCommit(false);
			
			String sql1 = "SELECT COUNT(*) FROM sm_product WHERE product_prodNo = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1,prodNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0;
			}
			
			
			if(result == 0) {
				String sql2 = "INSERT INTO sm_buy (buy_userNo ,buy_prodNo ,buy_amount) VALUES (?,?,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, no);
				pstmt.setInt(2, prodNum);
				pstmt.setInt(3, buyAmount);
				
				result = pstmt.executeUpdate();
				
			}else {
				result = 0;
			}
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
}
