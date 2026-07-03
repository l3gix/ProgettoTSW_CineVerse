<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/login.css">
    <title>Login</title>
    <script src="script/validator.js"></script>
</head>
<body>
   <header>
        <p>Accedi</p>
    </header>
    <div id="container">
        <section id="login">
            <form action="Login" method="post">
                <label for="email">Email</label>
                <input type="email" placeholder="email" name="email" id="email" onchange="validateFormElem(this,document.getElementById('errorEmail'),emailErrorMessage)"><p id="errorEmail"></p>

                <label for="password">Password</label>
                 <input type="password" placeholder="password" name="password" id="password" required pattern="^(?=.*[A-Z]).{8}$" onchange="validateFormElem(this,document.getElementById('errorPassword'),passwordErrorMessage)"><p id="errorPassword"></p>
                <%
			        String errore = (String) request.getAttribute("errore");
			        if (errore != null) {
			    %>
			        <p style="color:red"><%= errore %></p>
			    <%
			        }
			    %>
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