<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <t:base>
          <jsp:attribute name="title">
            <title>Health Track - Perfil
            </title>
          </jsp:attribute>
          <jsp:body>
            <c:set var="baseUrl" value="${pageContext.request.contextPath}" />
            <div class="container-fluid">
              <div class="row">
                <jsp:include page="/_includes/sidenav.jsp" />
                <div class="col-md-8 col-lg-8 mb-3">
                  <!-- Align with sidenav -->
                  <div class="p-1">
                    <div
                      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                      <h2>Olá, <c:out value="${user.name }"></c:out>
                      </h2>
                      <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group me-2">
                          <c:url value="user" var="link">
                            <c:param name="action" value="edit" />
                            <c:param name="id" value="${user.id}" />
                          </c:url>
                          <a class="btn btn-sm btn-outline-secondary" href="${link}">Editar</a>
                        </div>
                      </div>
                    </div>
                    <jsp:include page="/_includes/alert.jsp" />
                    <div class="container">
                      <canvas id="bmiChart"></canvas>

                    </div>
                  </div>
                </div>
              </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
            <script>
              function renderChart() {
                let data = [];
                data['labels'] = [];
                data['datasets'] = [];
                data['datasets'].push({label: 'Bmi', data: []})

                // Fetching data
                console.log("Fetching")
                fetch('bmi?action=overtime', {
                  headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                  }
                })
                  .then(function (response) {
                    return response.json();
                  }).then(function (resData) {
                    resData.forEach(element => {
                      data['labels'].push(`${element.measureDate['year']}-${element.measureDate['month']}-${element.measureDate['day']}`);
                      data['datasets'][0]['data'].push(element.measureValue)
                    });
                  });
                // render
                console.log(data);
                const myChart = new Chart(
                  document.querySelector('#bmiChart'),
                  {
                    type: 'line',
                    data: data,
                  }
                );
              }
              renderChart();
            </script>
          </jsp:body>
        </t:base>