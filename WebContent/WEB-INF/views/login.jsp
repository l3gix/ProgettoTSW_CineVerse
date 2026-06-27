<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/login.css">
    <title>Login</title>
</head>
<body>
   <header>
        <p>Accedi</p>
    </header>
    <div id="container">
        <section id="login">
            <form action="Login" method="post">
                <label for="email">Email</label>
                <input type="email" placeholder="email" name="email" id="email">

                <label for="password">Password</label>
                <input type="password" placeholder="password" id="password">
                <button type="submit">Accedi</button>
            </form>

            <div id="register">
                <p>Non hai account ?</p>
                <button onclick="window.location.href='<%=request.getContextPath()%>/WelcomeRegister'"
                >Registrati &#8594</button>
            </div>

        </section>
    </div>
</body>
</html>