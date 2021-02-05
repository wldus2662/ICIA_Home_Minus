package com.dao;

import java.util.List;

import com.dto.MemberDto;
import com.dto.ProductDto;

public interface Dao {
	//로그인 기능
	//로그인 처리 메소드
	public String selectPass(String mid);

	//등록 기능
	//직원 등록 메소드
	public int insertMember(MemberDto clerk);
	//상품 등록 메소드
	public int insertProduct(ProductDto pro);

	//삭제기능
	//직원 삭제 선언부
	public int deleteMember(String delMem); 
	//제품 삭제 선언부
	public int deleteProduct(String delPro); 

	//검색 기능
	//상품 검색 메소드
	public ProductDto codeSearch(String code);
	//직원 검색 메소드 
	public MemberDto idSearch(String id);
	//카테고리별 검색 메소드
	public List<ProductDto> categorySearch(int category);
	//부서별 검색 메소드
	public List<MemberDto> deptSearch(int dept);

	//전체 조회 기능
	//상품 전체 조회 메소드 선언부 정의
	public List<ProductDto> selectPList();
	//직원 전체 조회 메소드 선언부 정의
	public List<MemberDto> selectMList();

	//수정 기능
	//상품 정보 수정 메소드의 선언부 정의(update)
	public int updateProduct(ProductDto product);
	//회원 정보 수정 메소드의 선언부 정의(update)
	public int updateMember(MemberDto member);


}
