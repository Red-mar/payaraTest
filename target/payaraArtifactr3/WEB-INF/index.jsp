<html>
<body>
<h2>${HelloMessage}</h2>
<div>
    <h4>Create a Person with role user.</h4>
    <form method="post" action="${pageContext.request.contextPath}/person">
        <input name="name" value="${name}">
        <input name="register_user" value="${register_user}">
        <input name="register_pw" value="${register_pw}">
        <input type="submit" value="Register" />
    </form>
</div>
<div>
    <p><a href="${pageContext.request.contextPath}/second">Get List of users.</a></p>
</div>

</body>
</html>
