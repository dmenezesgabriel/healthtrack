<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
          <t:base>
            <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
            <div class="container-fluid">
              <div class="row">
                <jsp:include page="/_includes/sidenav.jsp" />
                <div class="col-md-8 col-lg-8">
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
                      <c:when test="${message != null}">
                        <div class="alert alert-info" role="alert">${message}</div>
                      </c:when>
                      <c:when test="${error != null}">
                        <div class="alert alert-danger" role="alert">${error}</div>
                      </c:when>
                      <c:otherwise> ${""} </c:otherwise>
                    </c:choose>
                  </div>
                </div>
              </div>
            </div>
            <c:choose>
              <c:when test="${sessionScope.user.name != null}"></c:when>
              <c:otherwise>
                <c:redirect url="/index.jsp" />
              </c:otherwise>
            </c:choose>
          </t:base>