package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

/**
 * Servlet implementation class BoardReWriteProcServlet
 */
@WebServlet("/boardReWriteProc.do")
public class BoardReWriteProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReWriteProcServlet() {
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
		
		request.setCharacterEncoding("UTF-8");
		
		BoardDTO bdto = new BoardDTO();
		bdto.setWriter(request.getParameter("writer"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setEmail(request.getParameter("email"));
		bdto.setPassword(request.getParameter("password"));
		bdto.setContent(request.getParameter("content"));
		
		//받아온 값들을 jsp에 던져서 히든 값에 넣어서 답글이 제자리로 들어가게끔 해줘야함.
		bdto.setRef(Integer.parseInt(request.getParameter("ref")));
		bdto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		bdto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		
		BoardDAO bdao = new BoardDAO();
		
		bdao.reWriteBoard(bdto);
		
		RequestDispatcher ds=request.getRequestDispatcher("boardlist.do");
		ds.forward(request, response);
	}

}
