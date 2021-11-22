<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
  <%@attribute name="title" fragment="true" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />

      <jsp:invoke fragment="title" />

      <link rel="stylesheet" href="resources/css/styles.css" />
      <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" />
      <script src="resources/lib/chart.min.js"></script>

    </head>

    <body>

      <jsp:include page="/_includes/header.jsp" />


      <jsp:doBody />


      <jsp:include page="/_includes/footer.jsp" />
      <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
      <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
      <script src="resources/lib/feather.min.js"></script>


      <script>
        feather.replace();
      </script>
    </body>

    </html>