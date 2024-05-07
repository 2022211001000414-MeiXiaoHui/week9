
<%@include file="header.jsp"%>

<h1>Login</h1>
<%
    if(!(request.getAttribute("massage")==null)){
        out.println(request.getAttribute("massage"));
    }
%>

<%
    Cookie[] allCookies=request.getCookies();
    String username="",password="",rememberMeVale="";
    if(allCookies!=null){
        for(Cookie c:allCookies){
            if(c.getName().equals("cUsername")){
                //get value of this cookies
                username =c.getValue();
            }

            if(c.getName().equals("cPassword")){
                //get value of this cookies
                password =c.getValue();
            }
            if(c.getName().equals("cRememberMe")){
                //get value of this cookies
                rememberMeVale =c.getValue();
            }

        }
    }
%>
<form method="post" action="login">
    Username : <input type="text" name="username" value="<%=username%>"><br/>
    Password : <input type="password" name="password" value="<%=password%>"><br/>
    <input type="checkbox" name="rememberMe" value="1" <%=rememberMeVale.equals("1") ?"checked":""%>checked/>RememberMe<br/>
    <input type="submit" value="Submit"/>

</form>


<%--<form id="Login"  method="post" action="/login">--%>
<%--<label for="username">Username:</label>--%>
<%--    <input type="text" id="username" name="username" required><br>--%>
<%--<label for="password">Password:</label>--%>
<%--<input type="password" id="password" name="password" style="margin-top: 10px" required><br>--%>
<%--<input type="submit" value="Submit" style="background-color:darkgray; color: white;width: 120px;height: 30px;margin-left: 50px;margin-top: 10px">--%>
<%--</form>--%>
<%@include file="footer.jsp"%>