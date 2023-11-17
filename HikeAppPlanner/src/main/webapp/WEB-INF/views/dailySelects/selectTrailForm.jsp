<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 10.11.2023
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Wybierz szlak</title>
    <style>
        body {
            background-color: #E6E6E6;
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            background-color: #F2F2F2;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            margin: 0 auto;
            width: 80%;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input[type="text"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #D9D9D9;
        }

        button {
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Wybierz szlak</h1>
<form:form method="post" modelAttribute="dailySelection" action="/select/trail">
    <c:forEach items="${dailySelections}" var="selection">
        <label for="trail-${selection.date}">Wybierz szlak dla ${selection.date}</label>
        <form:select path="trail" id="trail-${selection.date}">
            <form:option value="" label="Wybierz szlak"/>
            <form:options items="${trails}" itemLabel="name" itemValue="id"/>
        </form:select>
    </c:forEach>
    <input type="hidden" name="hikeId" value="${hikeId}"/>
    <button type="submit">Zapisz wybór szlaku</button>
</form:form>

</body>
</html>
