package com.gn.model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gn.model.vo.User;

public class UserDao {
	// 회원 가입
	public int insertMember(User u) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pwd = "tiger";
			conn = DriverManager.getConnection(url,id,pwd);
			
			
			
			String sql1 = "SELECT COUNT(*) FROM sm_user WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, u.getUser_id());
			
			rs=pstmt.executeQuery();
			// 중복되는 아이디가 있는 경우 -1
			// Insert 과정중 오류가 발생한 경우 0
			// 정상 Insert 1
			if(rs.next()) {
				result = rs.getInt(1);
			}
			if(result == 0) {
				String sql2 = "INSERT INTO sm_user (user_id,user_pwd,user_nick) VALUES (?,?,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, u.getUser_id());
				pstmt.setString(2, u.getUser_pwd());
				pstmt.setString(3, u.getUser_nick());
				
				result = pstmt.executeUpdate();
			} else {
				result = 0;
			}
	
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				 e.printStackTrace();
			}		
		}
		return result;		
	}
	// 로그인
	public User checkMember(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String sqlid = "scott";
			String sqlpwd = "tiger";
			conn = DriverManager.getConnection(url,sqlid,sqlpwd);
			
			String sql1 = "SELECT * FROM sm_user WHERE user_id = ? AND user_pwd = ?";
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			pstmt.setString(2,pwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				u=new User(rs.getInt("user_no")
						,rs.getString("user_id")
						,rs.getString("user_pwd")
						,rs.getString("user_nick")
						,rs.getTimestamp("user_reg_date").toLocalDateTime()
						,rs.getTimestamp("user_update_date").toLocalDateTime());
			}else {
				u=null;
			}	
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return u;
	}
	
	// ============
	
	
	public int checkUserInfo(String id,String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url="jdbc:mariadb://127.0.0.1:3306/super_market";
			String sqlId="scott";
			String sqlpwd = "tiger";
			conn=DriverManager.getConnection(url,sqlId,sqlpwd);
			
			String sql1 ="SELECT COUNT(*) FROM sm_user WHERE user_id=? AND user_pwd=?";
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	//=================================================
	public int changeUserInfo(String id,String newNick) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String sqlid="scott";
			String sqlpwd="tiger";
			
			conn=DriverManager.getConnection(url,sqlid,sqlpwd);
			
			String sql1 = "UPDATE sm_user SET user_nick = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, newNick);
			pstmt.setString(2,id);
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//=================================================
	public int deleteUser(String id,String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String sqlid = "scott";
			String sqlpwd = "tiger";
			conn = DriverManager.getConnection(url,sqlid,sqlpwd);
			
			String sql = "DELETE FROM sm_user WHERE user_id = ? AND user_pwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
