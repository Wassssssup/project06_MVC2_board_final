package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.board.dto.BoardDTO;

public class BoardDAO {
	//DB연결을 계속 사용하니 전역변수로 설정
	ResultSet rs;
	Connection conn=null;
	PreparedStatement pstmt;
	
	public BoardDAO() {
		
	}
	//인스턴스를 받아서 DB 연결하는 방법 사용
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	Connection getConnection() {
		Context initContext;
		
		try {
			initContext=new InitialContext();
			DataSource ds=(DataSource)initContext.lookup("java:/comp/env/jdbc/Oracle11g");
			conn=ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/* 전체 게시글 개수 리턴 메서드 **/
	public int getAllCount() {
		int count=0;
		
		try {
			conn=getConnection();
			String sql="select count(*) from reboard";
			pstmt = conn.prepareStatement(sql);
			//쿼리 실행 후 결과 리턴
			rs=pstmt.executeQuery();
			//count은 결과값 1개 이므로 while 사용할 필요x
			if(rs.next()) {
				count = rs.getInt(1);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/*화면에 데이터 10개씩 추출 후 리턴하는 메서드**/
	public Vector<BoardDTO> getAllBoard(int startRow, int endRow){
		Vector<BoardDTO> vec = new Vector<>(); //벡터 타입으로 리턴하기 위해
		
		try {
			conn=getConnection();
			String sql="select * from(select A.*,Rownum Rnum from(select * from reboard order by ref desc,re_step asc)A)"+"where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			
			//?값 대입
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			// 데이터 개수를 모르기 때문에 while문 사용
			while(rs.next()) {
				//데이터 패키징 (DTO클래스를 이용해서 뽑아온다고 생각)
				BoardDTO bdto = new BoardDTO();
				
				bdto.setNum(rs.getInt(1));
				bdto.setWriter(rs.getString(2));
				bdto.setEmail(rs.getString(3));
				bdto.setSubject(rs.getString(4));
				bdto.setPassword(rs.getString(5));
				bdto.setReg_date(rs.getDate(6).toString());
				bdto.setRef(rs.getInt(7));
				bdto.setRe_step(rs.getInt(8));
				bdto.setRe_level(rs.getInt(9));
				bdto.setReadcount(rs.getInt(10));
				bdto.setContent(rs.getString(11));
				
				vec.add(bdto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vec;
	}
	
	/* 하나의 게시글을 저장하는 메서드**/
	public void insertBoard(BoardDTO bdto) {
		
		int ref=0; // 부모와 자식을 구분할 수 있는 그룹
		int re_step=1; 
		int re_level=1; //새글
		
		try {
			conn=getConnection();
			// 가장 큰 그룹 값을 가져와서 +1을 통해 다음 그룹을 만들어주는 쿼리
			String refsql="select max(ref) from reboard";
			pstmt=conn.prepareStatement(refsql);
			rs=pstmt.executeQuery();
			
			//마찬가지로 max는 단일행함수이기 때문에 if를 통해 값을 넣는다.
			
			if(rs.next()) {
				ref=rs.getInt(1)+1; // 최대 그룹을 만들기 위함.
			}
			
			String sql="insert into reboard values(board_seq.nextval,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,bdto.getWriter());
			pstmt.setString(2,bdto.getEmail());
			pstmt.setString(3,bdto.getSubject());
			pstmt.setString(4,bdto.getPassword());
			pstmt.setInt(5,ref);
			pstmt.setInt(6,re_step);
			pstmt.setInt(7,re_level);
			pstmt.setString(8,bdto.getContent());
			
			//쿼리 실행
			pstmt.executeUpdate();
			//자원 반납
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 게시글을 선택했을때 num값을 이용하여 하나의 게시글을 볼 때 사용.
	/* 하나의 게시글을 리턴하는 메서드**/
	public BoardDTO getOneBoard(int num) {
		BoardDTO bdto=new BoardDTO();
		
		try {
			conn=getConnection();
			
			//조회수 증가 (num값 = key값)
			String readsql="update reboard set readcount=readcount+1 where num=?";
			pstmt=conn.prepareStatement(readsql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			//게시글에 대한 정보 리턴 (num값 = key값)
			String sql="select * from reboard where num=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			
			rs=pstmt.executeQuery();
			
			//하나의 게시글을 조회하기때문
			if(rs.next()) {
				bdto.setNum(rs.getInt(1));
				bdto.setWriter(rs.getString(2));
				bdto.setEmail(rs.getString(3));
				bdto.setSubject(rs.getString(4));
				bdto.setPassword(rs.getString(5));
				bdto.setReg_date(rs.getDate(6).toString());
				bdto.setRef(rs.getInt(7));
				bdto.setRe_step(rs.getInt(8));
				bdto.setRe_level(rs.getInt(9));
				bdto.setReadcount(rs.getInt(10));
				bdto.setContent(rs.getString(11));
			}
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bdto;
	}
	
	/*답변글이 저장되는 메서드**/
	
	public void reWriteBoard(BoardDTO bdto) {
		int ref=bdto.getRef();
		int re_step=bdto.getRe_step();
		int re_level=bdto.getRe_level();
	
		try {
			conn=getConnection();
			
			//※ 부모글 보다 큰 re_level의 값을 전부 1씩 증가
			String levelsql="update reboard set re_level=re_level+1 where ref=? and re_level > ?";
			pstmt=conn.prepareStatement(levelsql);
			pstmt.setInt(1,ref);
			pstmt.setInt(2,re_level);
			pstmt.executeUpdate();
			
			//답변글 데이터 저장
			String sql="insert into reboard values(board_seq.nextval,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, bdto.getWriter());
			pstmt.setString(2, bdto.getEmail());
			pstmt.setString(3, bdto.getSubject());
			pstmt.setString(4, bdto.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step+1); // 답글이기때문에 기존 부모글 +1
			pstmt.setInt(7, re_level+1); // 답글이기 때문에 기존 부모글 +1
			pstmt.setString(8,bdto.getContent());
			
			pstmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//삭제시 하나의 게시글을 리턴할 때 사용
	/*조회수 증가 없이 하나의 게시글 리턴 **/
		public BoardDTO getOneUpdateBoard(int num) {
			BoardDTO bdto=new BoardDTO();
			
		try {
			conn=getConnection();
			String sql="select * from reboard where num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				bdto.setNum(rs.getInt(1));
				bdto.setWriter(rs.getString(2));
				bdto.setEmail(rs.getString(3));
				bdto.setSubject(rs.getString(4));
				bdto.setPassword(rs.getString(5));
				bdto.setReg_date(rs.getDate(6).toString());
				bdto.setRef(rs.getInt(7));
				bdto.setRe_step(rs.getInt(8));
				bdto.setRe_level(rs.getInt(9));
				bdto.setReadcount(rs.getInt(10));
				bdto.setContent(rs.getString(11));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bdto;
	}
		
		//게시글 수정시 pass비교할 때 사용
		/*num값을 이용하여 pass값을 불러오는 메서드**/
		public String getPass(int num) {
			String pass="";
			
			try {
				conn=getConnection();
				
				String sql="select password from reboard where num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					pass=rs.getString(1);
				}
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return pass;
		}	
		
		/*하나의 게시글을 수정하는 메서드**/
		// BoardUpdateProcServlet에서 3개의 변수를 받아서 새로 세팅하기 위해 메서드 추가
		public void updateBoard(String subject, String content, int num) {
			
			try {
				
				conn=getConnection();
				
				String sql="update reboard set subject=?,content=? where num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,subject);
				pstmt.setString(2,content);
				pstmt.setInt(3,num);
				
				pstmt.executeUpdate();
				
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void deleteBoard(int num) {
			
			try {
				conn=getConnection();
				
				String sql="delete from reboard where num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}
