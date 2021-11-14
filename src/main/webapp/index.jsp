<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%> <%@ page isELIgnored="false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="refresh" content="5" />
    <title>HealthTrack</title>
    <link rel="stylesheet" href="resources/css/styles.css" />
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" />
  </head>
  <body>
    <header>
      <jsp:include page="/_includes/header.jsp">
        <jsp:param name="title" value="This is the page title" />
      </jsp:include>
    </header>
    <main class="flex-shrink-0">
      <div class="container">
        <h1 class="mt-5">Sticky footer</h1>

        <h2>hello</h2>
        <p class="lead">Pin a footer to the bottom of the viewport in desktop browsers with this custom HTML and CSS.</p>
      </div>
    </main>
    <jsp:include page="/_includes/footer.jsp" />
  </body>
</html>
