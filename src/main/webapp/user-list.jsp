<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <t:base>
          <jsp:attribute name="title">
            <title>Health Track - Admin
            </title>
          </jsp:attribute>
          <jsp:body>
            <div class="container-fluid">
              <div class="row justify-content-md-center">
                <div class="col-md-8 col-lg-8 mb-3">
                  <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h2>Admin - Users</h2>
                  </div>
                  <jsp:include page="/_includes/alert.jsp" />
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Data de Nascimento</th>
                        <th scope="col">Gênero</th>
                        <th scope="col">Email</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${users}" var="user">
                        <tr>
                          <td>${user.id}</td>
                          <td>${user.name}</td>
                          <td>${user.birthDate}</td>
                          <td>${user.gender}</td>
                          <td>${user.email}</td>
                          <td>
                            <c:url value="user" var="link">
                              <c:param name="action" value="edit" />
                              <c:param name="id" value="${user.id}" />
                            </c:url>
                            <a class="btn btn-primary rounded-pill" href="${link}">Editar</a>
                          </td>
                          <td>
                            <button type="button" class="btn btn-danger rounded-pill"
                              data-bs-toggle="modal" data-bs-target="#excludeModal"
                              onclick="exclusionId.value = ${user.id}">
                              Excluir
                            </button>
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="excludeModal" tabindex="-1"
              aria-labelledby="excludeModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="excludeModalLabel">Confirmação</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                      aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    Deseja realmente excluir o usuário?
                  </div>
                  <div class="modal-footer">
                    <form action="user" method="post">
                      <input type="hidden" name="action" value="delete">
                      <input type="hidden" name="id" id="exclusionId">
                      <button type="button" class="btn btn-primary"
                        data-bs-dismiss="modal">Cancelar</button>
                      <button type="submit" class="btn btn-secondary">Excluir</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </jsp:body>
        </t:base>