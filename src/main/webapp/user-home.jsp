<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<!-- <%@ page isELIgnored="false" %> -->
<!-- <%@taglib prefix="t" tagdir="/WEB-INF/tags"%> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> -->
<t:base>
  <div class="container w-50 p-3">
    <h2>Olá, <c:out value="${sessionScope.user.name }"></c:out></h2>
    <hr />
  </div>
</t:base>
