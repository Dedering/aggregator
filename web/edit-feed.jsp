<%--

    Edit Feed
    @author Dedering

--%>

<%-- Import Util --%>
<%@ page import="java.util.*" %>

<%-- Import Util MAp --%>
<%@ page import = "java.util.Map" %>

<%-- Import ArrayList --%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Iterator"%>

<%-- UTF 8 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>

        <%-- Include Script Queue --%>
        <%@ include file="/file-que.jsp" %>

        <%-- Title --%>
        <title>News Aggregator - My Account</title>

    </head>
<body>

    <%-- Include Nav Bar --%>
    <%@ include file="/user-nav.jsp" %>

    <%-- Bootstrap Container --%>
    <div class="container">

        <%-- Headline --%>
        <h1>Manage Your Feeds</h1>

        <%-- Horizontal Rule --%>
        <hr />

        <%-- Bootstrap Row --%>
        <div class="row">

            <%-- Form --%>
            <form action="/edit-feed" method="get">

                <%-- Bootstrap Column --%>
                <div class="col-sm-6" id="cell-two">

                    <%-- Headline --%>
                    <h2>Choose Feeds</h2>

                    <%-- Horizontal Rules --%>
                    <hr />

                    <%-- Checkboxes --%>
                    ${checkboxes}

                </div>

                <%-- Bootstrap Column --%>
                <div class="col-sm-6" id="cell-three">

                    <%-- Headline --%>
                    <h2>Add Another Feed</h2>

                    <%-- Horizontal Rules --%>
                    <hr />

                    <%-- Feed Name --%>
                    <label>
                        Feed Name<br />
                        <input type="text" name="addFeedName" placeholder="Feed Name" />
                    </label><br />

                    <%-- Feed URL --%>
                    <label>RSS Url<br />
                        <input type="text" name="addFeedURL" placeholder="RSS Url"  />
                    </label>
                    <br />
                    <br />
                </div>

                <%-- Hidden Input --%>
                <input type="hidden" name="feeds" value="0" />

                <%-- Submit Button --%>
                <input type="submit" value="update" />
            </form>
        </div>
    </div>

    <%-- Include Script Queue --%>
    <%@ include file="/script-queue.jsp" %>

</body>
</html>
