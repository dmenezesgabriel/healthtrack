<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
          <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <t:base>
              <jsp:attribute name="title">
                <title>Health Track - Usuário
                </title>
              </jsp:attribute>
              <jsp:body>
                <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
                <div class="container-fluid">
                  <c:choose>
                    <c:when test="${not empty sessionScope.user.name}">
                      <div class="row">
                    </c:when>
                    <c:otherwise>
                      <div class="row justify-content-md-center">
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
                        <h2>Users</h2>
                      </div>
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">id</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Data de Nascimento</th>
                            <th scope="col">Gênero</th>
                            <th scope="col">Email</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${users}" var="user">
                            <tr>
                              <td>${user.id}</td>
                              <td>${user.name}</td>
                              <td>${user.birthDate}</td>
                              <td>${user.gender}</td>
                              <td>${user.email}</td>
                              <td>
                                <c:url value="user" var="link">
                                  <c:param name="action" value="edit" />
                                  <c:param name="id" value="${user.id}" />
                                </c:url>
                                <a href="${link}">Edit</a>
                              </td>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
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