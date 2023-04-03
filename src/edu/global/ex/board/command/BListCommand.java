package edu.global.ex.board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.global.board.dao.BDao;
import edu.global.board.vo.BoardVO;

public class BListCommand implements BCommand {

	@Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) {

	      BDao dao = new BDao(); // Board DAO 호출
	      
	      List<BoardVO> vos = dao.list(); // DAO 안의 list()를 호출하여 BoardVO의 List 객체 생성
	      
	      request.setAttribute("boards", vos); // 요청하여 갖고 오기
	   }
	   
	}