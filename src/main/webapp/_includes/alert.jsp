<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <c:choose>
        <c:when test="${not empty message}">
          <div class="alert alert-info" role="alert">${message}</div>
        </c:when>
        <c:when test="${not empty error}">
          <div class="alert alert-danger" role="alert">${error}</div>
        </c:when>
        <c:otherwise></c:otherwise>
      </c:choose>