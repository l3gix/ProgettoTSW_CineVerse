<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/register.css">
    <title>Register</title>
</head>
<body>
   <header>
        <p>Registrati </p>
    </header>
    <div id="container">
        <section id="login">
            <form action="Register" method="post">
                <label for="email">Email</label>
                <input type="email" placeholder="email" name="email" id="email">

                <label for="password">Password</label>
                <input type="password" placeholder="password" id="password">
                
                <label for="nome">Nome</label>
                <input type="text" name="nome" placeholder="nome" id="nome">
                
                <label for="cognome">Cognome</label>
                <input type="text" name="cognome" placeholder="cognome" id="cognome">
                
                <label for="telefono">Telefono</label>
                <input type="text" name="telefono" placeholder="telefono" id="telefono">
                
                <button type="submit">Registrati</button>
            </form>

            <div id="register">
                <p>Hai gia un account ?</p>
                <button onclick="window.location.href='<%=request.getContextPath()%>/WelcomeLogin'"
                >Accedi &#8594</button>
            </div>

        </section>
    </div>
</body>
</html>