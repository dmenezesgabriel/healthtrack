<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%> <%@ page isELIgnored="false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="refresh" content="60*5" />
    <title>HealthTrack</title>
    <link rel="stylesheet" href="resources/css/styles.css" />
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" />
  </head>
  <body>
    <header>
      <jsp:include page="/_includes/header.jsp">
        <jsp:param name="title" value="This is the page title" />
      </jsp:include>
    </header>
    <main>
      <div class="container">
        <div class="row align-items-center p-3">
          <div class="col p-3">
            <img src="resources/img/people-fitness.jpg" alt="" />
            <a class="text-muted d-none" href="https://www.freepik.com/vectors/people">People vector created by pch.vector - www.freepik.com</a>
          </div>
          <div class="col p-3">
            <!-- login -->
            <div class="card p-3">
              <h3 class="card-title text-muted">Login</h3>
              <div class="card-body">
                <form class="needs-validation" action="login" method="post" novalidate>
                  <div class="has-validation mb-3">
                    <label class="form-label" for="email">Email</label>
                    <input class="form-control" type="email" name="Email" id="email" placeholder="email@email.com.br" required />
                    <div class="invalid-feedback">Campo obrigatório</div>
                  </div>
                  <div class="has-validation mb-3">
                    <label class="form-label" for="password">Senha</label>
                    <input class="form-control" type="text" name="password" id="password" placeholder="Password" required />
                    <div class="invalid-feedback">Campo obrigatório</div>
                  </div>
                  <div class="d-grid gap-2">
                    <button class="btn btn-primary" type="submit">Login</button>
                    <a class="text-muted text-center" href="">Registrar</a>
                  </div>
                </form>
              </div>
            </div>
            <!-- login -->
          </div>
        </div>
      </div>
    </main>
    <jsp:include page="/_includes/footer.jsp" />
    <script src="resources/js/form-validate.js"></script>
  </body>
</html>
