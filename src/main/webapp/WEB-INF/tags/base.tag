<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>HealthTrack</title>
    <link rel="stylesheet" href="resources/css/styles.css" />
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" />
  </head>

  <body>
    <jsp:include page="/_includes/header.jsp" />

    <jsp:doBody />

    <jsp:include page="/_includes/footer.jsp" />
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="webjars/feather-icons/4.28.0/feather.js"></script>
    <script>
      feather.replace();
    </script>
  </body>

  </html>