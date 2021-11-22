<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <t:base>
          <jsp:attribute name="title">
            <title>Health Track - Cadastrar Pressao
            </title>
          </jsp:attribute>
          <jsp:body>
            <div class="container-fluid">
              <div class="row justify-content-md-center">
                <div class="col-md-8 col-lg-8 mb-3">
                  <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h2>Cadastrar Pressao</h2>
                  </div>
                  <jsp:include page="/_includes/alert.jsp" />
                  <form class="needs-validation" action="pressure" method="post" novalidate>
                    <input type="hidden" value="create" name="action">

                    <div class="has-validation mb-3">
                      <label for="systolicPressure">Pressão Sistolica</label>
                      <input class="form-control" type="number" name="systolicPressure"
                        id="systolicPressure" required />
                      <div class="invalid-feedback">Campo obrigatório</div>
                    </div>
                    <div class="has-validation mb-3">
                      <label for="diastolicPressure">Pressão Diastolica</label>
                      <input class="form-control" type="number" name="diastolicPressure"
                        id="diastolicPressure" required />
                      <div class="invalid-feedback">Campo obrigatório</div>
                    </div>
                    <div class="has-validation mb-3">
                      <label class="form-label" for="measureDate">Data da medida</label>
                      <input class="form-control" type="date" name="measureDate" id="measureDate"
                        required />
                      <div class="invalid-feedback">Campo obrigatório</div>
                    </div>
                    <button class="btn btn-danger rounded-pill" type="submit">Cadastrar
                      agora</button>
                  </form>
                </div>
              </div>
            </div>
            </div>
            <script>
              document.getElementById('measureDate').valueAsDate = new Date()
            </script>
            <script src="resources/js/form-validate.js"></script>
            <script src="resources/js/form-password.js"></script>
          </jsp:body>
        </t:base>