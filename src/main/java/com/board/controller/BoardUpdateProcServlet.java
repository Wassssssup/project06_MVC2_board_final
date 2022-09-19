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

/**
 * Servlet implementation class BoardUpdateProcServlet
 */
@WebServlet("/boardUpdateProc.do")
public class BoardUpdateProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateProcServlet() {
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
		/*
		 * 해당 서블릿에서는 해야할 일 : 1. num값으로 업데이트 메서드 사용 
		 * 2. BoardUpdate.jsp에서 히든 처리하여 받아온 패스워드 비교
		 */
		request.setCharacterEncoding("UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String getpassword = request.getParameter("pass");
		String pass = request.getParameter("password");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		if(pass.equals(getpassword)) {
			BoardDAO bdao=new BoardDAO();
			bdao.updateBoard(subject,content,num);
			
			request.setAttribute("msg", "수정되었습니다.");
			
			RequestDispatcher ds=request.getRequestDispatcher("boardlist.do");
			ds.forward(request, response);
		}else {
			//서블릿에서 얼럿을 띄우기 위한 방법
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('비밀번호가 맞지 않습니다. 다시 입력해주세요.'); history.go(-1);;</script>"); 
			writer.close();
		}
	}

}
