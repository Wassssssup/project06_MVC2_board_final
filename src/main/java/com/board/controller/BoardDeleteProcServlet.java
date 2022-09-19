package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;

/**
 * Servlet implementation class BoardDeleteProcServlet
 */
@WebServlet("/boardDeleteProc.do")
public class BoardDeleteProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteProcServlet() {
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
		
		int num = Integer.parseInt(request.getParameter("num"));
		//화면 상의 입력 패스워드
		String pass = request.getParameter("password");
		
		//데이터베이스에 있는 패스워드
		BoardDAO bdao = new BoardDAO();
		String password = bdao.getPass(num);
		
		if(pass.equals(password)) {
			bdao.deleteBoard(num);
			
			request.setAttribute("msg","삭제 되었습니다.");
			RequestDispatcher ds = request.getRequestDispatcher("boardlist.do");
			ds.forward(request, response);
		}else {
			request.setAttribute("msg","비밀번호가 일치하지 않습니다.");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('비밀번호가 맞지 않습니다. 다시 입력해주세요.'); history.go(-1);;</script>"); 
			writer.close();
		}
	}

}
