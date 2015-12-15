<%--

  Login Page
  @author Dedering

--%>

<%-- UTF 8 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- HTML 5 --%>
<!DOCTYPE html>
<html>
  <head>

    <%-- Include Script Queue --%>
    <%@ include file="/file-que.jsp" %>

    <%-- Title --%>
    <title>News Aggregator</title>
  </head>
  <body>

    <%-- Login Message --%>
    <div class="alert">

      <%-- Anmiated Type --%>
      <span id="typed-headline"><%-- Dynamic Content, please save element --%></span>

    </div>

    <%-- Include Login Box --%>
    <%@ include file="/login-box.jsp" %>

    <%-- Include Script Queue --%>
    <%@ include file="/script-queue.jsp" %>

  </body>
</html>
