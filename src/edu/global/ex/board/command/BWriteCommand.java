package edu.global.ex.board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.global.board.dao.BDao;


public class BWriteCommand implements BCommand {

	@Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		
        String bname = request.getParameter("bname");
        String btitle = request.getParameter("btitle");
        String bcontent = request.getParameter("bcontent");
   
        
   
         BDao dao = new BDao(); // Board DAO »£√‚         
        dao.write(bname, btitle, bcontent);
		
		
	   }
	   
	}