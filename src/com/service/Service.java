package com.service;

import java.util.List;

import com.dao.DaoImpl;
import com.dto.MemberDto;
import com.dto.ProductDto;

public class Service {
	DaoImpl dao = null;

	
	//DAO 인스턴스 생성
	public Service() {
		dao = DaoImpl.getInstance();
	}//Service end
	

	//로그인 처리용 메소드
	public int logincheck(MemberDto clerk) {
		int result = 0;

		String pass = dao.selectPass(clerk.getM_ID());

		if(pass != null) {
			if(pass.equals(clerk.getM_PASS())) {
				//로그인 성공
				result = 0;
			}//if end
			else {
				//패스워드 실패
				result = 14;

			}//else end

		}//if end
		else {
			//입력한 ID가 DB에 없는 경우
			result = 13;
		}

		return result;
	}//logincheck end

	
	//상품 등록 메소드
		public int regproduct(ProductDto pro) {
			int result = -1;

			result = dao.insertProduct(pro);

			if(result > 0) {
				result = 4;
			}
			else {
				result = 5;
			}
			return result;
		}//regproduct end 
		
		
	//직원 등록 메소드
	public int regclerk(MemberDto clerk) {
		int result = 1;

		result = dao.insertMember(clerk);

		if(result > 0) {
			result = 4;
		}
		else {
			result = 5;
		}

		return result;
	}//regclerk end

	
	//상품 삭제
		public int delProduct(String delPro) {
			int result = 0;

			result = dao.deleteProduct(delPro);

			if(result > 0) {
				result = 10; // 삭제 완료 메시지 코드
			}
			else {
				result = 9; // 삭제 실패 메시지 코드
			}

			return result;
		}//delProduct end
	
		
	//직원 삭제
	public int delMember(String delMem) {
		int result = 0;

		result = dao.deleteMember(delMem);

		if(result > 0) {
			result = 10; // 삭제 완료 메시지 코드
		}
		else {
			result = 9; // 삭제 실패 메시지 코드
		}
		return result;
	}//delMember end

	
	//상품 검색
	public ProductDto searchData(String code) {
		//들어온 코드로 프로덕트에서 검색.
		ProductDto p = dao.codeSearch(code);
		return p;
	}//searchData end
	
	
	//직원 검색
	public MemberDto searchMember(String id) {
		MemberDto m = dao.idSearch(id);
		return m;
	}//searchMember end

	
	//카테고리별 검색
	public List<ProductDto> searchCategory(int category) {
		List<ProductDto> pList = dao.categorySearch(category);
		return pList;
	}
	//부서별 검색
	public List<MemberDto> searchDept(int dept) {
		List<MemberDto> mList = dao.deptSearch(dept);
		return mList;
	}
	
	
	//상품 전체 조회
	public List<ProductDto> productList() {
		List<ProductDto> pList = null;

		pList = dao.selectPList();

		return pList;
	}


	//직원 전체 조회
	public List<MemberDto> memberList() {
		List<MemberDto> mList = null;

		mList = dao.selectMList();

		return mList;
	}
	
	
	//상품 수정
	public int updateInfoP(ProductDto product) {
		int result = -1;
		//DB에 수정 작업은  DAO가 한다.      
		result = dao.updateProduct(product);

		if(result > 0) {
			result = 7;
		}
		else {
			result = 8;
		}
		return result;
	}
	
	
	//직원 수정
	public int updateInfoM(MemberDto member) {
		int result = -1;

		//DB에 수정 작업은  DAO가 한다.      
		result = dao.updateMember(member);

		if(result > 0) {
			result = 7;
		}
		else {
			result = 8;
		}
		return result;

	}

}//class end
