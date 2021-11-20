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
                <a class="nav-link text-dark ${pageUrl.toString().endsWith('/user-home.jsp') ? 'active' : ''}"
                  href="${baseUrl}/user-home.jsp">
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
                    <li><a href="#" class="nav-link text-dark">Peso</a></li>
                    <li><a href="#" class="nav-link text-dark">Índice de massa corporal</a></li>
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
                    <li>
                      <button type="button" class="nav-link btn btn-link btn-sm text-muted"
                        data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Excluir
                      </button>
                    </li>
                  </ul>
                </div>

              </li>
              <li class="border-top my-3"></li>
              <li class="nav-item">
                <a class="nav-link text-muted" href="${baseUrl}">
                  <span data-feather="log-out"></span>
                  Sair
                </a>
              </li>
            </ul>
          </div>
        </nav>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
          aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Confirmar exclusão de conta</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                  aria-label="Close"></button>
              </div>
              <div class="modal-body">
                Você realmente gostaria de excluir a sua conta?
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Não</button>
                <a href="${baseUrl}/user?action=delete" class="btn btn-secondary">
                  Sim</a>
              </div>
            </div>
          </div>
        </div>