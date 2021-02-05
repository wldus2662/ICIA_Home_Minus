package com.dto;

public class MemberDto {
   private String M_ID;
   private String M_PASS;
   private String M_NAME;
   private String M_DEPT;
   private String M_POS;
   
   
   public String getM_ID() {
      return M_ID;
   }
   public void setM_ID(String m_ID) {
      M_ID = m_ID;
   }
   public String getM_PASS() {
      return M_PASS;
   }
   public void setM_PASS(String m_PASS) {
      M_PASS = m_PASS;
   }
   public String getM_NAME() {
      return M_NAME;
   }
   public void setM_NAME(String m_NAME) {
      M_NAME = m_NAME;
   }
   public String getM_DEPT() {
      return M_DEPT;
   }
   public void setM_DEPT(String m_DEPT) {
      M_DEPT = m_DEPT;
   }
   public String getM_POS() {
      return M_POS;
   }
   public void setM_POS(String m_POS) {
      M_POS = m_POS;
   }
   
   @Override
   public String toString() {
      String str = "ID : " + M_ID + "\n"
            + "이름 : " + M_NAME + " \n"
            + "부서 : " + M_DEPT + " \n"
            + "직급 : " + M_POS;
      return str;
   }

}