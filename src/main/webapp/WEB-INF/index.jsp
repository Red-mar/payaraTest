<html>
<body>
<h2>${HelloMessage}</h2>
<div>
    <h4>Create a Person</h4>
    <form method="post" action="${pageContext.request.contextPath}/person">
        <input name="name" value="${name}">
        <input type="submit" value="Submit complete person create form." />
    </form>
</div>
<div>
    <p><a href="${pageContext.request.contextPath}/second">Go to the second page.</a></p>
</div>

</body>
</html>
