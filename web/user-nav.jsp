<%--
  Created by IntelliJ IDEA.
  User: Private
  Date: 11/1/15
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td id="nav-name">${pageContext.request.remoteUser}</td>

      <td><a href="/feed.jsp">Feed</a></td>
      <td><a href="/edit-feed.jsp">My Account</a></td>
      <td><a href="/logout.jsp">Logout</a></td>
    </tr>
  </table>
</nav>