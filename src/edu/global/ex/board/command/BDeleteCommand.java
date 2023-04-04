package edu.global.ex.board.command;

import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.global.board.dao.BDao;
import edu.global.board.vo.BoardVO;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
        String bid = request.getParameter("bid");
        
        
        BDao dao = new BDao(); // Board DAO »£√‚         
        dao.delete(bid);
  
		
	}
}

