<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
      <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <t:base>
          <jsp:attribute name="title">
            <title>Health Track - Cadastrar medidas
            </title>
          </jsp:attribute>
          <jsp:body>
            <div class="container-fluid">
              <div class="row justify-content-md-center">
                <div class="col-md-8 col-lg-8 mb-3">
                  <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h2>Cadastrar medidas</h2>
                  </div>
                  <jsp:include page="/_includes/alert.jsp" />
                  <form class="needs-validation" action="bmi" method="post" novalidate>
                    <input type="hidden" value="create" name="action">

                    <div class="has-validation mb-3">
                      <label for="height">Altura</label>
                      <input class="form-control" type="number" name="height" id="height"
                        required />
                      <div class="invalid-feedback">Campo obrigatório</div>
                    </div>
                    <div class="has-validation mb-3">
                      <label for="weight">Peso</label>
                      <input class="form-control" type="number" name="weight" id="weight"
                        required />
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
            <script src="resources/js/form-validate.js"></script>
            <script src="resources/js/form-password.js"></script>
          </jsp:body>
        </t:base>