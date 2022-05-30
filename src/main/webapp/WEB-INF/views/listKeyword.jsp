<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>직원 목록</h2>
	<a href="writeForm">입력</a>
	<p>uptCnt 수정시 전달 Message : ${uptCnt }</p>
	
	<form action="listKeyword">
		<input type="hidden" name="currentPage" value="${pg.currentPage }">
		<select name="search">
			<option value="s_all">전체조회</option>
			<option value="s_job">업무조회</option>
			<option value="s_ename">이름조회</option>
		</select>
		<input type="text" name="keyword" value="${keyword }" placeholder="keyword를 입력하세요">
		<button type="submit">keyword 검색</button>
	</form>
	
	
	<c:set var="num" value="${pg.total-pg.start+1 }"></c:set>
	<table>
		<tr><th>번호</th><th>사번</th><th>이름</th><th>업무</th><th>급여</th></tr>
		<c:forEach var="emp" items="${listEmp }">
			<tr>
				<td>${num }</td><td>${emp.empno }</td>
				<td><a href="detail?empno=${emp.empno }">${emp.ename}</a></td>
				<td>${emp.job }</td><td>${emp.sal }</td>
			</tr>
			<c:set var="num" value="${num-1 }"></c:set>
		</c:forEach>
	</table>
	<c:if test="${pg.startPage > pg.pageBlock }">
		<a href="listKeyword?currentPage=${pg.startPage-pg.pageBlock }&search=s_all&keyword=${keyword}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
		<a href="listKeyword?currentPage=${i }&search=s_all&keyword=${keyword}">[${i}]</a>
	</c:forEach>
	<c:if test="${pg.endPage < pg.totalPage }">
		<a href="listKeyword?currentPage=${pg.startPage+pg.pageBlock }&search=s_all&keyword=${keyword}">[다음]</a>
	</c:if>
</body>
</html>