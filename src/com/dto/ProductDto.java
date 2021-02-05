package com.dto;

public class ProductDto {
	private String P_CATEGORY;
	private String P_CODE;
	private String P_NAME;
	private String P_BRAND;
	private int P_AMOUNT;
	private int P_PRICE;
	private int P_SALE;


	public String getP_CATEGORY() {
		return P_CATEGORY;
	}

	public void setP_CATEGORY(String p_CATEGORY) {
		P_CATEGORY = p_CATEGORY;
	}

	public String getP_CODE() {
		return P_CODE;
	}

	public void setP_CODE(String p_CODE) {
		P_CODE = p_CODE;
	}

	public String getP_NAME() {
		return P_NAME;
	}

	public void setP_NAME(String p_NAME) {
		P_NAME = p_NAME;
	}

	public String getP_BRAND() {
		return P_BRAND;
	}

	public void setP_BRAND(String p_BRAND) {
		P_BRAND = p_BRAND;
	}

	public int getP_AMOUNT() {
		return P_AMOUNT;
	}

	public void setP_AMOUNT(int p_AMOUNT) {
		P_AMOUNT = p_AMOUNT;
	}

	public int getP_PRICE() {
		return P_PRICE;
	}

	public void setP_PRICE(int p_PRICE) {
		P_PRICE = p_PRICE;
	}

	public int getP_SALE() {
		return P_SALE;
	}

	public void setP_SALE(int p_SALE) {
		P_SALE = p_SALE;
	}

	@Override
	public String toString() {
		String str = null;
		
		if(P_SALE == 0) {
			str = "카테고리 : " + P_CATEGORY + "\n"
					+ "제품 코드 : " + P_CODE + "\n"
					+ "제품명: " + P_NAME + "\n"
					+ "제조사 : " + P_BRAND + "\n"
					+ "수량 : " + P_AMOUNT + "\n"
					+ "가격 : " + P_PRICE + " 원\n"
					+ "할인율 : " + P_SALE + " %";
		}
		else {
			str = "카테고리 : " + P_CATEGORY + "\n"
					+ "제품 코드 : " + P_CODE + "\n"
					+ "제품명 : " + P_NAME + "\n"
					+ "제조사 : " + P_BRAND + "\n"
					+ "수량 : " + P_AMOUNT + "\n"
					+ "가격 : " + P_PRICE + " 원"
					+ " -> " + (P_PRICE - ((P_PRICE/100)*P_SALE)) + " 원\n"
					+ "할인율 : " + P_SALE + " %";
		}
			
		return str;
	}

}