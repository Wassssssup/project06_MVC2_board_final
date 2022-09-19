package com.board.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/boardlist.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * @Override public void init() throws ServletException { // TODO Auto-generated
	 * method stub System.out.println("서블릿 초기화"); }
	 * 
	 * @Override public void destroy() { // TODO Auto-generated method stub
	 * System.out.println("서블릿 소멸"); }
	 * 
	 * @Override protected void service(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { // TODO Auto-generated method
	 * stub System.out.println("서블릿 서비스 진행"); }
	 */
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// 화면에 보여질 게시글의 개수
			int pageSize=10;
		
			String pageNum=request.getParameter("pageNum");
			
			// 만약 처음이다
			if(pageNum == null){
				pageNum="1";
			}
			
			int count=0; //전체 글
			int number=0; // 페이지 넘버링
			
			// 현재 보고자하는 페이지 숫자를 지정
			int currentPage=Integer.parseInt(pageNum);
			
			BoardDAO bdao=new BoardDAO();
			
			// 전체 게시글의 개수
			count=bdao.getAllCount();
			
			// 현재 페이지에 보여줄 시작 번호와 끝번호를 설정
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			Vector<BoardDTO> vec=bdao.getAllBoard(startRow, endRow);
			
			number=count-(currentPage-1)*pageSize;
			
			//수정 삭제 시 비밀번호 틀림.
			String msg = request.getParameter("msg");
			
			//BoardList.jsp에 값전달
			request.setAttribute("vec", vec);
			request.setAttribute("number", number);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/BoardList.jsp").forward(request, response);
				
	}

}
