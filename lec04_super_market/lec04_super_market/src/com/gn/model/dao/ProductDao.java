package com.gn.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gn.model.vo.Product;

public class ProductDao {
	
	
	// 제품 등록
	public int newproduct(Product p) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pwd="tiger";
						
			conn=DriverManager.getConnection(url,id,pwd);
			
			conn.setAutoCommit(false);
			
			String sql1 = "SELECT COUNT(*) FROM sm_product WHERE product_prodName = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, p.getProdName());
			
			rs= pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			if(result == 0) {
				String sql2 = "INSERT INTO sm_product "
						+ "(product_prodName ,product_prodPrice ,product_haveAmount) "
						+ "VALUES (?,?,?)";
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, p.getProdName());
				pstmt.setInt(2, p.getProdPrice());
				pstmt.setInt(3, p.getHaveAmount());
				
				result = pstmt.executeUpdate();
				// 여기서 인서트에 성공하면 1 실패하면 0
			} else {
				// 중복되는 결과가 있어 이전 result 값이 1이면 오류인 0으로 반환
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
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// ====================================================================
	
	public int insertproduct(Product p) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 1;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url="jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pwd = "tiger";
			conn = DriverManager.getConnection(url,id,pwd);
			
			conn.setAutoCommit(false);
			
			String sql1="SELECT COUNT(*) FROM sm_product WHERE product_prodNo=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, p.getProdNo());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 0;
			}
			
			if(result == 0) {
				String sql2="UPDATE sm_product SET product_haveAmount=product_haveAmount + ?"
						+ " WHERE product_prodNo = ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, p.getHaveAmount());
				pstmt.setInt(2, p.getProdNo());
				result = pstmt.executeUpdate();
			}else {
				result = 0;
			}
			
			conn.commit();
			
		} catch (Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return result ;
	}
	
	// ==================구매시 제품 총량 감소
	public int deleteProduct(int prodNum, int buyAmount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 1;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url="jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pwd = "tiger";
			conn = DriverManager.getConnection(url,id,pwd);
			
			conn.setAutoCommit(false);
			String sql1="SELECT COUNT(*) FROM sm_product WHERE product_prodNo=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, prodNum);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				result = 0;
			}
			
			if(result == 0) {
				String sql2 = "UPDATE sm_product SET product_haveAmount=product_haveAmount - ? "
						+ "WHERE product_prodNo = ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, buyAmount);
				pstmt.setInt(2, prodNum);
				
				result = pstmt.executeUpdate();	
			} else {
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
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// =======================제품 조회
	public List<Product> checkProduct() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Product> resultList = new ArrayList<Product>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pwd = "tiger";
			conn = DriverManager.getConnection(url,id,pwd);
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM sm_product";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Product p =new Product(rs.getInt("product_prodNo")
						,rs.getString("product_prodName")
						,rs.getInt("product_prodPrice")
						,rs.getInt("product_haveAmount")
						);
				resultList.add(p);
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
	
	
	
	
	
	
	
}
