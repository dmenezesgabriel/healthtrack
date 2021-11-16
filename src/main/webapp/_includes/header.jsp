<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<!-- <%@ page isELIgnored="false" %> <%@taglib prefix="t" tagdir="/WEB-INF/tags" %> -->
<t:top-nav-wrapper>
  <c:set var="baseUrl" value="${pageContext.request.contextPath}/healthtrack" />
  <a class="nav-link active" href="${ctx}" aria-disabled="true">Home</a>
  <a class="nav-link" href="${ctx}/features" aria-disabled="true">Funcionalidades</a>
</t:top-nav-wrapper>
