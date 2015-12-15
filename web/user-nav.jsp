<%--

  User Nav
  @author Dedering

--%>

<%-- UTF 8 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Nav Element --%>
<nav id="nav-menu" class="navbar navbar-default">

    <%-- List of Pages --%>
    <ul class="nav navbar-nav">

        <%-- User Name --%>
        <li>${pageContext.request.remoteUser}'s Feed</li>

        <%-- Feed --%>
        <li><a href="/feed">Feed</a></li>

        <%-- My Account - Edit Feed --%>
        <li><a href="/edit-feed">My Account</a></li>

        <%-- Logout --%>
        <li><a href="/logout">Logout</a></li>

      </ul>

</nav>