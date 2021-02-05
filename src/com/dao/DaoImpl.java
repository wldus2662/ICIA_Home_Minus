package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.MemberDto;
import com.dto.ProductDto;

public class DaoImpl implements Dao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.147:1521:xe";
	private String user = "BOSS";
	private String password = "1234";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private static DaoImpl dao = null;

	//드라이버 로드
	private DaoImpl() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("드라이버로드 실패!");
		}//catch end
	}//DaoImpl end

	//인스턴스 생성 메소드
	public static DaoImpl getInstance() {
		if(dao == null) {
			dao= new DaoImpl();
		}//if end
		return dao;
	}//DaoImpl getInstance end


	//비밀번호 조회
	@Override
	public String selectPass(String mid) {
		//DB에서 해당 ID(입력값)에 대한 M_PASS 컬럼의 값을 조회
		String result = null;

		String query = "SELECT M_PASS FROM M WHERE M_ID = ?";

		try {
			conn = DriverManager.getConnection(url, user, password);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getString("M_PASS");
			}//if end

		} catch (SQLException e) {
			//e.printStackTrace();

		} finally {
			close();
		}//finally end

		return result;
	}//selectPass end


	//상품 등록
	@Override
	public int insertProduct(ProductDto pro) {
		int result = 0;

		String query = "INSERT INTO P VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = DriverManager.getConnection(url, user, password);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pro.getP_CATEGORY());
			pstmt.setString(2, pro.getP_CODE());
			pstmt.setString(3, pro.getP_NAME());
			pstmt.setString(4, pro.getP_BRAND());
			pstmt.setInt(5, pro.getP_AMOUNT());
			pstmt.setInt(6, pro.getP_PRICE());
			pstmt.setInt(7, pro.getP_SALE());

			result = pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				close();
			}
		}

		return result;
	}//insertProduct end


	//직원 등록
	@Override
	public int insertMember(MemberDto clerk) {
		int result = 0;
		String query = "INSERT INTO M VALUES (?, ?, ?, ?, ?)";

		try {
			conn = DriverManager.getConnection(url, user, password);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, clerk.getM_ID());
			pstmt.setString(2, clerk.getM_PASS());
			pstmt.setString(3, clerk.getM_NAME());
			pstmt.setString(4, clerk.getM_DEPT());
			pstmt.setString(5, clerk.getM_POS());

			result = pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			try {
				conn.rollback();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}//catch end
		} finally {
			close();
		}//finally end

		return result;
	}//insertMember end


	//상품 삭제
	@Override
	public int deleteProduct(String delPro) {
		int result = 0;

		String query = "DELETE FROM P WHERE P_CODE = ?";

		try {
			conn = DriverManager.getConnection(url, user, password);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, delPro);
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		return result;
	}//deleteProduct end


	//직원 삭제
	@Override
	public int deleteMember(String delMem) {
		int result = 0;

		String query = "DELETE FROM M WHERE M_ID = ?";

		try {
			conn = DriverManager.getConnection(url, user, password);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, delMem);
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		} finally {
			close();
		}

		return result;
	}//deleteMember end


	//상품 검색
	@Override
	public ProductDto codeSearch(String code) {
		String query = "SELECT * FROM P WHERE P_CODE = ?";

		ProductDto p = null;
		try {
			// 드라이버 연결
			conn = DriverManager.getConnection(url, user, password);
			// 미완성 쿼리입력
			pstmt = conn.prepareStatement(query);
			// 쿼리완성
			pstmt.setString(1, code);
			// 완성되고 실행하는 쿼리를 rs 에 저장.
			rs = pstmt.executeQuery();

			// 저장되있는 쿼리를 하나의 컬럼씩 뽑아와서 Dto에 저장.
			if (rs.next()) {
				p = new ProductDto();
				p.setP_CATEGORY(rs.getString(1));
				p.setP_CODE(rs.getString("P_CODE"));
				p.setP_NAME(rs.getString("P_NAME"));
				p.setP_BRAND(rs.getString("P_BRAND"));
				p.setP_AMOUNT(rs.getInt("P_AMOUNT"));
				p.setP_PRICE(rs.getInt("P_PRICE"));
				p.setP_SALE(rs.getInt("P_SALE"));
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			close();
		}
		return p;
	}//codeSearch end


	//직원 검색
	public MemberDto idSearch(String id) {
		String query = "SELECT * FROM M WHERE M_ID = ?";

		MemberDto m = null;
		try {
			// 드라이버 연결
			conn = DriverManager.getConnection(url, user, password);
			// 미완성 쿼리입력
			pstmt = conn.prepareStatement(query);
			// 쿼리완성
			pstmt.setString(1, id);
			// 완성되고 실행하는 쿼리를 rs 에 저장.
			rs = pstmt.executeQuery();

			// 저장되있는 쿼리를 하나의 컬럼씩 뽑아와서 Dto에 저장.
			if (rs.next()) {
				m = new MemberDto();
				m.setM_ID(rs.getString(1));
				m.setM_PASS(rs.getString(2));
				m.setM_NAME(rs.getString(3));
				m.setM_DEPT(rs.getString(4));
				m.setM_POS(rs.getString(5));

			}
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			close();
		}
		return m;
	}//idSearch end


	//카테고리별 검색
	@Override
	public List<ProductDto> categorySearch(int category) {
		String query = "SELECT * FROM P WHERE P_CODE LIKE ?";
		String str = "E%";
		if(category == 2) {
			str = "L%";
		}
		else if(category == 3){
			str = "F%";
		}
		
		ArrayList<ProductDto> pList = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, str);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDto p = new ProductDto();
				
				p.setP_CATEGORY(rs.getString(1));
				p.setP_CODE(rs.getString(2));
				p.setP_NAME(rs.getString(3));
				p.setP_BRAND(rs.getString(4));
				p.setP_AMOUNT(rs.getInt(5));
				p.setP_PRICE(rs.getInt(6));
				p.setP_SALE(rs.getInt(7));
				
				pList.add(p);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close();
		}
		
		return pList;
	}
	
	
	//부서별 검색
	@Override
	public List<MemberDto> deptSearch(int dept) {
		String query = "SELECT * FROM M WHERE M_DEPT = ?";
		String str = "ICIA-1";
		if(dept == 2) {
			str = "ICIA-2";
		}
		else if(dept == 3) {
			str = "ICIA-3";
		}
		
		ArrayList<MemberDto> mList = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, str);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDto m = new MemberDto();
				
				m.setM_ID(rs.getString(1));
				m.setM_PASS(rs.getString(2));
				m.setM_NAME(rs.getString(3));
				m.setM_DEPT(rs.getString(4));
				m.setM_POS(rs.getString(5));
				
				mList.add(m);
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close();
		}
		
		return mList;
	}
	
	
	//상품 전체 조회
	@Override
	public List<ProductDto> selectPList() {

		ArrayList<ProductDto> pList = new ArrayList<>();
		String query = "SELECT * FROM P";

		try {
			conn = DriverManager.getConnection(url, user, password);

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ProductDto p = new ProductDto();
				p.setP_CATEGORY(rs.getString(1));
				p.setP_CODE(rs.getString(2));
				p.setP_NAME(rs.getString(3));
				p.setP_BRAND(rs.getString(4));
				p.setP_AMOUNT(rs.getInt(5));
				p.setP_PRICE(rs.getInt(6));
				p.setP_SALE(rs.getInt(7));

				pList.add(p);
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close();
		}


		return pList;
	}//selectPList end


	//직원 전체 조회
	@Override
	public List<MemberDto> selectMList() {

		ArrayList<MemberDto> mList = new ArrayList<MemberDto>();
		String query = "SELECT * FROM M";

		try {
			conn = DriverManager.getConnection(url, user, password);

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberDto m = new MemberDto();
				m.setM_ID(rs.getString(1));
				m.setM_PASS(rs.getString(2));
				m.setM_NAME(rs.getString(3));
				m.setM_DEPT(rs.getString(4));
				m.setM_POS(rs.getString(5));

				mList.add(m);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close();
		}

		return mList;
	}//selectMList end


	//상품 수정
	@Override
	public int updateProduct(ProductDto p) {
		int result = 0;
		String query = "UPDATE P SET P_CATEGORY = ?, P_NAME = ?, P_BRAND = ?, P_AMOUNT = ?, P_PRICE=?, P_SALE=? WHERE P_CODE =?";
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getP_CATEGORY());
			pstmt.setString(2, p.getP_NAME());
			pstmt.setString(3, p.getP_BRAND());
			pstmt.setInt(4, p.getP_AMOUNT());
			pstmt.setInt(5, p.getP_PRICE());
			pstmt.setInt(6, p.getP_SALE());
			pstmt.setString(7, p.getP_CODE());

			result = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			//e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				//e1.printStackTrace();
			}
		} finally {
			close();
		}

		return result;
	}

	
	//직원 수정
	@Override
	public int updateMember(MemberDto m) {
		int result = -1;
		String query = "UPDATE M SET M_NAME = ?, M_PASS =?, M_DEPT = ?, M_POS = ? WHERE M_ID = ?";
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getM_NAME());
			pstmt.setString(2, m.getM_PASS());
			pstmt.setString(3, m.getM_DEPT());
			pstmt.setString(4, m.getM_POS());
			pstmt.setString(5, m.getM_ID());

			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				//e1.printStackTrace();
			}
		} finally {
			close();
		}

		return  result;
	}


	//연결 해제
	public void close() {

		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
		}

	}//close end



}//class end
