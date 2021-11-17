<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<!-- <%@ page isELIgnored="false" %>  -->
<!-- <%@taglib prefix="t" tagdir="/WEB-INF/tags" %> -->
<div class="bg-white">
  <ul class="list-unstyled ps-0">
    <li class="mb-1">
      <button
        class="btn btn-toggle align-items-center rounded collapsed"
        data-bs-toggle="collapse"
        data-bs-target="#medidas-collapse"
        aria-expanded="false"
      >
        Medidas
      </button>
      <div class="collapse p-2" id="medidas-collapse">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="#" class="link-dark rounded">Peso</a></li>
          <li><a href="#" class="link-dark rounded">Altura</a></li>
        </ul>
      </div>
    </li>
    <li class="mb-1">
      <button
        class="btn btn-toggle align-items-center rounded collapsed"
        data-bs-toggle="collapse"
        data-bs-target="#rotina-collapse"
        aria-expanded="false"
      >
        Rotina
      </button>
      <div class="collapse p-2" id="rotina-collapse">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="#" class="link-dark rounded">Atividades físicas</a></li>
          <li><a href="#" class="link-dark rounded">Alimentação</a></li>
        </ul>
      </div>
    </li>
    <hr />
    <li class="mb-1">
      <button
        class="btn btn-toggle align-items-center rounded collapsed"
        data-bs-toggle="collapse"
        data-bs-target="#conta-collapse"
        aria-expanded="false"
      >
        Conta
      </button>
      <div class="collapse p-2" id="conta-collapse">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="#" class="link-dark rounded">Editar</a></li>
          <li><a href="#" class="link-dark rounded">Apagar</a></li>
        </ul>
      </div>
    </li>
  </ul>
</div>
