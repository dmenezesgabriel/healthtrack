<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<!-- <%@ page isELIgnored="false" %> -->
<!-- <%@taglib prefix="t" tagdir="/WEB-INF/tags"%> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> -->
<t:base>
  <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
  <jsp:include page="/_includes/sidenav.jsp" />

  <div class="container w-50 p-3">
    <c:choose>
      <c:when test="${message != null}">
        <div class="alert alert-info" role="alert">${message}</div>
      </c:when>
      <c:when test="${error != null}">
        <div class="alert alert-danger" role="alert">${error}</div>
      </c:when>
      <c:otherwise> ${""} </c:otherwise>
    </c:choose>
    <h2>Olá, <c:out value="${sessionScope.user.name }"></c:out></h2>
    <hr />
    <a class="btn btn-warning" href="${baseUrl}/user?action=edit">Editar</a>
    <a class="btn btn-secondary" href="${baseUrl}/user?action=delete">Deletar conta</a>
  </div>
</t:base>
