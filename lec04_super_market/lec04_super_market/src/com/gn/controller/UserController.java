package com.gn.controller;

import com.gn.model.dao.UserDao;
import com.gn.model.vo.User;
import com.gn.view.SuperMarketMenu;

public class UserController {
	// 회원가입
	public void insertMember(String id, String pwd, String nickName) {
		User u = new User();
		u.setUser_id(id);
		u.setUser_pwd(pwd);
		u.setUser_nick(nickName);
		// 중복되는 아이디가 있는 경우 -1
		// Insert 과정중 오류가 발생한 경우 0
		// 정상 Insert 1
		int result = new UserDao().insertMember(u);
		String work = "회원추가";
		if(result>0) {
			new SuperMarketMenu().displaySuccess(work);
		} else {
			new SuperMarketMenu().displayFail(work);
		}
		
		
	}
	// 로그인
	public void checkmember(String id,String pwd) {
		
		User us = new UserDao().checkMember(id, pwd);
		if(us == null) {
			new SuperMarketMenu().displayFail("로그인");
		} else {
			int no = us.getUser_no();
			String us_id = us.getUser_id();
			String result = us.getUser_nick();
			
			if(result.equals("관리자")) {
				new SuperMarketMenu().loginAdminSuccess(result);
			} else {
				new SuperMarketMenu().loginUserSuccess(no,us_id,result);
			}
		}
	}
	
	
	// 아이디 비밀번호 일치 여부 조회 후 닉네임 변경
	public void changeUserInfo(String id,String newNick) {
		int result = new UserDao().changeUserInfo(id,newNick);
		String work = "개인 정보 수정";
		if(result == 0) {
			new SuperMarketMenu().displayFail(work);
		} else {
			new SuperMarketMenu().displaySuccess(work);
		}
	}
	
	// ==============================================
	public void deleteUser(String id,String pwd) {
		int result = new UserDao().deleteUser(id,pwd);
		
		String work = "회원 탈퇴";
		if(result == 0) {
			new SuperMarketMenu().displayFail(work);
		}else {
			new SuperMarketMenu().displaySuccess(work);
		}
	}
	
}
