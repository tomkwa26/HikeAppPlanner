<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 10.11.2023
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<c:forEach items="${dailySelections}" var="dailySelection">
    <form action="/select/trail" method="post">
        <fieldset>
            <legend>Dzień ${dailySelection.date}</legend>
            <input type="hidden" name="dailySelectionId" value="${dailySelection.id}" />
            <input type="hidden" name="hikeId" value="${hikeId}" />
            <input type="hidden" name="categoryId" value="${dailySelection.category.id}" />
            <label for="trailId_${dailySelection.id}">Wybierz szlak:</label>
            <select id="trailId_${dailySelection.id}" name="trailId">
                <!-- Pobranie dostępnych kategorii dla danego dnia wyprawy -->
                <c:forEach items="${trails}" var="trail">
                    <option value="${trail.id}">${trail.name}</option>
                </c:forEach>
            </select>
        </fieldset>
        <br />
        <button type="submit">Zapisz</button>
    </form>
</c:forEach>
</body>
</html>
