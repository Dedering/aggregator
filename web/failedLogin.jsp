<%--

  Failed Login
  @author Dedering

--%>

<%-- UTF 8 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>

    <%-- Include Script Queue --%>
    <%@ include file="/file-que.jsp" %>

    <%-- Title --%>
    <title>News Aggregator - Login Failed</title>

  </head>
  <body>

    <%-- Animated Type --%>
    <div class="alert">
      <span id="typed-failed-login"><%-- Dynamic Content, please save element --%></span>
    </div>

    <%-- Include Login Box --%>
    <%@ include file="/login-box.jsp" %>

    <%-- Include Script Queue--%>
    <%@ include file="/script-queue.jsp" %>

  </body>
</html>
