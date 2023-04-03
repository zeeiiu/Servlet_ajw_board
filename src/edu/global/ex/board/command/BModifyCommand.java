package edu.global.ex.board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.global.board.dao.BDao;


public class BModifyCommand implements BCommand {

	@Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("BModifyCommand()..");
		
        String bid = request.getParameter("bid");
        String bname = request.getParameter("bname");
        String bcontent = request.getParameter("bcontent");
        String btitle = request.getParameter("btitle");
        
   
         BDao dao = new BDao(); // Board DAO »£√‚         
         dao.modify(bid, bname,btitle,bcontent);  
		
		
	   }
	   
	}