<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<!-- <%@ page isELIgnored="false" %>  -->
<!-- <%@taglib prefix="t" tagdir="/WEB-INF/tags" %> -->
<t:top-nav>
  <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
  <c:set var="pageUrl" value="${pageContext.request.requestURL}" />
  <c:choose>
    <c:when test="${sessionScope.user.name != null}">
      <a class="nav-link ${pageUrl.toString().endsWith('/user-home.jsp') ? 'active' : ''}" href="${baseUrl}/user-home.jsp">Perfil</a>
    </c:when>
    <c:otherwise>
      <a class="nav-link ${pageUrl.toString().endsWith('/') ? 'active' : ''}" href="${baseUrl}">Home</a>
      <a class="nav-link ${pageUrl.toString().endsWith('/features.jsp') ? 'active' : ''}" href="${baseUrl}/features.jsp">Funcionalidades</a>
    </c:otherwise>
  </c:choose>
</t:top-nav>
