package edu.global.ex.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.global.ex.board.command.BCommand;
import edu.global.ex.board.command.BContentCommand;
import edu.global.ex.board.command.BDeleteCommand;
import edu.global.ex.board.command.BListCommand;
import edu.global.ex.board.command.BModifyCommand;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		actionDo(request, response);

	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // 이 클래스 안에서만 써먹을 수 있음 = private

		request.setCharacterEncoding("UTF-8");

		String viewPage = null;
		BCommand command = null;

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = uri.substring(contextPath.length());

		System.out.println("uri :" + uri);
		System.out.println("contextPath :" + contextPath);
		System.out.println("com :" + com);

		// http://localhost:8282/suvrvlet_ajw_board/content_view.do?bid=1

		if (com.equals("/list.do")) { // list.do로 치고 들어오게 되면 해당 request 객체 안에 게시판 글들을 가지고 옴.
			command = new BListCommand(); // 다형성 적용
			command.execute(request, response); // request안에다가 게시물 내용 가지고옴
			viewPage = "list.jsp";

		} else if (com.equals("/content_view.do")) {// http://localhost:8282/suvrvlet_ajw_board/content_view.do?bid=1}

			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";

		} else if (com.equals("/modify.do")) {
			System.out.println("/modify.do");
			
			command = new BModifyCommand();
			command.execute(request, response);
			viewPage = "list.do";
			
		}  else if (com.equals("/delete.do")) {
			System.out.println("/delete.do.."); // if문 타는지 확인
			
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do";
			
		}  

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); // list.jsp가 viewPage안에 들어감.
		dispatcher.forward(request, response); // request안에 게시글이 들어있고.. 이 페이지를 forward시켜서 view에 뿌려줌.

	}
}
