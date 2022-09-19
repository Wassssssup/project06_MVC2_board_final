<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>게시글 보기</h2>
		<table width="600" border="1" bgcolor="skyblue">
			<tr height="40">
				<td width="100" align="center">글번호</td>
				<td width="180" align="left">${bdto.num}</td>
				<td width="120" align="center">조회수</td>
				<td width="180" align="center">${bdto.readcount}</td>
			</tr>
			<tr height="40">
				<td width="100" align="center">작성자</td>
				<td width="180" align="left">${bdto.writer}</td>
				<td width="120" align="center">작성일</td>
				<td width="180" align="center">${bdto.reg_date}</td>
			</tr>
			<tr height="40">
				<td width="120" align="center">이메일</td>
				<td colspan="3" align="center">${bdto.email}</td>
			</tr>
			<tr height="40">
				<td width="120" align="center">제목</td>
				<td colspan="3" align="center">${bdto.subject}</td>
			</tr>
			<tr height="80">
				<td width="120" align="center">글 내용</td>
				<td colspan="3" align="center">${bdto.content}</td>
			</tr>
			<tr height="40">
				<td align="center" colspan="4">
					<input type="button" value="답글쓰기" onclick="location.href='boardReWrite.do?num=${bdto.num }&ref=${bdto.ref }&re_step=${bdto.re_step }&re_level=${bdto.re_level }'">
					<input type="button" value="수정하기" onclick="location.href='boardUpdate.do?num=${bdto.num }'">
					<input type="button" value="삭제하기" onclick="location.href='boardDelete.do?num=${bdto.num }'">
					<input type="button" value="목록보기" onclick="location.href='BoardList.jsp'">
				</td>
			</tr>
		</table> 	
</div>
</body>
</html>