<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <h2>Sign in to Spitter</h2>

    <p>
        If you've been using Spitter from your phone,
        then that's amazing...we don't support IM yet. xxxx
    </p>

    <spring:url var="authUrl" value="/static/j_spring_security_check" /><!--<co id="co_securityCheckPath"/>-->
    <form method="post" class="signin" action="${authUrl}">

        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th></th>
                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                </tr>
                <tr>
                    <th><label for="username_or_email">Username or Email</label></th>
                    <td><input id="username_or_email"
                               name="j_username"
                               type="text"/>  <!--<co id="co_usernameField"/>-->
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Password</label></th>
                    <td><input id="password"
                               name="j_password"
                               type="password"/> <!--<co id="co_passwordField"/>-->
                        <small><a href="/account/resend_password">Forgot?</a></small>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input id="remember_me"
                               name="_spring_security_remember_me"
                               type="checkbox"/> <!--<co id="co_rememberMe"/>-->
                        <label for="remember_me"
                               class="inline">Remember me</label></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit" value="Sign In"/></td>
                </tr>
                <td>
                    <th></th>
                    <spring:url var="logoutUrl" value="/static/j_spring_security_logout" />
                    <td><a href="${logoutUrl}">LOGOUT</a></td>
            </table>
        </fieldset>
    </form>

    <script type="text/javascript">
        document.getElementById('username_or_email').focus();
    </script>
</div>