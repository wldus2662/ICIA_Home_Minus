package com.controller;

import java.util.List;
import java.util.Scanner;

import javax.swing.JTable.PrintMode;

import com.dto.MemberDto;
import com.dto.ProductDto;
import com.service.Service;
import com.view.View;

public class Controller {
	View v = new View();
	Service s = new Service();

	public void execute() {
		MemberDto member = null;
		MemberDto loginMember = null;
		ProductDto product = null;
		int menu = -1;//메뉴번호 저장
		int lr = 0;//로그인 결과 저장
		boolean b = false;//직원 및 제품등록 수락 여부
		String user = null;//로그인시 ID 저장

		while(true) {
			if(loginMember == null) {
				int programMenu = -1;
				v.programMain();
				programMenu = v.inputNumber("선택 : ");

				if(programMenu == 0) {
					v.printMsg(0);
					break;
				}
				else if(programMenu == 1) {
					loginMember = new MemberDto();
					v.loginInput(loginMember);//로그인 입력
					lr = s.logincheck(loginMember);//로그인 확인
				}
				else {
					v.printMsg(2);
					continue;
				}
			}
			if(lr == 0) {//로그인 성공
				user = loginMember.getM_ID();
			}
			else {//로그인실패
				v.printMsg(lr);

				if(lr == 13) {//아이디 없음
					b = v.regInputm(loginMember);
					if(b == false) {//회원 가입 안함
						v.printMsg(0);
						return;//종료
					}
					int regNum = s.regclerk(loginMember);//회원가입
					v.printMsg(regNum);
				}//if end
				loginMember = null;
				continue;
			}//else end

			v.showMenu(user);
			menu = v.inputNumber("선택 : ");//메뉴 선택

			if(menu == 0) {//로그아웃 처리
				int i = v.logout();
				if(i == 1) {
					loginMember = null;
					v.printMsg(15);
					continue;
				}
				else if(i == 2) {
					v.printMsg(1);
					continue;
				}
				else {
					v.printMsg(2);
				}
				break;
			}

			switch(menu) {
			case 1://상품 등록
				product = new ProductDto();
				b = v.regInputp(product);
				if(b == false) {
					v.printMsg(1);
				}
				else {
					v.printMsg(s.regproduct(product));
				}
				break;
			case 2://상품 삭제
				String code = v.inputDelPro();
				v.printMsg(s.delProduct(code));
				break;
			case 3://상품 조회
				while(true) {
					int subMenuP = -1;
					v.showSearchMenu();
					subMenuP = v.inputNumber("선택 : ");

					if(subMenuP == 0) {
						v.printMsg(1);
						break;
					}

					switch(subMenuP) {
					case 1://제품 검색
						code = v.productSearch();
						product = s.searchData(code);
						if(product == null) {
							v.printMsg(12);
						} else {
							v.productSearchR(product);
						}

						break;

					case 2://카테고리별 조회
						List<ProductDto> pList = null;
						int category = v.categorySearch();
						if(category == 0) {
							v.printMsg(1);
							break;
						}
						else {
							pList = s.searchCategory(category);
							if(pList == null) {
								v.printMsg(12);
							}
							else {
								v.printProAll(pList);
							}
							break;
						}
					case 3://제품 전체 조회
						pList = s.productList();
						if(pList == null) {
							v.printMsg(12);
						}
						else {
							v.printProAll(pList);
						}
						break;
					default :
						v.printMsg(2);
					}

				}//while end

				break;
			case 4://상품 수정
				product = new ProductDto();
				product = s.searchData(v.updatePTitle());

				if(product == null) {
					v.printMsg(6);
				}
				else {
					v.productSearchR(product);

					product = v.updateInputP(product);
					v.printMsg(s.updateInfoP(product));
				}

				break;
			case 5://직원 관리(관리자용)
				if(!user.equals("boss")) {
					v.printMsg(2);
					break;
				}

				while(true) {
					int adminMenu = -1;
					v.showAdminMenu();
					adminMenu = v.inputNumber("선택 : ");

					if(adminMenu == 0) {//뒤로가기 처리
						v.printMsg(1);
						break;
					}

					switch(adminMenu) {
					case 1://직원 등록
						member = new MemberDto();
						b = v.regInputm(member);
						if(b == false) {
							v.printMsg(1);
						}
						else {
							v.printMsg(s.regclerk(member));
						}
						break;
					case 2://직원 삭제
						String id = v.inputDelMem();
						v.printMsg(s.delMember(id));
						break;
					case 3://직원 조회
						while(true) {
							int subMenuM = -1;
							v.showAdminSearchMenu();
							subMenuM = v.inputNumber("선택 : ");

							if(subMenuM == 0) {
								v.printMsg(1);
								break;
							}

							switch(subMenuM) {
							case 1://직원 검색
								id = v.memberSearch();
								member = s.searchMember(id);

								if(member == null) {
									v.printMsg(12);
								}
								else {
									v.memberSearchR(member);
								}
								break;
							case 2://부서별 조회
								List<MemberDto> mList = null;
								int dept = v.deptSearch();
								if(dept == 0) {
									v.printMsg(1);
									break;
								}
								else {
									mList = s.searchDept(dept);

									if(mList == null) {
										v.printMsg(12);
									}
									else {
										v.printMemberAll(mList);
									}
									break;
								}
							case 3://직원 전체 조회
								mList = s.memberList();

								if(mList == null) {
									v.printMsg(12);
								}
								else {
									v.printMemberAll(mList);
								}
								break;
							default :
								v.printMsg(2);
							}

						}//while end

						break;
					case 4://직원 수정
						member = new MemberDto();
						member = s.searchMember(v.updateMTitle());

						if(member == null) {
							v.printMsg(6);
						}
						else {
							v.memberSearchR(member);

							member = v.updateInputM(member);
							v.printMsg(s.updateInfoM(member));
						}

						break;
					default :
						v.printMsg(2);
					}
				}
				break;

			default :
				v.printMsg(2);
			}//switch end

		}//while end

	}//execute2 end

}
