<%--

    Login Box
    @author Dedering

--%>

<%-- Background Image --%>
<div id="spacecouch"><%-- Dynamic Content, please save element --%></div>

<%-- Background Mask --%>
<div id="login-mask">

    <%-- Form Wrapper --%>
    <div id="login-main">

        <%-- Login Form --%>
        <form action="j_security_check" method="post">
            <table>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td>
                                    <%-- Username --%>
                                    <label>
                                        <table>
                                            <tr>
                                                <td>
                                                    username
                                                </td>
                                                <td>
                                                    <input type="text" name="j_username" autofocus>
                                                </td>
                                            </tr>
                                        </table>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <%-- Password --%>
                                    <label>
                                        <table>
                                            <tr>
                                                <td>
                                                    password
                                                </td>
                                                <td>
                                                    <input type="password" name="j_password">
                                                </td>
                                            </tr>
                                        </table>
                                    </label>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <%-- Submit Button --%>
                        <button type="submit">
                            &#10097;
                        </button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>