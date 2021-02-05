package com.view;

import java.util.List;
import java.util.Scanner;

import com.dto.MemberDto;
import com.dto.ProductDto;

public class View {
	private Scanner scan = new Scanner(System.in);
	
	//프로그램 메인 메뉴
	public void programMain() { 

		System.out.println();
		System.out.println("***************************");
		System.out.println("*    HomeMinus Manager    *");
		System.out.println("*    1.로그인        0.종료                 *");
		System.out.println("***************************");

	}//programMain end

	//로그인 기능
	public void loginInput(MemberDto member) {
		while(true) {
			System.out.println();
			System.out.println("*** 로그인 ***");

			System.out.print("ID : ");
			member.setM_ID(scan.nextLine());

			if(member.getM_ID().equals("")) {
				System.out.println("ID를 입력하세요");
				continue;
			}//if end
			System.out.print("PASS : ");
			member.setM_PASS(scan.nextLine());

			if(member.getM_PASS().equals("")) {
				System.out.println("PASS를 입력하세요");
				continue;
			}//if end
			else {
				break;
			}//else end

		}//while end

	}//loginInput end


	//숫자 입력
	public int inputNumber(String str) {
		int num = -1;

		while(true) {
			System.out.print(str);
			try {
				num = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				System.out.println("숫자만 입력해 주세요");
			}
		}
		return num;
	}//inputNumber end

	//문자 입력
	public String inputText(String str) {
		String tex = null;
		while(true) {
			System.out.print(str);
			tex = scan.nextLine();

			if(tex.equals("")) {
				System.out.println("값을 입력헤주세요");
				continue;
			}//if end

			return tex;
		}//while end

	}//inputText end


	//메인 메뉴
	public void showMenu(String user) {
		System.out.println();
		System.out.println("*** HOEMMINUS MANAGER ***");
		System.out.println("===========================");
		System.out.println("<<메뉴>>");
		System.out.println("1. 상품 등록");
		System.out.println("2. 상품 삭제");
		System.out.println("3. 상품 조회");
		System.out.println("4. 상품 수정");

		if(user.equals("boss")) {//관리자용
			System.out.println("5. 직원 관리");
		}//if end
		System.out.println("0. 로그 아웃");
		System.out.println("===========================");

	}//showMenu end


	//로그아웃 출력
	public int logout() {
		int res = 0;


		System.out.println();
		System.out.println("로그아웃 하시겠습니까 (y/n)?");
		String str = scan.nextLine();

		if(str.toUpperCase().equals("Y")) {
			res = 1;
		}
		else if(str.toUpperCase().equals("N")) {
			res = 2;
		}
		else {
			res = 3;
		}

		return res;
	}


	//조회 메뉴
	public void showSearchMenu() {
		System.out.println();
		System.out.println("*** 상품 조회 ***");
		System.out.println("====================");
		System.out.println("1. 상품 검색");
		System.out.println("2. 카테고리별 조회");
		System.out.println("3. 상품 전체 조회");
		System.out.println("0. 뒤로가기");
	}


	//관리자용 메뉴
	public void showAdminMenu() {
		System.out.println();
		System.out.println("*** 직원관리 ***");
		System.out.println("====================");
		System.out.println("1. 직원 등록");
		System.out.println("2. 직원 삭제");
		System.out.println("3. 직원 조회");
		System.out.println("4. 직원 수정");
		System.out.println("0. 뒤로가기");
	}


	//관리자용 조회 메뉴
	public void showAdminSearchMenu() {
		System.out.println();
		System.out.println("*** 직원 조회 ***");
		System.out.println("====================");
		System.out.println("1. 직원 검색");
		System.out.println("2. 부서별 조회");
		System.out.println("3. 직원 전체 조회");
		System.out.println("0. 뒤로가기");
	}


	//제품 등록
	public boolean regInputp(ProductDto pro) {
		boolean b = false;

		System.out.println();
		System.out.println("상품등록을 하시겠습니까?(y/n)");
		String str = scan.nextLine();

		if(!str.toUpperCase().equals("Y")) {
			return b;
		}//if end
		System.out.println();
		System.out.println("*** 상품 등록 ****");
		System.out.println("====================");
		pro.setP_CATEGORY(inputText("CATEGORY(전자제품 / 생활용품 / 식품)\n: "));
		pro.setP_CODE(inputText("CODE : "));
		pro.setP_NAME(inputText("NAME : "));
		pro.setP_BRAND(inputText("BRAND : "));
		pro.setP_AMOUNT(inputNumber("AMOUNT : "));
		pro.setP_PRICE(inputNumber("PRICE : "));
		pro.setP_SALE(inputNumber("SALE : "));

		b = true;
		return b;

	}//regInputp end


	//직원 등록
	public boolean regInputm(MemberDto member) {
		boolean b = false;
		System.out.println();
		System.out.println("직원등록을 하시겠습니까?(y/n)");
		String str = scan.nextLine();

		if(!str.toUpperCase().equals("Y")) {
			return b;
		}//if end
		System.out.println();
		System.out.println("*** 직원등록 ***");
		System.out.println("=====================");
		System.out.print("ID : " );
		member.setM_ID(scan.nextLine());
		System.out.print("PASS : ");
		member.setM_PASS(scan.nextLine());
		System.out.print("NAME : ");
		member.setM_NAME(scan.nextLine());
		System.out.print("DEPT : ");
		member.setM_DEPT(scan.nextLine());
		System.out.print("POS : ");
		member.setM_POS(scan.nextLine());
		b = true;

		return b;
	}//regInputm end


	//제품 삭제
	public String inputDelPro() {
		String delPro = null;
		System.out.println();
		System.out.println("*** 제품 삭제 ***");
		System.out.println("================");
		System.out.print("삭제할 제품 코드 : ");
		delPro = scan.nextLine();

		return delPro;
	}//inputDelpro end


	//직원 삭제
	public String inputDelMem() { //직원 삭제 id 입력 메소드
		String delMem = null;
		System.out.println();
		System.out.println("*** 직원 삭제 ***");
		System.out.println("================");
		System.out.print("삭제할 직원 ID : ");
		delMem = scan.nextLine();

		return delMem;
	}//inputDelmem end


	//제품 검색
	public String productSearch() {
		String result = null;

		//타이틀
		System.out.println();
		System.out.println("*** 제품 검색 ***");
		System.out.println("================");
		System.out.print("제품 코드 입력 : ");
		result = scan.nextLine();

		return result;
	}//productSearch end


	//제품 검색 결과
	public void productSearchR(ProductDto p) {
		System.out.println();
		System.out.println("*** 제품 검색 결과 ***");
		System.out.println(p);

	}//productSearchR end


	//직원 검색
	public String memberSearch() {
		System.out.println();
		System.out.println("*** 직원 검색 ***");
		System.out.println("================");
		System.out.println("직원 ID 입력 : ");
		String result = scan.nextLine();

		return result;
	}//memberSearch end


	//직원 검색 결과
	public void memberSearchR(MemberDto m) {
		System.out.println();
		System.out.println("*** 직원 검색 결과 ***");
		System.out.println(m);
	}//memberSearchR end


	//제품 카테고리 검색
	public int categorySearch() {
		System.out.println();
		System.out.println("*** 카테고리별 조회 ***");
		System.out.println("==================");
		System.out.println("1. 가전제품");
		System.out.println("2. 생활용품");
		System.out.println("3. 식품");
		System.out.println("0. 뒤로가기");
		int result = inputNumber("선택 : ");

		return result;
	}//categorySearch end


	//직원 부서 검색
	public int deptSearch() {
		System.out.println();
		System.out.println("*** 부서별 검색 ***");
		System.out.println("==================");
		System.out.println("1. ICIA-1");
		System.out.println("2. ICIA-2");
		System.out.println("3. ICIA-3");
		System.out.println("0. 뒤로가기");
		int result = inputNumber("선택 : ");

		return result;
	}

	//제품 전체 조회
	public void printProAll(List<ProductDto> pList) {
		System.out.println();
		System.out.println("*** 제품 조회 결과 ***");
		System.out.println("=================");
		for(ProductDto p : pList) {
			System.out.println(p);
			System.out.println("-------------------");
		}

	}//printProAll end


	//직원 전체 조회
	public void printMemberAll(List<MemberDto> mList) {
		System.out.println();
		System.out.println("*** 직원 전체 조회 ***");
		System.out.println("=================");
		for(MemberDto m : mList) {
			System.out.println(m);
			System.out.println("-------------------");
		}

	}//printMemberAll end


	//제품 정보 수정
	public String updatePTitle() {
		String code = null;
		System.out.println();
		System.out.println("*** 상품 정보 수정 ***");
		System.out.println("===============");

		System.out.print("제품코드 : ");
		code = scan.nextLine();

		return code;

	}//updatPTitle end


	//제품 정보 수정용 입력
	public ProductDto updateInputP(ProductDto product) {
		String str = null;

		//입력
		System.out.println();
		System.out.println("수정사항을 입력하세요.(없으면 enter)");
		System.out.print("카테고리 : ");
		str = scan.nextLine();
		if(!str.equals("")) {
			product.setP_CATEGORY(str);
		}
		System.out.print("제품명 : ");
		str = scan.nextLine();
		if(!str.equals("")) {
			product.setP_NAME(str);
		}
		System.out.print("제조사 : ");
		str = scan.nextLine();
		if(!str.equals("")) {
			product.setP_BRAND(str);
		}
		System.out.print("수량 : ");
		str = scan.nextLine();
		if(str.equals("")) {
		}
		else {
			try {
				int i = Integer.parseInt(str);
				product.setP_AMOUNT(i);
			} catch (NumberFormatException e) {
				printMsg(2);
			}   
		}
		System.out.print("가격 : ");
		str = scan.nextLine();
		if(str.equals("")) {
		}
		else {
			try {
				int i = Integer.parseInt(str);
				product.setP_PRICE(i);
			} catch (NumberFormatException e) {
				printMsg(2);
			}   

		}
		System.out.print("할인율 : ");
		str = scan.nextLine();
		if(str.equals("")) {
		}
		else {
			try {
				int i = Integer.parseInt(str);
				product.setP_SALE(i);
			} catch (NumberFormatException e) {
				printMsg(2);
			}   
		}

		return product;      

	}//updateInputP end


	//직원 정보 수정
	public String updateMTitle() {
		String id = null;
		System.out.println();
		System.out.println("*** 직원 정보 수정 ***");
		System.out.println("===============");

		System.out.print("직원 ID : ");
		id = scan.nextLine();

		return id;

	}//updateMTitle end


	//직원 정보 수정용 입력 메소드
	public MemberDto updateInputM(MemberDto member) {
		String ID = null;
		System.out.println();
		System.out.println("수정사항을 입력하세요.(없으면 enter)");
		System.out.print("이름 : ");
		String str = scan.nextLine();
		if(!str.equals("")) {
			member.setM_NAME(str);
		}
		System.out.print("비밀번호 : ");
		str = scan.nextLine();
		if(!str.equals("")) {
			member.setM_PASS(str);
		}
		System.out.print("부서 : ");
		str = scan.nextLine();
		if(!str.equals("")) {
			member.setM_DEPT(str);
		}
		System.out.print("직급 : ");
		str = scan.nextLine();
		if(!str.equals("")) {
			member.setM_POS(str);
		}

		return member;
	}//updateInputM end

	//기타 메시지 출력
	public void printMsg(int i) {
		switch(i) {
		case 0 :
			System.out.println("프로그램을 종료합니다");
			break;
		case 1 : 
			System.out.println("이전 메뉴로 돌아갑니다");
			break;
		case 2 :
			System.out.println("잘못입력하였습니다");
			break;
		case 3 :
			System.out.println("저장된 데이터가 없습니다");
			break;
		case 4 :
			System.out.println("저장 완료");
			break;
		case 5 :
			System.out.println("저장 실패");
			break;
		case 6 :
			System.out.println("수정할 데이터가 없습니다");
			break;
		case 7 :
			System.out.println("수정 완료");
			break;
		case 8 :
			System.out.println("수정 실패");
			break;
		case 9 :
			System.out.println("삭제할 데이터가 없습니다");
			break;
		case 10 :
			System.out.println("삭제 완료");
			break;
		case 11 : 
			System.out.println("삭제 실패");
			break;
		case 12 :
			System.out.println("검색 결과가 없습니다");
			break;
		case 13 :
			System.out.println("해당되는 ID가 없습니다");
			break;
		case 14 :
			System.out.println("비밀번호가 틀립니다");
			break;
		case 15 :
			System.out.println("로그아웃 되었습니다");

		}//switch end
	}//printMsg end


}//class end