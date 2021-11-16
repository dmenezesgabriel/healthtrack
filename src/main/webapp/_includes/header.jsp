<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<!-- <%@ page isELIgnored="false" %>  -->
<!-- <%@taglib prefix="t" tagdir="/WEB-INF/tags" %> -->
<t:top-nav>
  <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
  <a class="nav-link active" href="${baseUrl}" aria-disabled="true">Home</a>
  <a class="nav-link" href="${baseUrl}/features.jsp" aria-disabled="true">Funcionalidades</a>
</t:top-nav>
