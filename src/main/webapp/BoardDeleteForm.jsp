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
	<form action="boardDeleteProc.do" method="post">
		<table width="600" border="1" bgcolor="skyblue">
			<tr height="40">
				<td width="120" align="center">작성자</td>
				<td width="180" align="center">${bdto.writer }</td>
				<td width="120" align="center">작성일</td>
				<td width="120" align="center">${bdto.reg_date }</td>
			</tr>
			<tr height="40">
				<td width="120" align="center">제목</td>
				<td width="180" align="center">${bdto.subject }</td>
			</tr>
			<tr height="40">
				<td width="120" align="center">패스워드</td>
				<td align="left" colspan="3"><input type="password" name="password"></td>
			</tr>
			<tr height="40">
				<td align="center" colspan="4">
				<input type="hidden" name="num" value="${bdto.num }">
				<input type="hidden" name="password" value="${bdto.password }">
				<input type="submit" value="글 삭제">&nbsp;&nbsp;
				<input type="button" onclick="location.href='boardlist.do'" value="목록보기">
			</tr>
		</table>
	</form>
</div>
</body>
</html>