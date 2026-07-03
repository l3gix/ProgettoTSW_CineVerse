<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/register.css">
    <title>Register</title>
    <script src="script/validator.js"></script>
</head>
<body>
   <header>
        <p>Registrati </p>
    </header>
    <div id="container">
        <section id="login">
            <form action="Register" method="post">
                <label for="email">Email</label>
                <input type="email" placeholder="email" name="email" id="email" onchange="validateFormElem(this,document.getElementById('errorEmail'),emailErrorMessage)"><p id="errorEmail"></p>

                <label for="password">Password</label>
                <input type="password" placeholder="password" name="password" id="password" required pattern="^(?=.*[A-Z]).{8}$" onchange="validateFormElem(this,document.getElementById('errorPassword'),passwordErrorMessage)"><p id="errorPassword"></p>
                
                <label for="nome">Nome</label>
                <input type="text" name="nome" placeholder="nome" id="nome" required pattern="^[A-Za-z]+$"
                onchange="validateFormElem(this, document.getElementById('errorName'),nameOrLastnameErrorMessage)"><p id="errorName"></p>
                
                <label for="cognome">Cognome</label>
                <input type="text" name="cognome" placeholder="cognome" id="cognome" required pattern="^[A-Za-z]+$" 
                onchange="validateFormElem(this, document.getElementById('errorLastname'),nameOrLastnameErrorMessage)"><p id="errorLastname"></p>
                
                <label for="telefono">Telefono</label>
                <input type="tel" name="telefono" placeholder="###-#######" id="telefono" required pattern="^([0-9]{3}-[0-9]{7})$"
                onchange="validateFormElem(this,document.getElementById('errorTelefono'),phoneErrorMessage)"><p id="errorTelefono"></p>
                
                 <%
			        String errore = (String) request.getAttribute("errore");
			        if (errore != null) {
			    %>
			        <p style="color:red"><%= errore %></p>
			    <%
			        }
			    %>
			    
                <button type="submit" onclick="">Registrati</button>
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