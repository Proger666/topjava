<%--
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
        .green{color: lightseagreen}
        .red{color: red}
    </style>
</head>
<body>
<h2>User meals list:</h2>
<table about="">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
    <jsp:useBean id="mealsList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealsList}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
        <tr class="${meal.exceed ? 'red' : 'green'}">
            <td>
            <c:out value="${meal.dateTime}"/>
            </td>
            <td>
                <c:out value="${meal.description}"/>
            </td>
            <td>
                <c:out value="${meal.calories}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
