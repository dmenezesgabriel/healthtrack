<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
          <t:base>
            <jsp:attribute name="title">
              <title>Health Track - Usuário
              </title>
            </jsp:attribute>
            <jsp:body>
              <div class="container w-50 p-3">
                <h2>${title}</h2>
                <hr />
                <form class="${formClass}" action="user?action=${action}" method="post" novalidate>
                  <div class="${controlclass} mb-3">
                    <label class="form-label" for="name">Nome Completo</label>
                    <input class="form-control" type="text" name="name" id="name"
                      placeholder="João da Silva"
                      value="${sessionScope.user.name != null ? sessionScope.user.name : ''}" />
                    <div class="invalid-feedback">Campo obrigatório</div>
                  </div>
                  <div class="${controlclass} mb-3">
                    <label class="form-label" for="birthDate">Data de Nascimento</label>
                    <input class="form-control" type="date" name="birthDate" id="birthDate"
                      placeholder="João da Silva"
                      value="${sessionScope.user.birthDate != null ? sessionScope.user.birthDate : ''}" />
                    <div class="invalid-feedback">Campo obrigatório</div>
                  </div>
                  <div class="${controlclass} mb-3">
                    <label class="form-label" for="email">E-mail</label>
                    <input class="form-control" type="email" name="email" id="email"
                      placeholder="email@email.com.br"
                      value="${sessionScope.user.email != null ? sessionScope.user.email : ''}" />
                    <div class="invalid-feedback">Campo obrigatório</div>
                  </div>
                  <div class="${controlclass} mb-3">
                    <label for="gender">Gênero</label>
                    <select class="form-select" id="gender" name="gender">
                      <c:choose>
                        <c:when test="${sessionScope.user.gender.equals('Masculino')}">
                          <option value="Masculino" selected>Masculino</option>
                          <option value="Feminino">Feminino</option>
                        </c:when>
                        <c:when test="${sessionScope.user.gender.equals('Feminino')}">
                          <option value="Masculino">Masculino</option>
                          <option value="Feminino" selected>Feminino</option>
                        </c:when>
                        <c:otherwise>
                          <option value="Masculino">Masculino</option>
                          <option value="Feminino">Feminino</option>
                        </c:otherwise>
                      </c:choose>
                    </select>
                    <div class="invalid-feedback">Campo obrigatório</div>
                  </div>
                  <div class="${controlclass} mb-3">
                    <label class="form-label" for="password">Senha</label>
                    <input class="form-control" type="password" name="password" id="password"
                      placeholder="Senha"
                      value="${sessionScope.user.password != null ? sessionScope.user.password : ''}" />
                    <div class="invalid-feedback">Campo obrigatório</div>
                  </div>
                  <div class="${controlclass} mb-3">
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"
                      onclick="showPassword()" />
                    <label class="form-check-label" for="flexCheckChecked">Mostrar senha</label>
                  </div>
                  <div class="d-grid gap-2">
                    <button class="btn btn-danger" type="submit">${button}</button>
                  </div>
                </form>
              </div>
              <script src="resources/js/form-validate.js"></script>
              <script src="resources/js/form-password.js"></script>
            </jsp:body>
          </t:base>