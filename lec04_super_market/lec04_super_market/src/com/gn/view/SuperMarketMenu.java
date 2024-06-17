package com.gn.view;

import java.util.List;
import java.util.Scanner;

import com.gn.controller.BuyController;
import com.gn.controller.ProductController;
import com.gn.controller.UserController;
import com.gn.model.dao.BuyDao;
import com.gn.model.dao.ProductDao;
import com.gn.model.dao.UserDao;
import com.gn.model.vo.Buy;
import com.gn.model.vo.Product;


public class SuperMarketMenu {
	private Scanner sc = new Scanner(System.in);
	
	// 회원가입 또는 로그인
	public void mainMenu() {
		while(true) {
			System.out.println("==== 환영합니다 ====");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인 메뉴");
			System.out.println("0. 프로그램 종료"); 
			System.out.print("메뉴 : ");
			String select = sc.nextLine();
			int menu = -1;
			try {
				menu =Integer.parseInt(select); 
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
			}
			
			switch(menu) {
			case -1:break;
			case 1:createMember();break;
			case 2:logIn();break;
			case 0: System.out.println("이용해주셔서 감사합니다.");return;
				default: System.out.println("다시 입력해주세요.");break;
			}
			
		}
	}
	
	// 관리자 메뉴
	
	// 제품 등록
	// 제품 입고
	// 판매현황
	public void adminMenu() {
		while(true) {
			System.out.println("==== 관리자 메뉴 ====");
			System.out.println("1. 제품 등록");
			System.out.println("2. 제품 입고");
			System.out.println("3. 판매 현황");
			System.out.println("0. 종료");
			System.out.print("메뉴 : ");
			String select = sc.nextLine();
			int menu = -1;
			try {
				menu =Integer.parseInt(select); 
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
			}
			
			switch(menu) {
			case -1:break;
			case 1:newProduct();break;
			case 2:insertProduct();break;
			case 3:searchBuyList();break;
			case 0: System.out.println("이용해주셔서 감사합니다");return;
			default: System.out.println("다시 입력해주세요.");break;
			}
			
		}
	}
	
	// 사용자 메뉴
	public void userMenu(int no,String id) {
		while(true) {
			System.out.println("==== 사용자 메뉴 ====");
			System.out.println("1. 제품 구매");
			System.out.println("2. 개인 정보 수정");
			System.out.println("3. 탈퇴");
			System.out.println("0. 종료");
			System.out.print("메뉴 : ");
			String select = sc.nextLine();
			int menu = -1;
			try {
				menu =Integer.parseInt(select); 
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
			}
			
			switch(menu) {
			case -1:break;
			case 1:buyProduct(no);break;
			case 2:checkUserInfo(no,id);break;
			case 3:deleteUser(no,id);break;
			case 0: System.out.println("이용해주셔서 감사합니다");return;
				default: System.out.println("다시 입력해주세요.");break;
			}
		}
	}
	// 제품 구매
	// 개인 정보 수정
	// 탈퇴
	
	
	// 관리자==============================================================
	// 제품 등록
	public void newProduct() {
		System.out.println("==== 제품 등록 ====");
		System.out.print("제품명 : ");
		String pdName = sc.nextLine();
		System.out.print("제품 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("입고 개수 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		
		new ProductController().newProduct(pdName,price,amount);
	}
	
	
	
	// 제품 입고
	public void insertProduct() {
		System.out.println("==== 제품 입고 ====");
		System.out.print("제품 번호 : ");
		int prodNum = sc.nextInt();
		sc.nextLine();
		System.out.print("입고 개수 : ");
		int prodinput = sc.nextInt();
		sc.nextLine();
		
		new ProductController().insertproduct(prodNum , prodinput);
	}
	
	
	// 판매현황
	public void searchBuyList() {
		List<Buy> resultList = new BuyDao().checkBuyList();
		if(resultList.isEmpty()) {
			displayNoData();
		} else {
			displayBuyList(resultList);
		}
	}
	
	
	
	// 관리자==============================================================
	
	
	
	
	// 사용자==============================================================
	// 제품 구매
	public void buyProduct(int no) {
		// 전체 제품 목록 출력
		List<Product> resultList = new ProductDao().checkProduct();
		if(resultList.isEmpty()) {
			displayNoData();
		} else {
			displayMemberList(resultList);
		}
		System.out.println("구매를 원하는 제품 및 정보를 입력하세요.");
		System.out.print("제품 번호 : ");
		int prodNum = sc.nextInt();
		sc.nextLine();
		System.out.print("구매 할 개수 : ");
		int buyAmount = sc.nextInt();
		sc.nextLine();
		
		new ProductController().buyDelete(prodNum,buyAmount);
		new BuyController().addList(no,prodNum,buyAmount);
	}
	// 개인 정보 수정
	public void checkUserInfo(int no,String id) {
		
		System.out.println("비밀번호를 입력하세요.");
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		int result = new UserDao().checkUserInfo(id,pwd);
		String work = "비밀번호 인증";
		if(result == 0) {
			displayFail(work);
			userMenu(no,id);
		}
		
		System.out.println("변경하고자 하는 닉네임을 입력하세요");
		System.out.print("닉네임 : ");
		String newNick = sc.nextLine();
		
		new UserController().changeUserInfo(id,newNick);
		
	}
	// 탈퇴
	public void deleteUser(int no,String id) {
		System.out.println("비밀번호를 입력하세요.");
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		int result = new UserDao().checkUserInfo(id,pwd);
		String work = "비밀번호 인증";
		if(result == 0) {
			displayFail(work);
			userMenu(no,id);
		} else {
			new UserController().deleteUser(id,pwd);
		}
		
		
	}
	// 사용자==============================================================
	
	
	
	// 회원가입
	public void createMember() {
		System.out.println("==== 회원 추가 ====");
		
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		System.out.print("닉네임 : ");
		String nickName = sc.nextLine();
		
		new UserController().insertMember(id, pwd, nickName);
	}
	// 로그인
	public void logIn() {
		System.out.println("===== 로그인 =====");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		new UserController().checkmember(id,pwd);
	}
	
	// 결과 출력 메소드
	public void displaySuccess(String work) {
		System.out.println("요청하신 "+work+"를(을) 성공적으로 수행하였습니다.");
	}
	public void displayFail(String work) {
		System.out.println("요청하신 "+work+" 수행중 오류가 발생하였습니다.");
	}
	// 로그인 관련
	public void loginAdminSuccess(String nick) {
		System.out.println(nick+"님 환영합니다!");
		adminMenu();
	}
	public void loginUserSuccess(int no,String id,String nick) {
		System.out.println(nick+"님 환영합니다!");
		userMenu(no,id);
	}
	
	public void displayNoData() {
		System.out.println("조회된 결과가 없습니다.");
	}
	public void displayMemberList(List<Product> list) {
		System.out.println("조회된 결과는 다음과 같습니다.");
		for(Product p : list) {
			System.out.println(p);
		}
	}
	public void displayBuyList(List<Buy> list) {
		System.out.println("조회된 결과는 다음과 같습니다.");
		for(Buy b : list) {
			System.out.println(b);
		}
	}
	// =================
}
