<%--

  Logout
  @author Dedering

--%>

<%-- Get Session --%>
<%@ page session="true"%>

<%-- Invalidate Session --%>
<% session.invalidate(); %>

<%-- Redirect to /Feed --%>
<%
  String site = new String("/feed");
  response.setStatus(response.SC_MOVED_PERMANENTLY);
  response.setHeader("Location", site);
%>