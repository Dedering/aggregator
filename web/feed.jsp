<%--

  Feed
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
    <title>My Feed</title>

  </head>
  <body>

    <%-- Include User Nav --%>
    <%@ include file="/user-nav.jsp" %>

    <%-- Feed Column --%>
    <div class="feed">

      <%-- Feed Nav --%>
      ${feedLinks}

      <%-- Feed --%>
      ${feed}
    </div>

  </body>
</html>
