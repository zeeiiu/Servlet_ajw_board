package edu.global.ex.board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.global.board.dao.BDao;
import edu.global.board.vo.BoardVO;

public class BListCommand implements BCommand {

	@Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) {

	      BDao dao = new BDao(); // Board DAO ȣ��
	      
	      List<BoardVO> vos = dao.list(); // DAO ���� list()�� ȣ���Ͽ� BoardVO�� List ��ü ����
	      
	      request.setAttribute("boards", vos); // ��û�Ͽ� ���� ����
	   }
	   
	}