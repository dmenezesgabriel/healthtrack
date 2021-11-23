<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ page isELIgnored="false" %>
        <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
          <t:base>
            <jsp:attribute name="title">
              <title>Health Track - Índice de massa corporal
              </title>
            </jsp:attribute>
            <jsp:body>
              <div class="container-fluid">
                <div class="row justify-content-md-center">
                  <div class="col-md-8 col-lg-8 mb-3">
                    <div
                      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                      <h2>Índice de massa corporal</h2>
                      <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group me-2">
                          <c:url value="bmi" var="link">
                            <c:param name="action" value="new" />
                          </c:url>
                          <a class="btn btn-sm btn-outline-secondary" href="${link}">Criar</a>
                        </div>
                      </div>
                    </div>
                    <jsp:include page="/_includes/alert.jsp" />
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th scope="col">Altura</th>
                          <th scope="col">Peso</th>
                          <th scope="col">Data</th>
                          <th scope="col">índice de massa corporal</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${bmis}" var="bmi">
                          <tr>
                            <td>${bmi.height.measureValue}</td>
                            <td>${bmi.weight.measureValue}</td>
                            <td>${bmi.measureDate}</td>
                            <td>
                              <fmt:formatNumber type="percent" maxIntegerDigits="3"
                                maxFractionDigits="2" value="${bmi.measureValue}" />
                            </td>
                            <td>
                              <c:url value="bmi" var="link">
                                <c:param name="action" value="edit" />
                                <c:param name="id" value="${bmi.id}" />
                              </c:url>
                              <a class="btn btn-secondary rounded-pill" href="${link}">Editar</a>
                            </td>
                            <td>
                              <button type="button" class="btn btn-light rounded-pill"
                                data-bs-toggle="modal" data-bs-target="#excludeModal"
                                onclick="exclusionId.value = ${bmi.id}">
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
                      Deseja realmente excluir a medida?
                    </div>
                    <div class="modal-footer">
                      <form action="bmi" method="post">
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