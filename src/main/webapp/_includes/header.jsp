<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
        <c:set var="pageUrl" value="${pageContext.request.requestURL}" />
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
          <div class="container-fluid">
            <span class="navbar-brand">Health Track</span>
            <c:choose>
              <c:when test="${sessionScope.user.name != null}">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                  data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false"
                  aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
              </c:when>
              <c:otherwise>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                  data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                  aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
              </c:otherwise>
            </c:choose>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <c:choose>
                  <c:when test="${sessionScope.user.name != null}">
                  </c:when>
                  <c:otherwise>
                    <a class="nav-link ${pageUrl.toString().endsWith('/') ? 'active' : ''}"
                      href="${baseUrl}">Home</a>
                    <a class="nav-link ${pageUrl.toString().endsWith('/features.jsp') ? 'active' : ''}"
                      href="${baseUrl}/features.jsp">Funcionalidades</a>
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
          </div>
        </nav>