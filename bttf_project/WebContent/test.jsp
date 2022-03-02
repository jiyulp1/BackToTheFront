<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:if test="${true }">
		<a>추천true</a>
		</c:if>
		<c:if test="${true }">
		<a>추천false</a>
		</c:if>
		
		<c:choose>
			<c:when test="${false }">
				<a>추천true</a>
			</c:when>
			<c:otherwise>
				<a>추천false</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>