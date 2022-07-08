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

	<jsp:useBean id="dao" class="testBoard.TestDAO" />
	
	<!-- 총 글의 갯수를 얻어옴 -->
	<c:set var="totalPage" value="${dao.getTotalPage() }"/>
	
	<!-- pageCount 메소드 가져오기 -->
	<c:set var="pc" value="${dao.pagingNum(param.start) }" />
	 
	<c:set var="list" value="${dao.list(pc.startPage, pc.endPage) }"/>
	
	<table border="1">
		<tr>
			<th>번호</th> <th>제목</th>
			<th>등록날짜</th> <th>조회수</th>
		</tr>
		
		<c:choose>
			<c:when test="${list.size() == 0 }">
				<tr>
					<th colspan="4"> 데이터 없음</th>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach var="dto" items="${list }">
				<tr>
					<td>${dto.num }</td>
					<td> 
						<a href="count.jsp?num=${dto.num }">
							${dto.title } 
						</a>
					</td>
					<td>${dto.pdate }</td> <td>${dto.count }</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td colspan="4">
				
				<c:choose>
					<c:when test="${param.start == null }">
						<c:set var="s" value="1" />
					</c:when>
					<c:otherwise>
						<c:set var="s" value="${param.start }" />
					</c:otherwise>
				</c:choose>
				
				<!-- 이전 버튼 -->
				<c:choose>
					<c:when test="${s > 1}">
						<button type="button" 
							onclick="location.href='list.jsp?start=${s-1}'">이전</button>
					</c:when>
					<c:otherwise>
						<button disabled>이전</button>
					</c:otherwise>
				</c:choose>
				
				<!-- 페이지 넘버링 ( end에는 총 페이지 값이 와야 합니다. end=달러{page} -->
				<c:forEach var="cnt" begin="1" end="${pc.totEndPage }" step="1">
					<a href="list.jsp?start=${cnt }">
						${cnt}
					</a>
				</c:forEach>
				
				<!-- 다음 버튼 -->
				<c:choose>
					<c:when test="${s < pc.totEndPage }">
						<button type="button" 
							onclick="location.href='list.jsp?start=${s+1}'">다음</button>
					</c:when>
					<c:otherwise>
						<button disabled>다음</button>
					</c:otherwise>
				</c:choose>
			
				${s } / ${pc.totEndPage }
				<input type="button" value="글 등록"
					   onclick="location.href='regForm.jsp'">
			</td>
		</tr>
		
	</table>

</body>
</html>




