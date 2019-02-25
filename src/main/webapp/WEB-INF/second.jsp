<%--
  Created by IntelliJ IDEA.
  User: redmar
  Date: 18-2-19
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Second Page Page!</title>
</head>
<body>

<h1>Person List</h1>
<ul>
    <c:forEach items="${personList}" var="person">
        <li>${person.name}</li>
    </c:forEach>
</ul>

<p><a href="${pageContext.request.contextPath}">Go to the first page.</a></p>

</body>
</html>
