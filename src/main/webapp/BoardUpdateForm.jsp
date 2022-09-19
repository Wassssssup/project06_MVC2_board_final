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
	<h2>게시글 수정</h2>
	<form action="boardUpdateProc.do" method="post">
      <table width="600" border="1" bgcolor="skyblue">
         <tr height="40">
         	<td width="120" align="center">작성자</td>
         	<td width="180" align="center">${bdto.writer}</td>
         	<td width="120" align="center">작성일</td>
         	<td width="180" align="center">${bdto.reg_date}</td>
		</tr>
	    <tr height="40">
         	<td width="120" align="center">제목</td>
         	<td width="480" colspan="3">&nbsp;<input type="text" name="subject" value="${bdto.subject }" size="60"></td>
        </tr>
        <tr height="40">
         	<td width="120" align="center">패스워드</td>
         	<td width="480" colspan="3">&nbsp;<input type="password" name="password" size="60"></td>
       	</tr>
       	<tr height="40">
         	<td width="120" align="center">글내용</td>
         	<td width="480" colspan="3"><textarea rows="10" cols="60" name="content" align="left">${bdto.content}</textarea></td>
       	</tr>
       	<tr height="40">
         	<td colspan="4" align="center">
         		<input type="hidden" name="num" value="${bdto.num }">
         		<!-- password 비교를 위해 추가 -->
         		<input type="hidden" name="pass" value="${bdto.password}">
         		<input type="submit" value="글수정">&nbsp;&nbsp;
         		<input type="button" onclick="location.href='boardlist.do'" value="전체 글 보기">
         	</td>
        </tr>
      </table>
   </form>

</div>
</body>
</html>