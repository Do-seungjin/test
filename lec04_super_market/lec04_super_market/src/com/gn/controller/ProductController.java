package com.gn.controller;

import com.gn.model.dao.ProductDao;
import com.gn.model.vo.Product;
import com.gn.view.SuperMarketMenu;

public class ProductController {
	// 제품 등록 ==============================================================
	public void newProduct(String pdName, int price, int amount) {
		Product p = new Product();
		p.setProdName(pdName);
		p.setProdPrice(price);
		p.setHaveAmount(amount);
		
		int result = new ProductDao().newproduct(p);
		String work = "제품 등록";
		if(result > 0) {
			new SuperMarketMenu().displaySuccess(work);
		} else {
			new SuperMarketMenu().displayFail(work);
		}
	}
	
	// 제품 입고 ============================================================
	public void insertproduct(int prodNum ,int prodinput) {
		Product p = new Product();
		p.setProdNo(prodNum);
		p.setHaveAmount(prodinput);
		
		int result = new ProductDao().insertproduct(p);
		// 0 일때 실패 1일때 성공
		String work="제품 입고";
		if(result>0) {
			new SuperMarketMenu().displaySuccess(work);
		}else {
			new SuperMarketMenu().displayFail(work);
		}		
	}
	
	// 제품 구매시 제품 총량 감소
	public void buyDelete(int prodNum,int buyAmount) {
		
		int result = new ProductDao().deleteProduct(prodNum, buyAmount);
		// 0 일때 실패 1일때 성공
		String work="제품 구매";
		if(result>0) {
			new SuperMarketMenu().displaySuccess(work);
		}else {
			new SuperMarketMenu().displayFail(work);
		}
	}
	
	
}
