<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: Scorpa
  Date: 3/4/2016
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User meals</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        .normal{color: lightseagreen}
        .exceeded{color: red}
    </style>
</head>
<body>
<section>
<a href="index.html">Back</a>
<h2><a href="meals?action=create">Add meal</a> </h2>
       <h2>User meals list:</h2>
<table about="">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
    <jsp:useBean id="mealsList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealsList}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
        <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
            <td><%=TimeUtil.toSring(meal.getDateTime())%></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a> </td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a> </td>
        </tr>
    </c:forEach>
</table>
</section>
</body>
</html>
