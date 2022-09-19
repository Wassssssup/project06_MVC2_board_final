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
 * Servlet implementation class BoardWriteProcServlet
 */
@WebServlet("/boardWriteProc.do")
public class BoardWriteProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteProcServlet() {
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
		//하는 이유 : 기존에 useBean을 통해 property="*" 했던 행위
		BoardDTO bdto = new BoardDTO();
		bdto.setWriter(request.getParameter("writer"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setEmail(request.getParameter("email"));
		bdto.setPassword(request.getParameter("password"));
		bdto.setContent(request.getParameter("content"));
		
		BoardDAO bdao = new BoardDAO();
		
		bdao.insertBoard(bdto);
		
		RequestDispatcher ds=request.getRequestDispatcher("boardlist.do");
		ds.forward(request, response);
		
	}

}
