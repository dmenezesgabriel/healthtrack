<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%> <%@ page isELIgnored="false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>HealthTrack</title>
    <link rel="stylesheet" href="resources/css/styles.css" />
    <link rel="stylesheet" href="resources/css/login.css" />
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" />
  </head>
  <body class="bg">
    <header>
      <jsp:include page="/_includes/header.jsp">
        <jsp:param name="title" value="This is the page title" />
      </jsp:include>
    </header>
    <main>
      <div class="container p-3">
        <!-- login -->
        <div class="d-flex flex-row-reverse">
          <div class="card w-auto p-3 bg-dark align-middle">
            <h3 class="card-title text-muted text-center">Login</h3>
            <div class="card-body">
              <form class="needs-validation" action="login" method="post" novalidate>
                <div class="has-validation mb-3">
                  <label class="form-label text-white" for="email">Email</label>
                  <input class="form-control" type="email" name="Email" id="email" placeholder="email@exemplo.com.br" required />
                  <div class="invalid-feedback">Campo obrigatório</div>
                </div>
                <div class="has-validation mb-3">
                  <label class="form-label text-white" for="password">Senha</label>
                  <input class="form-control" type="text" name="password" id="password" placeholder="123DeOliveira4" required />
                  <div class="invalid-feedback">Campo obrigatório</div>
                </div>
                <div class="d-grid gap-2">
                  <button class="btn btn-danger" type="submit">Entrar</button>
                  <a class="btn btn-warning" href="">Registrar</a>
                </div>
              </form>
            </div>
          </div>
        </div>
        <!-- login -->
      </div>
    </main>
    <jsp:include page="/_includes/footer.jsp" />

    <script src="resources/js/form-validate.js"></script>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
  </body>
</html>
