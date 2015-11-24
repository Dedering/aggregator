<%@ page import="java.util.*" %>
<%@ page import = "java.util.Map" %>

<%@ page import="java.util.List,java.util.ArrayList,java.util.Iterator"%>
<%--
  Created by IntelliJ IDEA.
  User: Private
  Date: 11/1/15
  Time: 10:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ include file="/file-que.jsp" %>

  <title></title>
</head>
<body>
<%@ include file="/user-nav.jsp" %>

<div class="container">
    <h1>Manage Your Feeds</h1>
    <hr />
    <div class="row">


        <form action="/edit-feed" method="get">


            <div class="col-sm-6" id="cell-two">
                <h2>Choose Feeds</h2>
                <hr />
                ${checkboxes}
            </div>



            <!--<a href="#" id="add-another">Add Another Feed</a>-->



            <div class="col-sm-6" id="cell-three">

                <h2>Add Another Feed</h2>
                <hr />
                <label>Feed Name<br />
                    <input type="text" name="addFeedName" placeholder="Feed Name" />
                </label><br />

                <label>RSS Url<br />
                    <input type="text" name="addFeedURL" placeholder="RSS Url"  />
                </label>
                <br />
                <br />


            </div>

            <input type="hidden" name="feeds" value="0" />

            <input type="submit" value="update" />
        </form>


    </div>
</div>


<%@ include file="/script-queue.jsp" %>


</body>
</html>
