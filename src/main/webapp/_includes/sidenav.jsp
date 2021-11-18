<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
        <c:set var="pageUrl" value="${pageContext.request.requestURL}" />
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block sidebar collapse">
          <div class="position-sticky pt-3">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link ${pageUrl.toString().endsWith('/user-home.jsp') ? 'active' : ''}"
                  href="${baseUrl}/user-home.jsp">
                  <span data-feather="bar-chart-2"></span>
                  Perfil
                </a>
              </li>
              <li class="m-2 nav-item">
                <button class="btn btn-toggle rounded collapsed" data-bs-toggle="collapse"
                  data-bs-target="#measures-collapse">
                  <span data-feather="chevron-down"></span>
                  <span data-feather="layers"></span>
                  Medidas
                </button>
                <div class="collapse" id="measures-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="#" class="nav-link">Peso</a></li>
                    <li><a href="#" class="nav-link">Altura</a></li>
                    <li><a href="#" class="nav-link">Índice de massa corporal</a></li>
                  </ul>
                </div>
              </li>
              <li class="m-2 nav-item">
                <button class="btn btn-toggle rounded collapsed" data-bs-toggle="collapse"
                  data-bs-target="#routine-collapse">
                  <span data-feather="chevron-down"></span>
                  <span data-feather="layers"></span>
                  Rotina
                </button>
                <div class="collapse" id="routine-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="#" class="nav-link">Atividades Físicas</a></li>
                    <li><a href="#" class="nav-link">Alimentação</a></li>
                  </ul>
                </div>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${baseUrl}">
                  <span data-feather="bar-chart-2"></span>
                  Sair
                </a>
              </li>
            </ul>
          </div>
        </nav>