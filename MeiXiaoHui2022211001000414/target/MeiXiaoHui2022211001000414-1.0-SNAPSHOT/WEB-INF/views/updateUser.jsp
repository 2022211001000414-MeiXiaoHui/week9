<%--
  Created by IntelliJ IDEA.
  User: 梅晓辉
  Date: 2024/4/19
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp"%>
<h1>User Update</h1>

<%
    User u=(User)session.getAttribute("user");
    if (u == null) {
        System.out.println("u is mull");
        return;
    }
    else System.out.println("u is not null");
%>

<form id="registrationForm"  method="post" action="updateUser">

    <!-- id -->
    <label for="id">ID:</label>
    <input type="hidden" id="id" name="id" value="<%=u.getId()%>" placeholder="ID" required><br>
    <!-- Username -->
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" value="<%=u.getUsername()%>" placeholder="Username" required><br>

    <!-- Password -->
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"  value="<%=u.getPassword()%>" placeholder="password" required><br>

    <!-- Email -->
    <label for="email">Email:</label>
    <input type="text" id="email" name="email" value="<%=u.getEmail()%>" placeholder="Email" required><br>
    <!--Gender-->
    <label>Gender:</label>
    <input type="radio" id="genderMale" name="gender" value="male" <%="male".equals(u.getGender())?"checked" :""%>>
    <label for="genderMale">Male</label>

    <input type="radio" id="genderFemale" name="gender" value="female" <%="female".equals(u.getGender())?"checked" :""%>>
    <label for="genderFemale">Female</label><br>

    <!-- Birthdate -->
    <label for="birthdate">Birthdate:</label>
    <input type="text" id="birthdate" name="birthdate" value="<%=u.getBirthDate()%>" placeholder="Date of Birth(yyyy-mm-dd)" required ><br>

    <!-- Submit button -->
    <input type="submit" value="Save Changes" style="background-color: orange; color: white;width: 120px;height: 30px;margin-left: 50px;margin-top: 10px">
</form>

<%@include file="footer.jsp"%>