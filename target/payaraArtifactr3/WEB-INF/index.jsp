<html>
<body>
<h2>${HelloMessage}</h2>
<div>
    <h4>Create a Person with role user.</h4>
    <form method="post" action="${pageContext.request.contextPath}/person">
        <p>Name</p>
        <input name="name" value="${name}">
        <p>Username</p>
        <input name="register_user" value="${register_user}">
        <p>Password</p>
        <input type="password" name="register_pw" value="${register_pw}">
        <input type="submit" value="Register" />
    </form>
    <h4>Login</h4>
    <form method="POST" action="${pageContext.request.contextPath}/rest/personr/login">
        <p>Username</p>
        <input name="username" value="${username}">
        <p>Password</p>
        <input type="password" name="password" value="${password}">
        <p>Rememberme</p>
        <input type="text" name="rememberme" value="${rememberme}">

        <input type="submit" name="login_user" value="Login" />
    </form>
    <h4>!---!---! Admin only area !---!---!</h4>
    <h3>Delete account</h3>
    <form method="post" action="${pageContext.request.contextPath}/rest/person">
        <p>id</p>
        <input type=text name="id">
        <input type="submit" value="submit" />
    </form>
</div>
<div>
    <p><a href="${pageContext.request.contextPath}/second">Get List of users.</a></p>
</div>

</body>
</html>
