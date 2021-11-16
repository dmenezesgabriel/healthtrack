<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<!-- <%@ page isELIgnored="false" %> -->
<!-- <%@taglib prefix="t" tagdir="/WEB-INF/tags"%> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> -->
<t:base>
  <div class="bg">
    <div class="container p-3">
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
                <a class="btn btn-warning" href="/healthtrack/user/new">Registrar</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="resources/js/form-validate.js"></script>
</t:base>
