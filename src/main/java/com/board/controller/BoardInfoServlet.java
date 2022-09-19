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
 * Servlet implementation class BoardInfoServlet
 */
@WebServlet("/boardinfo.do")
public class BoardInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInfoServlet() {
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
		//키값이 num이기 때문에 boardlist.jsp에서 넘긴 num값을 받아온다.
		int num = Integer.parseInt(request.getParameter("num").trim());
		//num값을 이용해 게시판을 가져와야함.
		BoardDAO bdao = new BoardDAO();
		//BoardDTO 타입이기 때문에 해당 객체에 저장해서 set해야함.
		BoardDTO bdto = bdao.getOneBoard(num);
		request.setAttribute("bdto", bdto);
		
		RequestDispatcher ds = request.getRequestDispatcher("BoardInfo.jsp");
		ds.forward(request, response);
		
		
	}

}
