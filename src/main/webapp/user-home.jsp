<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <t:base>
          <jsp:attribute name="title">
            <title>Health Track - Perfil
            </title>
          </jsp:attribute>
          <jsp:body>
            <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
            <div class="container-fluid">
              <div class="row">
                <jsp:include page="/_includes/sidenav.jsp" />
                <div class="col-md-8 col-lg-8 mb-3">
                  <!-- Align with sidenav -->
                  <div class="p-1">
                    <div
                      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                      <h2>Olá, <c:out value="${user.name }"></c:out>
                      </h2>
                      <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group me-2">
                          <c:url value="user" var="link">
                            <c:param name="action" value="edit" />
                            <c:param name="id" value="${user.id}" />
                          </c:url>
                          <a class="btn btn-sm btn-outline-secondary" href="${link}">Editar</a>
                        </div>
                      </div>
                    </div>
                    <jsp:include page="/_includes/alert.jsp" />
                    <div class="container w-75">
                      <canvas id="bmiChart"></canvas>
                      <hr>
                      <canvas id="pressureChart"></canvas>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <script src="resources/js/render-chart.js"></script>
            <script>
              renderBmiChart();
              renderPressureChart();
            </script>
          </jsp:body>
        </t:base>