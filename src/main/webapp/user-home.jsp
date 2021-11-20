<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
          <t:base>
            <jsp:attribute name="title">
              <title>Health Track - Perfil
              </title>
            </jsp:attribute>
            <jsp:body>
              <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
              <c:choose>
                <c:when test="${not empty sessionScope.user.name}"></c:when>
                <c:otherwise>
                  <c:redirect url="${baseUrl}/index.jsp" />
                </c:otherwise>
              </c:choose>
              <div class="container-fluid">
                <c:choose>
                  <c:when test="${not empty sessionScope.user.name}">
                    <div class="row">
                  </c:when>
                  <c:otherwise>
                    <div class="row sessionScope.user.name">
                  </c:otherwise>
                </c:choose>
                <c:if test="${sessionScope.user.name != null}">
                  <jsp:include page="/_includes/sidenav.jsp" />
                </c:if>
                <div class="col-md-8 col-lg-8 mb-3">
                  <!-- Align with sidenav -->
                  <div class="p-1">
                    <div
                      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                      <h2>Olá, <c:out value="${sessionScope.user.name }"></c:out>
                      </h2>
                      <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group me-2">
                          <a href="${baseUrl}/user?action=edit"
                            class="btn btn-sm btn-outline-secondary">Editar</a>
                        </div>
                      </div>
                    </div>
                    <c:choose>
                      <c:when test="${not empty message}">
                        <div class="alert alert-info" role="alert">${message}</div>
                      </c:when>
                      <c:when test="${not empty error}">
                        <div class="alert alert-danger" role="alert">${error}</div>
                      </c:when>
                      <c:otherwise></c:otherwise>
                    </c:choose>
                  </div>
                </div>
              </div>
              </div>
            </jsp:body>
          </t:base>