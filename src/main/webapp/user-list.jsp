<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <t:base>
          <jsp:attribute name="title">
            <title>Health Track - Admin
            </title>
          </jsp:attribute>
          <jsp:body>
            <div class="container-fluid">
              <div class="row justify-content-md-center">
                <div class="col-md-8 col-lg-8 mb-3">
                  <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h2>Admin - Users</h2>
                  </div>
                  <jsp:include page="/_includes/alert.jsp" />
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
                            <a class="btn btn-primary" href="${link}">Edit</a>
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
            </div>
          </jsp:body>
        </t:base>