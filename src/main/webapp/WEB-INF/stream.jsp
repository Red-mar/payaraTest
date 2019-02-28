<%--
  Created by IntelliJ IDEA.
  User: redmar
  Date: 27-2-19
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h3>Play the stream</h3>
    <audio controls="">
         <source src="http://localhost:8000/test" type="audio/mp3">
    </audio>

    <h3>Current Queue</h3>
    <c:forEach items="${queueList}" var="song">
        <li>${song.name}</li>
    </c:forEach>

    <h4>Add Song to database</h4>
    <form method="post" action="${pageContext.request.contextPath}/stream">
        <input name="songName" value="${songName}">
        <input name="url" value="${url}">
        <input type="submit" name="addSongToDatabase" value="Submit song to database." />
     </form>

    <h4>Add song to queue</h4>
    <form method="post" action="${pageContext.request.contextPath}/stream">
        <input name="id" value="${id}">
        <input type="submit" name="addSongToQueue" value="Add Song to queue." />
    </form>

    <h4>Start Streaming</h4>
    <form method="post" action="${pageContext.request.contextPath}/stream">
        <input type="submit" name="start" value="Start" />
    </form>

    <h3>Available Songs</h3>
    <c:forEach items="${songList}" var="song">
        <li>Name: ${song.name} Url: ${song.url}</li>
    </c:forEach>

</body>
</html>
