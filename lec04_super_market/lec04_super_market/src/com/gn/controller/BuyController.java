package com.gn.controller;

import com.gn.model.dao.BuyDao;
import com.gn.view.SuperMarketMenu;

public class BuyController {
	
	// 구매 내역 추가
	public void addList(int no,int prodNum,int buyAmount) {
		int result = new BuyDao().addList(no,prodNum,buyAmount);
		
		String work = "구매 내역 추가";
		
		if(result>0) {
			new SuperMarketMenu().displaySuccess(work);
		} else {
			new SuperMarketMenu().displayFail(work);
		}
		
		
		
		
	}
	// ===============================================
}
