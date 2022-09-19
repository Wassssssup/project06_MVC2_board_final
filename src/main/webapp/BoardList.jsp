<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<form method="post">
	<h2>전체 게시글 보기</h2>
	<table width="700" border="1" bgcolor="skyblue">
		<tr height="40">
			<td align="right" colspan="5">
				<input type="button" value="글쓰기" onclick="location.href='BoardWriteForm.jsp'">
			</td>
		</tr>
		<tr height="40">
			<td width="50" align="center">번호</td>
			<td width="320" align="center">제목</td>
			<td width="100" align="center">작성자</td>
			<td width="150" align="center">작성일</td>
			<td width="80" align="center">조회수</td>
		</tr>
		<%-- <c:set var="number" value="${number }" /> --%>
		<c:forEach var ="bdto" items="${vec }">
		
		<tr height = "40">
		<td width="50" align="center">${bdto.num }</td>
		<td width="320" align="left">
			<c:if test="${bdto.re_step>1 }">
				<c:forEach var="j" begin="1" end="${(bdto.re_step-1)*5 }">
				&nbsp;
				</c:forEach>
			</c:if>
			<a href="boardinfo.do?num=${bdto.num }" style="text-decoration:none;">${bdto.subject }</a>
		</td>
		<td width = "100" align="left">${bdto.writer }</td>
		<td width="150" align="center">${bdto.reg_date }</td>
		<td width="80" align="center">${bdto.readcount }</td>
		</tr>
		<%-- <c:set var="number" value="${number-1 }"></c:set>  --%> 
		</c:forEach>
	</table>
	
	<!-- 페이지 카운트 처리 -->
	<c:if test="${count>0 }">
		<c:set var="pageCount" value="${count/pageSize + (count%pageSize == 0 ? 0 : 1) }" />
		<c:set var="startPage" value="${1 }"></c:set>
		
	<!-- 10페이지 기준 시작페이지 설정 -->
		<c:if test="currentPage%10 != 0">
			<c:set var="startPage" value="${(currentPage/10)*10+1}"></c:set>
		</c:if>
		<c:if test="currentPage%10 == 0">
			<c:set var="startPage" value="${(currentPage-9)}"></c:set>
		</c:if>
		<!-- 화면에 보일 페이지 표현 -->
		<c:set var="pageBlock" value="${10 }"/>
		<c:set var="endPage" value="${startPage+pageBlock-1 }"/>
		
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }"/>
		</c:if>
		
		<!-- '이전' 링크 -->
		<c:if test="${startPage > 10 }">
			<a href="BoardList.do?pageNum=${startPage-10 }">[이전]</a>
		</c:if>
		
		<!-- 숫자 반복 후 링크 -->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="boardlist.do?pageNum=${i }" style="text-decoration:none;">[${i }]</a>
		</c:forEach>
		
		<!-- '이전' 링크 -->
		<c:if test="${endPage < pageCount }">
			<a href="boardlist.do?pageNum=${startPage+10 }">[다음]</a>
		</c:if>
	</c:if>
</form>
</div>
</body>
</html>








