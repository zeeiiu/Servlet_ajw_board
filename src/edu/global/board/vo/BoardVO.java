package edu.global.board.vo;

import java.sql.Timestamp;

/*
�̸�       ��?       ����             
-------- -------- -------------- 
BID      NOT NULL NUMBER(4)      
BNAME             VARCHAR2(50)   
BTITLE            VARCHAR2(100)  
BCONTENT          VARCHAR2(1000) 
BDATE             DATE           
BHIT              NUMBER(4)      
BGROUP            NUMBER(4)      
BSTEP             NUMBER(4)      
BINDENT           NUMBER(4)      
*/

public class BoardVO {
   private int bid;
   private String bname;
   private String btitle;
   private String bcontent;
   private Timestamp bdate;
   private int bhit;
   private int bgroup;
   private int bstep;
   private int bindent;

   public BoardVO() {}
   
   public BoardVO(int bid, String bname, String btitle, String bcontent, Timestamp bdate, int bhit, int bgroup,
         int bstep, int bindent) {
      this.bid = bid;
      this.bname = bname;
      this.btitle = btitle;
      this.bcontent = bcontent;
      this.bdate = bdate;
      this.bhit = bhit;
      this.bgroup = bgroup;
      this.bstep = bstep;
      this.bindent = bindent;
   }

   public int getBid() {
      return bid;
   }

   public void setBid(int bid) {
      this.bid = bid;
   }

   public String getBname() {
      return bname;
   }

   public void setBname(String bname) {
      this.bname = bname;
   }

   public String getBtitle() {
      return btitle;
   }

   public void setBtitle(String btitle) {
      this.btitle = btitle;
   }

   public String getBcontent() {
      return bcontent;
   }

   public void setBcontent(String bcontent) {
      this.bcontent = bcontent;
   }

   public Timestamp getBdate() {
      return bdate;
   }

   public void setBdate(Timestamp bdate) {
      this.bdate = bdate;
   }

   public int getBhit() {
      return bhit;
   }

   public void setBhit(int bhit) {
      this.bhit = bhit;
   }

   public int getBgroup() {
      return bgroup;
   }

   public void setBgroup(int bgroup) {
      this.bgroup = bgroup;
   }

   public int getBstep() {
      return bstep;
   }

   public void setBstep(int bstep) {
      this.bstep = bstep;
   }

   public int getBindent() {
      return bindent;
   }

   public void setBindent(int bindent) {
      this.bindent = bindent;
   }
   
   
}