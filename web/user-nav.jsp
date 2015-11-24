<%--
  Created by IntelliJ IDEA.
  User: Private
  Date: 11/1/15
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<nav class="navbar navbar-default navbar-fixed-top">
  <button data-toggle="collapse" data-target="#nav-menu">Collapsible</button>
  <div id="nav-menu" class="container collapse">
    <table cellspacing="0" cellpadding="0">
      <tr>
        <td id="nav-name">${pageContext.request.remoteUser}</td>

        <td><a href="/feed">Feed</a></td>
        <td><a href="/edit-feed">My Account</a></td>
        <td><a href="/logout.jsp">Logout</a></td>
      </tr>
    </table>
  </div>
</nav>
--%>
<nav id="nav-menu" class="navbar navbar-default">
      <ul class="nav navbar-nav">
        <li>${pageContext.request.remoteUser}'s Feed</li>
        <li><a href="/feed">Feed</a></li>
        <li><a href="/edit-feed">My Account</a></li>
        <li><a href="/logout">Logout</a></li>
      </ul>
</nav>