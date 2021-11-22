<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block sidebar collapse">
          <div class="position-sticky pt-3">
            <ul class="nav flex-column">
              <li class="nav-item">
                <c:url value="user" var="link">
                  <c:param name="action" value="get" />
                  <c:param name="id" value="${user.id}" />
                </c:url>
                <a class="nav-link text-dark" href="${link}">
                  <span data-feather="user"></span>
                  Perfil
                </a>
              </li>
              <li class="border-top my-3"></li>
              <li class="m-2 nav-item">
                <button class="btn btn-toggle rounded collapsed" data-bs-toggle="collapse"
                  data-bs-target="#measures-collapse">
                  <span data-feather="chevron-down"></span>
                  Medidas
                </button>
                <div class="collapse" id="measures-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <c:url value="bmi" var="link">
                      <c:param name="action" value="list" />
                    </c:url>
                    <li><a href=${link} class="nav-link text-dark">Medidas</a></li>
                  </ul>
                </div>
              </li>
              <li class="m-2 nav-item">
                <button class="btn btn-toggle rounded collapsed" data-bs-toggle="collapse"
                  data-bs-target="#routine-collapse">
                  <span data-feather="chevron-down"></span>
                  Rotina
                </button>
                <div class="collapse" id="routine-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="#" class="nav-link text-dark">Atividades Físicas</a></li>
                    <li><a href="#" class="nav-link text-dark">Alimentação</a></li>
                  </ul>
                </div>
              </li>
              <li class="m-2 nav-item">
                <button class="btn btn-toggle rounded collapsed" data-bs-toggle="collapse"
                  data-bs-target="#account-collapse">
                  <span data-feather="chevron-down"></span>
                  Conta
                </button>
                <div class="collapse" id="account-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="#" class="nav-link text-dark">Configurações</a></li>
                  </ul>
                </div>

              </li>
              <li class="border-top my-3"></li>
              <li class="nav-item">
                <a class="nav-link text-muted" href="${baseUrl}/login">
                  <span data-feather="log-out"></span>
                  Sair
                </a>
              </li>
            </ul>
          </div>
        </nav>